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
	
	private int jour;
	
	private int mois;
	
	private int annee;
	
	private int id_villeOrigine;

	
	
	
	/** a new client
	 * @param id_client is unique
	 * @param nom
	 * @param prenom
	 * @param dateNaissance
	 * @param id_villeOrigine refer to the class Ville
	 */
	public Client(int id_client, String nom, String prenom,int jour, int mois, int annee, int id_villeOrigine) {
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
		this.id_villeOrigine = id_villeOrigine;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
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

	
	public String toString(){
		return this.getNom()+" "+this.getPrenom();
	}

	/**
	 * @return the mois
	 */
	public int getMois() {
		return mois;
	}

	/**
	 * @param mois the mois to set
	 */
	public void setMois(int mois) {
		this.mois = mois;
	}

	/**
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	/**
	 * @return the jour
	 */
	public int getJour() {
		return jour;
	}

	/**
	 * @param jour the jour to set
	 */
	public void setJour(int jour) {
		this.jour = jour;
	}

	
}
