
/**
 * @author vanelle
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class BDDConnection {

	private static BDDConnection INSTANCE = null;
	
	private static Connection c;
	
	public BDDConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://172.18.15.22:3306/vanelle?user=vanelle&password=mouais^^030792");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur classforname");
		} catch (SQLException e) {
			System.out.println("Erreur connection \n");
		}
	}
	
	public static BDDConnection getInstance(){
		if (INSTANCE == null){
			return new BDDConnection();
		}
		else {
			return INSTANCE;
		}
	}

	
	/**
	 * retourne la categorie avec ce nom et cet identifiant d'hotel correspondant
	 * @param idHotel 
	 * @param nom about the categorie
	 * @return
	 */
	public static  ResultSet selectCategorie(int idHotel, String nom){
		PreparedStatement stmt;
		ResultSet numero = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Categorie where Id_hotel = ? and Nom= ?);");
			stmt.setInt(1, idHotel);
			stmt.setString(2, nom);
			numero = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numero;
	}

}
