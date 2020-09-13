# To run this, download the BeautifulSoup zip file
# http://www.py4e.com/code3/bs4.zip
# and unzip it in the same directory as this file

import urllib.request, urllib.parse, urllib.error
from bs4 import BeautifulSoup
import ssl

# Ignore SSL certificate errors
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

#Create a list to store url each time we scrapying the website
#Repeat the process for 4 times
l = []
url = input('Enter - ')
count = int(input('Enter count'))
position = int(input('Enter position'))
print(url)


for i in range(count):
    html = urllib.request.urlopen(url, context=ctx).read()
    soup = BeautifulSoup(html, 'html.parser')
    # Retrieve all of the anchor tags
    tags = soup('a')
    for tag in tags:
        l.append(tag.get('href', None))
    url = l[position-1] #Made the third url to be next object that will be scraped.
    l = []
    print(url)
#We are looking for the name on the last url like "http://py4e-data.dr-chuck.net/known_by_Fikret.html“
#Example: Looking for the name: Fikret
#split the url into 3 part by "-" and then split the remain part by '.'
#First position will be our target
url = url.split('_')
url = url[2].split('.')
print(url[0])
