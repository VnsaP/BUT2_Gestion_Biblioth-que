package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import serveur.Service;
import utile.Abonne;

public class RetourService extends Service {
	
	@Override
	public void run() {
		try {
			Socket client = this.getSocket();
			this.setMediatheque(getMediatheque());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			
			out.println("Tapez le numero du dvd que vous voulez retourner : ");
			int dvd = Integer.parseInt(in.readLine());
			
			//fait le retour
			this.getMediatheque().getDocument(dvd).retour();

			
		}catch (Exception e) {
			System.err.println("Pb sur le port d'écoute :"+e);
		}		
	}

	
}
