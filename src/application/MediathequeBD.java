package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utile.Abonne;
import utile.DVD;
import utile.Document;

public class MediathequeBD {
	
	public MediathequeBD() {}
	
	// renvoie la liste des DVD disponibles cad pas de numEmprunt
		public List<Document> tousLesDvdDisponibles() {
			 Connection con = ConnexionBDD.getConnexion();
			 List<Document> documents = new ArrayList<>();

			 String req = "SELECT * FROM DOCUMENTS WHERE numEmprunte is null";

			 synchronized (this) {
				 try {
					 PreparedStatement st = con.prepareStatement(req);
					 ResultSet rs = st.executeQuery();

					 while (rs.next()) {
						 int numero = rs.getInt("numDVD");
						 String titre = rs.getString("titre");
						 boolean adultle = rs.getBoolean("adultle");
						 int numEmprunteur = rs.getInt("numEmprunteur");
						 int numReserveur = rs.getInt("numReserveur");

						 Document doc = new DVD( numero,  titre, adultle,  numEmprunteur,  numReserveur);
						 documents.add(doc);
					 }
				 } catch (SQLException e) {
					 e.printStackTrace();
				 }
			 }
			ConnexionBDD.close(con);
			return documents;
		}


		// va récupérer le document de numéro numDVD1 dans la BD
		// et le renvoie
		// si pas trouvé, renvoie null
		public Document getDocument(int numDVD1) {
			Connection con = ConnexionBDD.getConnexion();
			String req = "SELECT * FROM DVD WHERE numDVD=?";

			synchronized (this) {
				try {
					PreparedStatement ps = con.prepareStatement(req);
					ps.setInt(1, numDVD1);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						String titre = rs.getString("titre");
						 boolean adultle = rs.getBoolean("adultle");
						 int numEmprunteur = rs.getInt("numEmprunteur");
						 int numReserveur = rs.getInt("numReserveur");

						return new DVD( numDVD1,  titre, adultle,  numEmprunteur,  numReserveur);
						
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ConnexionBDD.close(con);
			return null;
		}

		// va récupérer l'abonne dans la BD et le renvoie
		// si pas trouvé, renvoie null
		public Abonne abonne(int numAbonne) {
			Connection con = ConnexionBDD.getConnexion();
			String req = "SELECT * FROM Abonne WHERE numAbonne=?";

			synchronized (this) {
				try {
					PreparedStatement ps = con.prepareStatement(req);
					ps.setLong(1, numAbonne);
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {
						String nom = rs.getString("NomAbonne");
						Date dateNaissance = rs.getDate("DateNaiss");
						return new Abonne(numAbonne, nom, dateNaissance);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			ConnexionBDD.close(con);
			return null;
		}

}
