BEGIN WORK;
SET TRANSACTION READ WRITE;

SET datestyle = DMY;

-- Borrar taules

DROP TABLE IF EXISTS llave;
DROP TABLE IF EXISTS incidence;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS qr;

-- Creacio taules

CREATE TABLE qr(
    id INTEGER NOT NULL PRIMARY KEY,
    data BYTEA NOT NULL
);

CREATE TABLE users(
    id INTEGER NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    profile_picture BYTEA,
    qr_id INTEGER REFERENCES qr(id) NOT NULL
);

CREATE TABLE llave(
    id INTEGER NOT NULL PRIMARY KEY,
    room_name VARCHAR(50),
    qr_id INTEGER REFERENCES qr(id) NOT NULL,
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE incidence(
    id INTEGER NOT NULL PRIMARY KEY,
    topic VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    send_date date NOT NULL,
    state INTEGER NOT NULL,
    user_id INTEGER REFERENCES users(id)
);

COMMIT;
