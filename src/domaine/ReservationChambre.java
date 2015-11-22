/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class ReservationChambre {
	
	private int id_reservation;
	
	private int idChambre;
	
	private String date;

	/**
	 * @param id_reservation
	 * @param idChambre
	 * @param date
	 */
	public ReservationChambre(int id_reservation, int idChambre, String date) {
		this.id_reservation = id_reservation;
		this.idChambre = idChambre;
		this.date = date;
	}

	/**
	 * @return the id_reservation
	 */
	public int getId_reservation() {
		return id_reservation;
	}

	/**
	 * @param id_reservation the id_reservation to set
	 */
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}

	/**
	 * @return the idChambre
	 */
	public int getIdChambre() {
		return idChambre;
	}

	/**
	 * @param idChambre the idChambre to set
	 */
	public void setIdChambre(int idChambre) {
		this.idChambre = idChambre;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idChambre;
		result = prime * result + id_reservation;
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
		ReservationChambre other = (ReservationChambre) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idChambre != other.idChambre)
			return false;
		if (id_reservation != other.id_reservation)
			return false;
		return true;
	}
	
	
}
