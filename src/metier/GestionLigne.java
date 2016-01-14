package metier;

import domaine.Ligne;
import fabrique.FabriqueLigne;
/**
 * Une ligne permet de generer les vols.
 * 
 * Le chef de l'agence peut cliquer pour generer els vosl de la semaine prochaine grace a ces lignes.
 * @author Tchioben
 *
 */
public class GestionLigne {

	/**
	 * ajout d'une ligne
	 * @param id_villeDepart la ville de départ
	 * @param id_villeArrivee la ville d'arrivée
	 * @param jours les jours "1010101" signifie qu'il y aura un vol le lundi, le mercredi, le vendredi et el dimanche
	 * @param heure heure de départ
	 * @param min minute de depart
	 * @param heureDuree heure durée vol
	 * @param minDuree minute durée vol
	 * @param nb1ereClasse nombre de place en 1ere classe
	 * @param prix1ereClasse prix d'une place en premiere classe
	 * @param nb2emeClasse nb place 2 eme classe
	 * @param prix2emeClasse prix d'une place en deuxieme classe
	 * @param dureeAnnulation nombre de jours avant le vol à partir duquel on ne peut plus annuler le voyage
	 * @return la ligne créé
	 */
	public static Ligne ajouterLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		Ligne Ligne = fv.addLigne(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree,
				minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
		return Ligne;
	}
	
	/**
	 * supprime une ligne
	 * @param idLigne l'id de la ligne
	 * @return null si la ligne a ete supprimé sinon la ligne
	 */
	public static Ligne supprimerligne(int idLigne){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		fv.deleteLigne(idLigne);
		return fv.getLigneBDDWithIdLigne(idLigne);
	}
	
	/**
	 * modification d'une ligne
	 * @param id_villeDepart la ville de départ
	 * @param id_villeArrivee la ville d'arrivée
	 * @param jours les jours "1010101" signifie qu'il y aura un vol le lundi, le mercredi, le vendredi et el dimanche
	 * @param heure heure de départ
	 * @param min minute de depart
	 * @param heureDuree heure durée vol
	 * @param minDuree minute durée vol
	 * @param nb1ereClasse nombre de place en 1ere classe
	 * @param prix1ereClasse prix d'une place en premiere classe
	 * @param nb2emeClasse nb place 2 eme classe
	 * @param prix2emeClasse prix d'une place en deuxieme classe
	 * @param dureeAnnulation nombre de jours avant le vol à partir duquel on ne peut plus annuler le voyage
	 * @return la ligne modifiée
	 */
	public static Ligne modifierLigne(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueLigne fv = FabriqueLigne.getInstance();
		fv.deleteLigne(fv.getLigneAvecVilleDepartEtArrivee(id_villeDepart, id_villeArrivee));
		return fv.addLigne(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree, minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
	}
	
	
	
}
