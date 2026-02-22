CREATE TABLE IF NOT EXISTS users (
                                     id    BIGSERIAL PRIMARY KEY,
                                     username VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL
    );

-- INSERT INTO users (email) VALUES
--                               ('alice@example.com'),
--                               ('bob@example.com'),
--                               ('charlie@example.com');