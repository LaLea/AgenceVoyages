/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class Vol {

		private int id_vol;
		
		private int id_villeDepart;
		
		private int id_villeArrivee;
		
		private String jour;
		
		private String heure;
		
		private String dureeVol;
		
		private int nbPassagersFirstClass;
		
		private int nbPassagersSecondClass;
		
		private float priceFirstClass;
		
		private float priceSecondClass;
		
		private int delaiAnnulation;

		/**
		 * @param id_vol
		 * @param id_villeDepart
		 * @param id_villeArrivee
		 * @param jour
		 * @param heure
		 * @param dureeVol
		 * @param nbPassagersFirstClass
		 * @param nbPassagersSecondClass
		 * @param priceFirstClass
		 * @param priceSecondClass
		 * @param delaiAnnulation
		 */
		public Vol(int id_vol, int id_villeDepart, int id_villeArrivee,
				String jour, String heure, String dureeVol,
				int nbPassagersFirstClass, int nbPassagersSecondClass,
				float priceFirstClass, float priceSecondClass,
				int delaiAnnulation) {
			this.id_vol = id_vol;
			this.id_villeDepart = id_villeDepart;
			this.id_villeArrivee = id_villeArrivee;
			this.jour = jour;
			this.heure = heure;
			this.dureeVol = dureeVol;
			this.nbPassagersFirstClass = nbPassagersFirstClass;
			this.nbPassagersSecondClass = nbPassagersSecondClass;
			this.priceFirstClass = priceFirstClass;
			this.priceSecondClass = priceSecondClass;
			this.delaiAnnulation = delaiAnnulation;
		}

		/**
		 * @return the id_vol
		 */
		public int getId_vol() {
			return id_vol;
		}

		/**
		 * @param id_vol the id_vol to set
		 */
		public void setId_vol(int id_vol) {
			this.id_vol = id_vol;
		}

		/**
		 * @return the id_villeDepart
		 */
		public int getId_villeDepart() {
			return id_villeDepart;
		}

		/**
		 * @param id_villeDepart the id_villeDepart to set
		 */
		public void setId_villeDepart(int id_villeDepart) {
			this.id_villeDepart = id_villeDepart;
		}

		/**
		 * @return the id_villeArrivee
		 */
		public int getId_villeArrivee() {
			return id_villeArrivee;
		}

		/**
		 * @param id_villeArrivee the id_villeArrivee to set
		 */
		public void setId_villeArrivee(int id_villeArrivee) {
			this.id_villeArrivee = id_villeArrivee;
		}

		/**
		 * @return the jour
		 */
		public String getJour() {
			return jour;
		}

		/**
		 * @param jour the jour to set
		 */
		public void setJour(String jour) {
			this.jour = jour;
		}

		/**
		 * @return the heure
		 */
		public String getHeure() {
			return heure;
		}

		/**
		 * @param heure the heure to set
		 */
		public void setHeure(String heure) {
			this.heure = heure;
		}

		/**
		 * @return the dureeVol
		 */
		public String getDureeVol() {
			return dureeVol;
		}

		/**
		 * @param dureeVol the dureeVol to set
		 */
		public void setDureeVol(String dureeVol) {
			this.dureeVol = dureeVol;
		}

		/**
		 * @return the nbPassagersFirstClass
		 */
		public int getNbPassagersFirstClass() {
			return nbPassagersFirstClass;
		}

		/**
		 * @param nbPassagersFirstClass the nbPassagersFirstClass to set
		 */
		public void setNbPassagersFirstClass(int nbPassagersFirstClass) {
			this.nbPassagersFirstClass = nbPassagersFirstClass;
		}

		/**
		 * @return the nbPassagersSecondClass
		 */
		public int getNbPassagersSecondClass() {
			return nbPassagersSecondClass;
		}

		/**
		 * @param nbPassagersSecondClass the nbPassagersSecondClass to set
		 */
		public void setNbPassagersSecondClass(int nbPassagersSecondClass) {
			this.nbPassagersSecondClass = nbPassagersSecondClass;
		}

		/**
		 * @return the priceFirstClass
		 */
		public float getPriceFirstClass() {
			return priceFirstClass;
		}

		/**
		 * @param priceFirstClass the priceFirstClass to set
		 */
		public void setPriceFirstClass(float priceFirstClass) {
			this.priceFirstClass = priceFirstClass;
		}

		/**
		 * @return the priceSecondClass
		 */
		public float getPriceSecondClass() {
			return priceSecondClass;
		}

		/**
		 * @param priceSecondClass the priceSecondClass to set
		 */
		public void setPriceSecondClass(float priceSecondClass) {
			this.priceSecondClass = priceSecondClass;
		}

		/**
		 * @return the delaiAnnulation
		 */
		public int getDelaiAnnulation() {
			return delaiAnnulation;
		}

		/**
		 * @param delaiAnnulation the delaiAnnulation to set
		 */
		public void setDelaiAnnulation(int delaiAnnulation) {
			this.delaiAnnulation = delaiAnnulation;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id_vol;
			return result;
		}

		/** looking only id_vol
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
			Vol other = (Vol) obj;
			if (id_vol != other.id_vol)
				return false;
			return true;
		}
		
		
		
		
	
}
