package metier;

import java.util.ArrayList;

import domaine.Ville;
import fabrique.FabriqueVille;

public class GestionVille {
	
	/**
	 * ajout d'une ville
	 * @param nom nom de la ville
	 * @param pays pays de la ville
	 * @return la ville créée
	 */
	public static Ville ajouterVille(String nom, String pays){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville = fv.addVille(nom, pays);
		return ville;
		}
	
	public static Ville supprimerVille(int id_Ville){
		FabriqueVille fv = FabriqueVille.getInstance();
		fv.deleteVille(id_Ville);
		return fv.getVilleWithIdVille(id_Ville);
	}
	
	public static void supprimerVille(String nom, String pays){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville= fv.getVilleBDDWithNomAndHotel(nom, pays);
		fv.deleteVille(ville.getId_ville());
	}
	
	public static ArrayList<Ville> consulterVille(){
		FabriqueVille fv = FabriqueVille.getInstance();
		return fv.allVilles();
	}
	
	public static ArrayList<Ville> rechercheVille(String nom){
		FabriqueVille fv = FabriqueVille.getInstance();
		return fv.rechercheVille(nom);
	}
	
}
