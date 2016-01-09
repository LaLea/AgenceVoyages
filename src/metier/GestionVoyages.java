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

	
	public static void ajouterUnVoyage(int idClient, int nombrePersonne,int idVol, int classe, Date dateVol, int idCategorie, Date dateReservationChambre){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.getCategorieWithIdCategorie(idCategorie);
		int nbPersonneParCategorie = categorie.getCapacite();
		int nbpersonneRestante = nombrePersonne;
		while (nbpersonneRestante >0){
			if (nbpersonneRestante>nbPersonneParCategorie){
			BDDConnection.ajouteReservation(idClient, idVol, classe, dateVol, idCategorie, dateReservationChambre,nbPersonneParCategorie);
			BDDConnection.miseAJourNbPlace(nbPersonneParCategorie,classe,idVol);
		}
		else {
			BDDConnection.ajouteReservation(idClient, idVol, classe, dateVol, idCategorie, dateReservationChambre,nbpersonneRestante);
			BDDConnection.miseAJourNbPlace(nbpersonneRestante,classe,idVol);
		}
			nbpersonneRestante -= nbPersonneParCategorie;
		}
	}
	
	public static ArrayList<Reservation> consultationReservation(int idClient){
		FabriqueReservation fr = FabriqueReservation.getInstance();
		ArrayList<Reservation> lesReservs= fr.getReservationDUnePersonne(idClient);
		return lesReservs;
	}
	
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
	
	public static Reservation recuperationReservation(int idReservation){
		FabriqueReservation fr = FabriqueReservation.getInstance();
		Reservation reservation = fr.getReservation(idReservation);
		return reservation;
	}
	
}
