/**
 * 
 */
package domaine;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Benoit Bailleul & Vanelle Lea
 *
 */
public class Vol {

		private ArrayList<Integer> idsVol;
		
		private int id_villeDepart;
		
		private int id_villeArrivee;
		
		private Date depart;
		
		private Date arrivee;
		
		private int nbPassagersFirstClass;
		
		private int nbPassagersSecondClass;
		
		private float priceFirstClass;
		
		private float priceSecondClass;
		
		private int delaiAnnulation;
		

		
	
		
		
	
		/**
		 * @param idsVol
		 * @param id_villeDepart
		 * @param id_villeArrivee
		 * @param depart
		 * @param arrivee
		 * @param nbPassagersFirstClass
		 * @param nbPassagersSecondClass
		 * @param priceFirstClass
		 * @param priceSecondClass
		 * @param delaiAnnulation
		 */
		public Vol(ArrayList<Integer> idsVol, int id_villeDepart,
				int id_villeArrivee, Date depart, Date arrivee,
				int nbPassagersFirstClass,
				int nbPassagersSecondClass, float priceFirstClass,
				float priceSecondClass, int delaiAnnulation) {
			this.idsVol = idsVol;
			this.id_villeDepart = id_villeDepart;
			this.id_villeArrivee = id_villeArrivee;
			this.depart = depart;
			this.arrivee = arrivee;
			this.nbPassagersFirstClass = nbPassagersFirstClass;
			this.nbPassagersSecondClass = nbPassagersSecondClass;
			this.priceFirstClass = priceFirstClass;
			this.priceSecondClass = priceSecondClass;
			this.delaiAnnulation = delaiAnnulation;
		}



		public String toString(){
			return this.id_villeDepart+","+this.id_villeArrivee+","+this.depart+","+this.arrivee;
		}

		/**
		 * @return the idsVol
		 */
		public ArrayList<Integer> getIdsVol() {
			return idsVol;
		}

		/**
		 * @param idsVol the idsVol to set
		 */
		public void setIdsVol(ArrayList<Integer> idsVol) {
			this.idsVol = idsVol;
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
		 * @return the depart
		 */
		public Date getDepart() {
			return depart;
		}

		/**
		 * @param depart the depart to set
		 */
		public void setDepart(Date depart) {
			this.depart = depart;
		}

		/**
		 * @return the arrivee
		 */
		public Date getArrivee() {
			return arrivee;
		}



		/**
		 * @param arrivee the arrivee to set
		 */
		public void setArrivee(Date arrivee) {
			this.arrivee = arrivee;
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


	
}
