/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Voyage;

/**
 * @author Tchioben
 *
 */
public class FabriqueVoyage {

	private static FabriqueVoyage INSTANCE = null;
	
	private HashMap<Integer,Voyage> lesVoyages =null;
	
	private FabriqueVoyage(){
		this.lesVoyages = new HashMap<Integer, Voyage>();
	}
	
	public static FabriqueVoyage getInstance() {
		if (INSTANCE==null){
			INSTANCE=new FabriqueVoyage();
		}
		return null;
	}

	public Voyage addVoyage(int id_reserv_vol, int id_reserv_chambre,int id_client) {
		int numero = BDDConnection.addVoyage(id_reserv_vol, id_reserv_chambre, id_client);
		Voyage voyage = new Voyage(numero,id_reserv_vol, id_reserv_chambre, id_client);
		lesVoyages.put(numero, voyage);
		return voyage;
	}
	
	

}
