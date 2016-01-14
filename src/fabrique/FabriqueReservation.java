package fabrique;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Reservation;
import domaine.Ville;

public class FabriqueReservation {
	private static FabriqueReservation INSTANCE = null;
	

	
	private FabriqueReservation(){
	}
	
	public static FabriqueReservation getInstance(){
		if (INSTANCE==null){
			INSTANCE = new FabriqueReservation();
		}
		return INSTANCE;
	}
	
	
	/**
	 * permet d'ajouter une reservation
	 * @param idClient l'id du client
	 * @param idVol l'id du vol
	 * @param classe la classe pour le vol ( 1ere ou 2eme classe)
	 * @param dateVol la date du vol du type sql.Date
	 * @param idCategorie l'id de categorie
	 * @param dateReservationChambre la date de reservation du type sql.Date
	 * @param nbPersonne le nb de personne
	 */
	public void ajouterReservation(int idClient, int idVol, int classe,
			Date dateVol, int idCategorie, Date dateReservationChambre, int nbPersonne,
			int idVolRetour, Date dateVolRetour){
		BDDConnection.ajouteReservation(idClient, idVol, classe, dateVol, idCategorie, dateReservationChambre, nbPersonne,idVolRetour,dateVolRetour);
	}
	
	
	/**
	 * permet de recuperer la liste des reservation d'une personne grace à son id
	 * @param idPers l'id de la personne
	 * @return la liste des reservation
	 */
	public ArrayList<Reservation> getReservationDUnePersonne(int idPers){
		ArrayList<Reservation> lesReservations = new ArrayList<Reservation>();
		ResultSet rs = BDDConnection.selectReservation(idPers);
			try{
				while (rs.next()){
					int id_reservation=rs.getInt(1);
					int idClient=rs.getInt(2);		
					int idVol=rs.getInt(3);	
					int classe=rs.getInt(4);
					Date dateVol=rs.getDate(5);				
					int idCategorie=rs.getInt(6);
					Date dateReservation=rs.getDate(7);
					int nbPersonne=rs.getInt(8);	
					int idVolRetour=rs.getInt(9);	
					Date dateVolRetour=rs.getDate(10);		
					Reservation reserv = new Reservation(id_reservation,idClient,idVol,dateVol,classe,idCategorie,dateReservation,nbPersonne,idVolRetour,dateVolRetour);
					lesReservations.add(reserv);
				}
			}
			catch (SQLException e){
				return lesReservations;
			}
		return lesReservations;
	}

	/**
	 * permet de recuperer la reservation
	 * @param idReservation l'id de la reservation
	 * @return la reservation
	 */
	public Reservation getReservation(int idReservation) {
		Reservation reservation = null;
		if (reservation== null){
			ResultSet rs = BDDConnection.ligneVille(idReservation);
			try{
				int idClient=rs.getInt(2);		
				int idVol=rs.getInt(3);	
				int classe=rs.getInt(4);
				Date dateVol=rs.getDate(5);				
				int idCategorie=rs.getInt(6);
				Date dateReservation=rs.getDate(7);
				int nbPersonne=rs.getInt(8);
				int idVolRetour=rs.getInt(9);	
				Date dateVolRetour=rs.getDate(10);	
				reservation = new Reservation(idReservation,idClient,idVol,dateVol,classe,idCategorie,dateReservation,nbPersonne,idVolRetour,dateVolRetour);
			}
			catch (SQLException e){
				return null;
			}
		}// fin IF
		return reservation;

	}

}
