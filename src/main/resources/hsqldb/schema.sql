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

DROP TABLE purchase IF EXISTS;

CREATE TABLE purchase
(
    id                INTEGER IDENTITY PRIMARY KEY,
    user_id           INTEGER,
    product_id        INTEGER,
    product_size      VARCHAR(50),
    product_color     VARCHAR(50),
    product_cnt       INTEGER,
    order_date        DATE,
    product_price     INTEGER,
    total_price       INTEGER,
    discount_price    INTEGER
);

DROP TABLE inventory IF EXISTS;

CREATE TABLE inventory
(
    id                INTEGER NOT NULL,
    product_id        INTEGER NOT NULL,
    product_size      VARCHAR(50),
    product_color     VARCHAR(50),
    cnt               INTEGER NOT NULL,
    PRIMARY KEY (id)
);