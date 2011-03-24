--SELECT COUNT("userID") FROM habitude.geo_tempo_events 
SELECT * FROM habitude.geo_tempo_events 
WHERE "userID" =1002 
AND "zoneID" =6 
AND interest ='commercial' 
AND EXTRACT(DOW from "timeIn")=1
AND '02:00:00' BETWEEN "time"("timeIn") AND "time"("timeOut");