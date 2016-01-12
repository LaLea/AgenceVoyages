package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import metier.GestionVol;

import org.junit.Test;

import sun.util.resources.fr.TimeZoneNames_fr;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import domaine.Vol;
import fabrique.BDDConnection;
import fabrique.FabriqueVol;

public class TestVol {

/**	public void ajouteVol(){
		//BDDConnection.addVol(1, 2, "2", 10, 30, 2, 10, 2, new Float(500), 5, new Float(200), 2);
	}
	
@Test
	public void getVol(){
		FabriqueVol fv =FabriqueVol.getInstance();
		Vol vol = fv.getVolBDDWithIdVol(1);
		System.out.println(vol.toString());
		
	}

@Test
public void testGetVolsAvecVilleDepartEtArrivee(){
	//ArrayList<Vol> lesVols = volsAvecVilleDepartEtArrivee.getVolsAvecVilleDepartEtArrivee("Hantay","Lille");
	//System.out.println(lesVols);
}*/
	
	@Test
	public void test(){
		GestionVol.genererLesVolsDeLaSemaine();
	}
	
	
	@Test
	public void test2(){
		Calendar cal = Calendar.getInstance();
		Date date= cal.getTime();
		System.out.println(date);
	}
	
	

}
