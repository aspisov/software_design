CREATE TABLE IF NOT EXISTS Artist(
                                     ArtistId SERIAL PRIMARY KEY,
                                     Name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Album(
                                    AlbumId SERIAL PRIMARY KEY,
                                    Title VARCHAR(20),
                                    ArtistId INTEGER,
                                    Column1 TEXT,
                                    FOREIGN KEY (ArtistId) REFERENCES Artist(ArtistId)
);

CREATE TABLE IF NOT EXISTS Genre(
                                    GenreId SERIAL PRIMARY KEY,
                                    Name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS MediaType(
                                        MediaTypeId SERIAL PRIMARY KEY,
                                        Name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS Track(
                                    TrackId SERIAL PRIMARY KEY,
                                    Name VARCHAR(20),
                                    AlbumId INTEGER,
                                    MediaTypeId INTEGER,
                                    GenreId INTEGER,
                                    Composer VARCHAR(20),
                                    Milliseconds INTEGER,
                                    Bytes INTEGER,
                                    UnitPrice INTEGER,
                                    FOREIGN KEY (AlbumId) REFERENCES Album(AlbumId),
                                    FOREIGN KEY (MediaTypeId) REFERENCES MediaType(MediaTypeId),
                                    FOREIGN KEY (GenreId) REFERENCES Genre(GenreId)
);

CREATE TABLE IF NOT EXISTS Playlist(
                                       PlaylistId SERIAL PRIMARY KEY,
                                       Name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS PlaylistTrack(
                                            PlaylistId INTEGER,
                                            TrackId INTEGER,
                                            FOREIGN KEY (PlaylistId) REFERENCES Playlist(PlaylistId),
                                            FOREIGN KEY (TrackId) REFERENCES Track(TrackId)
);

CREATE VIEW view_track_details AS
SELECT
    t.TrackId,
    t.Name AS TrackName,
    a.Title AS AlbumTitle,
    g.Name AS GenreName,
    ar.Name AS ArtistName,
    t.Composer,
    t.Milliseconds,
    t.Bytes,
    t.UnitPrice
FROM Track t
         JOIN Album a ON t.AlbumId = a.AlbumId
         JOIN Artist ar ON a.ArtistId = ar.ArtistId
         LEFT JOIN Genre g ON t.GenreId = g.GenreId;

INSERT INTO ARTIST (Name)
VALUES
    ('21 Savage'),
    ('Post Malone'),
    ('Kendrick Lamar'),
    ('Flume');

INSERT INTO Album (Title, ArtistId, Column1)
VALUES
    ('Skin', 4, 'Column1'),
    ('Album_21savage', 1, 'Column1'),
    ('Album2_21savage', 1, 'Column1');

INSERT INTO Genre (Name)
VALUES
    ('Rap'),
    ('Rock'),
    ('R&B');

INSERT INTO MediaType (Name)
VALUES
    ('Audio'),
    ('Video');

INSERT INTO Track (Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice)
VALUES
    ('Song1_21savage', 2, 1, 1, 'Unknown', 213000, 64, 1),
    ('Song2_21savage', 2, 1, 1, 'Unknown', 283000, 75, 1);

INSERT INTO PLaylist (Name)
VALUES
    ('MyPlaylist');

INSERT INTO PlaylistTrack (PlaylistId, TrackId)
VALUES
    (1, 1);

-- Выведите имена плейлистов в которые входит больше 2 треков в жанрах рок и рэп;
SELECT p.Name AS PlaylistName
FROM Playlist p
         JOIN PlaylistTrack pt ON p.PlaylistId = pt.PlaylistId
         JOIN Track t ON pt.TrackId = t.TrackId
         JOIN Genre g ON t.GenreId = g.GenreId
WHERE g.Name IN ('Rap', 'Rock')
GROUP BY p.PlaylistId
HAVING COUNT(t.TrackId) > 2;

-- Выведите топ 3 артистов по количеству треков с указанием количества треков у каждого из них.
SELECT a.Name AS Artist, COUNT(t.TrackId) AS Tracks
FROM Artist a
         JOIN Album al ON a.ArtistId = al.ArtistId
         JOIN Track t ON al.AlbumId = t.AlbumId
GROUP BY a.ArtistId
ORDER BY COUNT(t.TrackId) DESC
LIMIT 3;