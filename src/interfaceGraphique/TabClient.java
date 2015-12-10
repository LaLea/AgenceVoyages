package interfaceGraphique;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TabClient extends JPanel{
	public TabClient(){
		super();
		add(new JLabel("Gestion des clients"));
		add(new JLabel("Ajout d'un client"));
		add(new JTextField("Nom :"));
	}
}
