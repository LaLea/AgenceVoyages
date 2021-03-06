package metier;

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

	/**
	 * ajout d'un vol
	 * @param id_villeDepart l'id de la ville de depart
	 * @param id_villeArrivee l'id de la ville d'arrivee
	 * @param depart date de depart
	 * @param arrivee date d'arrivee
	 * @param nb1ereClasse nombre place 1 ere classe
	 * @param prix1ereClasse prix d'une place en 1ere classe
	 * @param nb2emeClasse nombre place 2 eme classe
	 * @param prix2emeClasse prix d'une place en 2 eme classe
	 * @param dureeAnnulation nombre de jours aavant à laquelle on peut annuler le vol
	 * @param PlaceRestante1ereClasse places restantes dans la 1 ere classe
	 * @param PlaceRestante2emeClasse places restantes dans la 2 eme classe
	 * @return
	 */
	public static Vol ajouterVol(int id_villeDepart,int  id_villeArrivee,Date depart,Date arrivee,
			int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation,
			 int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		FabriqueVol fv = FabriqueVol.getInstance();

		Vol Vol = fv.addVol(id_villeDepart, id_villeArrivee, depart,arrivee, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation, PlaceRestante1ereClasse, PlaceRestante2emeClasse);
		return Vol;
	}
	
	/**
	 * supprimer un vol
	 * @param idVol l'id du vol
	 * @return le vol si n'a pas ete supp
	 */
	public static Vol supprimerVol(int idVol){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(idVol);
		return fv.getVolBDDWithIdVol(idVol);
	}
	
	public static Vol modifierVol(int id_villeDepart,int  id_villeArrivee,Date depart, Date arrivee,int nb1ereClasse,float prix1ereClasse,int nb2emeClasse,float prix2emeClasse,int dureeAnnulation,
			 int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		FabriqueVol fv = FabriqueVol.getInstance();
		fv.deleteVol(fv.getVolAvecVilleDepartEtArriveeEtJours(id_villeDepart, id_villeArrivee,depart));
		return fv.addVol(id_villeDepart, id_villeArrivee, depart,arrivee, nb1ereClasse, prix1ereClasse, nb2emeClasse, prix2emeClasse, dureeAnnulation, PlaceRestante1ereClasse,PlaceRestante2emeClasse);
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
