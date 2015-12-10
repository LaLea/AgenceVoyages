/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import domaine.Categorie;

/**
 * @author Tchioben
 *
 */
public class FabriqueCategorie {

	private static FabriqueCategorie INSTANCE = null;
	
	private HashMap<Integer, Categorie> lesCategories;

	
	private FabriqueCategorie(){
		this.lesCategories = new HashMap<Integer, Categorie>();
	}
	
	public FabriqueCategorie getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueCategorie();
		}
		return INSTANCE;
	}
	
	/**
	 * crée la categorie dans la base de données si elle n'existe pas déjà et l'ajoute à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return le numero d'identification de la categorie dans la bDD et dna sla hashmap
	 */
	public int ajouteCategorie(int id_hotel,String nom,int capacite,float tarif) throws SQLException{
		int numero_id_categorie = BDDConnection.addCategorie(id_hotel, nom, capacite, tarif);	
		Categorie cat = new Categorie(numero_id_categorie, capacite, tarif);
		this.lesCategories.put(numero_id_categorie, cat);
		return numero_id_categorie;
	}

	
	/**
	 * recupere la categorie en fonction du numero id.
	 */
	public  Categorie getCategorieWithId(int id_categorie) {
		return this.lesCategories.get(id_categorie);
	}
	
	/**
	 * permet de recuperer la categorie en fonction de l'id de l'hotel et du nom de la categorie
	 * @param idHotel id de l'hotel
	 * @param nom de lla categorie
	 * @return Categorie sinon null si la categorie n'existe pas en base
	 */
	public Categorie getCategorieWithNomAndHotel(int idHotel, String nom){
		try {
			ResultSet rs = BDDConnection.selectCategorie(idHotel, nom);
			rs.next();
			int numero = ajouteCategorie(idHotel, nom, rs.getInt("Capacite"), rs.getFloat("Tarif"));
			return getCategorieWithId(numero);
		}
		catch(Exception e){
			System.out.println("la categorie n'a pas ete trouvee");
		}
		return null;
	}


	
	
	
}
