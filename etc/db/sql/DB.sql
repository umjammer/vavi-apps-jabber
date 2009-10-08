DROP DATABASE jabber;
CREATE DATABASE jabber;

GRANT ALL PRIVILEGES ON jabber.*
TO jabber@localhost
IDENTIFIED BY 'jabber' WITH GRANT OPTION;

DROP TABLE jabber.Message;
CREATE TABLE jabber.Message(
	username VARCHAR(255),
	buddyname VARCHAR(255),
	sent DATETIME,
	message TEXT,
	type INTEGER
);
