package interfaceGraphique;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import domaine.Categorie;
import domaine.Client;
import domaine.Hotel;
import domaine.Ville;
import domaine.Vol;

public class TabReservation extends JPanel{

	// Barre de recherche
	private JTextField tfSearch = new JTextField(15);
	private JComboBox<String> cbSearch = new JComboBox<String>();
	// Liste de clients
	private JList<Client> lCust = new JList<Client>();
	private DefaultListModel<Client> dlmCust = new DefaultListModel<Client>();
	// Champs a remplir
	private JComboBox<Ville> cbVilleDep;
	private JComboBox<Ville> cbVilleArr;
	private JDateChooser dcDteAller = new JDateChooser();
	private JDateChooser dcDteRetr = new JDateChooser();
	private JTextField tfNbPersAcc = new JTextField(2);
	// Boutons Radio
	private JRadioButton rbPriceOrder;
	private JRadioButton rbTimeOrder;
	// Liste de vols
	private JList<Vol> lVols = new JList<Vol>();
	private DefaultListModel<Vol> dlmVols = new DefaultListModel<Vol>();
	// radio button 1ere et 2eme classe
	private JRadioButton rb1Classe;
	private JRadioButton rb2Classe;
	// les 2 listes hotel + categories de chambres
	private DefaultListModel<Hotel> dlmHotel = new DefaultListModel<Hotel>();
	private JList<Hotel> lHotel = new JList<Hotel>();
	private DefaultListModel<Categorie> dlmCat = new DefaultListModel<Categorie>();
	private JList<Categorie> lCat = new JList<Categorie>();
	
	public TabReservation(){
		super();
		// Creation d'un grand JPanel contenant le titre + JPanel middlePane + Jbutton d'enregistrement de la réservation
		JPanel general = new JPanel();
		add(general);
		general.setLayout(new BoxLayout(general,BoxLayout.PAGE_AXIS));
		general.add(InterfaceGraphique.createTitle("Enregistrer une reservation."));
		JPanel middlePane = new JPanel();
		general.add(middlePane);
		general.add(InterfaceGraphique.createOneButton(new bSaveBookingListener(),"Enregistrer la réservation."));
		// Creation du middlePane contenant un Jpanel a gauche et un Jpanel à droite
		middlePane.setLayout(new GridLayout(1,2));
		middlePane.add(createLeftMiddlePane());
		middlePane.add(createRightMiddlePane());
	}
	
	public JPanel createLeftMiddlePane(){
		JPanel leftMiddlePane = new JPanel();
		leftMiddlePane.setLayout(new BoxLayout(leftMiddlePane,BoxLayout.Y_AXIS));
		// Barre de recherche
		leftMiddlePane.add(InterfaceGraphique.createSubTitle("Rechercher un client :"));
		leftMiddlePane.add(InterfaceGraphique.createSearchCust(tfSearch, cbSearch, dlmCust, lCust));
		// Liste des clients
		leftMiddlePane.add(InterfaceGraphique.createSubTitle("Les clients :"));
		leftMiddlePane.add(InterfaceGraphique.createListCust(dlmCust, lCust, 50, 200, new LstCustListener()));
		//InterfaceGraphique.addAllClientsIntoList(dlmCust);
		// Boutons
		leftMiddlePane.add(InterfaceGraphique.createButtonsPair(new bDeselCustListener(),new bAffCustListener()));
		//Champs a remplir
		leftMiddlePane.add(InterfaceGraphique.createCBVille("Ville de départ* :", cbVilleDep));
		//cbVilleDep.addActionListener(new cbVilleDepListener());
		leftMiddlePane.add(InterfaceGraphique.createCBVille("Ville d'arrivée :", cbVilleArr));
		leftMiddlePane.add(InterfaceGraphique.createDateChooser("Date de départ :",dcDteAller));
		leftMiddlePane.add(InterfaceGraphique.createDateChooser("Date de retour :",dcDteRetr));
		leftMiddlePane.add(InterfaceGraphique.createInputBox("Nombre de personnes accompagnantes :", tfNbPersAcc));
		
		return leftMiddlePane;
	}

	public JPanel createRightMiddlePane() {
		JPanel rightMiddlePane = new JPanel();
		rightMiddlePane.setLayout(new BoxLayout(rightMiddlePane, BoxLayout.Y_AXIS));
		//En tete de liste
		rightMiddlePane.add(InterfaceGraphique.createGroupRadioButton("Les vols possibles : ",rbPriceOrder, "Tier par prix", rbTimeOrder, "Trier par temps de voyage",true));
		// La liste
		rightMiddlePane.add(InterfaceGraphique.createListVol(dlmVols, lVols, 50, 200, new LstVolListener()));
		
		// A voir pour l'ajout du bouton deselectionner tout si nécessaire
		rightMiddlePane.add(InterfaceGraphique.createGroupRadioButton("Classe :", rb1Classe, "1ère classe", rb2Classe, "2ème classe",true));
		rightMiddlePane.add(InterfaceGraphique.createSubTitle("Les hébergements possibles :"));
		// Les 2 listes hotels et catégories de chambres
		JPanel twoListPane = new JPanel();
		rightMiddlePane.add(twoListPane);
		twoListPane.setLayout(new FlowLayout());
		JPanel leftTwoListPane = new JPanel();
		leftTwoListPane.setLayout(new BoxLayout(leftTwoListPane, BoxLayout.Y_AXIS));
		JPanel rightTwoListPane = new JPanel();
		rightTwoListPane.setLayout(new BoxLayout(rightTwoListPane, BoxLayout.Y_AXIS));
		twoListPane.add(leftTwoListPane);
		twoListPane.add(rightTwoListPane);
		leftTwoListPane.add(InterfaceGraphique.createListHotel(dlmHotel, lHotel, 250, 200, new LstHotelListener()));
		rightTwoListPane.add(InterfaceGraphique.createListCat(dlmCat, lCat, 250, 200));
		return rightMiddlePane;
	}
	
	public DefaultListModel<Client> getDlmCust(){
		return dlmCust;
	}
	
	private class bDeselCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lCust.clearSelection();
		}
	}
	
	private class bAffCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dlmCust.clear();
			InterfaceGraphique.addAllClientsIntoOneList(dlmCust);
		}
	}
	
	private class bSaveBookingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class LstCustListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class LstVolListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			
		}
	}
	
	private class LstHotelListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class cbVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			cbVilleArr.removeItemAt(cbVilleDep.getSelectedIndex());
		}
	}
}
