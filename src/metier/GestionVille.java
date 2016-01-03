package metier;

import java.util.ArrayList;

import domaine.Ville;
import fabrique.FabriqueVille;

public class GestionVille {
	
	public static Ville ajouterVille(String nom, String pays){
		FabriqueVille fv = FabriqueVille.getInstance();
		Ville ville = fv.addVille(nom, pays);
		return ville;
		}
	
	public static void supprimerVille(int id_Ville){
		FabriqueVille fv = FabriqueVille.getInstance();
		fv.deleteVille(id_Ville);
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
}