BEGIN WORK;
SET TRANSACTION READ WRITE;

SET datastyle = YMD;

-- Borrar taules

-- Creacio taules

CREATE TABLE qr(
    id INTEGER NOT NULL PRIMARY KEY,
    data BYTEA NOT NULL
);

CREATE TABLE llave(
    id INTEGER NOT NULL PRIMARY KEY,
    room_name VARCHAR(50),
    qr_id INTEGER REFERENCES qr(id) NOT NULL
);

CREATE TABLE users
(
    id INTEGER NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    profile_picture INTEGER NOT NULL,
    qr_id INTEGER REFERENCES qr(id) NOT NULL
);

CREATE TABLE incidence(
    id INTEGER NOT NULL PRIMARY KEY,
    topic VARCHAR(200),
    description VARCHAR(2000) NOT NULL,
    send_date date NOT NULL,
    state INTEGER NOT NULL,
    id_user INTEGER REFERENCES users(id)
);



