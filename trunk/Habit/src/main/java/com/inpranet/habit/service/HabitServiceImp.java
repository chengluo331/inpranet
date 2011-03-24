package com.inpranet.habit.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Logger;

import javax.jws.WebService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.inpranet.core.model.GeoPos;
import com.inpranet.core.model.Interest;
import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;
import com.inpranet.habit.dao.IPositionDAO;
import com.inpranet.habit.dao.IWeeklyHabitDAO;
import com.inpranet.habit.dao.PositionDAO;
import com.inpranet.habit.model.Position;
import com.inpranet.habit.model.WeeklyHabit;

/**
 * 
 * L'implementation du IHabitService
 * @author Yiquan
 *
 */
@WebService(endpointInterface = "com.inpranet.habit.service.IHabitService")		
public class HabitServiceImp implements IHabitService {
		
	/** Logger */
	static Logger log = Logger.getLogger(HabitServiceImp.class.getName());
	
	private IPositionDAO positionDao;
	
	private IWeeklyHabitDAO weeklyHabitDao;
	
	private ClassPathXmlApplicationContext appContext;
	
	public void StockData(User user, GeoPos geoPos, Collection<Zone> zones) {
		 appContext = new ClassPathXmlApplicationContext("inpranet-data.xml");
		
		// Récupération du bean DAO 
		positionDao = (IPositionDAO) appContext.getBean("positionDao"); 
		weeklyHabitDao = (IWeeklyHabitDAO) appContext.getBean("jdbcWeeklyHabitDao");
		
		log.info("---------------------begin stock raw data------------------------------");
		Position position = new Position(user.getIdUser(), geoPos.getLongitude(), geoPos.getLatitude(), geoPos.getTime());
		positionDao.createPosition(position);
		
		int timeOfWeek = time2Int(geoPos.getTime());
		
		for (Zone z:zones) {
			log.info("-------------une zone!");
			WeeklyHabit weeklyHabit = new WeeklyHabit(user.getIdUser(), timeOfWeek, z.getIdZone(), 0, 0);
			weeklyHabitDao.InsertWeeklyHabit(weeklyHabit);
		}
		
		
	}

	public Collection<Zone> DeduceZone(User user, int planningHorizon,
			Collection<Interest> interests) {
		log.info("deduction");
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Convertir une date a un entier selon la regle suivante:
	 * Entier = Jour_de_semaine*24 + Heure_du_jour
	 * @param date
	 * @return entier
	 */
	private int time2Int(Date date) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int i = (c.get(Calendar.DAY_OF_WEEK)-1)*24 + c.get(Calendar.HOUR_OF_DAY);
			return i; 
		}
		else {
			return -1;
		}
		
	}
}
