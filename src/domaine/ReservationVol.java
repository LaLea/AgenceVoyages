/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class ReservationVol {

		private int id_ReservationVol;
		
		private int idVol;
	
		private Classe classe;
		
		private String date;

		/**
		 * @param id_ReservationVol
		 * @param idVol
		 * @param classe fst or snd
		 * @param date
		 */
		public ReservationVol(int id_ReservationVol, int idVol, Classe classe,
				String date) {
			this.id_ReservationVol = id_ReservationVol;
			this.idVol = idVol;
			this.classe = classe;
			this.date = date;
		}

		/**
		 * @return the id_ReservationVol
		 */
		public int getId_ReservationVol() {
			return id_ReservationVol;
		}

		/**
		 * @param id_ReservationVol the id_ReservationVol to set
		 */
		public void setId_ReservationVol(int id_ReservationVol) {
			this.id_ReservationVol = id_ReservationVol;
		}

		/**
		 * @return the idVol
		 */
		public int getIdVol() {
			return idVol;
		}

		/**
		 * @param idVol the idVol to set
		 */
		public void setIdVol(int idVol) {
			this.idVol = idVol;
		}

		/**
		 * @return the classe
		 */
		public Classe getClasse() {
			return classe;
		}

		/**
		 * @param classe the classe to set
		 */
		public void setClasse(Classe classe) {
			this.classe = classe;
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
			result = prime * result + idVol;
			result = prime * result + id_ReservationVol;
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
			ReservationVol other = (ReservationVol) obj;
			if (idVol != other.idVol)
				return false;
			if (id_ReservationVol != other.id_ReservationVol)
				return false;
			return true;
		}
		
		
		
}
