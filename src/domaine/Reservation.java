package domaine;

import java.sql.Date;

public class Reservation {
	private int id_reservation;
	
	private int idClient;
	
	private int idVol;
	
	private Date dateVol;
	
	private int classe;
	
	private int idCategorie;
	
	private Date dateReservation;
	
	private int nbPersonne;




	/**
	 * @param id_reservation
	 * @param idClient
	 * @param idVol
	 * @param classe
	 * @param idCategorie
	 * @param dateReservation
	 * @param nbPersonne
	 */
	public Reservation(int id_reservation, int idClient, int idVol,Date dateVol, int classe,
			int idCategorie, Date dateReservation, int nbPersonne) {
		this.id_reservation = id_reservation;
		this.idClient = idClient;
		this.idVol = idVol;
		this.dateVol = dateVol;
		this.classe = classe;
		this.idCategorie = idCategorie;
		this.dateReservation = dateReservation;
		this.nbPersonne = nbPersonne;
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
	public int getClasse() {
		return classe;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(int classe) {
		this.classe = classe;
	}

	/**
	 * @return the idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}

	/**
	 * @param idCategorie the idCategorie to set
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	/**
	 * @return the dateReservation
	 */
	public Date getDateReservation() {
		return dateReservation;
	}

	/**
	 * @param dateReservation the dateReservation to set
	 */
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	/**
	 * @return the nbPersonne
	 */
	public int getNbPersonne() {
		return nbPersonne;
	}

	/**
	 * @param nbPersonne the nbPersonne to set
	 */
	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	/**
	 * @return the dateVol
	 */
	public Date getDateVol() {
		return dateVol;
	}

	/**
	 * @param dateVol the dateVol to set
	 */
	public void setDateVol(Date dateVol) {
		this.dateVol = dateVol;
	}

	
}
