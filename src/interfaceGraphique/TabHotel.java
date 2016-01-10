package interfaceGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.Categorie;
import domaine.Chambre;
import domaine.Hotel;
import domaine.Ville;
import fabrique.FabriqueHotel;
import fabrique.FabriqueVille;
import metier.GestionHotel;
import metier.GestionVille;

public class TabHotel extends JPanel{

	// Listes
	private DefaultListModel<Ville> dlmVilles = new DefaultListModel<Ville>();
	private JList<Ville> lVilles = new JList<Ville>();
	private DefaultListModel<Hotel> dlmHotels = new DefaultListModel<Hotel>();
	private JList<Hotel> lHotels = new JList<Hotel>();
	private DefaultListModel<Categorie> dlmCats = new DefaultListModel<Categorie>();
	private JList<Categorie> lCats = new JList<Categorie>();
	private DefaultListModel<Chambre> dlmChbres = new DefaultListModel<Chambre>();
	private JList<Chambre> lChbres = new JList<Chambre>();
	// Les champs texte
	private JTextField tfNomVille = new JTextField(20);
	private JTextField tfPaysVille = new JTextField(20);
	private JTextField tfNomHotel = new JTextField(20);
	private JTextField tfVilleHotel = new JTextField(20);
	private JTextField tfNomCat = new JTextField(20);
	private JTextField tfCapCat = new JTextField(20);
	private JTextField tfTarifCat = new JTextField(20);
	private JTextField tfNomChbre = new JTextField(20);
	
	public TabHotel(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(InterfaceGraphique.createTitle("Gestion des villes, des hotels, des catégories de chambres et des chambres."));
		// Creation du grand JPanel pour positionner les 2 pane un à gauche, l'autre à droite
		JPanel general = new JPanel();
		add(general);
		general.add(createGauchePane());
		general.add(createDroitePane());
		
	}
	
	public JPanel createGauchePane(){
		JPanel gauche = new JPanel();
		gauche.setLayout(new BoxLayout(gauche,BoxLayout.Y_AXIS));
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
		pVilles.add(InterfaceGraphique.createListVilles(dlmVilles,lVilles,50,300, new lstVilleListener()));
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
		droite.add(InterfaceGraphique.createInputBox("Nom :", tfNomVille));
		droite.add(InterfaceGraphique.createInputBox("Pays :", tfPaysVille));
		droite.add(InterfaceGraphique.createButtonAddDel(new bAddVilleListener(), new bDelVilleListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("L'hotel :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", tfNomHotel));
		droite.add(InterfaceGraphique.createInputBox("Ville :", tfVilleHotel));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddHotelListener(), new bDelHotelListener(), new bEditHotelListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("La catégorie :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", tfNomCat));
		droite.add(InterfaceGraphique.createInputBox("Capacité :", tfCapCat));
		droite.add(InterfaceGraphique.createInputBox("Tarif :", tfTarifCat));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddCatListener(), new bDelCatListener(), new bEditCatListener()));
		
		droite.add(InterfaceGraphique.createSubTitle("La chambre :"));
		droite.add(InterfaceGraphique.createInputBox("Nom :", tfNomChbre));
		droite.add(InterfaceGraphique.createButtonAddDelEd(new bAddChbreListener(), new bDelChbreListener(), new bEditChbreListener()));
		return droite;
	}
	
	public DefaultListModel<Ville> getDlmVille(){
		return dlmVilles;
	}
	
	private class DeselAllVille implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lVilles.clearSelection();
		}
	}
	
	private class DeselAllHotel implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lHotels.clearSelection();
		}
	}
	
	private class DeselAllCat implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lCats.clearSelection();
		}
	}
	
	private class DeselAllChbres implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lChbres.clearSelection();
		}
	}
	
	private class bAddVilleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Ville v = GestionVille.ajouterVille(tfNomVille.getText(), tfPaysVille.getText());
			InterfaceGraphique.addVilleIntoAllList(v);
			lVilles.setSelectedValue(v, true);
		}
	}
	
//	private class bEditVilleListener implements ActionListener{
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			Ville v = lVilles.getSelectedValue();
//			if (v.getNom() != tfNomVille.getText()
//					|| v.getPays() != tfPaysVille.getText()){
//				//GestionVille.modifierVille(tfNomVille.getText(), tfPaysVille.getText());
//			}
//		}
//	}
	
	private class bDelVilleListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Ville v = lVilles.getSelectedValue();
			InterfaceGraphique.delVilleIntoAllList(v);
			GestionVille.supprimerVille(v.getId_ville());
		}
	}
	
	private class bAddHotelListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			FabriqueVille fv = FabriqueVille.getInstance();
			Ville v = fv.getVilleBDDWithNom(tfVilleHotel.getText());
			GestionHotel.ajoutHotel(v.getId_ville(), tfNomHotel.getText());	
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
	
	private class lstVilleListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (!lVilles.isSelectionEmpty()){
				System.out.println("=== TabHotel / Liste ville listener selection not empty =====");
				Ville v = lVilles.getSelectedValue();
				tfNomVille.setText(v.getNom());
				tfPaysVille.setText(v.getPays());
				FabriqueVille fv = FabriqueVille.getInstance();
				fv.addVilleDansFabrique(v.getId_ville(), v.getNom(), v.getPays());
				ArrayList<Hotel> lh = GestionHotel.listerHotelDUneVille(v.getId_ville());
				System.out.println(lh);
				
				InterfaceGraphique.addHotelsIntoOneList(lh, dlmHotels);
			}
			else {
				tfNomVille.setText("");
				tfPaysVille.setText("");
				tfNomHotel.setText("");
				tfVilleHotel.setText("");
				tfNomCat.setText("");
				tfTarifCat.setText("");
				tfCapCat.setText("");
				tfNomChbre.setText("");
				dlmHotels.clear();
				dlmChbres.clear();
				dlmCats.clear();
			}
		}
	}
}
