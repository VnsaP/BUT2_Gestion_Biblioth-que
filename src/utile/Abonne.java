package utile;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Period;

import application.ConnexionBDD;
import utile.Document;

public class Abonne {
	private int numero;
	private String nom;
	private Date dateNaissance;
	
	public Abonne(int numero, String nom, Date dateNaissance) {
  	  this.numero = numero;
      this.nom = nom;
      this.dateNaissance = dateNaissance;
	}

	//get Numero
	public int getNumero() {
		Connection con = ConnexionBDD.getConnexion();

        String req = "SELECT numAbonne FROM ABONNES ";
        synchronized (this) {
            try {
                PreparedStatement st = con.prepareStatement(req);
                st.setInt(1, this.numero);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    return rs.getInt("numAbonne");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ConnexionBDD.close(con);
        return 0;
	}
	
	//get age avec LocalDate
	public int getAge() {
		LocalDate currentDate = LocalDate.now();
		return Period.between(dateNaissance, currentDate).getYears();
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

}
