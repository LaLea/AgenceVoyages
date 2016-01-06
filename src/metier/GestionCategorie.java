package metier;

import java.util.ArrayList;

import domaine.Categorie;
import fabrique.FabriqueCategorie;

public class GestionCategorie {

	public static Categorie ajouterCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.addCategorie(id_hotel, nom, capacite, tarif, delai);
		return categorie;
	}
	
	public static Categorie modifierCategorie(int id_hotel,String nom, int capacite, float tarif, int delai){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		Categorie categorie = fc.getCategorieBDDWithNomAndHotel(id_hotel, nom);
		fc.deleteCategorie(categorie.getId_categorie());
		categorie = fc.addCategorie(id_hotel, nom, capacite, tarif, delai);
		return categorie;
	}
	
	public static void supprimerCategorie(int idCategorie){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		fc.deleteCategorie(idCategorie);
	}
	
	public static ArrayList<Categorie> listerCategorieHotel(int idHotel){
		FabriqueCategorie fc = FabriqueCategorie.getInstance();
		return fc.selectCategorie(idHotel);
	}
}
