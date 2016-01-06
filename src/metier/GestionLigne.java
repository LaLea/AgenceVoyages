package metier;

import domaine.Ligne;
import fabrique.FabriqueLigne;

public class GestionLigne {

	public static Ligne ajouterLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		Ligne Ligne = fv.addLigne(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree,
				minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
		return Ligne;
	}
	
	public static void supprimerligne(int idLigne){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		fv.deleteLigne(idLigne);
	}
	
	public static Ligne modifierLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		fv.deleteLigne(fv.getLigneAvecVilleDepartEtArrivee(id_villeDepart, id_villeArrivee));
		return fv.addLigne(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree, minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
	}
	
	
	
}
