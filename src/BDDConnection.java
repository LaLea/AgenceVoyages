
/**
 * @author vanelle
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class BDDConnection {
	
	String url = "sql2.olympe.in";
	String userName = "y5cf3srf";
	String password = "P@ssw0rd2015";
	
	private static BDDConnection INSTANCE = null;
	
	private Connection c;
	
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
	
	public Connection getC(){
		return this.c;
	}

}
