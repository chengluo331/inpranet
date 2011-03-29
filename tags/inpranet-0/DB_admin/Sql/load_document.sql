INSERT INTO indexation.category (id, name) VALUES (1, 'Culture');
INSERT INTO indexation.category (id, name) VALUES (2, 'Loisirs');
INSERT INTO indexation.category (id, name) VALUES (3, 'Bien-etre');
INSERT INTO indexation.category (id, name) VALUES (4, 'Sport');
INSERT INTO indexation.category (id, name) VALUES (5, 'Scolaire');
INSERT INTO indexation.category (id, name) VALUES (6, 'Shopping');

INSERT INTO indexation."document" (id, title, uri, start_date, end_date, latitude, longitude, "data", reference, urgent) VALUES (12, 'Karnaval Humanitaire', 'http://envue.insa-lyon.fr/2011mars/art5_0311.php#karnaval', '2011-03-17 00:00:00', '2011-04-20 23:59:00', 4.87221, 45.7813,
'Le Karnaval Humanitaire est ne de l`initiative tres originale d`associer festival et action de solidarite.
Une grande fete organisee autour d`un defile qui rassemble les enfants de Villeurbanne et les etudiants du campus.
Les fonds recoltes pendant cette semaine de festivites permettent de financer un projet de solidarite internationale.
Ce projet baptise Eau Pour Tous a ete mis en place en partenariat avec l`AJS Burkina (Association Jeunesse Solide) dans le but de faciliter l`acces a l`eau potable a Pella, un petit village du Burkina Faso.
Chaque annee de nouveau travaux sont realises et un systeme d`autogestion est progressivement mis en place pour que le village puisse utiliser ses installations hydrauliques en parfaite autonomie.
La Semaine de la Solidarite est devenue au fil des annees l`un des evenements incontournables de la fin d`hiver a Lyon.
Aujourd`hui, c`est plus de vingt mille personnes qui participent a cette manifestation, notamment pendant le defile deguise, qui rassemble petits et grands dans une ambiance joyeuse et coloree.
Resume du programme :
- 17/03 : sur le theme des Cultures Regionales et Traditionnelles.
- 21/03 : Conference et debat sur le theme de l`immigration (gratuit).
- 22/03 : Projection et debat sur "la marche de l`egalite" (gratuit).
- 23/03 : Koncert : La marmite (3€ ou lance de de / gratuit pour les enfants).
- 24/03 : Defile avec les enfants des ecoles de Villeurbanne (Parcours a travers Villeurbanne avec des chars et les enfants deguises).
- 25/03 : Koncert : Darkham / Konrad Kuechenmeister / Stand High Patrol (7€ sur place / 5€ prevente).
- 20/04 : Koncert : Paracetamol / Z.E.P. / KKC Orchestra (7€ sur place / 5€ prevente).'
, 'KARNAVAL-2011', false);
INSERT INTO indexation."document" (id, title, uri, start_date, end_date, latitude, longitude, "data", reference, urgent) VALUES (13, 'La Part-Dieu accueille Elite Model Look', 'http://www.centrecommercial-partdieu.com/W/do/centre/evenements-centre', '2011-02-05 00:00:00', '2011-08-06 23:59:00', 4.85967, 45.76,
'- Decouvrez l`univers des top models : Conseils, make up, coiffure, colorimetrie, photos...
- Prenez des cours de marche en talons
- Devenez top model : Participez au concours international de la prestigieuse agence de mannequins Elite
Casting Elite du samedi 05/02 au vendredi 09/04 de 11h a 14h en place centrale.'
, 'ELITE-2011', false);

INSERT INTO indexation.document_category (document_id, category_id) VALUES (12, 5);
INSERT INTO indexation.document_category (document_id, category_id) VALUES (13, 6);

INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (12, 9);
INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (13, 2);
INSERT INTO indexation.document_zone (document_id, zone_id) VALUES (13, 6);