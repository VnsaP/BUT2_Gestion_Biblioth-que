package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.Service;
import utile.Abonne;

public class ReservationService extends Service{
	
	public ReservationService() {};
	
	@Override
	public void run() {
		try {
			Socket client = this.getSocket();
			this.setMediatheque(getMediatheque());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			out.println("Voici notre catalogue de DVD: ");
			this.getMediatheque().tousLesDvdDisponibles();
			
			out.println("Tapez votre nom et le numero du dvd que vous voulez reserver : ");
			int nomAbo = Integer.parseInt(in.readLine());
			int dvd = Integer.parseInt(in.readLine());
			
			//Fait la reservation
			Abonne aboReserv = this.getMediatheque().abonne(nomAbo);
			this.getMediatheque().getDocument(dvd).reservationPour(aboReserv);

			
		}catch (Exception e) {
			System.err.println("Pb sur le port d'écoute :"+e);
		}
	}

}
