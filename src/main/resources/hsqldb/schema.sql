DROP TABLE user IF EXISTS;

CREATE TABLE user
(
    id    INTEGER NOT NULL,
    name  VARCHAR(100),
    point INTEGER,
    PRIMARY KEY (id)
);

DROP TABLE product IF EXISTS;

CREATE TABLE product
(
    id    INTEGER NOT NULL,
    name  VARCHAR(255),
    price INTEGER,
    PRIMARY KEY (id)
);

DROP TABLE coupon IF EXISTS;

CREATE TABLE coupon
(
    id              INTEGER NOT NULL,
    name            VARCHAR(50),
    discount_amount INTEGER,
    PRIMARY KEY (id)
);

