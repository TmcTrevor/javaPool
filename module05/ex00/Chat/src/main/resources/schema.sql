-- Create the database
CREATE DATABASE IF NOT EXISTS testDB;

-- Select the database
USE testDB;

CREATE TABLE IF NOT EXISTS Users {
	userId INT UNIQUE AUTO_INCREMENT PRIMARY KEY;
	login VARCHAR(50) NOT NULL,
	Password VARCHAR(255) NOT NULL,

}


Users           rooms                 Message
