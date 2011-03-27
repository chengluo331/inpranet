package com.inpranet.frontservice.service;


import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.inpranet.core.model.Document;
import com.inpranet.core.model.GeoPos;

/**
 * Interface a suivre pour implementer un service frontal proposant les services web Restful suivants
 * Services accessible depuis ../services/NomDuService
 * Liste :
 *  - /ws/geo
 *  - /ws/doc
 * @author inpranet team
 *
 */
@Path("/")
public interface IFrontService {

	/**
	 * Service receptionnant une donnee geospatiale
	 * Methode HTTP : POST
	 * Accepte du JSON et du XML
	 * Retourne du JSON ou du XML suivant les preferences du client
	 * @param token token d'identification (doit etre present dans l'url : .../geo/token
	 * @param pos position geospatiale (objet present dans le corps de la requete POST)
	 */
	@POST
	@Path("/geo/{token}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	void postGeoPos(@PathParam("token") String token, GeoPos pos);
	
	/**
	 * Service qui propose une liste de document adapte aux habitudes de l'utilisateur represente par token
	 * Methode HTTP : GET (../doc/{token}
	 * Accepte du JSON et du XML
	 * Retourne du JSON ou du XML suivant les preferences du client
	 * @param token token d'identification de l'utilisateur
	 * @return une liste de document
	 */
	@GET
	@Path("/doc/{token}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Document> getDocumentList(@PathParam("token") String token);
	
	
	
	public GeoPos  testJSON();
	
}
