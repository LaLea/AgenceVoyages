/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Categorie;

/**
 * @author Lea Vannelle & Benoit Bailleul
 *
 */
public class FabriqueCategorie {

	private static FabriqueCategorie INSTANCE = null;
	
	private HashMap<Integer, Categorie> lesCategories;

	
	private FabriqueCategorie(){
		this.lesCategories = new HashMap<Integer, Categorie>();
	}
	
	public static FabriqueCategorie getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueCategorie();
		}
		return INSTANCE;
	}
	
	/**
	 * crée la categorie dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite le nombre de lits pour cette categorie
	 * @param tarif le prix de cette categorie
	 * @return la categorie 
	 */
	public Categorie addCategorie(int id_hotel,String nom,int capacite,float tarif,int delai){
		int numero_id_categorie = BDDConnection.addCategorie(id_hotel, nom, capacite, tarif,delai);	
		Categorie cat = new Categorie(numero_id_categorie, capacite, tarif, id_hotel,nom,delai);
		this.lesCategories.put(numero_id_categorie, cat);
		return cat;
	}
	
	/**
	 * crée la categorie dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite le nombre de lits pour cette categorie
	 * @param tarif le prix de cette categorie
	 * @return la categorie
	 */
	public Categorie addCategorieDansFabrique(int idCategorie ,int id_hotel,String nom,int capacite,float tarif,int delai){
		Categorie cat = new Categorie(idCategorie, capacite, tarif, id_hotel,nom,delai);
		this.lesCategories.put(idCategorie, cat);
		return cat;
	}

	
	/**
	 * recupere la categorie en fonction du numero id.
	 */
	private  Categorie getCategorieWithId(int id_categorie) {
		return this.lesCategories.get(id_categorie);
	}
	
	/**
	 * permet de recuperer la categorie en fonction de l'id de l'hotel et du nom de la categorie
	 * @param idHotel id de l'hotel
	 * @param nom de lla categorie
	 * @return Categorie sinon null si la categorie n'existe pas en base
	 */
	public Categorie getCategorieBDDWithNomAndHotel(int id_hotel, String nom){
		int idCategorie = BDDConnection.getCategorie(id_hotel, nom);
		Categorie categorie = this.getCategorieWithId(idCategorie);
		//si la categorie n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (categorie== null){
			ResultSet ligneCategorie = BDDConnection.ligneCategorie(idCategorie);
			try{
				int capacite = ligneCategorie.getInt(4);
				float tarif = ligneCategorie.getFloat(5);
				int delai= ligneCategorie.getInt(6);
				categorie = this.addCategorieDansFabrique(idCategorie,id_hotel, nom, capacite, tarif,delai);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return categorie;
	}
	
	/**
	 * permet de recuperer la categorie en fonction de l'id de la categorie
	 * @param idHotel id de l'hotel
	 * @param nom de lla categorie
	 * @return Categorie sinon null si la categorie n'existe pas en base
	 */
	public Categorie getCategorieWithIdCategorie(int idCategorie){
		Categorie categorie = this.getCategorieWithId(idCategorie);
		//si la categorie n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (categorie== null){
			ResultSet ligneCategorie = BDDConnection.ligneCategorie(idCategorie);
			try{
				int id_hotel = ligneCategorie.getInt(2);
				String nom = ligneCategorie.getString(3);
				int capacite = ligneCategorie.getInt(4);
				float tarif = ligneCategorie.getFloat(5);
				int delai = ligneCategorie.getInt(6);
				categorie = this.addCategorieDansFabrique(idCategorie,id_hotel, nom, capacite, tarif,delai);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return categorie;
	}
	
	/**
	 * supprime une categorie grace à l'id de la categorie
	 * @param id_categorie
	 */
	public void deleteCategorie(int id_categorie){
		BDDConnection.deleteCategorie(id_categorie);
		try{
			this.lesCategories.remove(id_categorie);
		}
		catch (Exception e){
		}
	}
	
	/**
	 * permet de recuperer toutes les categories d'un hotel 
	 * @param idHotel l'id de l'hotel
	 * @return une liste des hotel
	 */
	public ArrayList<Categorie> selectCategorie(int idHotel) {
		ResultSet rs = BDDConnection.getCategorie(idHotel);
		ArrayList<Categorie> lesCategories = new ArrayList<Categorie>();
		try {
			while (rs.next()){
				Categorie categorie = addCategorieDansFabrique(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6));
				lesCategories.add(categorie);
			}
		}
		catch (Exception e){
			e.printStackTrace();
			return lesCategories;
			}
		return lesCategories;
	}

	public Categorie modifierCategorie(int idCategorie,int id_hotel, String nom, int capacite,
			float tarif, int delai) {
			BDDConnection.modifieCategorie(idCategorie,id_hotel,nom,capacite,tarif,delai);
			this.lesCategories.remove(idCategorie);
			return getCategorieWithIdCategorie(idCategorie);
	}
}
