/**
 * 
 */
package fabrique;

import java.util.ArrayList;
import java.util.HashMap;

import domaine.Categorie;

/**
 * @author Tchioben
 *
 */
public class FabriqueCategorie {

	private static FabriqueCategorie INSTANCE = null;
	
	private ArrayList<Categorie> lesCategories;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	private FabriqueCategorie(){
		this.lesCategories = new ArrayList<Categorie>();
		
		this.idUnique=1;
	}
	
	public FabriqueCategorie getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueCategorie();
		}
		return INSTANCE;
	}
	
	public void addCategorie(Categorie nouvelleCategorie){
		if (!lesCategories.contains(nouvelleCategorie)){
			lesCategories.add(nouvelleCategorie);
		}
	}

	
	/**
	 * create the categorie if she dont exist
	 * @param capacite
	 * @param tarif
	 * @return
	 */
	public  Categorie getCategorie(int capacite, float tarif) {
		Categorie laCategorie = new Categorie(idUnique++, capacite, tarif);
		addCategorie(laCategorie);
		return laCategorie;
	}


	
	
	
}
