package test;

import java.util.ArrayList;

import metier.GestionCategorie;

import org.junit.Test;

import domaine.Categorie;
/**
 *  CATEGORIE OKKKKKKKK
 * @author Tchioben
 *
 */


public class TestCategorie {

/**	@Test
	public void addCategorie(){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.addCategorie(1, "Luxe",2, new Float(350),5);
	}*/

/**	@Test
	public void deleteCategorie(){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.deleteCategorie(fc.getCategorieBDDWithNomAndHotel(1, "Luxe").getId_categorie());
	}
	*/
	
	@Test
	public void getCategorie(){
		ArrayList<Categorie> l = GestionCategorie.listerCategorieHotel(1);
		for (Categorie l2 : l){
			System.out.println(l2);
		}
	}
}
