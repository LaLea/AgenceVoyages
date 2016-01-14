package metier;

import java.util.ArrayList;

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
	
	public static Hotel supprimerHotel(int idVille,String nomHotel){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		Hotel hotel = fh.getHotelBDDWithVilleAndNom(idVille, nomHotel);
		fh.deleteHotel(hotel.getId_hotel());
		return fh.getHotelBDDWithVilleAndNom(idVille, nomHotel);
	}
	
	public static ArrayList<Hotel> listerHotelDUneVille(int idVille){
		FabriqueHotel fh = FabriqueHotel.getInstance();
		return fh.getHotelAvecIdVille(idVille);
	}
}
