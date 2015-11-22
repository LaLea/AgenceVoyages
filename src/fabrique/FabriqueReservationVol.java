/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.ReservationVol;

/**
 * @author Tchioben
 *
 */
public class FabriqueReservationVol {
	private static FabriqueReservationVol INSTANCE = null;
	
	private HashMap<Integer,ReservationVol> lesReservationVols;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueReservationVol(){
		this.lesReservationVols = new HashMap<Integer,ReservationVol>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueReservationVol getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueReservationVol();
		}
		return INSTANCE;
	}
}
