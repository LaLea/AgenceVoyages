/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.ReservationChambre;

/**
 * @author Tchioben
 *
 */
public class FabriqueReservationChambre {
	private static FabriqueReservationChambre INSTANCE = null;
	
	private HashMap<Integer,ReservationChambre> lesReservationChambres;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueReservationChambre(){
		this.lesReservationChambres = new HashMap<Integer,ReservationChambre>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueReservationChambre getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueReservationChambre();
		}
		return INSTANCE;
	}
}
