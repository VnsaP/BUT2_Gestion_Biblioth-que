package utile;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import application.ConnexionBDD;

public class DVD implements Document {
	private int numero;
	private String titre;
	private Boolean adultle = true;
	private int numEmprunteur;
	private int numReserveur;
	
    public DVD(int numero, String titre,boolean adultle, int numEmprunteur, int numReserveur) {
        this.numero = numero;
        this.titre = titre;
        this.adultle = adultle;
        this.numEmprunteur = numEmprunteur;
        this.numReserveur = numReserveur;
    }
	
	@Override
	public int numero() {
		return numero;
	}
	
    public String getTitre() {
        return titre;
    }

    
	// va récupérer l'abonne qui a emprunte le DVD courant dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Abonne emprunteur() {
		Connection con = ConnexionBDD.getConnexion();
		String req = "SELECT * FROM Abonne WHERE numEmprunt=?";

		synchronized (this) {
			try {
				PreparedStatement ps = con.prepareStatement(req);
				ps.setLong(1, numEmprunteur);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int numero = rs.getInt("NumAbonne");
					String nom = rs.getString("NomAbonne");
					Date dateNaissance = rs.getDate("DateNaiss");
					return new Abonne(numero, nom, dateNaissance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ConnexionBDD.close(con);
		return null;
	}

	
	// va récupérer l'abonne qui a reserve le DVD courant dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Abonne reserveur() {
		Connection con = ConnexionBDD.getConnexion();
		String req = "SELECT * FROM Abonne WHERE numReserveur=?";

		synchronized (this) {
			try {
				PreparedStatement ps = con.prepareStatement(req);
				ps.setLong(1, numReserveur);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int numero = rs.getInt("NumAbonne");
					String nom = rs.getString("NomAbonne");
					Date dateNaissance = rs.getDate("DateNaiss");
					return new Abonne(numero, nom, dateNaissance);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ConnexionBDD.close(con);
		return null;
	}

	
	//Fait un update dans la BD
	//ajoute le numero de l'abonne en tant que reserveur du DVD
	//verifier si l'abonne a l'age de reserver
	@Override
	public void reservationPour(Abonne ab) throws RestrictionException {
		Connection conn = ConnexionBDD.getConnexion();
        String req = "UPDATE DVD SET numReserveur = ? WHERE idDoc = ?";
        if(ab.getAge() < 18 && adultle) throw new RestrictionException("Vous n'avez pas l'age d'emprunter ce document !");
        synchronized (this) {
            try {
                PreparedStatement st = conn.prepareStatement(req);
                st.setInt(1, ab.getNumero());
                st.setInt(2, this.numero);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnexionBDD.close(conn);
		
	}

	//Fait un update dans la BD
	//ajoute le numero de l'abonne en tant qu'emprunteur du DVD
	//verifier si l'abonne a l'age de reserver
	@Override
	public void empruntPar(Abonne ab) throws RestrictionException {
		Connection conn = ConnexionBDD.getConnexion();
        String req = "UPDATE DVD SET numEmprunt = ? WHERE idDoc = ?";
        if(ab.getAge() < 18 && adultle) throw new RestrictionException("Vous n'avez pas l'age d'emprunter ce document !");
        synchronized (this) {
            try {
                PreparedStatement st = conn.prepareStatement(req);
                st.setInt(1, ab.getNumero());
                st.setInt(2, this.numero);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnexionBDD.close(conn);
		
	}

	//Fait un update dans la BD
	//retire le numero de l'abonne qui avait emprunter le DVD
	// set 0, faux
	@Override
	public void retour() {
        Connection conn = ConnexionBDD.getConnexion();
        String req = "UPDATE DOCUMENTS SET emprunte = 0, idAbonne = '' WHERE idDoc = ?";

        synchronized (this) {
            try {
                PreparedStatement st = conn.prepareStatement(req);
                st.setInt(1, this.numero);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ConnexionBDD.close(conn);
		
	}
}
