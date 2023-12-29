package services;

import java.io.*;
import java.net.Socket;

import application.MediathequeBD;
import serveur.Service;
import utile.Abonne;

public class EmpruntService extends Service {

	public EmpruntService() {}
		
	@Override
	public void run() {
		try {
			Socket client = this.getSocket();
			this.setMediatheque(getMediatheque());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			out.println("Voici notre catalogue de DVD: ");
			this.getMediatheque().tousLesDvdDisponibles();
			
			out.println("Tapez votre nom et le numero du dvd que vous voulez emprunter : ");
			int nomAbo = Integer.parseInt(in.readLine());
			int dvd = Integer.parseInt(in.readLine());
			
			//Fait l'emprunt
			Abonne aboReserv = this.getMediatheque().abonne(nomAbo);
			this.getMediatheque().getDocument(dvd).empruntPar(aboReserv);
			
			
		}catch (Exception e) {
			System.err.println("Pb sur le port d'écoute :"+e);
		}
		
		
	}

}
