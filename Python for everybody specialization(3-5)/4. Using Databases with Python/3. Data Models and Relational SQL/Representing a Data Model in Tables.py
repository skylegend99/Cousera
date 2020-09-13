# Representing Data model in table:
#     CREATE TABLE Genre(
#           id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
#           name TEXT
#     )
#     CREATE TABLE Album(
#           id     INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
#           artist_id INTEGER,
#           title     TEXT
#     )
#     CREATE TABLE Track(
#            id     INTEGER NOT NULL PRIMARY KEY
#                   AUTOINCREMENT UNIQUE,
#            title TEXT,
#            album_id INTEGER,
#            genre_id INTEGER,
#            len INTEGER,
#            rating INTEGER,
#            count INTEGER
#     )

# Inserting Relational Data:
#     insert into Artist(name) values ('Led Zepplin')
#     insert into Artist(name) values ('AC/DC')
#     insert into Album(title,artist_id) values ('Who Made Who',2)
#     insert into Album(title,artist_id) values ('IV',1)

# Reconstructing Data with join
#     SELECT Album.title,Album.artist_id,Artist.id,Artist.name FROM Album join Artist on Album.artist_id = Artist.id