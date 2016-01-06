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
		p.add(InterfaceGraphique.createListCust(dlmCust, lClient, 60, 200, new lstCustListener()));
		//InterfaceGraphique.addAllClientsIntoList(dlmCust);
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
			tfCustNom.setText("");
			tfPrenomCust.setText("");
			dcDteNaissCust.setDate(new Date());
			tfVilleCust.setText("");
			tfPaysCust.setText("");
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
			FabriqueClient fc = FabriqueClient.getInstance();
			FabriqueVille fv = FabriqueVille.getInstance();
			Ville v = fv.getVilleBDDWithNomAndHotel(tfVilleCust.getText(), tfPaysCust.getText()); //Récupération de l'id de la ville
			Date d = dcDteNaissCust.getDate();
			Client clt = fc.addClient(tfCustNom.getText(), tfPrenomCust.getText(), v.getId_ville(), d.getDate(), d.getMonth(), d.getYear()); //Ajout du client dans la base et dans la fabrique
			//dlmCust.addElement(clt); // Ajout du client dans liste de l'interface
			InterfaceGraphique.addClientIntoAllList(clt);
			// Remise à zéro des champs
			tfCustNom.setText("");
			tfPrenomCust.setText("");
			dcDteNaissCust.setDate(new Date());
			tfVilleCust.setText("");
			tfPaysCust.setText("");
		}
	}
	
	private class DelCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			FabriqueClient fc = FabriqueClient.getInstance();
			Client c = lClient.getSelectedValue();
			InterfaceGraphique.delClientIntoAllList(c);
			fc.deleteClient(c.getId_client());
		}	
	}
	
	private class EditCustListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dcDteNaissCust.setDate(sdf.parse(c.getJour() + "/" + c.getMois() + "/" + c.getAnnee()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Ville v = fv.getVilleWithIdVille(idVille);
				tfVilleCust.setText(v.getNom());
				tfPaysCust.setText(v.getPays());
			}
		}
	}
}

