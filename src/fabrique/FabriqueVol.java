/**
 * 
 */
package fabrique;

import java.util.HashMap;

import domaine.Vol;

/**
 * @author Tchioben
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
	public Vol addVol(int id_hotel,String nom,int capacite,float tarif){
		int numero_id_Vol = BDDConnection.addVol(id_hotel, nom, capacite, tarif);	
		Vol cat = new Vol(numero_id_Vol, capacite, tarif, id_hotel,nom);
		this.lesVols.put(numero_id_Vol, cat);
		return cat;
	}
	
	/**
	 * crée la Vol dans la base de données si elle n'existe pas déjà dans la fabrique, et la BDD 
	 * puis l'ajoute a la BDD et à la fabrique
	 * @param capacite
	 * @param tarif
	 * @return la Vol
	 */
	public Vol addVolDansFabrique(int idVol ,int id_hotel,String nom,int capacite,float tarif){
		Vol cat = new Vol(idVol, capacite, tarif, id_hotel,nom);
		this.lesVols.put(idVol, cat);
		return cat;
	}

	
	/**
	 * recupere la Vol en fonction du numero id.
	 */
	private  Vol getVolWithId(int id_Vol) {
		return this.lesVols.get(id_Vol);
	}
	
	/**
	 * permet de recuperer la Vol en fonction de l'id de l'hotel et du nom de la Vol
	 * @param idHotel id de l'hotel
	 * @param nom de lla Vol
	 * @return Vol sinon null si la Vol n'existe pas en base
	 */
	public Vol getVolBDDWithNomAndHotel(int id_hotel, String nom){
		int idVol = BDDConnection.getVol(id_hotel, nom);
		Vol Vol = this.getVolWithId(idVol);
		//si la Vol n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Vol== null){
			ResultSet ligneVol = BDDConnection.ligneVol(idVol);
			try{
				int capacite = ligneVol.getInt(4);
				float tarif = ligneVol.getFloat(5);
				Vol = this.addVolDansFabrique(idVol,id_hotel, nom, capacite, tarif);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Vol;
	}
	
	/**
	 * permet de recuperer la Vol en fonction de l'id de la Vol
	 * @param idHotel id de l'hotel
	 * @param nom de lla Vol
	 * @return Vol sinon null si la Vol n'existe pas en base
	 */
	public Vol getVolWithIdVol(int idVol){
		Vol Vol = this.getVolWithId(idVol);
		//si la Vol n'existe pas dans al farbqiue, il faut aller chercher les
		//infos dans la base pour le creer
		if (Vol== null){
			ResultSet ligneVol = BDDConnection.ligneVol(idVol);
			try{
				int id_hotel = ligneVol.getInt(2);
				String nom = ligneVol.getString(3);
				int capacite = ligneVol.getInt(4);
				float tarif = ligneVol.getFloat(5);
				Vol = this.addVolDansFabrique(idVol,id_hotel, nom, capacite, tarif);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return Vol;
	}
	
	public void deleteVol(int id_Vol){
		BDDConnection.deleteVol(id_Vol);
		try{
			this.lesVols.remove(id_Vol);
		}
		catch (Exception e){
		}
	}
}
