/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import domaine.Chambre;

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
	
	
	public FabriqueChambre getInstance(){
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
	public Chambre addChambre(int id_hotel,int id_categorie, int numero) throws SQLException{
		int idChambre = BDDConnection.addChambre(id_hotel, );	
		Chambre chambre = new Chambre(numero_id_Chambre, capacite, tarif, id_hotel,nom);
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
	public Chambre addChambreDansFabrique(int idChambre ,int id_hotel,String nom,int capacite,float tarif) throws SQLException{
		Chambre cat = new Chambre(idChambre, capacite, tarif, id_hotel,nom);
		this.lesChambres.put(idChambre, cat);
		return cat;
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
	public Chambre getChambreBDDWithNomAndHotel(int id_hotel, String nom){
		int idChambre = BDDConnection.getChambre(id_hotel, nom);
		Chambre Chambre = this.getChambreWithId(idChambre);
		//si la Chambre n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Chambre== null){
			ResultSet ligneChambre = BDDConnection.ligneChambre(idChambre);
			try{
				int capacite = ligneChambre.findColumn("Capacite");
				float tarif = ligneChambre.findColumn("Tarif");
				Chambre = this.addChambreWithIdChambre(idChambre,id_hotel, nom, capacite, tarif);
			}
			catch (SQLException e){
				return null;
			}
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
				int id_hotel = ligneChambre.findColumn("IDHotel");
				String nom = ligneChambre.getString(3);
				int capacite = ligneChambre.findColumn("Capacite");
				float tarif = ligneChambre.findColumn("Tarif");
				Chambre = this.addChambreWithIdChambre(idChambre,id_hotel, nom, capacite, tarif);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Chambre;
	}
	
}
