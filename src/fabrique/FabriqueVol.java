/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
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
	public Vol addVol(int id_villeDepart, int id_villeArrivee,String jours,
			int heure,int min ,int heureDuree,int minDuree,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation){
		int numero_id_Vol = BDDConnection.addVol(id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);	
		Vol vol = new Vol(numero_id_Vol,id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);
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
	public Vol addVolDansFabrique(int idVol ,int id_villeDepart, int id_villeArrivee,String jours,
			int heure,int min ,int heureDuree,int minDuree,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation){
		Vol vol = new Vol(idVol,id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);
		this.lesVols.put(idVol, vol);
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
	public Vol getVolBDDWithIdVol(int id_vol){
		int idVol = BDDConnection.getVol(id_vol);
		Vol vol = this.getVolWithId(idVol);
		//si la Vol n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (vol== null){
			ResultSet ligneVol = BDDConnection.selectVol(idVol);
			try{
				int idVilleDepart = ligneVol.getInt(2);
				int idVilleArrivee = ligneVol.getInt(3);
				String jours = ligneVol.getString(4);
				Time heure = ligneVol.getTime(5);
				Time duree = ligneVol.getTime(6);
				int nb1ereClasse = ligneVol.getInt(7);
				float prix1ere = ligneVol.getFloat(8);
				int nb2emeClasse = ligneVol.getInt(9);
				float prix2eme = ligneVol.getFloat(10);
				int dureeAnnulation = ligneVol.getInt(11);
				vol = this.addVolDansFabrique(idVol,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
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
	public ArrayList<Vol> getVolsAvecVilleDepartEtArrivee(int id_ville,
			int id_ville2) {
		ArrayList<Vol> lesVols = new ArrayList<Vol>();
		ResultSet rs = BDDConnection.lesVolsAvecVilleDepartetArrivee(id_ville,id_ville2);
		recupereVol(rs, lesVols);
		return lesVols;
	}
	

	private void recupereVol(ResultSet ligneVol, ArrayList<Vol> lesVols){
		try{
			while (ligneVol.next()){
				int idVol = ligneVol.getInt(1);
				int idVilleDepart = ligneVol.getInt(2);
				int idVilleArrivee = ligneVol.getInt(3);
				String jours = ligneVol.getString(4);
				Time heure = ligneVol.getTime(5);
				Time duree = ligneVol.getTime(6);
				int nb1ereClasse = ligneVol.getInt(7);
				float prix1ere = ligneVol.getFloat(8);
				int nb2emeClasse = ligneVol.getInt(9);
				float prix2eme = ligneVol.getFloat(10);
				int dureeAnnulation = ligneVol.getInt(11);
				Vol vol = this.addVolDansFabrique(idVol,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
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
	public int getVolAvecVilleDepartEtArriveeEtJours(int idDepart, int idArrivee,String jour){
		ResultSet VolVol = BDDConnection.getVol(idDepart,idArrivee,jour);
		try{
			if (VolVol.next()){
				int idVol = VolVol.getInt(1);
				int idVilleDepart = VolVol.getInt(2);
				int idVilleArrivee = VolVol.getInt(3);
				String jours = VolVol.getString(4);
				Time heure = VolVol.getTime(5);
				Time duree = VolVol.getTime(6);
				int nb1ereClasse = VolVol.getInt(7);
				float prix1ere = VolVol.getFloat(8);
				int nb2emeClasse = VolVol.getInt(9);
				float prix2eme = VolVol.getFloat(10);
				int dureeAnnulation = VolVol.getInt(11);
				Vol vol = this.addVolDansFabrique(idVol,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				return vol.getId_vol();
			}
		}
		catch (Exception e){
			return 0;
		}
		return 0;
	}
	
	
}
