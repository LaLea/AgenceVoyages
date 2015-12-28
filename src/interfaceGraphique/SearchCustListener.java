package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import domaine.Client;
import fabrique.BDDConnection;
import fabrique.FabriqueClient;

public class SearchCustListener implements ActionListener {

	private JComboBox<String> cbSearch;
	private JTextField tfSearch;
	private DefaultListModel<Client> dlmCust;

	public SearchCustListener(JComboBox<String> cb, JTextField tf, DefaultListModel<Client> dlm) {
		this.cbSearch = cb;
		this.tfSearch = tf;
		this.dlmCust = dlm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
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
}
