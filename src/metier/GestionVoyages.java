/**
 * 
 */
package metier;

import fabrique.FabriqueVille;
import fabrique.FabriqueVoyage;

/**
 * @author Tchioben
 *
 */
public class GestionVoyages {

	
	public static void ajouterUnVoyage(String nom,String prenom,String date,String nom_ville, String pays_ville){
		FabriqueVille fville= FabriqueVille.getInstance();
		int id_ville = fville.addVille(nom_ville,pays);
		
		int id_client = fc.addClient(nom, prenom, id_ville, date);
		FabriqueVoyage fv = FabriqueVoyage.getInstance();
		fv.addVoyage(id_reserv_vol,id_reserv_chambre ,id_client);
	}
	int id_reserv_vol,int id_reserv_chambre,int id_client
	
}
