package test;

import domaine.Hotel;
import fabrique.FabriqueHotel;
/**
 *    HOTEL OKKKKKKKKKKKKK
 *    
 * @author Tchioben
 *
 */


public class TestHotel {

	@Test
	public void deleteHotel(){
		FabriqueHotel fv = FabriqueHotel.getInstance();
		int n = fv.getHotelBDDWithVilleAndNom(1, "ChezBenoit").getId_hotel();
		fv.deleteHotel(n);
	}
	
	@Test
	public void ajoutHotel(){
		FabriqueHotel fv = FabriqueHotel.getInstance();
		fv.addHotel(1,"ChezMaryse");
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

	

}
