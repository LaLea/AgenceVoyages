/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class Client {
	private int id_client;
	
	private String nom;
	
	private String prenom;
	
	private String dateNaissance;
	
	private int id_villeOrigine;

	
	
	
	/** a new client
	 * @param id_client is unique
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param id_villeOrigine refer to the class Ville
	 */
	public Client(int id_client, String nom, String prenom,
			String dateNaissance, int id_villeOrigine) {
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.id_villeOrigine = id_villeOrigine;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateNaissance == null) ? 0 : dateNaissance.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
		Client other = (Client) obj;
		if (dateNaissance == null) {
			if (other.dateNaissance != null)
				return false;
		} else if (!dateNaissance.equals(other.dateNaissance))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}

	/**
	 * @return the id_client
	 */
	public int getId_client() {
		return id_client;
	}

	/**
	 * @param id_client the id_client to set
	 */
	public void setId_client(int id_client) {
		this.id_client = id_client;
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
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return dateNaissance;
	}

	/**
	 * @param dateNaissance the dateNaissance to set
	 */
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	/**
	 * @return the id_villeOrigine
	 */
	public int getId_villeOrigine() {
		return id_villeOrigine;
	}

	/**
	 * @param id_villeOrigine the id_villeOrigine to set
	 */
	public void setId_villeOrigine(int id_villeOrigine) {
		this.id_villeOrigine = id_villeOrigine;
	}

	
	

	
}
