/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Chambre;
import domaine.Hotel;

/**
 * @author Lea Vannelle & Benoit Bailleul
 * Les chambres sont rangées dans une hashmap par le code d'indentification 
 * de la chambre dans la BDD.
 */
public class FabriqueChambre {

	private static FabriqueChambre INSTANCE = null;
	
	private HashMap<Integer,Chambre> lesChambres; 
	 //permet de donner un id unique à chaque Chambre
	
	
	private FabriqueChambre(){
		this.lesChambres = new HashMap<Integer,Chambre>();
		
	}
	
	
	public static FabriqueChambre getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueChambre();
		}
		return INSTANCE;
	}

	/**
	 * crée la Chambre dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Chambre
	 */
	public Chambre addChambre(int id_hotel,int id_categorie,int numero){
		int numero_id_Chambre = BDDConnection.addChambre(id_hotel, id_categorie,numero);	
		Chambre cat = new Chambre(numero_id_Chambre,id_hotel,id_categorie,numero);
		this.lesChambres.put(numero_id_Chambre, cat);
		return cat;
	}
	
	/**
	 * crée la Chambre dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Chambre
	 */
	public Chambre addChambreDansFabrique(int idChambre ,int id_hotel,int id_categorie,int numero){
		Chambre chambre = new Chambre(idChambre, id_hotel,id_categorie,numero);
		this.lesChambres.put(idChambre,chambre);
		return chambre;
	}

	
	/**
	 * recupere la Chambre en fonction du numero id.
	 */
	private  Chambre getChambreWithId(int id_Chambre) {
		return this.lesChambres.get(id_Chambre);
	}
	
	/**
	 * permet de recuperer la Chambre en fonction de l'id de l'hotel et du nom de la Chambre
	 * @param idHotel id de l'hotel
	 * @param nom de lla Chambre
	 * @return Chambre sinon null si la Chambre n'existe pas en base
	 */
	public Chambre getChambreBDDWithIdhotelIdcategorieAndNumero(int id_hotel,int id_categorie,int numero) throws SQLException{
		int idChambre = BDDConnection.getChambre(id_hotel,id_categorie,numero);
		Chambre Chambre = this.getChambreWithId(idChambre);
		//si la Chambre n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Chambre== null){
			Chambre = this.addChambreDansFabrique(idChambre, id_hotel,id_categorie,numero);
		}// fin IF
		return Chambre;
	}
	
	/**
	 * permet de recuperer la Chambre en fonction de l'id de la Chambre
	 * @param idHotel id de l'hotel
	 * @param nom de lla Chambre
	 * @return Chambre sinon null si la Chambre n'existe pas en base
	 */
	public Chambre getChambreWithIdChambre(int idChambre){
		Chambre Chambre = this.getChambreWithId(idChambre);
		//si la Chambre n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Chambre== null){
			ResultSet ligneChambre = BDDConnection.ligneChambre(idChambre);
			try{
				int id_hotel = ligneChambre.getInt(2);
				int id_categorie = ligneChambre.getInt(3);
				int numero = ligneChambre.getInt(4);
				Chambre = this.addChambreDansFabrique(idChambre, id_hotel,id_categorie,numero);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Chambre;
	}
	
	public void deleteChambre(int id_Chambre){
		BDDConnection.deleteChambre(id_Chambre);
		try{
			this.lesChambres.remove(id_Chambre);
		}
		catch (Exception e){
		}
	}


	public ArrayList<Chambre> listeChambreParHotel(int idHotel) {
			ResultSet rs = BDDConnection.getChambre(idHotel);
			ArrayList<Chambre> lesChambres = new ArrayList<Chambre>();
			try {
				while (rs.next()){
					Chambre chambre = getChambreWithId(rs.getInt(1));
					lesChambres.add(chambre);
				}
			}
			catch (Exception e){
				return lesChambres;
				}
			return lesChambres;
	}
}
