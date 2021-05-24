INSERT INTO users
    (identification, name, phone, address, email)
VALUES (1019151395, 'Andres Mateo', '317412112', 'Calle 5 No 1b', 'andres@mail.com'),
       (1019151394, 'Calderon Ortega', '34235423423', 'Calle 8 No 1b', 'calderon@mail.com');

INSERT INTO credit_cards
    (number, type, card_holder, expiration_date, user_identification)
VALUES ('1234-5678-8765-4321', 'VISA', 'ANDRES M CALDERON O', '2023-06-01', 1019151395),
       ('0000-0000-0000-0000', 'MASTERCARD', 'CUSTOM C CUSTOM C', '2023-06-01', 1019151394);

/*INSERT INTO payments
    (id, amount, currency, max_refund_date, credit_card_number)
VALUES ('da79b33c-bca8-11eb-8529-0242ac130003', 100000.0, 'COP', '2021-06-01', '1234-5678-8765-4321'),
       ('e2ea4a4a-bca8-11eb-8529-0242ac130003', 132131.2, 'COP', '2021-06-01', '0000-0000-0000-0000');

INSERT INTO purchase_orders
    (id, status, description, shipping_address, payment_id)
VALUES ('780de2b4-bcac-11eb-8529-0242ac130003', 'APPROVED', 'APPROVED', 'Calle 5 asjdadsa',
        'da79b33c-bca8-11eb-8529-0242ac130003'),
       ('7c78ba36-bcac-11eb-8529-0242ac130003', 'DECLINED', 'DECLINED', 'Carrera 4 asdadsa',
        'e2ea4a4a-bca8-11eb-8529-0242ac130003');*/
