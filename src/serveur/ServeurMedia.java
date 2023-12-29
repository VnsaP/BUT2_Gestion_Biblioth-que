package serveur;
import java.io.*;
import java.net.*;

import application.MediathequeBD;
import serveur.Service;


public class ServeurMedia implements Runnable { //extends Thread
	//Socket pour serveur : attendre les demandes de connexion
	private ServerSocket serverSocket;
	private Service service;
	private MediathequeBD mediatheque;
	    
	/* Cree un serveur - objet de la classe ServerSocket (port, service, mediatheque) en parametre*/
	public ServeurMedia(int port, Service leService, MediathequeBD mediatheque) throws IOException, InstantiationException, IllegalAccessException  {
			this.serverSocket = new ServerSocket(port);			//new ServerSocket
			this.service = leService;
			this.mediatheque = mediatheque;
			
			// Attendre une connexion client
			this.service.setSocket(this.serverSocket.accept());	
			this.service.setMediatheque(this.mediatheque);
	}
	
	
	// Le serveur ecoute et accepte les connexions.
	// pour chaque connexion, il cree un Service, 
	// qui va la traiter, et le lance	
	@Override
	public void run() {
	    while (true) {
		    System.err.println("Client connecté au port " + this.serverSocket.getLocalPort());
		    // Lancer le service pour traiter la requête client
		    this.service.run();
		}
		
	}

    public void close() {
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Erreur fermeture server socket: " + e.getMessage());
        }
    }
}
