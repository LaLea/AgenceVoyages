/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Client;

/**
 * @author Tchioben
 *
 */
public class FabriqueClient {

	private static FabriqueClient INSTANCE = null;
	
	private HashMap<Integer,Client> lesClients;
	
	private int idUnique; //permet de donner un id unique à chaque categorie
	
	
	private FabriqueClient(){
		this.lesClients = new HashMap<Integer,Client>();
		
		this.idUnique=1;
	}
	
	
	public FabriqueClient getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueClient();
		}
		return INSTANCE;
	}
}
