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
	
	
	public static FabriqueClient getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueClient();
		}
		return INSTANCE;
	}

	/**
	 * ajoute un client, si le client existe deja, il renvoie l'id de l'existant
	 * @return numero d'identification du client
	 */
	public int addClient(String nom,String prenom,int id_ville,String date) {
		int id = BDDConnection.addClient(nom,prenom,id_ville,date);	
		Client client= new Client(id,nom,prenom,date,id_ville);
		this.lesClients.put(id,client);
		return id;
	}
}
