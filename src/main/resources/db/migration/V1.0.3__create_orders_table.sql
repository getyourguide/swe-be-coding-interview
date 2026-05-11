CREATE TABLE IF NOT EXISTS getyourguide.orders (
    id LONG PRIMARY KEY,
    activity_id LONG,
    booking_reference VARCHAR(64),
    price FLOAT,
    status VARCHAR(32)
);

INSERT INTO getyourguide.orders (id, activity_id, booking_reference, price, status) VALUES
(1, 25651, 'BR-0001', 14, 'PENDING'),
(2, 6960, 'BR-0002', 21, 'PENDING'),
(3, 26823, 'BR-0003', 41, 'PENDING');
