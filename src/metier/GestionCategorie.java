package metier;

import java.util.ArrayList;

import domaine.Categorie;
import fabrique.FabriqueCategorie;

public class GestionCategorie {

	/**
	 * 
	 * @param id_hotel
	 * @param nom
	 * @param capacite
	 * @param tarif
	 * @param delai
	 * @return
	 */
	public static Categorie ajouterCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.addCategorie(id_hotel, nom, capacite, tarif, delai);
		return categorie;
	}
	
	public static Categorie modifierCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.getCategorieBDDWithNomAndHotel(id_hotel, nom);
		return fc.modifierCategorie(categorie.getId_categorie(),id_hotel, nom, capacite, tarif, delai);
	}
	
	public static Categorie supprimerCategorie(int idCategorie){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.deleteCategorie(idCategorie);
		return fc.getCategorieWithIdCategorie(idCategorie);
	}
	
	public static ArrayList<Categorie> listerCategorieHotel(int idHotel){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		return fc.selectCategorie(idHotel);
	}
}
