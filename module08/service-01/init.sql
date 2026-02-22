CREATE TABLE IF NOT EXISTS users (
                                     id    BIGSERIAL PRIMARY KEY,
                                     email VARCHAR(255) NOT NULL UNIQUE
    );

-- INSERT INTO users (email) VALUES
--                               ('alice@example.com'),
--                               ('bob@example.com'),
--                               ('charlie@example.com');