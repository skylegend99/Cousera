# Extract the time that given in the text and count how many times does the times repeats
name = input("what is the file")
if len(name) < 1:
	name = "mbox-short.txt"
file = open(name)
dic = {}
for line in file:

	# IF the length of the line is greater than 5, then its 5th elements would be times
	if line.startswith("From"):
		line = line.split()
		if len(line) > 5:
			time = line[5]
			time = time[:time.find(":")] # Find the index of : which is next to hours. Then time[:index] is the hours
			dic[time] = dic.get(time,0) + 1
l = list()

# Append tupple in a list
for k,v in dic.items():
	l.append((k,v))

# Sort list
for k,v in sorted(l):
	print(k,v)
