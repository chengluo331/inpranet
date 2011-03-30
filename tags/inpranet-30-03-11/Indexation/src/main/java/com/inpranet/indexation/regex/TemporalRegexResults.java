package com.inpranet.indexation.regex;

import java.util.Calendar;
import java.util.Date;
import java.util.ListIterator;

/**
 * Container pour les resultats d'une analyse Regex portant sur les dates
 * @author Stephane
 */
public class TemporalRegexResults extends RegexResults<Date> {
	/**
	 * Date de creation du document, on pourra s'en servir pour la suite
	 */
	private Date referenceDate = new Date();
	
	/**
	 * Transmet les heures entre les differents resultats trouves
	 */
	private void formatResultsHours() {
		// Utilisation d'iterateurs pour la mise a jour des elements de la liste
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		ListIterator<String> formatMappersListIterator = formatMappersList.listIterator();
		ListIterator<Integer> startPositionsListIterator = startPositionsList.listIterator();
		ListIterator<Integer> occurencesListIterator = occurencesList.listIterator();
		String formatMapper;
		
		// Variables tampons pour le parcours des listes
		int i = 0;
		int j;
		int k;
		Date result;
		Date _result;
		Date __result;
		Calendar calendar = Calendar.getInstance();
		Calendar duplicatedCalendar = Calendar.getInstance();
		Calendar _calendar = Calendar.getInstance();
		Calendar __calendar = Calendar.getInstance();
		
		// Parcours de la liste des resultats
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			formatMapper = formatMappersListIterator.next();
			startPositionsListIterator.next();
			occurencesListIterator.next();
			
			// Si la date qui a ete detectee ne contient pas d'heure
			if (!formatMapper.contains("H")) {
				// On essaye de trouver une heure dans les autres resultats (on ne garde que la premiere heure trouvee)
				j = filterFormatMapper("HH", i);
				
				// On a trouve une heure, mise a jour des listes a partir de l'heure trouvee
				if (j != -1) {
					_result = resultsList.get(j);
					
					calendar.setTime(result);
					_calendar.setTime(_result);
					calendar.set(Calendar.HOUR, _calendar.get(Calendar.HOUR));
					calendar.set(Calendar.MINUTE, _calendar.get(Calendar.MINUTE));
					formatMapper = formatMapper.concat("+H+m");
					
					// Mise a jour des attributs
					resultsListIterator.set(calendar.getTime());
					formatMappersListIterator.set(formatMapper);
					
					// Si deux heures sont proches, il se peut que ce soit une periode temporelle
					// Recherche une autre heure, la plus proche de l'heure deja trouvee
					// Il faut qu'on soit sur de cette heure
					k = filterFormatMapper("HH", j);
					
					// Il existe une autre heure dans le document
					if (k != -1) {
						// Verification de la proximite
						if (Math.abs(startPositionsList.get(j) - startPositionsList.get(k)) < Math.abs(startPositionsList.get(j) - startPositionsList.get(i))) {
							__result = resultsList.get(k);
							__calendar.setTime(__result);
							
							// Dans ce cas, on peut dupliquer la date actuelle pour appliquer la nouvelle heure
							duplicatedCalendar.setTime(calendar.getTime());
							duplicatedCalendar.set(Calendar.HOUR, __calendar.get(Calendar.HOUR));
							duplicatedCalendar.set(Calendar.MINUTE, __calendar.get(Calendar.MINUTE));
							
							// Effectue les remplacements
							resultsListIterator.add(duplicatedCalendar.getTime());
							formatMappersListIterator.add(formatMapper);
							startPositionsListIterator.add(startPositionsList.get(i));
							occurencesListIterator.add(occurencesList.get(i));
							
							// L'utilisation de add va nous faire sauter une execution de next
							i++;
						}
					}
				}
			}
			
			// Incrementation de l'index
			i++;
		}
	}
	
	/**
	 * Regle l'annee d'une date de facon a ce que celle-ci ne soit pas deja passee
	 * Ne fait aucun traitement si la date entree en parametre est deja dans le futur
	 * Ne fait rien egalement si l'annee traitee a ete obtenue de facon sure et qu'elle est quand meme dans le passe
	 * @param endDate La date a traiter (normalement, c'est une date de fin)
	 */
	private Date formatEndYear(Date endDate, String formatMapper) {
		Calendar nowCalendar = Calendar.getInstance();
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(endDate);
		
		// N'effectue pas le traitement si l'annee a ete trouvee de facon sure 
		if (!formatMapper.contains("+y")) {
			// On s'assure que la date n'a pas ete depassee si c'est une date de fin
			if (dateCalendar.compareTo(nowCalendar) <= 0) {
				dateCalendar.set(Calendar.YEAR, nowCalendar.get(Calendar.YEAR) + 1);
			}
		}
		
		// Retourne l'objet Date mis a jour
		return dateCalendar.getTime();
	}
	
	/**
	 * Regle l'heure de la date de fin de preference a 23:59 si aucune heure n'a ete obtenue
	 * Ne fait aucun changement sinon
	 */
	private Date formatEndHour(Date endDate, String formatMapper) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(endDate);
		
		// N'effectue pas le traitement si l'heure a ete trouvee (de facon sure ou pas) 
		if ((!formatMapper.contains("H")) && (!formatMapper.contains("m"))) {
			// On fixe l'heure a 23:59
			dateCalendar.set(Calendar.HOUR, 23);
			dateCalendar.set(Calendar.MINUTE, 59);
		}
		
		// Cas ou on avait qu'un seul resultat
		if (resultsList.size() == 1) {
			// On force l'heure a 23:59 (si on a une seule heure, c'est forcement l'heure de debut)
			dateCalendar.set(Calendar.HOUR, 23);
			dateCalendar.set(Calendar.MINUTE, 59);
		}
		
		// Retourne l'objet Date mis a jour
		return dateCalendar.getTime();
	}
	
	/**
	 * Complete les jours avec le jour de la date de reference si besoin
	 * (cas ou on a que des indications sur les heures mais pas de jour en particulier)
	 */
	private void deduceResultsDaysMonthsYears() {
		// Utilisation d'iterateurs pour la mise a jour des elements de la liste
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		ListIterator<String> formatMappersListIterator = formatMappersList.listIterator();
		Date result;
		String formatMapper;
		Calendar calendar = Calendar.getInstance();
		Calendar _calendar = Calendar.getInstance();
		_calendar.setTime(referenceDate);
		
		// Recherche au moins une information sur les jours
		boolean hasDayInformation = false;
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			formatMapper = formatMappersListIterator.next();
			
			// Si on a une date, alors c'est bon
			if ((formatMapper.contains("d")) || (formatMapper.contains("M")) || (formatMapper.contains("y"))) {
				hasDayInformation = true;
			}
		}
		
		// Si une information sur le jour a ete precisee, on peut arreter ici
		if (hasDayInformation) {
			return;			
		}
		
		// Sinon, si aucune information sur le jour n'a ete trouvee, on prendra la date de creation du document
		resultsListIterator = resultsList.listIterator();
		formatMappersListIterator = formatMappersList.listIterator();
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			formatMapper = formatMappersListIterator.next();
			
			// On ne modifie que les resultats qui en ont besoin
			if ((!formatMapper.contains("d")) && (!formatMapper.contains("M")) && (!formatMapper.contains("y"))) {
				calendar.setTime(result);
				calendar.set(Calendar.DAY_OF_MONTH, _calendar.get(Calendar.DAY_OF_MONTH));
				calendar.set(Calendar.MONTH, _calendar.get(Calendar.MONTH));
				calendar.set(Calendar.YEAR, _calendar.get(Calendar.YEAR));
				formatMapper = formatMapper.concat("+d+M+y");
				
				// Mise a jour des attributs
				resultsListIterator.set(calendar.getTime());
				formatMappersListIterator.set(formatMapper);				
			}
		}
	}
	
	/**
	 * Complete l'annee avec l'annee de la date de reference si besoin
	 * (cas ou on n'a pas d'indications sur l'annee en particulier)
	 */
	private void deduceResultsYears() {
		// Utilisation d'iterateurs pour la mise a jour des elements de la liste
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		ListIterator<String> formatMappersListIterator = formatMappersList.listIterator();
		Date result;
		String formatMapper;
		Calendar calendar = Calendar.getInstance();
		Calendar _calendar = Calendar.getInstance();
		_calendar.setTime(referenceDate);
		
		// Recherche au moins une information sur les annees
		boolean hasYearInformation = false;
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			formatMapper = formatMappersListIterator.next();
			
			// Si on a une date, alors c'est bon
			if (formatMapper.contains("Y")) {
				hasYearInformation = true;
			}
		}
		
		// Si une information sur le jour a ete precisee, on peut arreter ici
		if (hasYearInformation) {
			return;			
		}
		
		// Sinon, si aucune information sur l'annee n'a ete trouvee, on prendra l'annee de creation du document
		resultsListIterator = resultsList.listIterator();
		formatMappersListIterator = formatMappersList.listIterator();
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			formatMapper = formatMappersListIterator.next();
			
			// On ne modifie que les resultats qui en ont besoin
			// HH et mm designent les formats originaux (ie non ajoutes par le programme)
			if ((!formatMapper.contains("y")) && (!formatMapper.contains("HH")) && (!formatMapper.contains("mm"))) {
				calendar.setTime(result);
				calendar.set(Calendar.YEAR, _calendar.get(Calendar.YEAR));
				formatMapper = formatMapper.concat("+y");
				
				// Mise a jour des attributs
				resultsListIterator.set(calendar.getTime());
				formatMappersListIterator.set(formatMapper);				
			}
		}
	}
	
	/**
	 * Enleve les resultats ne disposant que d'une heure mais pas de jour
	 * On ne veut que les resultats complets
	 */
	private void filterResultsHours() {
		// Utilisation d'iterateurs pour la mise a jour des elements de la liste
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		ListIterator<Integer> occurencesListIterator = occurencesList.listIterator();
		ListIterator<String> formatMappersListIterator = formatMappersList.listIterator();
		
		String formatMapper;
		
		while (resultsListIterator.hasNext()) {
			resultsListIterator.next();
			occurencesListIterator.next();
			formatMapper = formatMappersListIterator.next();
			
			// On elimine ceux qui n'ont pas d'informations sur les jours
			if ((!formatMapper.contains("d")) && (!formatMapper.contains("M")) && (!formatMapper.contains("y"))) {
				resultsListIterator.remove();
				occurencesListIterator.remove();
				formatMappersListIterator.remove();
			}
		}
	}
	
	/**
	 * Formate les dates trouvees, le but etant de n'obtenir le moins de dates possibles
	 * Exemple : si on a 2011-03-05 et 14:38 a la suite alors la date retenue sera 2011-03-05T14:38
	 * @param referenceDate La date de reference utilisee (elle peut servir pour interpreter les dates qui s'y trouvent)
	 */
	public void FormatResults(Date referenceDate) {
		// Mise a jour de l'attribut
		if (referenceDate != null) {
			this.referenceDate = referenceDate; 
		}
		
		// Transmet les heures entre les differents resultats trouves
		formatResultsHours();
		
		// Recherche au moins une information sur le jour
		deduceResultsDaysMonthsYears();
		deduceResultsYears();
		
		// Elimination des resultats ne faisant pas mention aux jours
		filterResultsHours();
	}
	
	/**
	 * Trouve dans l'ensemble des resultats la date la plus appropriee pour etre une date de fin
	 * De plus, si l'heure n'est pas indiquee, fixe celle-ci a minuit (00:00)
	 * @return La date qui peut etre consideree comme la date de fin parmi les dates qui ont ete trouvees
	 */
	public Date GetEndDate() {
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		ListIterator<String> formatMappersListIterator = formatMappersList.listIterator();
		Date result;
		String _formatMapper;
		
		// Initialisation des variables tampons
		Date endDate = null;
		String formatMapper = new String();
		
		// Parcours la liste des resultats et trouve la date la plus lointaine
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			_formatMapper = formatMappersListIterator.next();
			
			// Comparaison des dates
			if (endDate == null) {
				// Cas de la premiere iteration 
				endDate = result;
				formatMapper = _formatMapper;
			} else {
				// On cherche la date la plus lointaine
				if (result.after(endDate)) {
					endDate = result;
				}
			}
		}
		
		// Application d'operations de formattage sur la date de fin,
		// celle-ci ayant des caracteristiques propres (elle doit etre dans le futur par exemple)
		endDate = formatEndYear(endDate, formatMapper);
		endDate = formatEndHour(endDate, formatMapper);
		
		return endDate;
	}
	
	/**
	 * Trouve dans l'ensemble des resultats la date la plus appropriee pour etre une date de debut
	 * De plus, si l'heure n'est pas indiquee, fixe celle-ci a minuit (00:00)
	 * @return La date qui peut etre consideree comme la date de debut parmi les dates qui ont ete trouvees
	 */
	public Date GetStartDate() {
		ListIterator<Date> resultsListIterator = resultsList.listIterator();
		Date result;
		
		// Initialisation des variables tampons
		Date startDate = null;
		
		// Parcours la liste des resultats et trouve la date la plus vielle
		while (resultsListIterator.hasNext()) {
			result = resultsListIterator.next();
			
			// Comparaison des dates
			if (startDate == null) {
				// Cas de la premiere iteration 
				startDate = result;
			} else {
				// On cherche la date la plus vielle
				if (result.before(startDate)) {
					startDate = result;
				}
			}
		}
		
		// Contrairement a la date de fin, il n'y a pas de transformations specifiques a appliquer
		// La date de debut commence par defaut a 00:00 si aucune heure n'a ete trouvee
		
		return startDate;
	}
}
