CREATE TABLE  Product
(
    id         INTEGER  IDENTITY PRIMARY KEY,
    name            VARCHAR(50) UNIQUE NOT NULL,
    price           FLOAT
);

