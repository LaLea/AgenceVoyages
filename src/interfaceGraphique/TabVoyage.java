package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import domaine.Client;
import domaine.Voyage;

public class TabVoyage extends JPanel{

	private JTextField tfSearchCust= new JTextField(15);
	private JComboBox<String> cbSearchCust = new JComboBox<String>();
	private JList<Client> lCust = new JList<Client>();
	private DefaultListModel<Client> dlmCust = new DefaultListModel<Client>();
	private JList<Voyage> lVoyage = new JList<Voyage>();
	private DefaultListModel<Voyage> dlmVoyage = new DefaultListModel<Voyage>();
	private JTextField tfVilleDepVol = new JTextField(20);
	private JTextField tfVilleArrVol = new JTextField(20);
	private JTextField tfJourVol = new JTextField(20);
	private JTextField tfHHeureVol = new JTextField(2);
	private JTextField tfMnHeureVol = new JTextField(2);
	private JTextField tfHDureeVol = new JTextField(2);
	private JTextField tfMnDureeVol = new JTextField(2);
	private JRadioButton rb1Classe;
	private JRadioButton rb2Classe;
	private JTextField tfTarifVol = new JTextField(20);
	private JTextField tfVilleHotel = new JTextField(20);
	private JTextField tfNomHotel = new JTextField(20);
	private JTextField tfCatChbre = new JTextField(20);
	private JTextField tfNbPers = new JTextField(2);

	public TabVoyage(){
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(InterfaceGraphique.createTitle("Gestion des réservations."));
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
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville de départ :", tfVilleDepVol));
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville d'arrivée :", tfVilleArrVol));
		rightMidPane.add(InterfaceGraphique.createInputBox("Jour :", tfJourVol));
		rightMidPane.add(InterfaceGraphique.createHeureInputBox("Heure : ", tfHHeureVol, tfMnHeureVol));
		rightMidPane.add(InterfaceGraphique.createHeureInputBox("Durée de vol : ", tfHDureeVol, tfMnDureeVol));
		rightMidPane.add(InterfaceGraphique.createGroupRadioButton("Classe :", rb1Classe, "1ère classe", rb2Classe, "2ème classe",false));
		rightMidPane.add(InterfaceGraphique.createInputBox("Tarif :", tfTarifVol));
		rightMidPane.add(InterfaceGraphique.createSubTitle("L'hébergement :"));
		rightMidPane.add(InterfaceGraphique.createInputBox("Ville :", tfVilleHotel));
		rightMidPane.add(InterfaceGraphique.createInputBox("Nom hotel :", tfNomHotel));
		rightMidPane.add(InterfaceGraphique.createInputBox("Catégorie de la chambre :", tfCatChbre));
		rightMidPane.add(InterfaceGraphique.createInputBox("Nombre de personnes supplémentaires :", tfNbPers));
		rightMidPane.add(InterfaceGraphique.createOneButton(new AnnulReservListener(), "Annuler la réservation"));
		return rightMidPane;
	}

	private JPanel createMiddleMidPane() {
		JPanel middleMidPane = new JPanel();
		middleMidPane.setLayout(new BoxLayout(middleMidPane, BoxLayout.Y_AXIS));
		middleMidPane.add(InterfaceGraphique.createSubTitle("Les voyages du client :"));
		middleMidPane.add(InterfaceGraphique.createListVoyage(dlmVoyage, lVoyage, 60, 460));
		middleMidPane.add(InterfaceGraphique.createOneButton(new DeselVoyageListener(), "Désélectionner tout"));
		return middleMidPane;
	}

	private JPanel createLeftMidPane() {
		JPanel leftMidPane = new JPanel();
		leftMidPane.setLayout(new BoxLayout(leftMidPane, BoxLayout.Y_AXIS));
		leftMidPane.add(InterfaceGraphique.createSubTitle("Rechercher un client :"));
		leftMidPane.add(InterfaceGraphique.createSearchCust(tfSearchCust, cbSearchCust, dlmCust, lCust));
		leftMidPane.add(InterfaceGraphique.createSubTitle("Les clients :"));
		leftMidPane.add(InterfaceGraphique.createListCust(dlmCust, lCust, 60, 400, null));
		//InterfaceGraphique.addAllClientsIntoList(dlmCust);
		leftMidPane.add(InterfaceGraphique.createButtonsPair(new DeselCustListener(), new AffToutCustListener()));
		return leftMidPane;
	}
	
	public DefaultListModel<Client> getDlmCust(){
		return dlmCust;
	}
	
	private class DeselCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lCust.clearSelection();
		}
	}
	
	private class AffToutCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dlmCust.clear();
			InterfaceGraphique.addAllClientsIntoOneList(dlmCust);
		}
	}
	
	private class DeselVoyageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
