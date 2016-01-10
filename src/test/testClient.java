package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.toedter.calendar.JDateChooser;

import domaine.Client;
import fabrique.BDDConnection;
import fabrique.FabriqueClient;

public class testClient {

@Test
	public void ajouteClient(){
		FabriqueClient fc = FabriqueClient.getInstance();
		JDateChooser date = new JDateChooser();
		java.util.Date d1 = new java.util.Date();
		BDDConnection.addClient("bailleul", "benoit22!",1,d1);
	}
/**	
	@Test
	public void ajouteClient2() throws SQLException{
		FabriqueClient fc = FabriqueClient.getInstance();
		ArrayList<Client> clients= fc.rechercheClientParNom("benoit");
		for(Client c: clients){
			System.out.println(c.toString());
		}
	}
	*/
}
