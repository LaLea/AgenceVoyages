package test;

import org.junit.Test;

import fabrique.FabriqueCategorie;
/**
 *  CATEGORIE OKKKKKKKK
 * @author Tchioben
 *
 */


public class TestCategorie {

	@Test
	public void addCategorie(){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.addCategorie(1, "Luxe",2, new Float(350));
	}

/**	@Test
	public void deleteCategorie(){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.deleteCategorie(fc.getCategorieBDDWithNomAndHotel(1, "Luxe").getId_categorie());
	}
	*/
}
