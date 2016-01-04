package metier;

import domaine.Vol;
import fabrique.FabriqueVol;

public class GestionLigne {

	public static Vol ajouterLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueVol fv = FabriqueVol.getInstance();
		Vol vol = fv.addVol(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree,
				minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
		return vol;
	}
	
	public static void supprimerligne(int idVol){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(idVol);
	}
	
	public static Vol modifierLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(fv.(id_villeDepart, id_villeArrivee));
		
	}
	
	
	
}
