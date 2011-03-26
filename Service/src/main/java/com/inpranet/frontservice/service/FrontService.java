package com.inpranet.frontservice.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.User;
import com.inpranet.frontservice.orchestration.BusinessProcessor;
import com.inpranet.frontservice.orchestration.IBusinessProcessor;

@Path("/services/")
public class FrontService implements IFrontService {

	private IBusinessProcessor bp = new BusinessProcessor();

	@POST
	// @Path("/geo/{id}")
	@Path("/geo")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public void postGeoPos(/* @PathParam("id")String id, */GeoPos pos) {

		Logger.getLogger("FrontService").log(Level.INFO, "CA MARCHE : ");

		User u = new User();
		u.setIdUser(Integer.parseInt("12"));
		bp.receiveRawPositions(u, pos);
	}

	/*
	@GET
	// @Path("/geo/{id}")
	@Path("/doc")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Document> getDocumentList(String id) {

		Collection<Document> c = new ArrayList<Document>();
		Document doc1 = new Document(10,"Server test1",true, "acceuil","http://www.test.com",new Date(),new Date(),123,123,"this is a server test");
		Document doc2 = new Document(11,"Server test2",true, "sport","http://www.test.com",new Date(),new Date(),123,123,"this is a server test");
		c.add(doc1);
		c.add(doc2);
		return c;
	}
	*/

	/*
	 * @POST
	 * 
	 * @Path("/geo")
	 * 
	 * @Consumes({ "application/json", "application/xml" })
	 * 
	 * @Produces({ "application/json", "application/xml" }) public void
	 * serviceGeo(GeoPos pos) {
	 * 
	 * Logger.getLogger("FrontService").log(Level.INFO, "CA MARCHE : " +
	 * pos.getLatitude());
	 * 
	 * 
	 * ClassPathXmlApplicationContext context = new
	 * ClassPathXmlApplicationContext(new String[] {"inpranet-service.xml"});
	 * IZoneManager zoneManager = (IZoneManager)context.getBean("serviceZone");
	 * 
	 * List<Zone> zones = zoneManager.getZones(2, 3);
	 * 
	 * Logger log = Logger.getLogger(this.getClass().getName()); for (Zone zone
	 * : zones) { log.info(zone.toString()); }
	 * 
	 * }
	 */
	/*
	 * @POST
	 * 
	 * @Path("/multi/{id}")
	 * 
	 * @Consumes({ "application/json", "application/xml" })
	 * 
	 * @Produces({ "application/json", "application/xml" }) public void
	 * testMulti(@PathParam("id")String id, List<GeoPos> pos) { Logger log =
	 * Logger.getLogger(this.getClass().getName()); log.info(id); log.info("lol"
	 * + pos.get(0).getLatitude()); log.info("lol" + pos.get(1).getLatitude());
	 * }
	 */
	@POST
	@Path("/multi")
	@Consumes({ "application/json", "application/xml" })
	@Produces({ "application/json", "application/xml" })
	public List<GeoPos> testMulti(List<GeoPos> pos) {
		Logger log = Logger.getLogger(this.getClass().getName());
		// log.info("lol" + pos.get(0).getLatitude());
		return pos;
	}

	@GET
	@Path("/json")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public GeoPos testJSON() {

		GeoPos pos = new GeoPos();
		return pos;
	}

}
