-- Create the database (PostgreSQL does not support "IF NOT EXISTS" with CREATE DATABASE)
-- So you should check manually if the database exists before running this script
-- Alternatively, you can use a bash script to check and create the database if needed.
CREATE DATABASE testdb;
-- Connect to the correct database
\c testdb

-- Create the User table
CREATE TABLE IF NOT EXISTS "User" (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    CreatedRooms JSONB DEFAULT '[]',  -- JSONB array to store IDs of rooms created by the user
    socializingRooms JSONB DEFAULT '[]'  -- JSONB array to store IDs of chatrooms where the user socializes
);

-- Create the Chatroom table
CREATE TABLE IF NOT EXISTS "Chatroom" (
    id SERIAL PRIMARY KEY,
    roomName  VARCHAR(50) NOT NULL,
    owner INT NOT NULL,
    messages JSONB DEFAULT '[]',
    FOREIGN KEY (owner) REFERENCES "User"(id) ON DELETE CASCADE
);
-- Create a table to map which chatrooms the user socializes in
CREATE TABLE IF NOT EXISTS "User_Chatroom" (
    userId INT NOT NULL,
    chatroomId INT NOT NULL,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY (userId, chatroomId),
    FOREIGN KEY (userId) REFERENCES "User"(id) ON DELETE CASCADE,
    FOREIGN KEY (chatroomId) REFERENCES "Chatroom"(id) ON DELETE CASCADE
);



-- Create the Message table
CREATE TABLE IF NOT EXISTS "Message" (
    id SERIAL PRIMARY KEY,
    author INT NOT NULL,
    room INT NOT NULL,
    text TEXT NOT NULL,
    datetime TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author) REFERENCES "User"(id) ON DELETE CASCADE,
    FOREIGN KEY (room) REFERENCES "Chatroom"(id) ON DELETE CASCADE
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_user_login ON "User"(login);
CREATE INDEX IF NOT EXISTS idx_chatroom_name ON "Chatroom"(roomName);
CREATE INDEX IF NOT EXISTS idx_message_datetime ON "Message"(datetime);
