package metier;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import domaine.Ligne;
import domaine.Ville;
import domaine.Vol;
import fabrique.BDDConnection;
import fabrique.FabriqueLigne;
import fabrique.FabriqueReservation;
import fabrique.FabriqueVille;
import fabrique.FabriqueVol;

public class GestionVol {


	public static Vol ajouterVol(int id_villeDepart,int  id_villeArrivee,String jours,int heure,int min,int heureDuree,
			 int minDuree,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation,
			 int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		Calendar cal = Calendar.getInstance();
		Time time= cal.getTime()
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
		Calendar cal = Calendar.getInstance();
		Date date =BDDConnection.getDateDuJour();
		cal.setTime(date);
		while (cal.get(Calendar.DAY_OF_WEEK)!=2){
			cal.add(Calendar.DATE, 1);
		}
		Date lundi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date mardi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date mercredi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date jeudi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date vendredi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date samedi = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date dimanche = cal.getTime();
		cal.add(Calendar.DATE, 1);
		
		FabriqueLigne fl = FabriqueLigne.getInstance();
		FabriqueVol fv = FabriqueVol.getInstance();
		ArrayList<Ligne> lesLignes = fl.getAllLignes();
		for (Ligne ligne:lesLignes){
			String mot = ligne.getJour();
			for (int i = 0 ; i <7;i++){
				if (mot.charAt(i)=='1'){
					Date jours = null;
					switch (i){
					case 0 : jours = lundi;break;
					case 1 : jours = mardi;break;
					case 2 : jours = mercredi;break;
					case 3 : jours = jeudi;break;
					case 4 : jours = vendredi;break;
					case 5 : jours = samedi;break;
					case 6 : jours = dimanche;break;
					}
					jours.setHours(ligne.getHeureDepart());
					jours.setMinutes(ligne.getMinDepart());
					Date date2 = new Date(jours.getTime()+ (new Date((ligne.getHeureDuree()*60+ligne.getMinDuree())*60*1000).getTime()));
					fv.addVol(ligne.getId_villeDepart(), ligne.getId_villeArrivee(), jours,date2, ligne.getNbPassagersFirstClass(), ligne.getPriceFirstClass(), ligne.getNbPassagersSecondClass(), ligne.getPriceSecondClass(), ligne.getDelaiAnnulation(),ligne.getNbPassagersFirstClass(), ligne.getNbPassagersSecondClass());		
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
