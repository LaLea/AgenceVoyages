package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

import domaine.Client;
import fabrique.BDDConnection;
import fabrique.FabriqueClient;

public class SearchCustListener implements ActionListener {

	private JComboBox<String> cbSearch;
	private JTextField tfSearch;
	private DefaultListModel<Client> dlmCust;
	private JList<Client> lCust;

	public SearchCustListener(JComboBox<String> cb, JTextField tf, DefaultListModel<Client> dlm, JList<Client> l) {
		this.cbSearch = cb;
		this.tfSearch = tf;
		this.dlmCust = dlm;
		this.lCust = l;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FabriqueClient fc = FabriqueClient.getInstance();
		ArrayList<Client> lClients = new ArrayList<Client>();
		lCust.clearSelection();
		dlmCust.clear();
		if (cbSearch.getSelectedItem() == "Prénom"){
			lClients = fc.rechercheClientParPrenom(tfSearch.getText());
		}
		else {
			lClients = fc.rechercheClientParNom(tfSearch.getText());
		}
		for (Client client : lClients) {
			dlmCust.addElement(client);
		}
		lCust.setSelectedIndex(0);
	}
}
