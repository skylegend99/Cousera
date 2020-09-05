#find sum of the numbers in txt
import re
handle = open("regex_sum_917398.txt")
num_list = []
Sum = 0
for word in handle:
    if re.search('[0-9]+',word):
        Sum += sum(list(map(int, re.findall('[0-9]+',word))))
print(Sum)