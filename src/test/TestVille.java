package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domaine.Ville;
import fabrique.FabriqueVille;

/**
 * 
 *  VILLE OKKKKKKKKKKKKK
 * 
 * 
 * @author Tchioben
 *
 */


public class TestVille {

	@Test
	public void ajoutVille(){
		FabriqueVille fv = FabriqueVille.getInstance();
		fv.addVille("Hantay","France");
		Ville ville = fv.getVilleBDDWithNomAndHotel("Hantay","France");
		assertTrue(ville.getNom().equals("Hantay"));
	}
	
	
	@Test
	public void deleteVille(){
		FabriqueVille fv = FabriqueVille.getInstance();
		int n = fv.getVilleBDDWithNomAndHotel("Hantay", "France").getId_ville();
		fv.deleteVille(n);
	}
	
	

}
