package metier;

import java.util.ArrayList;

import domaine.Hotel;
import fabrique.FabriqueHotel;

public class GestionHotel {
	
	/**
	 * ajout d'un hotel
	 * @param idVille id de la ville à laquelle on souhaite ajouter un hotel
	 * @param nomHotel le nom de l'hotel que l'on veut ajouter
	 * @return l'hotel si il a été créé sinon null
	 */
	public static Hotel ajoutHotel(int idVille,String nomHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		Hotel hotel = fh.addHotel(idVille, nomHotel);
		return hotel;
	}
	
	/**
	 * supprime un hotel grace à son id
	 * @param idHotel l'id de l'hotel que l'on souhaite supprimer
	 * @retrun null si l'hotel a été supprimé sinon l'hotel
	 */ 
	public static Hotel supprimerHotel(int idHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		fh.deleteHotel(idHotel);
		return fh.getHotelWithIdHotel(idHotel);
	}
	
	/**
	 * supprimer un hotel grace à son id vill et son nom
	 * @param idVille id de la ville
	 * @param nomHotel le nom de l'hotel
	 * @return null si l'hotel a été supprimé sinon l'hotel
	 */
	public static Hotel supprimerHotel(int idVille,String nomHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		Hotel hotel = fh.getHotelBDDWithVilleAndNom(idVille, nomHotel);
		fh.deleteHotel(hotel.getId_hotel());
		return fh.getHotelBDDWithVilleAndNom(idVille, nomHotel);
	}
	
	/**
	 * recupere tous les hotels dans une ville 
	 * @param idVille l'id de la ville
	 * @return une liste d'hotel
	 */
	public static ArrayList<Hotel> listerHotelDUneVille(int idVille){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		return fh.getHotelAvecIdVille(idVille);
	}
}
