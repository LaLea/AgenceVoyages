package interfaceGraphique;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabReservation extends JPanel{
	
	public TabReservation(){
		super();
		setLayout(new BoxLayout(arg0, arg1));
		add(new JLabel("Gestion des reservations"));
		
	}
}
