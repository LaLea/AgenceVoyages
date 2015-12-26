package interfaceGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import domaine.Client;
import domaine.Voyage;

public class TabVoyage extends JPanel{

	private JTextField tfSearchCust;
	private JComboBox<String> cbSearchCust;
	private JList<Client> lCust;
	private DefaultListModel<Client> dlmCust;
	private JList<Voyage> lVoyage;
	private DefaultListModel<Voyage> dlmVoyage;
	private JTextField tfVilleDepVol;
	private JTextField tfVilleArrVol;
	private JTextField tfJourVol;
	private JTextField tfHHeureVol;
	private JTextField tfMnHeureVol;
	private JTextField tfHDureeVol;
	private JTextField tfMnDureeVol;
	private JRadioButton rb1Classe;
	private JRadioButton rb2Classe;
	private JTextField tfTarifVol;
	private JTextField tfVilleHotel;
	private JTextField tfNomHotel;
	private JTextField tfCatChbre;
	private JTextField tfNbPers;

	public TabVoyage(){
		super();
		JPanel midPane = new JPanel();
		add(midPane);
		midPane.add(createLeftMidPane(),JPanel.BOTTOM_ALIGNMENT);
		midPane.add(createMiddleMidPane(),JPanel.BOTTOM_ALIGNMENT);
		midPane.add(createRightMidPane(),JPanel.BOTTOM_ALIGNMENT);
		//add(InterfaceGraphique.createOneButton(new AnnulReservListener(), "Annuler la réservation"));
	}
	
	private class AnnulReservListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private JPanel createRightMidPane() {
		JPanel rightMidPane = new JPanel();
		rightMidPane.setLayout(new BoxLayout(rightMidPane, BoxLayout.Y_AXIS));
		rightMidPane.add(InterfaceGraphique.createSubTitle("Le vol :"));
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville de départ :", 20, tfVilleDepVol));
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville d'arrivée :", 20, tfVilleArrVol));
		rightMidPane.add(InterfaceGraphique.createInputBox("Jour :", 20, tfJourVol));
		JPanel heure = new JPanel();
		heure.add(InterfaceGraphique.createInputBox("Heure :", 2, tfHHeureVol));
		heure.add(InterfaceGraphique.createInputBox(" : ", 2, tfMnHeureVol));
		rightMidPane.add(heure);
		JPanel duree = new JPanel();
		duree.add(InterfaceGraphique.createInputBox("Durée :", 2, tfHDureeVol));
		duree.add(InterfaceGraphique.createInputBox(" : ", 2, tfMnDureeVol));
		rightMidPane.add(duree);
		rightMidPane.add(InterfaceGraphique.createGroupRadioButton("Classe :", rb1Classe, "1ère classe", rb2Classe, "2ème classe",false));
		rightMidPane.add(InterfaceGraphique.createInputBox("Tarif :", 20, tfTarifVol));
		
		rightMidPane.add(InterfaceGraphique.createSubTitle("L'hébergement :"));
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville :", 20, tfVilleHotel));
		rightMidPane.add(InterfaceGraphique.createInputBox("Nom hotel :", 20, tfNomHotel));
		rightMidPane.add(InterfaceGraphique.createInputBox("Catégorie de la chambre :", 20, tfCatChbre));
		rightMidPane.add(InterfaceGraphique.createInputBox("Nombre de personnes supplémentaires :", 2, tfNbPers));
		rightMidPane.add(InterfaceGraphique.createOneButton(new AnnulReservListener(), "Annuler la réservation"));
		return rightMidPane;
	}

	private JPanel createMiddleMidPane() {
		JPanel middleMidPane = new JPanel();
		middleMidPane.setLayout(new BoxLayout(middleMidPane, BoxLayout.Y_AXIS));
		middleMidPane.add(InterfaceGraphique.createSubTitle("Les voyages du client :"));
		middleMidPane.add(InterfaceGraphique.createListVoyage(dlmVoyage, lVoyage, 60, 200));
		middleMidPane.add(InterfaceGraphique.createOneButton(new DeselVoyageListener(), "Désélectionner tout"));
		return middleMidPane;
	}

	private JPanel createLeftMidPane() {
		JPanel leftMidPane = new JPanel();
		leftMidPane.setLayout(new BoxLayout(leftMidPane, BoxLayout.Y_AXIS));
		leftMidPane.add(InterfaceGraphique.createSubTitle("Rechercher un client :"));
		leftMidPane.add(InterfaceGraphique.createSearchCust(tfSearchCust, new SearchCustListener(), cbSearchCust));
		leftMidPane.add(InterfaceGraphique.createSubTitle("Les clients :"));
		leftMidPane.add(InterfaceGraphique.createListCust(dlmCust, lCust, 60, 200));
		leftMidPane.add(InterfaceGraphique.createButtonsPair(new DeselCustListener(), new AffToutCustListener()));
		return leftMidPane;
	}
	
	private class SearchCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class AffToutCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselVoyageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
