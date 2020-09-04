name = input("what is the file")
if len(name) < 1:
	name = "mbox-short.txt"
file = open(name)
dic = {}
for line in file:
	if line.startswith("From"):
		line = line.split()
		if len(line) > 5:
			time = line[5]
			time = time[:time.find(":")]
			dic[time] = dic.get(time,0) + 1
l = list()
for k,v in dic.items():
	l.append((k,v))
for k,v in sorted(l):
	print(k,v)