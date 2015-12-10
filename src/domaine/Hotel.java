/**
 * 
 */
package domaine;

import java.util.ArrayList;

/**
 * @author Tchioben
 *
 */
public class Hotel {

	private int id_hotel;
	
	private String nom;
	
	private int id_ville;
	
	private ArrayList<Categorie> lesCategories ;

	/** create a new hotel 
	 * @param id_hotel
	 * @param nom
	 * @param id_ville
	 */
	public Hotel(int id_hotel, String nom, int id_ville) {
		this.id_hotel = id_hotel;
		this.nom = nom;
		this.id_ville = id_ville;
		this.setLesCategories(new ArrayList<Categorie>());
	}

	/**
	 * @return the id_hotel
	 */
	public int getId_hotel() {
		return id_hotel;
	}

	/**
	 * @param id_hotel the id_hotel to set
	 */
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the id_ville
	 */
	public int getId_ville() {
		return id_ville;
	}

	/**
	 * @param id_ville the id_ville to set
	 */
	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_ville;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (id_ville != other.id_ville)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

	/**
	 * @return the lesCategories
	 */
	public ArrayList<Categorie> getLesCategories() {
		return lesCategories;
	}

	/**
	 * @param lesCategories the lesCategories to set
	 */
	public void setLesCategories(ArrayList<Categorie> lesCategories) {
		this.lesCategories = lesCategories;
	}
	
	
	
}
