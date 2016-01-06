/**
 * 
 */
package fabrique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domaine.Client;
import domaine.Ville;

/**
 * @author Tchioben
 *
 */
public class FabriqueVille {
	
	
	private static FabriqueVille INSTANCE = null;
	
	private HashMap<Integer, Ville> lesVilles;

	
	private FabriqueVille(){
		this.lesVilles = new HashMap<Integer, Ville>();
	}
	
	public static FabriqueVille getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueVille();
		}
		return INSTANCE;
	}
	
	
	
	
	
	
	/**
	 * crée la Ville dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Ville
	 */
	public Ville addVille(String nom,String pays){
		int numero_id_Ville = BDDConnection.addVille(nom,pays);	
		Ville cat = new Ville(numero_id_Ville,nom,pays);
		this.lesVilles.put(numero_id_Ville, cat);
		return cat;
	}
	
	
	
	/**
	 * crée la Ville dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Ville
	 */
	public Ville addVilleDansFabrique(int idVille ,String nom,String pays){
		Ville cat = new Ville(idVille,nom,pays);
		this.lesVilles.put(idVille, cat);
		return cat;
	}

	
	/**
	 * recupere la Ville en fonction du numero id.
	 */
	private  Ville getVilleWithId(int id_Ville) {
		return this.lesVilles.get(id_Ville);
	}
	
	/**
	 * permet de recuperer la Ville en fonction de l'id de l'hotel et du nom de la Ville
	 * @param idHotel id de l'hotel
	 * @param nom de lla Ville
	 * @return Ville sinon null si la Ville n'existe pas en base
	 */
	public Ville getVilleBDDWithNomAndHotel(String nom, String pays){
		int idVille = BDDConnection.getVille(nom,pays);
		Ville Ville = this.getVilleWithId(idVille);
		//si la Ville n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Ville== null){
				Ville = this.addVilleDansFabrique(idVille,nom,pays);
		}// fin IF
		return Ville;
	}
	
	
	
	/**
	 * permet de recuperer la Ville en fonction du nom de la Ville
	 * @param nom de lla Ville
	 * @return Ville sinon null si la Ville n'existe pas en base
	 */
	public Ville getVilleBDDWithNom(String nom){
		//si la Ville n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		Ville ville2 = null;
		ResultSet ville = BDDConnection.getVille(nom);;
			try{
				int idVille = ville.getInt(1);
				String pays = ville.getString(3);
				ville2= this.addVilleDansFabrique(idVille,nom,pays);
			}
			catch (Exception e) {
				return null;
			}	
		return ville2;
	}
	
	/**
	 * permet de recuperer la Ville en fonction de l'id de la Ville
	 * @param idHotel id de l'hotel
	 * @param nom de lla Ville
	 * @return Ville sinon null si la Ville n'existe pas en base
	 */
	public Ville getVilleWithIdVille(int idVille){
		Ville Ville = this.getVilleWithId(idVille);
		//si la Ville n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Ville== null){
			ResultSet ligneVille = BDDConnection.ligneVille(idVille);
			try{
				String nom = ligneVille.getString("Nom");
				String pays = ligneVille.getString("Pays");
				Ville = this.addVilleDansFabrique(idVille,nom,pays);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Ville;
	}
	
	/**
	 * permet de supprimer une ville grace à l'id de la ville
	 * @param id_Ville l'id de la ville à supprimer
	 */
	public void deleteVille(int id_Ville){
		BDDConnection.deleteVille(id_Ville);
		try{
			this.lesVilles.remove(id_Ville);
		}
		catch (Exception e){
		}
	}
	
	/**
	 * permet de recuperer toute les villes
	 * @return une liste avec les villes
	 */
	public ArrayList<Ville> allVilles(){
		ArrayList<Ville> lesVilles = new ArrayList<Ville>();
		ResultSet rs = BDDConnection.laTable("Ville");
		try{
			while (rs.next()){
				int n = rs.getInt(1);
				String nom1= rs.getString(2);
				String pays =  rs.getString(3);
				Ville ville= this.addVilleDansFabrique(n,nom1,pays);
				lesVilles.add(ville);	
			}
		}
		catch (Exception e){
			e.printStackTrace();;
		}
		return lesVilles;
	}


}
