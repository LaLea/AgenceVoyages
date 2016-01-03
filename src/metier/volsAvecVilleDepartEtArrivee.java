package metier;

import java.util.ArrayList;

import domaine.Ville;
import domaine.Vol;
import fabrique.FabriqueVille;
import fabrique.FabriqueVol;

public class volsAvecVilleDepartEtArrivee {
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArrivee(String depart,String arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		FabriqueVille fville = FabriqueVille.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		Ville dep = fville.getVilleBDDWithNom(depart);
		Ville arr = fville.getVilleBDDWithNom(arrivee);
		lesVols = fvol.getVolsAvecVilleDepartEtArrivee(dep.getId_ville(),arr.getId_ville());
		return lesVols;
	}
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArrivee(int depart,int arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		lesVols = fvol.getVolsAvecVilleDepartEtArrivee(depart,arrivee);
		return lesVols;
	}
	
	
	
}
