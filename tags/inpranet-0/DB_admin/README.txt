serveur : localhost
username : postgres
password : soprasopra
database : postgis


Pour le projet, on partage la base de donn�es commune. A l'int�rieur, on s�pare en different schema pour les different module.

creation_schema : 
	creation de schema pour habitude  
	// profil et document pas encore...
	// zone est dans schema->public par d�faut mais on peut le renommer en Zone?

module zone : 
	creation et insertion se fasse par uDig.
	requete pour savoir quelles zones correspond des coordonn�es se trouve dans requete_zone

module habitude : 
	creation des tables concernant les habitudes
	insertion_localisation_raw ->les requetes INSERT pour remplir la table pour avoir une premi�re table de donn�es