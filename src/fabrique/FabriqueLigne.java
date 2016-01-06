/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Ligne;

/**
 * @author Benoit Bailleul & Lea Vanelle
 *
 */
public class FabriqueLigne {
	private static FabriqueLigne INSTANCE = null;
	
	private HashMap<Integer, Ligne> lesLignes;

	
	private FabriqueLigne(){
		this.lesLignes = new HashMap<Integer, Ligne>();
	}
	
	public static FabriqueLigne getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueLigne();
		}
		return INSTANCE;
	}
	
	/**
	 * crée la Ligne dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Ligne
	 */
	public Ligne addLigne(int id_villeDepart, int id_villeArrivee,String jours,
			int heure,int min ,int heureDuree,int minDuree,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation){
		int numero_id_Ligne = BDDConnection.addLigne(id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);	
		Ligne Ligne = new Ligne(numero_id_Ligne,id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);
		this.lesLignes.put(numero_id_Ligne, Ligne);
		return Ligne;
	}
	
	/**
	 * crée la Ligne dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Ligne
	 */
	public  Ligne addLigneDansFabrique(int idLigne ,int id_villeDepart, int id_villeArrivee,String jours,
			int heure,int min ,int heureDuree,int minDuree,int nb1ereClasse, float prix1ereClasse,
			int nb2emeClasse, float prix2emeClasse, int dureeAnnulation){
		Ligne Ligne = new Ligne(idLigne,id_villeDepart,id_villeArrivee,jours,
				heure,min ,heureDuree,minDuree,nb1ereClasse,prix1ereClasse,
				nb2emeClasse,prix2emeClasse,dureeAnnulation);
		this.lesLignes.put(idLigne, Ligne);
		return Ligne;
	}

	
	/**
	 * recupere la Ligne en fonction du numero id.
	 */
	private  Ligne getLigneWithId(int id_Ligne) {
		return this.lesLignes.get(id_Ligne);
	}
	
	/**
	 * permet de recuperer la Ligne en fonction de l'id du Ligne
	 * @param id_Ligne id du Ligne
	 * @return Ligne sinon null si la Ligne n'existe pas en base
	 */
	@SuppressWarnings("deprecation")
	public Ligne getLigneBDDWithIdLigne(int id_Ligne){
		int idLigne = BDDConnection.getLigne(id_Ligne);
		Ligne Ligne = this.getLigneWithId(idLigne);
		//si la Ligne n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Ligne== null){
			ResultSet ligneLigne = BDDConnection.selectLigne(idLigne);
			try{
				int idVilleDepart = ligneLigne.getInt(2);
				int idVilleArrivee = ligneLigne.getInt(3);
				String jours = ligneLigne.getString(4);
				Time heure = ligneLigne.getTime(5);
				Time duree = ligneLigne.getTime(6);
				int nb1ereClasse = ligneLigne.getInt(7);
				float prix1ere = ligneLigne.getFloat(8);
				int nb2emeClasse = ligneLigne.getInt(9);
				float prix2eme = ligneLigne.getFloat(10);
				int dureeAnnulation = ligneLigne.getInt(11);
				Ligne = this.addLigneDansFabrique(idLigne,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Ligne;
	}
	
	/**
	 * permet de supprimer une ligne grace à l'id de la ligne
	 * @param id_Ligne l'id de la ligne à supprimer
	 */
	public void deleteLigne(int id_Ligne){
		BDDConnection.deleteLigne(id_Ligne);
		try{
			this.lesLignes.remove(id_Ligne);
		}
		catch (Exception e){
		}
	}

	/**
	 * permet de recuperer les lignes grace à la ville de départ et la ville d'arrivée
	 * @param id_ville l'id de la ville de départ
	 * @param id_ville2 l'id de la ville d'arrivée
	 * @return la liste des lignes qui correspondent à ces 2 villes
	 */
	public ArrayList<Ligne> getLignesAvecVilleDepartEtArrivee(int id_ville,
			int id_ville2) {
		ArrayList<Ligne> lesLignes = new ArrayList<Ligne>();
		ResultSet rs = BDDConnection.lesLignesAvecVilleDepartetArrivee(id_ville,id_ville2);
		recupereLigne(rs, lesLignes);
		return lesLignes;
	}
	
	private void recupereLigne(ResultSet ligneLigne, ArrayList<Ligne> lesLignes){
		try{
			while (ligneLigne.next()){
				int idLigne = ligneLigne.getInt(1);
				int idVilleDepart = ligneLigne.getInt(2);
				int idVilleArrivee = ligneLigne.getInt(3);
				String jours = ligneLigne.getString(4);
				Time heure = ligneLigne.getTime(5);
				Time duree = ligneLigne.getTime(6);
				int nb1ereClasse = ligneLigne.getInt(7);
				float prix1ere = ligneLigne.getFloat(8);
				int nb2emeClasse = ligneLigne.getInt(9);
				float prix2eme = ligneLigne.getFloat(10);
				int dureeAnnulation = ligneLigne.getInt(11);
				Ligne Ligne = addLigneDansFabrique(idLigne,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				lesLignes.add(Ligne);
			}
		}
		catch (Exception e){		
		}
	}
	
	/**
	 * permet de recuperer l'id d'une ligne grace à la ville de depart et celle d'arrivee
	 * @param idDepart l'id de la ville de depart
	 * @param idArrivee l'id de la ville d'arrivee
	 * @return l'id de la ligne qui correspond à ces villes
	 */
	public int getLigneAvecVilleDepartEtArrivee(int idDepart, int idArrivee){
		ResultSet ligneLigne = BDDConnection.getLigne(idDepart,idArrivee);
		try{
			if (ligneLigne.next()){
				int idLigne = ligneLigne.getInt(1);
				int idVilleDepart = ligneLigne.getInt(2);
				int idVilleArrivee = ligneLigne.getInt(3);
				String jours = ligneLigne.getString(4);
				Time heure = ligneLigne.getTime(5);
				Time duree = ligneLigne.getTime(6);
				int nb1ereClasse = ligneLigne.getInt(7);
				float prix1ere = ligneLigne.getFloat(8);
				int nb2emeClasse = ligneLigne.getInt(9);
				float prix2eme = ligneLigne.getFloat(10);
				int dureeAnnulation = ligneLigne.getInt(11);
				Ligne ligne = this.addLigneDansFabrique(idLigne,idVilleDepart,idVilleArrivee,jours,heure.getHours(),heure.getMinutes(),
						duree.getHours(),duree.getMinutes(),nb1ereClasse,prix1ere,nb2emeClasse,prix2eme,dureeAnnulation);
				return ligne.getId_Ligne();
			}
		}
		catch (Exception e){
			return 0;
		}
		return 0;
	}
	
	/**
	 * permet de recuperer toutes les lignes
	 * @return la liste des lignes
	 */
	public ArrayList<Ligne> getAllLignes() {
		ArrayList<Ligne> lesLignes = new ArrayList<Ligne>();
		ResultSet rs = BDDConnection.selectLigne();
		recupereLigne(rs, lesLignes);
		return lesLignes;
	}
	
}
