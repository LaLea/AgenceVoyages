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
	private int idCategorieChambre;
	private int idHotel;
	private int numeroChambre;
	
	public int getIdCategorieChambre() {
		return idCategorieChambre;
	}

	public void setIdCategorieChambre(int idCategorieChambre) {
		this.idCategorieChambre = idCategorieChambre;
	}

	public int getNumeroChambre() {
		return numeroChambre;
	}

	public void setNumeroChambre(int numeroChambre) {
		this.numeroChambre = numeroChambre;
	}

	/**
	 * @param id_chambre the unique id to the chambre
	 * @param categorie the categorie, categorie object
	 * @param idHotel the id about the tower
	 * @param numeroChambre le numero de chambre pour cette hotel.
	 */
	public Chambre(int id_chambre, int idCategorie, int idHotel, int numeroChambre) {
		this.id_chambre = id_chambre;
		this.idCategorieChambre = idCategorie;
		this.idHotel = idHotel;
		this.numeroChambre = numeroChambre;
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
	
	
}
