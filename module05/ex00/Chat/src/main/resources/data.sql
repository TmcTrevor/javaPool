INSERT INTO
    User (login, password)
VALUES ('admin', 'admin'),
    ('user', 'user'),
    ('user2', 'user2'),
    ('user3', 'user3'),
    ('user4', 'user4'),
    ('user5', 'user5');

INSERT INTO
    Chatroom (
        "AdminRoom",
        (
            SELECT id
            from User
            where
                login = 'admin'
        )
    ),
    (
        "User1Room",
        (
            SELECT id
            from User
            where
                login = 'user'
        )
    ),
    (
        "User2Room",
        (
            SELECT id
            from User
            where
                login = 'user2'
        )
    ),
    (
        "User3Room",
        (
            SELECT id
            from User
            where
                login = 'user3'
        )
    ),
    (
        "User4Room",
        (
            SELECT id
            from User
            where
                login = 'user4'
        )
    ),
    (
        "User5Room",
        (
            SELECT id
            from User
            where
                login = 'user5'
        )
    );

INSERT INTO
    Message (
        (
            SELECT id
            from USER
            WHERE
                login = "admin"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "AdminRoom"
        ),
        "Hello, I'm admin",
        NOW()
    ),
    (
        (
            SELECT id
            from USER
            WHERE
                login = "user"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "User1Room"
        ),
        "Hello, I'm user",
        NOW()
    ),
    (
        (
            SELECT id
            from USER
            WHERE
                login = "user2"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "User2Room"
        ),
        "Hello, I'm user2",
        NOW()
    ),
    (
        (
            SELECT id
            from USER
            WHERE
                login = "user3"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "User3Room"
        ),
        "Hello, I'm user3",
        NOW()
    ),
    (
        (
            SELECT id
            from USER
            WHERE
                login = "user4"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "User4Room"
        ),
        "Hello, I'm user4",
        NOW()
    ),
    (
        (
            SELECT id
            from USER
            WHERE
                login = "user5"
        ),
        (
            SELECT id
            from Chatroom
            WHERE
                name = "User5Room"
        ),
        "Hello, I'm user5",
        NOW()
    );