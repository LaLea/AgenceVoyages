/**
 * 
 */
package domaine;

/**
 * @author Tchioben
 *
 */
public class Reduction {

		private int id_Redection;
		
		private int ageDebut;
		
		private int ageFin;
	
		private int pourcentageReduction;

		/**
		 * @param id_Redection
		 * @param ageDebut
		 * @param ageFin
		 * @param pourcentageReduction
		 */
		public Reduction(int id_Redection, int ageDebut, int ageFin,
				int pourcentageReduction) {
			this.id_Redection = id_Redection;
			this.ageDebut = ageDebut;
			this.ageFin = ageFin;
			this.pourcentageReduction = pourcentageReduction;
		}

		/**
		 * @return the id_Redection
		 */
		public int getId_Redection() {
			return id_Redection;
		}

		/**
		 * @param id_Redection the id_Redection to set
		 */
		public void setId_Redection(int id_Redection) {
			this.id_Redection = id_Redection;
		}

		/**
		 * @return the ageDebut
		 */
		public int getAgeDebut() {
			return ageDebut;
		}

		/**
		 * @param ageDebut the ageDebut to set
		 */
		public void setAgeDebut(int ageDebut) {
			this.ageDebut = ageDebut;
		}

		/**
		 * @return the ageFin
		 */
		public int getAgeFin() {
			return ageFin;
		}

		/**
		 * @param ageFin the ageFin to set
		 */
		public void setAgeFin(int ageFin) {
			this.ageFin = ageFin;
		}

		/**
		 * @return the pourcentageReduction
		 */
		public int getPourcentageReduction() {
			return pourcentageReduction;
		}

		/**
		 * @param pourcentageReduction the pourcentageReduction to set
		 */
		public void setPourcentageReduction(int pourcentageReduction) {
			this.pourcentageReduction = pourcentageReduction;
		}
		
		
}
