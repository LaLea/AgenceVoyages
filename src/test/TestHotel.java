package test;

import metier.GestionHotel;

import org.junit.Test;

import domaine.Hotel;
import fabrique.BDDConnection;
import fabrique.FabriqueHotel;
/**
 *    HOTEL OKKKKKKKKKKKKK
 *    
 * @author Tchioben
 *
 */


public class TestHotel {

/**	@Test
	public void deleteHotel(){
		FabriqueHotel fv = FabriqueHotel.getInstance();
		int n = fv.getHotelBDDWithVilleAndNom(1, "ChezBenoit").getId_hotel();
		fv.deleteHotel(n);
	}
	
	@Test
	public void ajoutHotel(){
		BDDConnection.addHotel(1, "ChezLea");
		Hotel hotel = fv.getHotelBDDWithVilleAndNom(1,"ChezMaryse");
		System.out.println(hotel);
	}
	/**
	@Test
	public void ajoutHotel2(){
		FabriqueHotel fv = FabriqueHotel.getInstance();
		fv.addHotel(1,"ChezBenoit");
		Hotel hotel = fv.getHotelBDDWithVilleAndNom(1,"ChezBenoit");
		System.out.println(hotel);
	}
	
	
	*/

	@Test
	public void testHotel(){
		GestionHotel.ajoutHotel(1,"Incconu3");
				}

}
