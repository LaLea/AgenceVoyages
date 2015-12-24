/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Client;

/**
 * @author Lea Vannelle & Benoit Bailleul
 *
 */
public class FabriqueClient {

	private static FabriqueClient INSTANCE = null;
	
	private HashMap<Integer,Client> lesClients;
	
	
	private FabriqueClient(){
		this.lesClients = new HashMap<Integer,Client>();
		}
	
	
	public static FabriqueClient getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueClient();
		}
		return INSTANCE;
	}

	/**
	 * crée un client, le rajoute à la BDD si il n'existe pas et l'ajoute à la abrique si
	 * il n'y est pas deja
	 * @param nom le nom du client
	 * @param prenom le prenom du client
	 * @param idVille id de la ville
	 * @param date la date de naissance du client
	 * @return l'objet client, null si il y a eu un probleme dans la BDD
	 */
	public Client addClient(String nom, String prenom, int idVille, String date){
		int idClient = BDDConnection.addClient(nom, prenom, idVille, date);
		if (idClient!=0){
			Client client = this.lesClients.get(idClient);
			if (client==null){
				client= new Client(idClient, nom, prenom, date, idVille);
			}
			return client;
		}
		return null;
	}
	
	/**
	 * ajoute un client, 
	 * @return le client, et null si il n'existe pas
	 */
	public int getClient(String nom,String prenom,int id_ville,String date) {
		int id = BDDConnection.getClient(nom,prenom,id_ville,date);	
		this.lesClients.put(id,client);
		return id;
	}
}
