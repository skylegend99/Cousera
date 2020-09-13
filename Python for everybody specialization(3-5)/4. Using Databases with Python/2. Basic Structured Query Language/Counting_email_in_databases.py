# Columns/Attributes
# Rows/Tuples
# Tables/Relations
# Structured Query Language(SQL) :
#   Some basic execution in SQL
#     Create a table: CREATE TABLE Counts
#     Retrieve data: SELECT * FROM Users WHERE email = 'siyin250@uw.edu'
#     Retrieve data in order: SELECT * FROM Users ORDER BY email
#     Insert data: INSERT INTO Users(name,email) VALUES ('fawef','afwe@uw.edu')
#     Delete data: DELETE FROM Users WHERE email = 'fewaf@uw.edu'
#     Update data: UPDATE Users SET name = 'andy' WHERE email = 'siyin250@uw.edu'
import sqlite3
conn = sqlite3.connect('emaildb.sqlite')
cur = conn.cursor()

cur.execute('DROP TABLE IF EXISTS Counts')
cur.execute('CREATE TABLE Counts (email TEXT, count INTEGER)') #Create a table in SQL that the name is 'Counts' and has email and count in row.

fname =  input('Enter name')
if (len(fname)<1): fname = 'mbox-short.txt'
fh = open(fname)
for line in fh:
    if not line.startswith('From: '): continue
    pieces = line.split()
    email = pieces[1]
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










