package metier;

import java.util.ArrayList;

import domaine.Ligne;
import domaine.Vol;
import fabrique.FabriqueLigne;
import fabrique.FabriqueVol;

public class GestionVol {

	public static Vol ajouterVol(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueVol fv = FabriqueVol.getInstance();
		Vol Vol = fv.addVol(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree,
				minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
		return Vol;
	}
	
	public static void supprimerVol(int idVol){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(idVol);
	}
	
	public static Vol modifierVol(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(fv.getVolAvecVilleDepartEtArriveeEtJours(id_villeDepart, id_villeArrivee,jours));
		return fv.addVol(id_villeDepart, id_villeArrivee, jours, heure, min, heureDuree, minDuree, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation);
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
					case 0 : jours = "Lundi";
					case 1 : jours = "Mardi";
					case 2 : jours = "Mercredi";
					case 3 : jours = "Jeudi";
					case 4 : jours = "Vendredi";
					case 5 : jours = "Samedi";
					case 6 : jours = "Dimanche";
					}
					fv.addVol(ligne.getId_villeDepart(), ligne.getId_villeArrivee(), jours, ligne.getHeureDepart(), ligne.getMinDepart(), ligne.getHeureDuree(), ligne.getMinDuree(), ligne.getNbPassagersFirstClass(), ligne.getPriceFirstClass(), ligne.getNbPassagersSecondClass(), ligne.getPriceSecondClass(), ligne.getDelaiAnnulation());		
					}
				}
			}
		}
	
	
}
