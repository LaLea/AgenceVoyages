package interfaceGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domaine.Categorie;
import domaine.Chambre;
import domaine.Hotel;
import domaine.Ville;

public class TabHotel extends JPanel{

	// Listes
	private DefaultListModel<Ville> dlmVilles;
	private JList<Ville> lVilles;
	private DefaultListModel<Hotel> dlmHotels;
	private JList<Hotel> lHotels;
	private DefaultListModel<Categorie> dlmCats;
	private JList<Categorie> lCats;
	private DefaultListModel<Chambre> dlmChbres;
	private JList<Chambre> lChbres;
	// Les champs texte
	private JTextField tfNomVille;
	private JTextField tfPaysVille;
	private JTextField tfNomHotel;
	private JTextField tfVilleHotel;
	private JTextField tfNomCat;
	private JTextField tfCapCat;
	private JTextField tfTarifCat;
	private JTextField tfNomChbre;
	
	public TabHotel(){
		super();
		// Creation du grand JPanel pour positionner les 2 pane un à gauche, l'autre à droite
		JPanel general = new JPanel();
		add(general);
		general.add(createGauchePane());
		general.add(createDroitePane());
	}
	
	public JPanel createGauchePane(){
		JPanel gauche = new JPanel();
		gauche.setLayout(new BoxLayout(gauche,BoxLayout.Y_AXIS));
		gauche.add(InterfaceGraphique.createTitle("Gestion des villes, des hotels, des catégories de chambres et des chambres."));
		gauche.add(createGaucheMiddlePane());
		return gauche;
	}
	
	private JPanel createGaucheMiddlePane() {
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(1,4));
		// Ajout des panels pour les differentes listes
		JPanel pVilles = new JPanel();
		pVilles.setLayout(new BoxLayout(pVilles,BoxLayout.Y_AXIS));
		JPanel pHotels = new JPanel();
		pHotels.setLayout(new BoxLayout(pHotels,BoxLayout.Y_AXIS));
		JPanel pCat = new JPanel();
		pCat.setLayout(new BoxLayout(pCat,BoxLayout.Y_AXIS));
		JPanel pChbres = new JPanel();
		pChbres.setLayout(new BoxLayout(pChbres,BoxLayout.Y_AXIS));
		pane.add(pVilles);
		pane.add(pHotels);
		pane.add(pCat);
		pane.add(pChbres);
		// Panel villes
		pVilles.add(InterfaceGraphique.createSubTitle("Les villes :"));
		pVilles.add(InterfaceGraphique.createListVilles(dlmVilles,lVilles,50,300));
		pVilles.add(InterfaceGraphique.createOneButton(new DeselAllVille(), "Déselectionner tout"));
		pHotels.add(InterfaceGraphique.createSubTitle("Les hotels :"));
		pHotels.add(InterfaceGraphique.createListHotel(dlmHotels, lHotels, 50, 300));
		pHotels.add(InterfaceGraphique.createOneButton(new DeselAllHotel(), "Déselectionner tout"));
		pCat.add(InterfaceGraphique.createSubTitle("Les categories :"));
		pCat.add(InterfaceGraphique.createListCat(dlmCats, lCats, 50, 300));
		pCat.add(InterfaceGraphique.createOneButton(new DeselAllCat(), "Déselectioner tout"));
		pChbres.add(InterfaceGraphique.createSubTitle("Les chambres :"));
		pChbres.add(InterfaceGraphique.createListChbres(dlmChbres, lChbres, 50, 300));
		pChbres.add(InterfaceGraphique.createOneButton(new DeselAllChbres(), "Déselectionner tout"));
		return pane;
	}

	public JPanel createDroitePane(){
		JPanel droite = new JPanel();
		droite.setLayout(new BoxLayout(droite,BoxLayout.Y_AXIS));
		droite.add(InterfaceGraphique.createSubTitle("La ville :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", 20, tfNomVille));
		droite.add(InterfaceGraphique.createInputBox("Pays :", 20, tfPaysVille));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddVilleListener(), new bDelVilleListener(), new bEditVilleListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("L'hotel :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", 20, tfNomHotel));
		droite.add(InterfaceGraphique.createInputBox("Ville :", 20, tfVilleHotel));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddHotelListener(), new bDelHotelListener(), new bEditHotelListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("La catégorie :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", 20, tfNomCat));
		droite.add(InterfaceGraphique.createInputBox("Capacité :", 20, tfCapCat));
		droite.add(InterfaceGraphique.createInputBox("Tarif :", 20, tfTarifCat));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddCatListener(), new bDelCatListener(), new bEditCatListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("La chambre :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", 20, tfNomChbre));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddChbreListener(), new bDelChbreListener(), new bEditChbreListener()));
		return droite;
	}
	
	private class DeselAllVille implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselAllHotel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselAllCat implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselAllChbres implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bAddVilleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bEditVilleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bDelVilleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bAddHotelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bEditHotelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bDelHotelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bAddCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bDelCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bEditCatListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bAddChbreListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bEditChbreListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bDelChbreListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
