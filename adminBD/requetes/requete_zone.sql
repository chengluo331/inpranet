select * from zone 
where geog && ST_GeomFromText('SRID=4326;POINT(4.8349 45.764)');