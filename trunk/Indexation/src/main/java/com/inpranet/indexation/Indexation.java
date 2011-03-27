package com.inpranet.indexation;

import java.util.Scanner;

import com.inpranet.core.model.User;
import com.inpranet.core.model.Zone;

/**
 * Lance le traitement des documents injectes
 * @author Stephane
 */
public class Indexation {
	/**	
	 * Variables statiques
	 */
	private static ProcessingEngine processingEngine = new ProcessingEngine();
	private static RequestEngine requestEngine = new RequestEngine();
	
	/**
	 * Methode point d'entree pour un Web Service
	 */
	public String StartProcessing(String documentPath) {
		return processingEngine.StartProcessing(documentPath);
	}
	
	/**
	 * Point d'entree de l'application, gere le menu
	 * @param args
	 */
	public static void main(String[] args) {
		// Variables necessaires pour l'E/S
		String menuChoice = "";
		String documentPath = "";
		Scanner scanner = new Scanner(System.in);
		
		// Gestion du menu
		while (!menuChoice.equals("0")) {
			// Affichage du menu
			System.out.println("MENU PRINCIPAL");
			System.out.println("**************");
			System.out.println();
			System.out.println("[0] Quitter");
			System.out.println("[1] Ajouter un document dans la base de donnees");
			System.out.println("[2] Effectuer une requete");
			System.out.println();
			System.out.print("> Entrez votre choix : ");
			
			// Attend une reponse de l'utilisateur
			menuChoice = scanner.nextLine();
			System.out.println();
			
			// Effectue le traitement demande
			if (menuChoice.equals("1")) {
				// On veut ajouter un document
				// Demande du fichier
				System.out.println("AJOUTER UN DOCUMENT");
				System.out.println("*******************");
				System.out.println();
				System.out.print("> Entrez le chemin du document : ");
				
				// Attend une reponse de l'utilisateur
				documentPath = scanner.nextLine();
				System.out.println();
				
				// Appelle le moteur de traitement de documents
				processingEngine.StartProcessing(documentPath);
			} else if (menuChoice.equals("2")) {
				// On veut effectuer une requete
				System.out.println("REQUETER UN DOCUMENT");
				System.out.println("********************");
				System.out.println();
				
				// Appelle le moteur de requete de documents
				//requestEngine.LaunchRequest(new User(), new Zone());
			} else if (!menuChoice.equals("0")) {
				System.out.println("Commande inconnue");
				System.out.println();
			}
			
			// Boucle sur le menu
		}
		
		// Fin de l'application
		scanner.close();
	}
}
