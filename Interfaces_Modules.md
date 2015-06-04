# Zone #

http://localhost:9000/zone/zone

```
Collection<Zone> getZoneListFromPos(double lng, double lat)
```

# Habitude #

http://localhost:9001/habitude/habitude

```
public void StockData(User user, GeoPos geoPos, Collection<Zone> zones) 
public Collection<Zone> DeduceZone(User user, int planningHorizon, Collection<Interest> interests) 
```

# Indexation #

http://localhost:9002/?

```
Collection<Document> getDocumentList(User user, Collection<Zone> zones)
```

# Bus #

http://localhost:8080/service/bus

```
Collection<Zone> getZoneListFromPos(GeoPos geopos)
```

# Service Frontal #

http://localhost:8080/service/public

http://localhost:8080/service/public/geo/{token}

http://localhost:8080/service/public/doc/{token}