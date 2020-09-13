# Url for testing:  http://py4e-data.dr-chuck.net/comments_42.xml
# asnwer: 2553
# Url for homework: http://py4e-data.dr-chuck.net/comments_917402.xml
# answer: 2558
import urllib.request, urllib.parse, urllib.error
import xml.etree.ElementTree as ET
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

address = input('Enter location: ')
data = urllib.request.urlopen(address, context=ctx).read() # open the url and convert to string
print('Retrieving', address)
print('Retrieved', len(data), 'characters')
tree = ET.fromstring(data)  # Get back a tree information that correctly parsed
counts_lst = tree.findall('.//count')  # Find all count in the sub tree and store all trees in a list
print('Count:', len(counts_lst))
s = 0;
for i in counts_lst:
    s += int(i.text)
print(s)


