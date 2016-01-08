package test;

import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Client;
import fabrique.FabriqueClient;

public class testClient {

@Test
	public void ajouteClient(){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.addClient("bailleul", "benoit2",1,23,12,1988);
		System.out.println(client.toString()+client.getId_client());
	}
	
	@Test
	public void ajouteClient2() throws SQLException{
		FabriqueClient fc = FabriqueClient.getInstance();
		ArrayList<Client> clients= fc.rechercheClientParNom("benoit");
		for(Client c: clients){
			System.out.println(c.toString());
		}
	}
}
