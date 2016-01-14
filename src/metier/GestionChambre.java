package metier;

import java.util.ArrayList;

import domaine.Chambre;
import fabrique.FabriqueChambre;

public class GestionChambre {
	
		/**
		 * ajouter une chambre à un hotel
		 * @param id_hotel l'id de l'hotel auquel on veut ajouter une chambre
		 * @param id_categorie l'id de la categorie de la chambre que l'on veut ajouter
		 * @param numero le numero de la chmabre
		 * @return la chambre ajoutée
		 */
		public static Chambre ajouterChambre(int id_hotel,int id_categorie,int numero){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			Chambre chambre = fc.addChambre(id_hotel, id_categorie, numero);
			return chambre;
		}
		
		/**
		 * supprime une chambre
		 * @param idChambre l'id de la chambre à supprimer
		 * @return la chambre si elle n'a pas été supprimé sinon null
		 */
		public static Chambre supprimerChambre(int idChambre){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			fc.deleteChambre(idChambre);
			return fc.getChambreWithIdChambre(idChambre);
		}
		
		/**
		 * recuperer le liste des chambre pour un hotel
		 * @param idHotel l'id de l'hotel duquel on veut recuperer les chambres
		 * @return une liste de chambre
		 */
		public static ArrayList<Chambre> listerChambre(int idHotel){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			return fc.listeChambreParHotel(idHotel);
		}
		
		/**
		 * lister les chambres avec cette categorie
		 * @param idCategorie l'id de la categtorie de la chambre
		 * @return une liste de chambre
		 */
		public static ArrayList<Chambre> listerChambreAvecCategorie(int idCategorie){
			FabriqueChambre fc = FabriqueChambre.getInstance();
			return fc.listeChambreParCategorie(idCategorie);
		}
		
}
