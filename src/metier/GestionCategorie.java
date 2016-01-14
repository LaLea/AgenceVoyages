package metier;

import java.util.ArrayList;

import domaine.Categorie;
import fabrique.FabriqueCategorie;

public class GestionCategorie {

	/**
	 * ajouter une categorie dans la BDD Categorie
	 * @param id_hotel l'id de l'hotel
	 * @param nom le nom de la categorie
	 * @param capacite la capacite de la categorie
	 * @param tarif le tarif de la categorie
	 * @param delai le delai maximal de retractation ( en jours ) pour annuler la reservation
	 * @return la Categorie modifiée
	 */
	public static Categorie ajouterCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.addCategorie(id_hotel, nom, capacite, tarif, delai);
		return categorie;
	}
	
	/**
	 * modifier une categorie dans la BDD Categorie
	 * @param id_hotel l'id de l'hotel
	 * @param nom le nom de la categorie
	 * @param capacite la capacite de la categorie
	 * @param tarif le tarif de la categorie
	 * @param delai le delai maximal de retractation ( en jours ) pour annuler la reservation
	 * @return la Categorie modifiée
	 */
	public static Categorie modifierCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.getCategorieBDDWithNomAndHotel(id_hotel, nom);
		return fc.modifierCategorie(categorie.getId_categorie(),id_hotel, nom, capacite, tarif, delai);
	}
	
	/**
	 * supprime une categorie
	 * @param idCategorie l'id de la categorie à supprimer
	 * @return la categorie si il n'a pas été supprimé sinon null
	 */
	public static Categorie supprimerCategorie(int idCategorie){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.deleteCategorie(idCategorie);
		return fc.getCategorieWithIdCategorie(idCategorie);
	}
	
	/**
	 * recuperer la liste des categorie pour un hotel
	 * @param idHotel l'id de l hotel pour lequel on veut recuperer els categories
	 * @return une liste avec les categories
	 */
	public static ArrayList<Categorie> listerCategorieHotel(int idHotel){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		return fc.selectCategorie(idHotel);
	}
}
