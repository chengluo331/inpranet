INSERT INTO indexation.category (id, name) VALUES (1, 'Culture');
INSERT INTO indexation.category (id, name) VALUES (2, 'Loisirs');
INSERT INTO indexation.category (id, name) VALUES (3, 'Bien-être');
INSERT INTO indexation.category (id, name) VALUES (4, 'Sport');
INSERT INTO indexation.category (id, name) VALUES (5, 'Scolaire');
INSERT INTO indexation.category (id, name) VALUES (6, 'Shopping');

INSERT INTO indexation."document" (id, title, uri, start_date, end_date, latitude, longitude, "data", reference, urgent) VALUES (12, 'Karnaval Humanitaire', 'http://envue.insa-lyon.fr/2011mars/art5_0311.php#karnaval', '2011-03-17 00:00:00', '2011-04-20 23:59:00', 4.87221, 45.7813, 'Test1', 'KARNAVAL-2011', false);
INSERT INTO indexation."document" (id, title, uri, start_date, end_date, latitude, longitude, "data", reference, urgent) VALUES (13, 'La Part-Dieu accueille Elite Model Look', 'http://www.centrecommercial-partdieu.com/W/do/centre/evenements-centre', '2011-02-05 00:00:00', '2011-08-06 23:59:00', 4.85967, 45.76, 'Test2', 'ELITE-2011', false);

INSERT INTO indexation.document_category (document_id, category_id) VALUES (12, 5);
INSERT INTO indexation.document_category (document_id, category_id) VALUES (13, 6);

INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (12, 9);
INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (13, 2);
INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (13, 6);