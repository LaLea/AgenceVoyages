package test;

import java.sql.Date;

import metier.GestionVoyages;

import org.junit.Test;

import fabrique.BDDConnection;

public class TestGestionVoyage {

	@SuppressWarnings("deprecation")
	public void test() {
		GestionVoyages.ajouterUnVoyage(1, 6, 1, 1, new Date(88,11,23) , 1, new Date(115,10,15));
	}
	

	@Test
	public void test2(){
		System.out.println(GestionVoyages.consultationReservation(1));
		GestionVoyages.supprimeReservation(GestionVoyages.consultationReservation(1));
		System.out.println(GestionVoyages.consultationReservation(1));
	}
}
