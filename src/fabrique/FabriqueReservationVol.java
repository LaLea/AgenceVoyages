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
	
	
	private FabriqueReservationVol(){
		this.lesReservationVols = new HashMap<Integer,ReservationVol>();
	
	}
	
	
	public static FabriqueReservationVol getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueReservationVol();
		}
		return INSTANCE;
	}
}
