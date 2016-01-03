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
		
		private int heureDepart;
		
		private int minDepart;
		
		private int heureDuree;
		
		private int minDuree;
		
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
		public Vol(int id_vol, int id_villeDepart2,
				int id_villeArrivee2, String jours, int heure2, int min,
				int heureDuree, int minDuree, int nb1ereClasse,
				float prix1ereClasse, int nb2emeClasse, float prix2emeClasse,
				int dureeAnnulation) {
			this.id_vol = id_vol;
			this.id_villeDepart = id_villeDepart2;
			this.id_villeArrivee = id_villeArrivee2;
			this.jour = jours;
			this.heureDepart = heure2;
			this.minDepart = min;
			this.heureDuree = heureDuree;
			this.minDuree = minDuree;
			this.nbPassagersFirstClass = nb1ereClasse;
			this.nbPassagersSecondClass = nb2emeClasse;
			this.priceFirstClass = prix1ereClasse;
			this.priceSecondClass = prix2emeClasse;
			this.delaiAnnulation = dureeAnnulation;
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


		/**
		 * @return the heureDuree
		 */
		public int getHeureDuree() {
			return heureDuree;
		}


		/**
		 * @param heureDuree the heureDuree to set
		 */
		public void setHeureDuree(int heureDuree) {
			this.heureDuree = heureDuree;
		}


		/**
		 * @return the heureDepart
		 */
		public int getHeureDepart() {
			return heureDepart;
		}


		/**
		 * @param heureDepart the heureDepart to set
		 */
		public void setHeureDepart(int heureDepart) {
			this.heureDepart = heureDepart;
		}


		/**
		 * @return the minDepart
		 */
		public int getMinDepart() {
			return minDepart;
		}


		/**
		 * @param minDepart the minDepart to set
		 */
		public void setMinDepart(int minDepart) {
			this.minDepart = minDepart;
		}


		/**
		 * @return the minDuree
		 */
		public int getMinDuree() {
			return minDuree;
		}


		/**
		 * @param minDuree the minDuree to set
		 */
		public void setMinDuree(int minDuree) {
			this.minDuree = minDuree;
		}
		
	
		public String toString(){
			return this.id_vol+","+this.id_villeDepart+","+this.id_villeArrivee+","+this.heureDepart+","+this.heureDuree;
		}
		
	
}
