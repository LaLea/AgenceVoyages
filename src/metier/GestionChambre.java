package metier;

import java.util.ArrayList;

import domaine.Chambre;
import domaine.Reservation;
import fabrique.FabriqueChambre;
import fabrique.FabriqueReservation;

public class GestionChambre {

		public static Chambre ajouterChambre(int id_hotel,int id_categorie,int numero){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			Chambre chambre = fc.addChambre(id_hotel, id_categorie, numero);
			return chambre;
		}
		
		public static void supprimerChambre(int idChambre){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			fc.deleteChambre(idChambre);
		}
		
		public static ArrayList<Chambre> listerChambre(int idHotel){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			return fc.listeChambreParHotel(idHotel);
		}
		
		public static ArrayList<Chambre> listerChambreAvecCategorie(int idCategorie){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			return fc.listeChambreParCategorie(idCategorie);
		}
		
	//	public static Chambre recupererChambreReserve(int idReservation){
		//	FabriqueReservation fres = FabriqueReservation.getInstance();
		//	Reservation reserv = fres.getReservation(idReservation);
		//	FabriqueChambre fc = FabriqueChambre.getInstance();
		//	return fc.getChambreWithIdChambre(reserv.getIdChambre());
		//	}
}
