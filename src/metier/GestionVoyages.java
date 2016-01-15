/**
 * 
 */
package metier;

import java.sql.Date;
import java.util.ArrayList;

import domaine.Categorie;
import domaine.Reservation;
import domaine.Vol;
import fabrique.BDDConnection;
import fabrique.FabriqueCategorie;
import fabrique.FabriqueReservation;
import fabrique.FabriqueVol;

/**
 * @author Tchioben
 *
 */
public class GestionVoyages {

	/**
	 * permet d'ajouter une reservation à un voyage 
	 * @param idClient l'id du client
	 * @param nombrePersonne nombre de personne ( client compris)
	 * @param idVolAller id du vol aller
	 * @param classe la classe du vol
	 * @param dateVolAller date vol aller
	 * @param idCategorie id de la categorie de chambre
	 * @param dateReservationChambre date reservation chambre
	 * @param idVolRetour id vol retour 
	 * @param dateVolRetour date vol retour
	 */
	public static void ajouterUnVoyage(int idClient, int nombrePersonne,int idVolAller,
			int classe, Date dateVolAller, int idCategorie, Date dateReservationChambre,
			int idVolRetour, Date dateVolRetour){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.getCategorieWithIdCategorie(idCategorie);
		int nbPersonneParCategorie = categorie.getCapacite();
		int nbpersonneRestante = nombrePersonne;
		while (nbpersonneRestante >0){
			if (nbpersonneRestante>nbPersonneParCategorie){
			BDDConnection.ajouteReservation(idClient, idVolAller, classe, dateVolAller, idCategorie, dateReservationChambre,nbPersonneParCategorie,idVolRetour,dateVolRetour);
			BDDConnection.miseAJourNbPlace(nbPersonneParCategorie,classe,idVolAller);
		}
		else {
			BDDConnection.ajouteReservation(idClient, idVolAller, classe, dateVolAller, idCategorie, dateReservationChambre,nbpersonneRestante,idVolRetour,dateVolRetour);
			BDDConnection.miseAJourNbPlace(nbpersonneRestante,classe,idVolAller);
		}
			nbpersonneRestante -= nbPersonneParCategorie;
		}
	}
	
	/**
	 * permet d'afficher les reservations
	 * @param idClient l'id du client
	 * @return la liste des reservation pour un client
	 */
	public static ArrayList<Reservation> consultationReservation(int idClient){
		FabriqueReservation fr = FabriqueReservation.getInstance();
		ArrayList<Reservation> lesReservs= fr.getReservationDUnePersonne(idClient);
		return lesReservs;
	}
	
	/**
	 * supprime une reservation
	 * @param lesReservs
	 */
	public static void supprimeReservation(ArrayList<Reservation> lesReservs){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		FabriqueVol fv = FabriqueVol.getInstance();
		for(Reservation res: lesReservs){
			Date aujourd = BDDConnection.getDateDuJour();
			Date reservVol = res.getDateVol();
			Date reservChambre= res.getDateReservation();
			Categorie cat = fc.getCategorieWithIdCategorie(res.getIdCategorie());
			int delaiCat = cat.getDelai();
			Vol vol = fv.getVolBDDWithIdVol(res.getIdVol());
			int delaiVol = vol.getDelaiAnnulation();
			int difference = reservChambre.compareTo(aujourd);
			int difference2 = reservVol.compareTo(aujourd);
			if (difference>=delaiCat && difference2>=delaiVol){
				BDDConnection.supprimeReservation(res.getId_reservation());
			}
		}
	}
	
	/**
	 * recuperer la reservation d'un client
	 * @param idReservation l'id de la reservation 
	 * @return la reservation
	 */
	public static Reservation recuperationReservation(int idReservation){
		FabriqueReservation fr = FabriqueReservation.getInstance();
		Reservation reservation = fr.getReservation(idReservation);
		return reservation;
	}
	
}
