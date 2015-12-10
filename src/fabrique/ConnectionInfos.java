/**
 * 
 */
package fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Tchioben
 *
 */
public class ConnectionInfos {
	
	private static ConnectionInfos INSTANCE=null;
	
	private static Connection c;
	
	
	public ConnectionInfos(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://172.18.15.22:3306/vanelle?user=vanelle&password=mouais^^030792");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur classforname");
		} catch (SQLException e) {
			System.out.println("Erreur connection \n");
		}
	}


	public static ConnectionInfos getInstance(){
		if (INSTANCE == null){
			return new ConnectionInfos();
		}
		else {
			return INSTANCE;
		}
	}
}

