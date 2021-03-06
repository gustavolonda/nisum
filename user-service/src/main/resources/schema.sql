CREATE TABLE users (
    id varchar(255) NOT NULL,
    name varchar(255),
    email varchar(255),
    password varchar(255),
    status varchar(1),
    role varchar(10),
    UNIQUE (email),
    create_date timestamp  NULL,
    create_update timestamp  NULL,
    last_login timestamp  NULL,
    PRIMARY KEY (id)
);

CREATE TABLE phones (
    id varchar(255) NOT NULL,
    number varchar(255),
    city_code varchar(255),
    contry_code varchar(255),
    user_id varchar(255) NOT NULL,
    status varchar(1),
    create_date timestamp  NULL,
    create_update timestamp  NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_UserPhone FOREIGN KEY (user_id)
    REFERENCES users(id)
);

