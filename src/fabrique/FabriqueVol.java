/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import domaine.Vol;

/**
 * @author Benoit Bailleul & Lea Vanelle
 *
 */
public class FabriqueVol {
	private static FabriqueVol INSTANCE = null;
	
	private HashMap<Integer, Vol> lesVols;

	
	private FabriqueVol(){
		this.lesVols = new HashMap<Integer, Vol>();
	}
	
	public static FabriqueVol getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueVol();
		}
		return INSTANCE;
	}
	
	/**
	 * crée la Vol dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Vol
	 */
	public Vol addVol(int id_villeDepart, int id_villeArrivee,Date depart,
			Date arrivee,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation,
			int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		int numero_id_Vol = BDDConnection.addVol(id_villeDepart,id_villeArrivee,
				depart, arrivee,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation,PlaceRestante1ereClasse,PlaceRestante2emeClasse);	
		ArrayList<Integer> id = new ArrayList<Integer>();
		id.add(numero_id_Vol);
		Vol vol = new Vol(id,id_villeDepart,id_villeArrivee,depart,arrivee,nb1ereClasse,
				nb2emeClasse,prix1ereClasse,prix2emeClasse,dureeAnnulation);
		this.lesVols.put(numero_id_Vol, vol);
		return vol;
	}
	
	/**
	 * crée la Vol dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Vol
	 */
	public Vol addVolDansFabrique(ArrayList<Integer> idsVol ,int id_villeDepart, int id_villeArrivee,Date depart, Date arrivee
			,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation){
		Vol vol = new Vol(idsVol,id_villeDepart,id_villeArrivee,depart,arrivee,nb1ereClasse,
				nb2emeClasse,prix1ereClasse,prix2emeClasse,dureeAnnulation);
		return vol;
	}

	
	/**
	 * recupere la Vol en fonction du numero id.
	 */
	private  Vol getVolWithId(int id_Vol) {
		return this.lesVols.get(id_Vol);
	}
	
	/**
	 * permet de recuperer la Vol en fonction de l'id du Vol
	 * @param id_vol id du vol
	 * @return Vol sinon null si la Vol n'existe pas en base
	 */
	@SuppressWarnings("deprecation")
	public Vol getVolBDDWithIdVol(int idVol){
		Vol vol = this.getVolWithId(idVol);
		//si la Vol n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (vol== null){
			ResultSet leVol = BDDConnection.selectVol(idVol);
			try{
				int idVilleDepart = leVol.getInt(2);
				int idVilleArrivee = leVol.getInt(3);
				Date depart = leVol.getDate(4);
				Date arrivee = leVol.getDate(5);
				int nb1ereClasse = leVol.getInt(6);
				float prix1ere = leVol.getFloat(7);
				int nb2emeClasse = leVol.getInt(8);
				float prix2eme = leVol.getFloat(9);
				int dureeAnnulation = leVol.getInt(10);
				ArrayList<Integer> lesVol = new ArrayList<Integer>();
				lesVol.add(idVol);
				vol = this.addVolDansFabrique(lesVol,idVilleDepart,idVilleArrivee,depart,arrivee,nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				return vol;
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return vol;
	}
	
	/**
	 * permet de supprimer un vol
	 * @param id_Vol l'id du vol à supprimer
	 */
	public void deleteVol(int id_Vol){
		BDDConnection.deleteVol(id_Vol);
		try{
			this.lesVols.remove(id_Vol);
		}
		catch (Exception e){
		}
	}

	/**
	 * permet de recuperer les vols grace aux villes de depart et d'arrivee
	 * @param id_ville l'id de la ville de depart
	 * @param id_ville2 l'id de la ville d'arrivee
	 * @return la liste des vols correspondant à ces parametres
	 */
	public ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParPrix(int id_ville,
			int id_ville2) {
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		ResultSet rs = BDDConnection.lesVolsAvecVilleDepartetArriveeParPrix(id_ville,id_ville2);
		recupereVol(rs, lesVols);
		return lesVols;
	}
	
	/**
	 * permet de recuperer les vols grace aux villes de depart et d'arrivee
	 * @param id_ville l'id de la ville de depart
	 * @param id_ville2 l'id de la ville d'arrivee
	 * @return la liste des vols correspondant à ces parametres
	 */
	public ArrayList<Vol> getVolsAvecVilleDepartEtArriveeParDuree(int id_ville,
			int id_ville2) {
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		ResultSet rs = BDDConnection.lesVolsAvecVilleDepartetArriveeParTemps(id_ville,id_ville2);
		recupereVol(rs, lesVols);
		return lesVols;
	}
	

	private void recupereVol(ResultSet leVol, ArrayList<Vol> lesVols){
		try{
			while (leVol.next()){
				int idVol = leVol.getInt(1);
				int idVilleDepart = leVol.getInt(2);
				int idVilleArrivee = leVol.getInt(3);
				Date depart = leVol.getDate(4);
				Date arrivee = leVol.getDate(5);
				int nb1ereClasse = leVol.getInt(6);
				float prix1ere = leVol.getFloat(7);
				int nb2emeClasse = leVol.getInt(8);
				float prix2eme = leVol.getFloat(9);
				int dureeAnnulation = leVol.getInt(10);
				ArrayList<Integer> lesVol = new ArrayList<Integer>();
				lesVol.add(idVol);
				Vol vol = this.addVolDansFabrique(lesVol,idVilleDepart,idVilleArrivee,depart,arrivee,nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				lesVols.add(vol);
			}
		}
		catch (Exception e){
			
		}
	}
	
	/**
	 * permet de recuperer les vols
	 * @param idDepart l'id de la ville de depart
	 * @param idArrivee l'id de la ville d'arrivee
	 * @param jour le jours du vol
	 * @return l'id du vol qui part de iDdepart et à destination de idArrivee ce jours là
	 */
	public int getVolAvecVilleDepartEtArriveeEtJours(int idDepart, int idArrivee,Date jour){
		ResultSet leVol = BDDConnection.getVol(idDepart,idArrivee,jour);
		try{
			if (leVol.next()){
				int idVol = leVol.getInt(1);
				int idVilleDepart = leVol.getInt(2);
				int idVilleArrivee = leVol.getInt(3);
				Date depart = leVol.getDate(4);
				Date arrivee = leVol.getDate(5);
				int nb1ereClasse = leVol.getInt(6);
				float prix1ere = leVol.getFloat(7);
				int nb2emeClasse = leVol.getInt(8);
				float prix2eme = leVol.getFloat(9);
				int dureeAnnulation = leVol.getInt(10);
				ArrayList<Integer> lesVol = new ArrayList<Integer>();
				lesVol.add(idVol);
				Vol vol = this.addVolDansFabrique(lesVol,idVilleDepart,idVilleArrivee,depart,arrivee,nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				return lesVol.get(1);
			}
		}
		catch (Exception e){
			return 0;
		}
		return 0;
	}
	
	
}
