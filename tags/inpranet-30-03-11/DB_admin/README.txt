serveur : localhost
username : postgres
password : soprasopra
database : postgis


Pour le projet, on partage la base de données commune. A l'intérieur, on sépare en different schema pour les different module.

creation_schema : 
	creation de schema pour habitude  
	// profil et document pas encore...
	// zone est dans schema->public par défaut mais on peut le renommer en Zone?

module zone : 
	creation et insertion se fasse par uDig.
	requete pour savoir quelles zones correspond des coordonnées se trouve dans requete_zone

module habitude : 
	creation des tables concernant les habitudes
	insertion_localisation_raw ->les requetes INSERT pour remplir la table pour avoir une première table de données