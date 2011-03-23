package com.inpranet.indexation.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Structure de donnees pour les resultats d'une analyse Regex 
 * @author Stephane
 */
public class RegexResults<T> {
	/**
	 * Liste des resultats sous la forme d'objets de type T
	 */
	protected List<T> resultsList = new ArrayList<T>();
	
	/**
	 * Permet de connaitre les informations dont on est sur
	 * On pourra en deduire les champs qu'il manque (pas d'annee ou d'heure par exemple)
	 */
	protected List<String> formatMappersList = new ArrayList<String>();
	
	/**
	 * Liste des positions ou les resultats ont ete trouvees
	 * Pourra servir pour faire des heuristiques
	 */
	protected List<Integer> startPositionsList = new ArrayList<Integer>();
	
	/**
	 * Nombre d'occurences par resultats
	 */
	protected List<Integer> occurencesList = new ArrayList<Integer>();
	
	/**
	 * Recherche un resultat qui correspond au filtre passe en ce qui concerne son formatMapper
	 * Si plusieurs resultats sont disponibles, ne garde que celui qui est le plus pres de l'index
	 * passe en parametre (via les start positions)
	 * Ne peut pas retourner l'index qui a ete passe en parametre
	 * @param filter Le filtre a appliquer lors de la recherche
	 * @param rootIndex L'index a prendre en compte lors de la selection du resultat le plus proche
	 * @return L'index du resultat 
	 */
	protected int filterFormatMapper(String filter, int referenceIndex) {
		int index = -1;
		int referenceStartPosition = startPositionsList.get(referenceIndex); 
		
		// Parcours la liste et retourne l'index le plus proche de celui qui a ete passe
		for (int i = 0; i < formatMappersList.size(); i++) {
			// On evite de traiter le format mapper associe a l'element qui est traite (designe par referenceIndex)
			if (i == referenceIndex) {
				// On saute cette iteration
				continue;
			}
				
			if (formatMappersList.get(i).contains(filter)) {
				// Met a jour les variables tampons
				if (index == -1) {
					index = i;
				} else {
					// Un autre resultat a deja ete trouve, selection selon la distance
					if (Math.abs(referenceStartPosition - startPositionsList.get(i)) < Math.abs(referenceStartPosition - startPositionsList.get(index))) {
						index = i;
					} else {
						// L'ancien resultat etait meilleur, on ne change rien
					}
				}
			}
		}
		
		// Debug
		ListIterator<Integer> startPositionsIterator = startPositionsList.listIterator();
		System.out.print("filterFormatMapper :");
		while (startPositionsIterator.hasNext()) {
			System.out.print(" " + startPositionsIterator.next());
		}
		System.out.println();
		
		// Debug
		System.out.println("filterFormatMapper : " + filter + " ; " + referenceIndex + " ; " + index);
		System.out.println();
		
		return index;
	}
	
	/**
	 * Ajoute un resultat dans la liste des resultats
	 * Si cet resultat a deja ete trouve, ne l'ajoute pas dans la liste mais augmente son nombre d'occurences 
	 * @param result Le resultat de la recherche Regex a ajouter
	 */
	public void AddResult(T result, String formatMapper, int startPosition) {
		// Augmente le nombre d'occurence si le resutat est deja present
		int i = resultsList.indexOf(result); 
		if ((i != -1) && (formatMappersList.get(i).equals(formatMapper))) {
			System.out.println("Doublon supprime");
			occurencesList.set(i, occurencesList.get(i) + 1);
		} else {
			resultsList.add(result);
			startPositionsList.add(startPosition);
			occurencesList.add(1);
			formatMappersList.add(formatMapper);
		}
	}
	
	/**
	 * Affiche les dates qui ont ete trouvees
	 */
	public void DisplayResults() {
		T result;
		ListIterator<T> i;
		i = resultsList.listIterator();
		
		while (i.hasNext()) {
			result = i.next();
			System.out.println(result.toString());
		}
	}
	
	/**
	 * @return La liste des resultats obtenus 
	 */
	public List<T> GetResultsList() {
		return resultsList;
	}
	
	/**
	 * @return Le nombre d'elements presents dans la liste des resultats obtenus 
	 */
	public int GetResultsListSize() {
		return resultsList.size();
	}
	
	/**
	 * @return La liste des mappeurs de format
	 */
	public List<String> GetFormatMapperList() {
		return formatMappersList;
	}
	
	/**
	 * @return La liste des positions ou les resultats ont ete trouves
	 */
	public List<Integer> GetStartPositionsList() {
		return startPositionsList;
	}
	
	/**
	 * @return La liste des nombres d'occurences pour chaque resultat trouve
	 */
	public List<Integer> GetOccurencesList() {
		return occurencesList;
	}
	
	/**
	 * Ajoute une autre liste de resultats a cette liste (en fin de liste)
	 * @param regexResults Une autre liste de resultats, de meme type que cette liste
	 */
	public void AppendRegexResults(RegexResults<T> regexResults) {
		// Effectue l'ajout en fin de liste
		resultsList.addAll(regexResults.GetResultsList());
		formatMappersList.addAll(regexResults.GetFormatMapperList());
		startPositionsList.addAll(regexResults.GetStartPositionsList());
		occurencesList.addAll(regexResults.GetOccurencesList());
		
		// TODO : Trier les listes selon leur startPosition
	}
	
	/**
	 * @return Le resultat qui a eu le plus d'occurences, si aucun resultat, retourne null
	 */
	public T GetBestResult() {
		// Cas ou il n'y a pas de resultat
		if (occurencesList.size() == 0) {
			return null;
		}
		
		int index = 0;
		int occurencesMax = occurencesList.get(0);
		
		// Recherche l'index du resultat ayant le plus eu d'occurences
		for (int i = 1; i < occurencesList.size(); i++) {
			// Inegalite stricte, on prefere la premiere date
			if (occurencesList.get(i) > occurencesMax) {
				// Mise a jour de l'index
				index = i;
				occurencesMax = occurencesList.get(i);
			}
		}
		
		return resultsList.get(index);
	}
}
