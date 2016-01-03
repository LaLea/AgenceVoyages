package metier;

import domaine.Hotel;
import fabrique.FabriqueHotel;

public class GestionHotel {
	public static Hotel ajoutHotel(int idVille,String nomHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		Hotel hotel = fh.addHotel(idVille, nomHotel);
		return hotel;
	}
	
	public static void supprimerHotel(int idHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		fh.deleteHotel(idHotel);
	}
	
	public static void supprimerHotel(int idVille,String nomHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		Hotel hotel = fh.getHotelBDDWithVilleAndNom(idVille, nomHotel);
		fh.deleteHotel(hotel.getId_hotel());
	}
	
	public static 
}
