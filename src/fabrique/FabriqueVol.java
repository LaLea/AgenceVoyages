/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Vol;

/**
 * @author Tchioben
 *
 */
public class FabriqueVol {
	private static FabriqueVol INSTANCE = null;
	
	private HashMap<Integer,Vol> lesVols;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueVol(){
		this.lesVols = new HashMap<Integer,Vol>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueVol getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueVol();
		}
		return INSTANCE;
	}
}
