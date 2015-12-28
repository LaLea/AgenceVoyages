package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import domaine.Client;
import fabrique.BDDConnection;
import fabrique.FabriqueClient;

public class TabClient extends JPanel{
	// Pane de gauche
	private JTextField tfSearchCust = new JTextField(15);
	private JComboBox<String> cbSearchCust;
	private DefaultListModel<Client> dlmCust;
	private JList<Client> lClient;
	// Pane de droite
	private JTextField tfCustNom = new JTextField(20);
	private JTextField tfPrenomCust = new JTextField(20);
	private JDateChooser dcDteNaissCust = new JDateChooser();
	private JTextField tfVilleCust = new JTextField(20);

	public TabClient(){
		super();
		JPanel general = new JPanel();
		general.setLayout(new BoxLayout(general, BoxLayout.Y_AXIS));
		add(general);
		general.add(InterfaceGraphique.createTitle("Gestion des clients."));
		JPanel middle = new JPanel();
		general.add(middle);
		middle.add(createLeftPane());
		middle.add(createRightPane());
	}

	private JPanel createLeftPane() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.add(InterfaceGraphique.createSubTitle("Rechercher un client :"));
		p.add(InterfaceGraphique.createSearchCust(tfSearchCust, new SearchCustListener(cbSearchCust, tfCustNom, dlmCust), cbSearchCust));
		p.add(InterfaceGraphique.createSubTitle("Les clients :"));
		p.add(InterfaceGraphique.createListCust(dlmCust, lClient, 60, 200));
		p.add(InterfaceGraphique.createButtonsPair(new DeselCustListener(), new AffToutCustListener()));
		return p;
	}

	private JPanel createRightPane() {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		p.add(InterfaceGraphique.createSubTitle("Le client :"));
		p.add(InterfaceGraphique.createInputBox("Nom :", tfCustNom));
		p.add(InterfaceGraphique.createInputBox("Prénom :", tfPrenomCust));
		p.add(InterfaceGraphique.createDateChooser("Date de naissance :", dcDteNaissCust));
		p.add(InterfaceGraphique.createInputBox("Ville d'origine :", tfVilleCust));
		p.add(InterfaceGraphique.createButtonAddDelEd(new AddCustListener(), new DelCustListener(), new EditCustListener()));
		return p;
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
	
	private class AddCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			FabriqueClient fc = FabriqueClient.getInstance();
			int idVille = BDDConnection.selectIDVille(tfVilleCust.getText());
			fc.addClient(tfCustNom.getText(), tfPrenomCust.getText(), idVille, dcDteNaissCust.getDate().toString());
		}
	}
	
	private class DelCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
	
	private class EditCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}

