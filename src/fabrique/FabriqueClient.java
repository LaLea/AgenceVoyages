/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	 * crée la Client dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Client
	 */
	public Client addClient(String nom, String prenom, int idVille,int jours , int mois, int annee){
		int numero_id_Client = BDDConnection.addClient(nom, prenom, idVille,  jours , mois,annee);	
		Client client = new Client(numero_id_Client,nom,prenom, jours , mois,annee,idVille);
		this.lesClients.put(numero_id_Client, client);
		return client;
	}
	
	/**
	 * crée la Client dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Client
	 */
	public Client addClientDansFabrique(int idClient ,String nom, String prenom, int idVille,int jours , int mois, int annee){
		Client Client = new Client(idClient,nom,prenom, jours , mois,annee,idVille);
		this.lesClients.put(idClient,Client);
		return Client;
	}

	
	/**
	 * recupere la Client en fonction du numero id.
	 */
	private  Client getClientWithId(int id_Client) {
		return this.lesClients.get(id_Client);
	}
	
	/**
	 * permet de recuperer la Client en fonction de l'id de l'Client et du nom de la Client
	 * @param nom du client
	 * @param prenom du client
	 * @return Client sinon null si la Client n'existe pas en base
	 */
	public Client getClientBDDWithNomAndPrenom(String nom,String prenom){
		int idClient = BDDConnection.getClient( nom,prenom);
		Client Client = this.getClientWithId(idClient);
		//si la Client n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Client== null){
			ResultSet rs = BDDConnection.ligneClient(idClient);
			try {
				int id_ville = rs.getInt(4);
				String date = rs.getString(5);
				//Client = this.addClientDansFabrique(idClient,nom,prenom,id_ville, jours , mois,annee);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Client;
	}
	
	/**
	 * permet de recuperer l Client en fonction de l'id de la Client
	 * @param idClient id de l'Client
	 * @param nom de lla Client
	 * @return Client sinon null si la Client n'existe pas en base
	 */
	public Client getClientWithIdClient(int idClient){
		Client Client = this.getClientWithId(idClient);
		//si la Client n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Client== null){
			ResultSet ligneClient = BDDConnection.ligneClient(idClient);
			try{
				String nom = ligneClient.getString(2);
				String prenom = ligneClient.getString(3); 
				int id_ville = ligneClient.getInt(4);
				String date = ligneClient.getString(5);
				//Client = this.addClientDansFabrique(idClient,nom,prenom,id_ville, date);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Client;
	}
	
	public void deleteClient(int id_Client){
		BDDConnection.deleteClient(id_Client);
		try{
			this.lesClients.remove(id_Client);
		}
		catch (Exception e){
		}
	}
}
