INSERT INTO rooms (type, price, capacity, hotel_id)
SELECT v.type, v.price, v.capacity, h.id
FROM (VALUES ('Солнечный Берег', 'DELUXE', 1200, 3),
             ('Солнечный Берег', 'LUXURY', 2500, 4),
             ('Солнечный Берег', 'LUXURY', 5000, 5),
             ('Оазис Люкс', 'DELUXE', 1500, 2),
             ('Оазис Люкс', 'LUXURY', 3000, 4),
             ('Оазис Люкс', 'LUXURY', 4500, 6),
             ('Городские Врата', 'STANDARD', 1000, 2),
             ('Городские Врата', 'DELUXE', 2000, 3),
             ('Городские Врата', 'LUXURY', 3500, 4),
             ('Золотые Врата', 'ECONOMY', 800, 2),
             ('Золотые Врата', 'STANDARD', 1200, 2),
             ('Золотые Врата', 'DELUXE', 1800, 3)) AS v(hotelName, type, price, capacity)
         JOIN hotels h ON h.name = v.hotelName ON CONFLICT (type, hotel_id) DO NOTHING;

