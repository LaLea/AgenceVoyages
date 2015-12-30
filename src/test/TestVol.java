package test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import domaine.Client;
import fabrique.BDDConnection;
import fabrique.FabriqueClient;

public class TestVol {

@Test
	public void ajouteVol(){
		BDDConnection.addVol(1, 2, "2", 10, 30, 2, 10, 2, new Float(500), 5, new Float(200), 2);
	}
	
}
