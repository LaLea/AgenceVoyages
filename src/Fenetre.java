import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import interfaceGraphique.TabChambre;
import interfaceGraphique.TabClient;
import interfaceGraphique.TabHotel;
import interfaceGraphique.TabReservation;
import interfaceGraphique.TabVol;
import interfaceGraphique.TabVoyage;

/**
 *
 * @author Lea Vanelle
 */
public class Fenetre extends JFrame {
	
	/** Le cadre de la fenetre */
	private JFrame frame;
		
	/** 
	 * 
	 */
	public Fenetre(){
		//Create and set up the window.
        this.frame = new JFrame("Agence de voyages.");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        Container pane = this.frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        
        // Creation des onglets
        JTabbedPane tabs = new JTabbedPane();
        tabs.setTabPlacement(SwingConstants.TOP);
        frame.add(tabs);
        tabs.addTab("Reservation", new TabReservation());
        tabs.addTab("Gestion des hotels", new TabHotel());
        tabs.addTab("Gestion des clients", new TabClient());
        tabs.addTab("Gestion des voyages", new TabVoyage());
        tabs.addTab("Gestion des chambres", new TabChambre());// gestion des chambres et des categories
        tabs.addTab("Gestion des vols", new TabVol()); // gestion des lignes et du planning des lignes
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	
	/**
	 * Permet de récupérer le JFrame constituant la fenetre
	 * @return leJFrame constituant la fenetre
	 */
	public JFrame getJFrame(){
		return this.frame;
	}
	
	/** Main permettant de lancer le programme */
	public static void main(String[] args) {
		new Fenetre();
	}
}
