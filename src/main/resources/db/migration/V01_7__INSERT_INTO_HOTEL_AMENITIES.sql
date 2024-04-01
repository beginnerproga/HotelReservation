INSERT INTO hotel_amenities (hotel_id, amenity)
SELECT h.id, v.amenity
FROM (VALUES ('Солнечный Берег', 'BEACH'),
             ('Оазис Люкс', 'WIFI'),
             ('Оазис Люкс', 'SAUNA'),
             ('Оазис Люкс', 'POOL'),
             ('Оазис Люкс', 'BEACH'),
             ('Солнечный Берег', 'WIFI'),
             ('Городские Врата', 'WIFI'),
             ('Городские Врата', 'FREE_TOURS'),
             ('Городские Врата', 'FREE_BREAKFAST'),
             ('Городские Врата', 'BEACH'),
             ('Золотые Врата', 'WIFI'),
             ('Золотые Врата', 'FITNESS'),
             ('Золотые Врата', 'FREE_PARKING'),
             ('Золотые Врата', 'FREE_BREAKFAST')) AS v(hotelName, amenity)
         JOIN hotels h ON h.name = v.hotelName ON CONFLICT (hotel_id, amenity) DO NOTHING;
