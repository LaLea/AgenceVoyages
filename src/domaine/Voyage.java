/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class Voyage {

	 private int id_voyage;
	 
	 
	 private int idReservation;
	 
	 private int idReservationChambre;
	 
	 private int idClient;

	/**
	 * @param id_voyage
	 * @param idReservation
	 * @param idReservationChambre
	 * @param idClient
	 */
	public Voyage(int id_voyage, int idReservation,
			int idReservationChambre, int idClient) {
		this.id_voyage = id_voyage;
		this.idReservation = idReservation;
		this.idReservationChambre = idReservationChambre;
		this.idClient = idClient;
	}

	/**
	 * @return the id_voyage
	 */
	public int getId_voyage() {
		return id_voyage;
	}

	/**
	 * @param id_voyage the id_voyage to set
	 */
	public void setId_voyage(int id_voyage) {
		this.id_voyage = id_voyage;
	}


	/**
	 * @return the idReservation
	 */
	public int getIdReservation() {
		return idReservation;
	}

	/**
	 * @param idReservation the idReservation to set
	 */
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	/**
	 * @return the idReservationChambre
	 */
	public int getIdReservationChambre() {
		return idReservationChambre;
	}

	/**
	 * @param idReservationChambre the idReservationChambre to set
	 */
	public void setIdReservationChambre(int idReservationChambre) {
		this.idReservationChambre = idReservationChambre;
	}

	/**
	 * @return the idClient
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * @param idClient the idClient to set
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idClient;
		result = prime * result + idReservation;
		result = prime * result + idReservationChambre;
		result = prime * result + id_voyage;
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
		Voyage other = (Voyage) obj;
		if (idClient != other.idClient)
			return false;
		if (idReservation != other.idReservation)
			return false;
		if (idReservationChambre != other.idReservationChambre)
			return false;
		if (id_voyage != other.id_voyage)
			return false;
		return true;
	}
	 
	 
	 
}
