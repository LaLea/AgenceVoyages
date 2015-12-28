package test;

import org.junit.Test;

import domaine.Client;
import fabrique.FabriqueClient;

public class testClient {

	@Test
	public void ajouteClient() {
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.addClient("benoit", "bailleul",1,23,12,1988);
		System.out.println(client.toString()+client.getId_client());
	}

}
