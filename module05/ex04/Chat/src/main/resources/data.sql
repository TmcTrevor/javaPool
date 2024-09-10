\c testdb

-- -- \d "User"
INSERT INTO "User" (login, password)
VALUES 
    ('admin', 'admin'),
    ('user', 'user'),
    ('user2', 'user2'),
    ('user3', 'user3'),
    ('user4', 'user4'),
    ('user5', 'user5');

INSERT INTO "Chatroom" (roomName, owner)
VALUES 
    ('AdminRoom', (SELECT id FROM "User" WHERE login = 'admin')),
    ('User1Room', (SELECT id FROM "User" WHERE login = 'user')),
    ('User2Room', (SELECT id FROM "User" WHERE login = 'user2')),
    ('User3Room', (SELECT id FROM "User" WHERE login = 'user3')),
    ('User4Room', (SELECT id FROM "User" WHERE login = 'user4')),
    ('User5Room', (SELECT id FROM "User" WHERE login = 'user5'));

INSERT INTO "User_Chatroom" (userId, chatroomId)
VALUES
    ((SELECT id FROM "User" WHERE login = 'admin'), (SELECT id FROM "Chatroom" WHERE roomName = 'AdminRoom')),
    ((SELECT id FROM "User" WHERE login = 'user'), (SELECT id FROM "Chatroom" WHERE roomName = 'User1Room')),
    ((SELECT id FROM "User" WHERE login = 'user2'), (SELECT id FROM "Chatroom" WHERE roomName = 'User2Room')),
    ((SELECT id FROM "User" WHERE login = 'user3'), (SELECT id FROM "Chatroom" WHERE roomName = 'User3Room')),
    ((SELECT id FROM "User" WHERE login = 'user4'), (SELECT id FROM "Chatroom" WHERE roomName = 'User4Room')),
    ((SELECT id FROM "User" WHERE login = 'user5'), (SELECT id FROM "Chatroom" WHERE roomName = 'User5Room')),

INSERT INTO "Message" (author, room, text, datetime)
VALUES 
    ((SELECT id FROM "User" WHERE login = 'admin'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'AdminRoom'), 
     'Hello, I''m admin', 
     NOW()),

    ((SELECT id FROM "User" WHERE login = 'user'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'User1Room'), 
     'Hello, I''m user', 
     NOW()),

    ((SELECT id FROM "User" WHERE login = 'user2'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'User2Room'), 
     'Hello, I''m user2', 
     NOW()),

    ((SELECT id FROM "User" WHERE login = 'user3'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'User3Room'), 
     'Hello, I''m user3', 
     NOW()),

    ((SELECT id FROM "User" WHERE login = 'user4'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'User4Room'), 
     'Hello, I''m user4', 
     NOW()),

    ((SELECT id FROM "User" WHERE login = 'user5'), 
     (SELECT id FROM "Chatroom" WHERE roomName = 'User5Room'), 
     'Hello, I''m user5', 
     NOW());
