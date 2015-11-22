/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class Chambre {

	private int id_chambre;
	private Categorie categorie;
	private int idHotel;
	/**
	 * @param id_chambre the unique id to the chambre
	 * @param categorie the categorie, categorie object
	 * @param idHotel the id about the tower
	 */
	public Chambre(int id_chambre, Categorie categorie, int idHotel) {
		this.id_chambre = id_chambre;
		this.categorie = categorie;
		this.idHotel = idHotel;
	}
	/**
	 * @return the id_chambre
	 */
	public int getId_chambre() {
		return id_chambre;
	}
	/**
	 * @param id_chambre the id_chambre to set
	 */
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	/**
	 * @return the categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	/**
	 * @return the idHotel
	 */
	public int getIdHotel() {
		return idHotel;
	}
	/**
	 * @param idHotel the idHotel to set
	 */
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categorie == null) ? 0 : categorie.hashCode());
		result = prime * result + idHotel;
		result = prime * result + id_chambre;
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
		Chambre other = (Chambre) obj;
		if (categorie == null) {
			if (other.categorie != null)
				return false;
		} else if (!categorie.equals(other.categorie))
			return false;
		if (idHotel != other.idHotel)
			return false;
		if (id_chambre != other.id_chambre)
			return false;
		return true;
	}
	
	
}
