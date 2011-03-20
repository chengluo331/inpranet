package com.inpranet.indexation.util;

import java.util.Arrays;

/**
 * Classe qui ne contient que des methodes statiques (sert de boite a outils)
 * @author Stephane
 */
public class Toolbox {
	/**
	 * Genere un objet String de taille length rempli avec des caracteres charToFill
	 * @param length La longueur de la chaine souhaitee
	 * @param charToFill Le caractere qui sera utilise pour la generation
	 * @return La chaine generee
	 */
	public static String generateString(int length, char charToFill) {
		if (length > 0) {
			char[] array = new char[length];
			Arrays.fill(array, charToFill);
			return new String(array);
		}
		return "";
	}
}
