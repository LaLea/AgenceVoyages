/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Hotel;

/**
 * @author Tchioben
 *
 */
public class FabriqueHotel {
	private static FabriqueHotel INSTANCE = null;
	
	private HashMap<Integer,Hotel> lesHotels;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueHotel(){
		this.lesHotels = new HashMap<Integer,Hotel>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueHotel getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueHotel();
		}
		return INSTANCE;
	}
}
