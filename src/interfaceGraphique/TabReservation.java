package interfaceGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import domaine.Categorie;
import domaine.Client;
import domaine.Hotel;
import domaine.Vol;

public class TabReservation extends JPanel{

	// Barre de recherche
	private JTextField tfSearch = new JTextField(15);
	private JComboBox<String> cbSearch;
	// Liste de clients
	private JList<Client> lCust;
	private DefaultListModel<Client> dlmCust;
	// Champs a remplir
	private JComboBox<String> cbVilleDep;
	private JComboBox<String> cbVilleArr;
	private JDateChooser dcDteAller = new JDateChooser();
	private JDateChooser dcDteRetr = new JDateChooser();
	private JTextField tfNbPersAcc = new JTextField(2);
	// Boutons Radio
	private JRadioButton rbPriceOrder;
	private JRadioButton rbTimeOrder;
	// Liste de vols
	private JList<Vol> lVols;
	private DefaultListModel<Vol> dlmVols;
	// radio button 1ere et 2eme classe
	private JRadioButton rb1Classe;
	private JRadioButton rb2Classe;
	// les 2 listes hotel + categories de chambres
	private DefaultListModel<Hotel> dlmHotel;
	private JList<Hotel> lHotel;
	private DefaultListModel<Categorie> dlmCat;
	private JList<Categorie> lCat;
	
	public TabReservation(){
		super();
		// Creation d'un grand JPanel contenant le titre + JPanel middlePane + Jbutton d'enregistrement de la réservation
		JPanel general = new JPanel();
		add(general);
		general.setLayout(new BoxLayout(general,BoxLayout.PAGE_AXIS));
		general.add(InterfaceGraphique.createTitle("Gestion des reservations"));
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
		leftMiddlePane.add(InterfaceGraphique.createSearchCust(tfSearch, new SearchCustListener(cbSearch, tfSearch, dlmCust), cbSearch));
		// Liste des clients
		leftMiddlePane.add(InterfaceGraphique.createSubTitle("Les clients :"));
		leftMiddlePane.add(InterfaceGraphique.createListCust(dlmCust, lCust, 60, 150));
		//InterfaceGraphique.addAllClientsIntoList(dlmCust);
		// Boutons
		leftMiddlePane.add(InterfaceGraphique.createButtonsPair(new bDeselCustListener(),new bAffCustListener()));
		//Champs a remplir
		leftMiddlePane.add(InterfaceGraphique.createCBVille("Ville de départ* :", cbVilleDep));
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
		rightMiddlePane.add(InterfaceGraphique.createListVol(dlmVols, lVols, 60, 150));
		// A voir pour l'ajout du bouton deselectionner tout si nécessaire
		rightMiddlePane.add(InterfaceGraphique.createGroupRadioButton("Classe :", rb1Classe, "1ère classe", rb2Classe, "2ème classe",true));
		rightMiddlePane.add(InterfaceGraphique.createSubTitle("Les hébergements possibles :"));
		// Les 2 listes hotels et catégories de chambres
		JPanel twoListPane = new JPanel();
		rightMiddlePane.add(twoListPane);
		twoListPane.setLayout(new GridLayout(1,2));
		JPanel leftTwoListPane = new JPanel();
		leftTwoListPane.setLayout(new BoxLayout(leftTwoListPane, BoxLayout.Y_AXIS));
		JPanel rightTwoListPane = new JPanel();
		rightTwoListPane.setLayout(new BoxLayout(rightTwoListPane, BoxLayout.Y_AXIS));
		twoListPane.add(leftTwoListPane);
		twoListPane.add(rightTwoListPane);
		leftTwoListPane.add(InterfaceGraphique.createListHotel(dlmHotel, lHotel, 30, 150));
		rightTwoListPane.add(InterfaceGraphique.createListCat(dlmCat, lCat, 30, 150));
		return rightMiddlePane;
	}
	
	/*private class bSearchReservListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ResultSet rs;
			FabriqueClient fc = FabriqueClient.getInstance();
			ArrayList<Client> lClients = new ArrayList<Client>();
			if (cbSearch.getSelectedItem() == "Prénom"){
				rs = BDDConnection.selectClientWithPrenom(tfSearch.getText());
			}
			else {
				rs = BDDConnection.selectClientWithNom(tfSearch.getText());
			}
			try {
				while (rs.next()){
					Client c = fc.createClient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
					lClients.add(c);
				}
				InterfaceGraphique.addClientIntoList(lClients, dlmCust);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}*/
	
	private class bDeselCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class bAffCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
	
	private class bSaveBookingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		}
	}
}
