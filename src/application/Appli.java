/*
 * Architecture Logiciel PROJET 
 * 01/04/2023
 * Auteur : Vanessa Pham 206 parcours C
 * IUT de Paris Rives de Seines
 * 
 * */

package application;
import java.io.IOException;

import serveur.ServeurMedia;
import services.EmpruntService;
import services.ReservationService;
import services.RetourService;

public class Appli {
	private final static int reserv_PORT = 3000;
	private final static int emprunt_PORT = 4000;
	private final static int retour_PORT = 5000;
	
	
	//Creation des serveurs et lancement des services
	public static void main(String[] args) {
		//BD charger les donnees dans une classe Metiatheque
		MediathequeBD mediatheque = new MediathequeBD();
		
		try {
			new Thread(new ServeurMedia(reserv_PORT, new ReservationService(), mediatheque)).start(); //param mediatheque 
			System.out.println("Serveur lance sur le port lol " + reserv_PORT);
			
			new Thread(new ServeurMedia(emprunt_PORT, new EmpruntService(), mediatheque)).start();
			System.out.println("Serveur lance sur le port " + emprunt_PORT);
			
			new Thread(new ServeurMedia(retour_PORT, new RetourService(), mediatheque )).start();
			System.out.println("Serveur lance sur le port " + retour_PORT);
			
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			System.err.println("Pb lors de la création du serveur : " +  e);
		}	
		
		
		//boucle qui attend toujours
		while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	}
}
