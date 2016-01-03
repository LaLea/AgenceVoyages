package fabrique;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domaine.Reservation;

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
					Reservation reserv = new Reservation(id_reservation,idClient,idVol,dateVol,classe,idCategorie,dateReservation,nbPersonne);
					lesReservations.add(reserv);
				}
			}
			catch (SQLException e){
				return lesReservations;
			}
		return lesReservations;
	}
}
