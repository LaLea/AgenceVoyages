/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import domaine.Hotel;

/**
 * @author Tchioben
 *
 */
public class FabriqueHotel {
	private static FabriqueHotel INSTANCE = null;
	
	private HashMap<Integer,Hotel> lesHotels;
	

	
	private FabriqueHotel(){
		this.lesHotels = new HashMap<Integer,Hotel>();
	}
	
	
	public static FabriqueHotel getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueHotel();
		}
		return INSTANCE;
	}
	
	/**
	 * crée la Hotel dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Hotel
	 */
	public Hotel addHotel(int idVille,String nom){
		int numero_id_Hotel = BDDConnection.addHotel(idVille, nom);	
		Hotel cat = new Hotel(numero_id_Hotel,nom,idVille);
		this.lesHotels.put(numero_id_Hotel, cat);
		return cat;
	}
	
	/**
	 * crée la Hotel dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Hotel
	 */
	public Hotel addHotelDansFabrique(int idHotel ,int id_ville,String nom){
		Hotel hotel = new Hotel(idHotel,nom,id_ville);
		this.lesHotels.put(idHotel,hotel);
		return hotel;
	}

	
	/**
	 * recupere la Hotel en fonction du numero id.
	 */
	private  Hotel getHotelWithId(int id_Hotel) {
		return this.lesHotels.get(id_Hotel);
	}
	
	/**
	 * permet de recuperer la Hotel en fonction de l'id de l'hotel et du nom de la Hotel
	 * @param idHotel id de l'hotel
	 * @param nom de lla Hotel
	 * @return Hotel sinon null si la Hotel n'existe pas en base
	 */
	public Hotel getHotelBDDWithVilleAndNom(int id_ville, String nom){
		int idHotel = BDDConnection.getHotel(id_ville, nom);
		Hotel Hotel = this.getHotelWithId(idHotel);
		//si la Hotel n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Hotel== null){
			Hotel = this.addHotelDansFabrique(idHotel,id_ville, nom);
		}// fin IF
		return Hotel;
	}
	
	/**
	 * permet de recuperer l Hotel en fonction de l'id de la Hotel
	 * @param idHotel id de l'hotel
	 * @param nom de lla Hotel
	 * @return Hotel sinon null si la Hotel n'existe pas en base
	 */
	public Hotel getHotelWithIdHotel(int idHotel){
		Hotel Hotel = this.getHotelWithId(idHotel);
		//si la Hotel n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Hotel== null){
			ResultSet ligneHotel = BDDConnection.ligneHotel(idHotel);
			try{
				int id_ville = ligneHotel.getInt(2);
				String nom = ligneHotel.getString(3);
				Hotel = this.addHotelDansFabrique(idHotel,id_ville, nom);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Hotel;
	}
	
	public void deleteHotel(int id_Hotel){
		BDDConnection.deleteHotel(id_Hotel);
		try{
			this.lesHotels.remove(id_Hotel);
		}
		catch (Exception e){
		}
	}
	
	
}
