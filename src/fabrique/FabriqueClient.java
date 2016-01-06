/**
 * 
 */
package fabrique;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	@SuppressWarnings("deprecation")
	public Client getClientBDDWithNomAndPrenom(String nom,String prenom){
		int idClient = BDDConnection.getClient( nom,prenom);
		Client Client = this.getClientWithId(idClient);
		//si la Client n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Client== null){
			ResultSet rs = BDDConnection.ligneClient(idClient);
			try {
				int id_ville = rs.getInt(4);
				Date date = rs.getDate(5);
				Client = this.addClientDansFabrique(idClient,nom,prenom,id_ville, date.getDay() , date.getMonth(),date.getYear());
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
				String nom = ligneClient.getString(3);
				String prenom = ligneClient.getString(4); 
				int id_ville = ligneClient.getInt(2);
				Date date = ligneClient.getDate(5);
				Client = this.addClientDansFabrique(idClient,nom,prenom,id_ville, date.getDay() , date.getMonth(),date.getYear());
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Client;
	}
	
	/**
	 * permet de supprimer un client
	 * @param id_Client l'id du client à supprimer
	 */
	public void deleteClient(int id_Client){
		BDDConnection.deleteClient(id_Client);
		try{
			this.lesClients.remove(id_Client);
		}
		catch (Exception e){
		}
	}
	
	/**
	 * permet de cherche un client grace à son nom
	 * @param nom le nom du client
	 * @return le liste des clients avec ce nom
	 */
	public ArrayList<Client> rechercheClientParNom(String nom){
		ArrayList<Client> lesClients = new ArrayList<Client>();
		ResultSet rs = BDDConnection.selectClientsParNom(nom);
		try{
			while (rs.next()){
				int n = rs.getInt(1);
				int n2 = rs.getInt(2);
				String nom1= rs.getString(3);
				String prenom1 =  rs.getString(4);
				Client client= this.addClientDansFabrique(n, nom1, prenom1, n2, 0, 0, 0);
				lesClients.add(client);	
			}
		}
		catch (Exception e){
			e.printStackTrace();;
		}
		return lesClients;
	}
	
	/**
	 * permet de cherche un client grace à son prenom
	 * @param prenom le prenom du client
	 * @return le liste des clients avec ce prenom
	 */
	public ArrayList<Client> rechercheClientParPrenom(String prenom){
		ArrayList<Client> lesClients = new ArrayList<Client>();
		ResultSet rs = BDDConnection.selectClientsParPrenom(prenom);
		try{
			while (rs.next()){
				int n = rs.getInt(1);
				int n2 = rs.getInt(2);
				String nom1= rs.getString(3);
				String prenom1 =  rs.getString(4);
				Client client= this.addClientDansFabrique(n, nom1, prenom1, n2, 0, 0, 0);
				lesClients.add(client);	
			}
		}
		catch (Exception e){
			e.printStackTrace();;
		}
		return lesClients;
	}
	
	/**
	 * permet de recuperer tous les clients
	 * @return la liste des clients
	 */
	public ArrayList<Client> allClients(){
		ArrayList<Client> lesClients = new ArrayList<Client>();
		ResultSet rs = BDDConnection.laTable("Client");
		try{
			while (rs.next()){
				int n = rs.getInt(1);
				int n2 = rs.getInt(2);
				String nom1= rs.getString(3);
				String prenom1 =  rs.getString(4);
				Client client= this.addClientDansFabrique(n, nom1, prenom1, n2, 0, 0, 0);
				lesClients.add(client);	
			}
		}
		catch (Exception e){
			e.printStackTrace();;
		}
		return lesClients;
	}
	
}
