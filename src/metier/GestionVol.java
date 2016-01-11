package metier;

import java.util.ArrayList;

import domaine.Ligne;
import domaine.Ville;
import domaine.Vol;
import fabrique.FabriqueLigne;
import fabrique.FabriqueReservation;
import fabrique.FabriqueVille;
import fabrique.FabriqueVol;

public class GestionVol {

	public static Vol ajouterVol(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation,
			 int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		FabriqueVol fv = FabriqueVol.getInstance();
		Vol Vol = fv.addVol(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree,
				minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation, PlaceRestante1ereClasse, PlaceRestante2emeClasse);
		return Vol;
	}
	
	public static void supprimerVol(int idVol){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(idVol);
	}
	
	public static Vol modifierVol(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation,
			 int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(fv.getVolAvecVilleDepartEtArriveeEtJours(id_villeDepart, id_villeArrivee,jours));
		return fv.addVol(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree, minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation, PlaceRestante1ereClasse,PlaceRestante2emeClasse);
	}
	
	public static void genererLesVolsDeLaSemaine(){
		FabriqueLigne fl = FabriqueLigne.getInstance();
		FabriqueVol fv = FabriqueVol.getInstance();
		ArrayList<Ligne> lesLignes = fl.getAllLignes();
		for (Ligne ligne:lesLignes){
			String mot = ligne.getJour();
			for (int i = 0 ; i <7;i++){
				if (mot.charAt(i)=='1'){
					String jours ="";
					switch (i){
					case 0 : jours = "Lundi";break;
					case 1 : jours = "Mardi";break;
					case 2 : jours = "Mercredi";break;
					case 3 : jours = "Jeudi";break;
					case 4 : jours = "Vendredi";break;
					case 5 : jours = "Samedi";break;
					case 6 : jours = "Dimanche";break;
					}
					fv.addVol(ligne.getId_villeDepart(), ligne.getId_villeArrivee(), jours, ligne.getHeureDepart(), ligne.getMinDepart(), ligne.getHeureDuree(), ligne.getMinDuree(), ligne.getNbPassagersFirstClass(), ligne.getPriceFirstClass(), ligne.getNbPassagersSecondClass(), ligne.getPriceSecondClass(), ligne.getDelaiAnnulation(),ligne.getNbPassagersFirstClass(), ligne.getNbPassagersSecondClass());		
					}
				}
			}
		}
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParPrix(String depart,String arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		FabriqueVille fville = FabriqueVille.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		Ville dep = fville.getVilleBDDWithNom(depart);
		Ville arr = fville.getVilleBDDWithNom(arrivee);
		lesVols = fvol.getVolsAvecVilleDepartEtArriveeParPrix(dep.getId_ville(),arr.getId_ville());
		return lesVols;
	}
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParPrix(int depart,int arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		lesVols = fvol.getVolsAvecVilleDepartEtArriveeParPrix(depart,arrivee);
		return lesVols;
	}
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParTemps(String depart,String arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		FabriqueVille fville = FabriqueVille.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		Ville dep = fville.getVilleBDDWithNom(depart);
		Ville arr = fville.getVilleBDDWithNom(arrivee);
		lesVols = fvol.getVolsAvecVilleDepartEtArriveeParPrix(dep.getId_ville(),arr.getId_ville());
		return lesVols;
	}
	
	public static ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParTemps(int depart,int arrivee){
		FabriqueVol fvol = FabriqueVol.getInstance();
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		lesVols = fvol.getVolsAvecVilleDepartEtArriveeParPrix(depart,arrivee);
		return lesVols;
	}
	
	public static Vol getVolWithIdReservation(int idReservation){
		FabriqueVol fvol = FabriqueVol.getInstance();
		FabriqueReservation fres = FabriqueReservation.getInstance();
		int idVol = fres.getReservation(idReservation).getIdVol();
		Vol vol = fvol.getVolBDDWithIdVol(idVol);
		return vol;
	}
	

}
