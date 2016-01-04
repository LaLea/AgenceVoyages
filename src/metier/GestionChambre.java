package metier;

import java.util.ArrayList;

import domaine.Chambre;
import fabrique.FabriqueChambre;

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
}
