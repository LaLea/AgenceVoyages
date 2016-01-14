package metier;

import java.util.ArrayList;
import java.util.Date;

import domaine.Client;
import domaine.Ville;
import fabrique.FabriqueClient;
import fabrique.FabriqueVille;

public class GestionClient {

	/**
	 * ajout d'un voyageur
	 * @param nom son nom
	 * @param prenom son prenom
	 * @param ville nom de la ville
	 * @param pays le pays 
	 * @param date sa date de naissance
	 * @return le client créé
	 */
	public static Client ajoutVoyageur(String nom, String prenom, String ville,String pays,Date date){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville2 = fv.addVille(ville,pays);
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.addClient(nom, prenom, ville2.getId_ville(), date);
		return client;
	}
	
	/**
	 * modification d'un voyageur
	 * @param nom son nom
	 * @param prenom son prenom
	 * @param ville nom de la ville
	 * @param pays le pays 
	 * @param date sa date de naissance
	 * @return le client modifié
	 */
	public static Client modifierClient(String nom, String prenom, String ville,String pays,Date date){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.getClientBDDWithNomAndPrenom(nom, prenom);
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville2 = fv.addVille(ville, pays);
		fc.modifierClient(client.getId_client(), nom,  prenom,  ville2.getId_ville(), date);
		return fc.getClientWithIdClient(client.getId_client());
	}
	
	/**
	 * supprime un client grace à son nom et prénom
	 * @param nom son nom
	 * @param prenom son prénom
	 * @return null si le client a été supprimé sinon l'objet client
	 */
	public static Client supprimerClient(String nom, String prenom){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.getClientBDDWithNomAndPrenom(nom, prenom);
		fc.deleteClient(client.getId_client());
		return fc.getClientBDDWithNomAndPrenom(nom, prenom);
	}
	
	/**
	 * supprime un client grace à son numéro id
	 * @param idclient son numero id 
	 * @return null si le client a été supprimé sinon l'objet client
	 */
	public static Client supprimerClient(int idclient){
		FabriqueClient fc = FabriqueClient.getInstance();
		fc.deleteClient(idclient);
		return fc.getClientWithIdClient(idclient);
	}
	
	/**
	 * recupere tous les clients de la BDD
	 * @return la liste des clients
	 */
	public ArrayList<Client> listeClient(){
		FabriqueClient fc = FabriqueClient.getInstance();
		return fc.allClients();
	}
	
	/**
	 * recupere les clients grace à leurs nom 
	 * @param nom le nom que l'on cherche
	 * @return la liste des clients avec ce nom
	 */
	public ArrayList<Client> rechercheClientParNom(String nom){
		FabriqueClient fc = FabriqueClient.getInstance();
		return fc.rechercheClientParNom(nom);
	}
	
	/**
	 * recupere les clients grace à leurs prenom
	 * @param prenom le prenom que l'on cherche
	 * @return la liste des clients avec ce prenom
	 */
	public ArrayList<Client> rechercheClientParPrenom(String prenom){
		FabriqueClient fc = FabriqueClient.getInstance();
		return fc.rechercheClientParNom(prenom);
	}
	
}

