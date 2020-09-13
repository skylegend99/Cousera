#   This works was inspire by the worked example "Counting email in databases" which we get the email from a txt file.
#   Then I want to retrieve the email in the url format and
#   I successfully get the email from "http://www.py4inf.com/code/mbox-short.txt"
#   However, I find it cannot read from "https://www.py4e.com/code3/mbox.txt."
#   The reason could be this web page is secure.
#   Result: mbox-short.txt(successed)
#           mbox.txt(fail)

import sqlite3
import urllib.request, urllib.parse, urllib.error
import ssl
from bs4 import BeautifulSoup
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

conn = sqlite3.connect('emaildb.sqlite')
cur = conn.cursor()
cur.execute('DROP TABLE IF EXISTS Counts')
cur.execute('CREATE TABLE Counts (email TEXT, count INTEGER)') #Create a table in SQL that the name is 'Counts' and has email and count in row.

#Get the content in Url and split the content by line
url = input('enter the url')
if (len(url)<1): url = 'http://www.py4inf.com/code/mbox-short.txt'
html = urllib.request.urlopen(url, context=ctx).read()
html = BeautifulSoup(html, "html.parser")
data = str(html).splitlines()

# We first create a list to store email that we get from url
emails = []
for line in data:
    if not line.startswith('From'):
        continue
    line = line.split()
    if len(line) > 5:
        emails.append(line[1])

for email in emails:
    cur.execute('SELECT count FROM Counts WHERE email = ? ', (email,)) #Find the table named 'Counts" in SQL and selct 'count' cell that corresponds to certain email
    row = cur.fetchone()
    if row is None:
        cur.execute('''INSERT INTO COUNTS (email, count) VALUES (?, 1)''', (email,))
    else:
        cur.execute('UPDATE Counts SET count = count + 1 WHERE email = ?', (email,))
    conn.commit() # Upgrade the change in the server

sqlstr = 'SELECT email, count FROM Counts ORDER BY count DESC LIMIT 10'
for row in cur.execute(sqlstr):
    print(str(row[0]),row[1])
cur.close()










