/**
 * 
 */
package domaine;

/**
 * @author Lea Vannelle & Benoit Bailleul
 *
 */
public class Categorie {

	private int id_categorie;
	
	private int capacite;
	
	private float tarif;
	
	private int id_ville;
	
	private String nom;

	/**
	 * @param id_categorie
	 * @param capacite
	 * @param tarif
	 */
	public Categorie(int id_categorie, int capacite, float tarif, int id_ville, String nom) {
		this.id_categorie = id_categorie;
		this.capacite = capacite;
		this.tarif = tarif;
		this.id_ville = id_ville;
		this.nom = nom;
	}

	/**
	 * @return the id_categorie
	 */
	public int getId_categorie() {
		return id_categorie;
	}

	/**
	 * @param id_categorie the id_categorie to set
	 */
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	/**
	 * @return the capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * @param capacite the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/**
	 * @return the tarif
	 */
	public float getTarif() {
		return tarif;
	}

	/**
	 * @param tarif the tarif to set
	 */
	public void setTarif(float tarif) {
		this.tarif = tarif;
	}


	public int getId_ville() {
		return id_ville;
	}

	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + capacite;
		result = prime * result + id_categorie;
		result = prime * result + Float.floatToIntBits(tarif);
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
		Categorie other = (Categorie) obj;
		if (capacite != other.capacite)
			return false;
		if (id_categorie != other.id_categorie)
			return false;
		if (Float.floatToIntBits(tarif) != Float.floatToIntBits(other.tarif))
			return false;
		return true;
	}
	
	
	
}
