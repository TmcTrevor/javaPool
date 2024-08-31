-- Create the database
CREATE DATABASE IF NOT EXISTS testDB;

-- Select the database
USE testDB;

CREATE TABLE IF NOT EXISTS User {
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
	login VARCHAR(50) UNIQUE NOT NULL,
	Password VARCHAR(255) NOT NULL,
	CreatedRooms JSONB DEFAULT '[]',  -- JSONB array to store IDs of rooms created by the user
    socializingRooms JSONB DEFAULT '[]'  -- JSONB array to store IDs of chatrooms where the user socializes
}

CREATE TABLE IF NOT EXISTS Chatroom {
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
	ChatroomName VARCHAR(50) NOT NULL,
	owner INT NOT NULL,
	messages JSONB DEFAULT '[]',
	FOREIGN KEY (owner) REFERENCES User(id) ON DELETE CASCADE,
}

CREATE TABLE IF NOT EXISTS Message {
	id INT UNIQUE AUTO_INCREMENT PRIMARY KEY,
	author INT NOT NULL,
	room	INT NOT NULL,
	text TEXT NOT NULL,
    datetime TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

	FOREIGN KEY (author) REFERENCES User(id) ON DELETE CASCADE,
	FOREIGN KEY (room) REFERENCES Chatroom(id) ON DELETE CASCADE,

}

CREATE INDEX idx_user_login ON users(login);
CREATE INDEX idx_chatroom_name ON chatrooms(chatroom_name);
CREATE INDEX idx_message_datetime ON messages(message_datetime);
