INSERT INTO users (username, password, email, enabled)

VALUES     ('user', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i','user@mail.nl', TRUE),
           ('admin', '$2a$12$TAKl2cn3O0KyQQed8d00MeAVomp2uQvNCJ4K8lQNwn1LLxuaASQ5i', 'admin@mail.nl', TRUE);


INSERT INTO authorities (username, authority)

VALUES    ('user', 'ROLE_USER'),
          ('admin', 'ROLE_USER'),
          ('admin', 'ROLE_ADMIN');


INSERT INTO parts (description, price, quantity)

VALUES  ( 'workinghour complete on 10min.', 66.00, 1),
        ( 'tire-24QA', 78.50, 18),
        ( 'tire-36YE', 112.50,  15 ),
        ( 'windshield-capri2q', 280.00, 4),
        ( 'windshield-escortwq2', 245.50, 7),
        ( 'fuel-pump-R3', 398.85,  4),
        ( 'fuel-pump-q6', 478.85,  4),
        ( 'clutch-12Lt', 560.00,  2),
        ( 'clutch-16Lt', 428.00,  2),
        ( 'battery-M45', 98.90,  5),
        ( 'battery-K60', 1025.70, 12),
        ( 'injector-kl123 R', 306.00, 12),
        ( 'oil-filter-4rt', 100.50, 16),
        ( 'brake-pads-G12', 98.90,  12),
        ( 'brake-disc Var23', 300.45, 7 ),
        ( 'head-gasket-8Ge', 798.50, 4),
        ( 'air-filter-WER2', 76.50, 18),
        ( 'injector vilt65', 323.00,  3),
        ( 'injector flip12', 299.98, 5),
        ('timing belt BG-2x', 259.00, 6 );


INSERT INTO operations ( description, price)

VALUES ( 'standard inspection', 45.00 ),
       ( 'maintenance inspection MOT incl.', 108.00 ),
       ( 'replace waterpump', 250.00 ),
       ( 'replace exhaustpipe', 245.00),
       ( 'replace brakepads', 195.00),
       ( 'replace timingbelt', 350.00),
       ( 'topup airconditioning', 58.00),
       ( 'replace brakediscs', 210.00),
       ( 'replace windshield', 259.00),
       ( 'wheelchange 4pcs', 105.00),
       ( 'replace clutch', 610.00);


INSERT INTO customers (name, email, telephone)

VALUES ('Pieters', 'pieters@gmail.com', '030-34504412'),
       ('Klaassen', 'klaas@hotmail.com', '06-98345612'),
       ('vandenBerg', 'willem@Mail.nl', '030-356635'),
       ('Karels', 'sjaak@planet.nl', '0656349823'),
       ('Boekels', 'boek@hotmail.com', '030-45238263');

INSERT INTO cars (license_plate, type, customer_id)

VALUES ('67ER34', 'Ford taunus-Q', 1),
       ('34GW56', 'Ford capri 3L', 2),
       ('88GD123', 'opel ascona 16s',3),
       ('12AB34', 'Opel manta', 4);

INSERT INTO carjobs  (status, repair_date, remarks, customer_id, car_id)

VALUES  ('PLANNED', '2021-12-10 10:30', 'airco does not work', 1, 1 ),
        ('COMPLETED', '2020-11-23 09:00', 'replaced timing-belt', 2, 2),
        ('COMPLETED', '2021-10-13 12:30', 'changed tire/fixed waterpump ', 3, 3),
        ('PLANNED', '2022-01-12 10:00', 'fix a lot of things', 3, 3);

INSERT INTO joboperations  (operation_id, carjob_id )
VALUES  ( 1, 2),
        (6, 2),
        (1, 3);


INSERT INTO jobparts  (part_id,  carjob_id, quantity)
VALUES  (20, 2, 1),
        (1, 3, 1.5);
