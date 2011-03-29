package com.inpranet.habit.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Logger;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;
import com.inpranet.habit.dao.IPositionDAO;
import com.inpranet.habit.dao.IWeeklyHabitDAO;
import com.inpranet.habit.model.Position;
import com.inpranet.habit.model.WeeklyHabit;

/**
 * 
 * L'implementation du IHabitService
 * @author Yiquan
 *
 */
@WebService(endpointInterface = "com.inpranet.habit.service.IHabitService")		
public class HabitService implements IHabitService {
		
	/** Logger */
	static Logger log = Logger.getLogger(HabitService.class.getName());
	
	/** Les dao utilisés */
	private IPositionDAO positionDao;
	
	private IWeeklyHabitDAO weeklyHabitDao;
	
	/** Context de l'application */
	private ClassPathXmlApplicationContext appContext;
	
	public HabitService() {
		// Rï¿½cupï¿½ration du bean DAO
		appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		positionDao = (IPositionDAO) appContext.getBean("positionDao"); 
		weeklyHabitDao = (IWeeklyHabitDAO) appContext.getBean("weeklyHabitDao");
	}
	
	public void StockData(User user, GeoPos geoPos, Collection<Zone> zones) {
		log.info("-------------- WS Habit.StockData -----------------");
		 
				
		// Sauvegarde des données positions */
		try {
			Position position = new Position(user.getIdUser(), geoPos.getLongitude(), geoPos.getLatitude(), geoPos.getTime().toGregorianCalendar().getTime());
			log.info("Stock des données positions : userId=" + user.getIdUser() + " longitude=" + 
					position.getLongitude() + " , latitude=" + position.getLatitude());
			positionDao.createPosition(position);
			
			// Récupérer l'identifiant de l'interval où se trouve l'heure 
			int timeOfWeek = weeklyHabitDao.GetIdInterval(geoPos.getTime().toGregorianCalendar().getTime());	
			// Sauvegarde des habitudes
			for (Zone z:zones) {
				WeeklyHabit weeklyHabit = new WeeklyHabit(user.getIdUser(), timeOfWeek, z.getIdZone(), 0, 0);
				weeklyHabitDao.createWeeklyHabit(weeklyHabit);
			}
		} catch (NullPointerException e1) {
			log.info("Champ null, pas de stockage !!!");
			return;
		} catch (SQLException e2) {
			return;
		}
				
			
	}

	public Collection<Zone> DeduceZone(User user, int planningHorizon,
			Collection<Interest> interests) {
		log.info("------- WS Habit.DeduceZone ----------");		
		
		// Instancier les zones a retoune
		Collection<Zone> zones = new ArrayList<Zone>();
		
		// L'heure requetee est l'heure actuelle plus l'horizon de planification
		Calendar c = Calendar.getInstance();
		log.info("Il est actuellement " + c.getTime().toString());
		c.add(Calendar.MINUTE, user.getPlanningHorizon());
		log.info("On recherche ses habitudes à " + c.getTime().toString());
		
		// Pour chaque centre d'interet de l'utilisateur, recuperer la zone la plus probable
		for (Interest i : user.getInterests()) {
			int idZone = weeklyHabitDao.DeduceIdZoneByInterest(user.getIdUser(), i.getIdInterest(), c.getTime());
			log.info("Il a habitude d'aller dans les zones suivantes :");
			if (idZone != 0) {
				Zone z = new Zone(idZone, i);
				zones.add(z);
				log.info("Zone numéro " + z.getIdZone());
			}			
		}
		// Renvoie la liste des zones interessantes
		return zones;
	}
	
	/**
	 * Convertir une date a un entier selon la regle suivante:
	 * Entier = Jour_de_semaine*24 + Heure_du_jour
	 * @param date
	 * @return entier
	 */
	/*private int time2Int(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int i = (c.get(Calendar.DAY_OF_WEEK)-1)*24 + c.get(Calendar.HOUR_OF_DAY);
			return i; 
		}
		else {
			return -1;
		}*/
}
