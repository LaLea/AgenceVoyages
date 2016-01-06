package metier;

import java.util.ArrayList;

import domaine.Client;
import domaine.Ville;
import fabrique.FabriqueClient;
import fabrique.FabriqueVille;

public class GestionClient {

	public static Client ajoutVoyageur(String nom, String prenom, int idVille,int jours, int mois,int annee){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.addClient(nom, prenom, idVille, jours, mois, annee);
		return client;
	}
	
	public static Client modifierClient(String nom, String prenom, int idVille,int jours, int mois,int annee){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.getClientBDDWithNomAndPrenom(nom, prenom);
		fc.deleteClient(client.getId_client());
		client= ajoutVoyageur(nom, prenom, idVille, jours, mois, annee);
		return client;
	}
	
	public static void supprimerClient(String nom, String prenom){
		FabriqueClient fc = FabriqueClient.getInstance();
		Client client = fc.getClientBDDWithNomAndPrenom(nom, prenom);
		fc.deleteClient(client.getId_client());
	}
	
	public static void supprimerClient(int idclient){
		FabriqueClient fc = FabriqueClient.getInstance();
		fc.deleteClient(idclient);
	}
	
	public ArrayList<Client> listeClient(){
		FabriqueClient fc = FabriqueClient.getInstance();
		return fc.allClients();
	}
	
}
