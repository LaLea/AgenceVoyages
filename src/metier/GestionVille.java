package metier;

import java.util.ArrayList;

import domaine.Ville;
import fabrique.FabriqueVille;

public class GestionVille {
	
	/**
	 * ajout d'une ville
	 * @param nom nom de la ville
	 * @param pays pays de la ville
	 * @return la ville cr��e
	 */
	public static Ville ajouterVille(String nom, String pays){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville = fv.addVille(nom, pays);
		return ville;
		}
	
	/**
	 * supprime une ville
	 * @param id_Ville l'id de la ville à supprimer
	 * @return null si la ville a ete supprimee sinon la ville
	 */
	public static Ville supprimerVille(int id_Ville){
		FabriqueVille fv = FabriqueVille.getInstance();
		fv.deleteVille(id_Ville);
		return fv.getVilleWithIdVille(id_Ville);
	}
	
	/**
	 * supprime la ville grace au nom et prenom
	 * @param nom le nom de la ville que l'on veut supprimer
	 * @param pays le pays de la ville que l'on veut supprimer
	 */
	public static Ville supprimerVille(String nom, String pays){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville= fv.getVilleBDDWithNomAndHotel(nom, pays);
		fv.deleteVille(ville.getId_ville());
		return fv.getVilleWithIdVille(ville.getId_ville());
	}
	
	/**
	 * permet d'afficher toutes les villes
	 * @return la liste des villes
	 */
	public static ArrayList<Ville> consulterVille(){
		FabriqueVille fv = FabriqueVille.getInstance();
		return fv.allVilles();
	}
	
	/**
	 * recherche une ville grace à son nom
	 * @param nom le nom de la ville
	 * @return la liste des villes
	 */
	public static ArrayList<Ville> rechercheVille(String nom){
		FabriqueVille fv = FabriqueVille.getInstance();
		return fv.rechercheVille(nom);
	}
	
}
