/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Categorie;
import domaine.Chambre;

/**
 * @author Tchioben
 * Les chambres sont rangées dans une hashmap par le hashcode de leurs categories.
 */
public class FabriqueChambre {

	private static FabriqueChambre INSTANCE = null;
	
	private HashMap<Integer,Chambre> lesChambres; 
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueChambre(){
		this.lesChambres = new HashMap<Integer,Chambre>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueChambre getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueChambre();
		}
		return INSTANCE;
	}
	
	
	private void addChambre(Chambre chambre){

	}
	
	/**
	 *  get the chambre with the following parameters,
	 *  create the chambre if she don't exist
	 * @param categorie
	 * @param idHotel
	 * @return
	 */
	public Chambre getChambre(Categorie categorie, int idHotel){
		Chambre nouvelleChambre= new Chambre(idUnique++,categorie, idHotel);
		addChambre(nouvelleChambre);
		return nouvelleChambre;
	}
	
	
}
