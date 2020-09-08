# Url for the testing: http://py4e-data.dr-chuck.net/comments_42.json
# Url for the homework: http://py4e-data.dr-chuck.net/comments_917403.json
# This homeowork assignment sum up the numbers from a json we loads from url
import urllib.request, urllib.parse, urllib.error
import json
import ssl


# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter location: ')
print('Retrieving', url)
data = urllib.request.urlopen(url, context=ctx).read() # Open the Url and read the Url as bytes
print(type(data))
print('Retrieved', len(data), 'characters')
info = json.loads(data) # Convert the readed data into json file(dictionary)
print(type(info))
sum = 0
for i in info["comments"]:
    sum += int(i["count"])
print(sum)