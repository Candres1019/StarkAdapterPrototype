CREATE TABLE users
(
    identification int8         NOT NULL,
    name           varchar(256) NOT NULL,
    phone          varchar(256) NOT NULL,
    address        varchar(256) NOT NULL,
    email          varchar(256) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (identification)
);

CREATE TABLE credit_cards
(
    number              varchar(40)  NOT NULL,
    type                varchar(10)  NOT NULL,
    card_holder         varchar(256) NOT NULL,
    expiration_date     DATE         NOT NULL,
    user_identification int8         NOT NULL,
    CONSTRAINT credit_card_pk PRIMARY KEY (number),
    CONSTRAINT usuario_fk FOREIGN KEY (user_identification) REFERENCES users (identification) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE payments
(
    id                 varchar(256) NOT NULL,
    amount             real         NOT NULL,
    currency           varchar(10)  NOT NULL,
    max_refund_date    DATE         NULL,
    credit_card_number varchar(40)  NOT NULL,
    CONSTRAINT payment_pk PRIMARY KEY (id),
    CONSTRAINT payment_fk FOREIGN KEY (credit_card_number) REFERENCES credit_cards (number)
);

CREATE TABLE purchase_orders
(
    id               varchar(256) NOT NULL,
    status           varchar(256) NOT NULL,
    description      varchar(516) NOT NULL,
    shipping_address varchar(516) NOT NULL,
    payment_id       uuid         NOT NULL,
    CONSTRAINT purchase_order_pk PRIMARY KEY (id),
    CONSTRAINT purchase_order_fk FOREIGN KEY (payment_id) REFERENCES payments (id)
);
