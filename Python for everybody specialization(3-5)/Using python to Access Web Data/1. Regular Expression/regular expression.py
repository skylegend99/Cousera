import re

###Find "From:" in the context; search in the regular expression compare to find method
hand = open("something")
#find method
for line in hand:
    line = line.rstrip()
    if line.find("From:") >= 0:
        print("something")
#re.search method in regular expression
for line in hand:
    line = line.rstrip()
    if re.search("From:", line):
        print("something")

###Find if the line start with "From:"
#startwith method
for line in hand:
    line = line.rstrip()
    if line.startswith("From:"):
        print("something")
#re.search method in regular expression
for line in hand:
    line = line.rstrip()
    if re.search('^From:', line):
        print("something")

###  ^X.*:    ----"means start with X charaacter and follows any numbers of characters between X and :