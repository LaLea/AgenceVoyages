package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import domaine.Client;
import domaine.Ville;
import fabrique.FabriqueClient;
import fabrique.FabriqueVille;
import metier.GestionClient;

public class TabClient extends JPanel{
	// Pane de gauche
	private JTextField tfSearchCust = new JTextField(15);
	private JComboBox<String> cbSearchCust = new JComboBox<String>();
	private DefaultListModel<Client> dlmCust = new DefaultListModel<Client>();;
	private JList<Client> lClient = new JList<Client>();
	// Pane de droite
	private JTextField tfCustNom = new JTextField(20);
	private JTextField tfPrenomCust = new JTextField(20);
	private JDateChooser dcDteNaissCust = new JDateChooser();
	private JTextField tfVilleCust = new JTextField(20);
	private JTextField tfPaysCust = new JTextField(20);

	public DefaultListModel<Client> getDlmCust (){
		return dlmCust;
	}
	
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
		p.add(InterfaceGraphique.createSearchCust(tfSearchCust, cbSearchCust, dlmCust, lClient));
		p.add(InterfaceGraphique.createSubTitle("Les clients :"));
		p.add(InterfaceGraphique.createListCust(dlmCust, lClient, 40, 300, new lstCustListener()));
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
		p.add(InterfaceGraphique.createInputBox("Pays d'origine : ", tfPaysCust));
		p.add(InterfaceGraphique.createButtonAddDelEd(new AddCustListener(), new DelCustListener(), new EditCustListener()));
		return p;
	}
	
	private class DeselCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lClient.clearSelection();
		}
	}
	
	private class AffToutCustListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dlmCust.clear();
			InterfaceGraphique.addAllClientsIntoOneList(dlmCust);
		}
	}
	
	private class AddCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Date d = dcDteNaissCust.getDate();
			Client clt = GestionClient.ajoutVoyageur(tfCustNom.getText(), tfPrenomCust.getText(), tfVilleCust.getText(), tfPaysCust.getText(), d);
			InterfaceGraphique.addClientIntoAllList(clt); // Ajout du client dans liste de l'interface
			lClient.setSelectedValue(clt, true); // Selection du client qui vient d'etre créé
		}
	}
	
	private class DelCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Client c = lClient.getSelectedValue();
			InterfaceGraphique.delClientIntoAllList(c);
			GestionClient.supprimerClient(c.getId_client());
		}	
	}
	
	private class EditCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Client clt = lClient.getSelectedValue();
			//if(tfCustNom.getText() != clt.getNom()
				//	|| tfPrenomCust.getText() != clt.getPrenom()
					//|| dcDteNaissCust.getDate()!= clt.getDateDeNaissance()){
				GestionClient.modifierClient(tfCustNom.getText(), tfPrenomCust.getText(), tfVilleCust.getText(), tfPaysCust.getText(), dcDteNaissCust.getDate());
				// Voir si le client est modifié dans toutes les listes
			//}
		}
	}
	
	private class lstCustListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if(!lClient.isSelectionEmpty()){
				FabriqueVille fv = FabriqueVille.getInstance();
				Client c = lClient.getSelectedValue();
				int idVille = c.getId_villeOrigine();
				tfCustNom.setText(c.getNom());
				tfPrenomCust.setText(c.getPrenom());
				dcDteNaissCust.setDate(c.getDateDeNaissance());
				Ville v = fv.getVilleWithIdVille(idVille);
				tfVilleCust.setText(v.getNom());
				tfPaysCust.setText(v.getPays());
			}
			else {
				tfCustNom.setText("");
				tfPrenomCust.setText("");
				tfVilleCust.setText("");
				tfPaysCust.setText("");
				dcDteNaissCust.setDate(new Date());
			}
		}
	}
}

