package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
	
	public ConnexionBDD() {}

	//Connection avec la base de données Access
    public static Connection getConnexion(){
        try {
            String dataURL = "jdbc:ucanaccess://D:\\BUT2A\\Database11.accdb";
            return  DriverManager.getConnection(dataURL);           
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //fermeture de la connexion
    public static void close(Connection conn) {
        try{
            conn.close();
        } catch (Exception ignored) {}
    }
}
