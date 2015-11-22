package domaine;
/**
 * 
 */

/**
 * @author Léa Vanelle & Benoît Bailleul
 *
 */
public class Ville {

	private int id_ville;
	
	private String nom;
	
	private String pays;

	/** create a city, the id_ville is unique
	 * @param id_ville
	 * @param nom
	 * @param pays
	 */
	public Ville(int id_ville, String nom, String pays) {
		super();
		this.id_ville = id_ville;
		this.nom = nom;
		this.pays = pays;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
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
		Ville other = (Ville) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		return true;
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
	 * @return the pays
	 */
	public String getPays() {
		return pays;
	}


	/**
	 * @param pays the pays to set
	 */
	public void setPays(String pays) {
		this.pays = pays;
	}
	
	
	
}
