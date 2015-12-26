package interfaceGraphique;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import domaine.Categorie;
import domaine.Chambre;
import domaine.Client;
import domaine.Hotel;
import domaine.Ville;
import domaine.Vol;
import domaine.Voyage;

/**
 *
 * @author Lea Vanelle
 */
public class InterfaceGraphique extends JFrame {
	
	/** Le cadre de la fenetre */
	private JFrame frame;
		
	/** 
	 * 
	 */
	public InterfaceGraphique(){
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
        //tabs.addTab("Gestion des chambres", new TabChambre());// gestion des chambres et des categories
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
	
	public static JPanel createInputBox(String label, int taille, JTextField tf){
		JPanel content = new JPanel();
		content.add(new JLabel(label));
		tf = new JTextField(taille);
		content.add(tf);
		return content;
	}
	
	public static JPanel createTitle(String label){
		JPanel t = new JPanel();
		t.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(label);
		t.add(title);
		return t;
	}
	
	public static JPanel createSubTitle(String label){
		JPanel subTitle = new JPanel();
		subTitle.add(new JLabel(label));
		subTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		return subTitle;
	}
	
	public static JPanel createSearchCust(JTextField tf, ActionListener al, JComboBox<String> cb){
		JPanel search = new JPanel();
		tf = new JTextField(15);
		search.add(tf);
		String [] content = {"Prénom","Nom"};
		cb = new JComboBox<String>(content);
		search.add(cb);
		JButton bSearch = new JButton("Rechercher");
		bSearch.addActionListener(al);
		search.add(bSearch);
		return search;
	}
	
	public static JPanel createSimpleSearch(JTextField tf, ActionListener al){
		JPanel search = new JPanel();
		tf = new JTextField(15);
		search.add(tf);
		JButton bSearch = new JButton("Rechercher");
		bSearch.addActionListener(al);
		search.add(bSearch);
		return search;
	}
	
	public static JScrollPane createListVol(DefaultListModel<Vol> dlm, JList<Vol> l, int lgr, int htr){
		l = new JList<Vol>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//sp.setPreferredSize(new Dimension(70, 150));
		dlm = new DefaultListModel<Vol>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JScrollPane createListCust(DefaultListModel<Client> dlm, JList<Client> l, int lgr, int htr){
		l = new JList<Client>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//sp.setPreferredSize(new Dimension(70, 150));
		dlm = new DefaultListModel<Client>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JScrollPane createListHotel(DefaultListModel<Hotel> dlm, JList<Hotel> l, int lgr, int htr) {
		l = new JList<Hotel>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//sp.setPreferredSize(new Dimension(35, 150));
		dlm = new DefaultListModel<Hotel>();
		l.setModel(dlm);
		return sp;
	}

	public static JScrollPane createListCat(DefaultListModel<Categorie> dlm, JList<Categorie> l, int lgr, int htr) {
		l = new JList<Categorie>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//sp.setPreferredSize(new Dimension(35, 150));
		dlm = new DefaultListModel<Categorie>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JScrollPane createListVilles(DefaultListModel<Ville> dlm, JList<Ville> l, int lgr, int htr) {
		l = new JList<Ville>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		dlm = new DefaultListModel<Ville>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JScrollPane createListChbres(DefaultListModel<Chambre> dlm, JList<Chambre> l, int lgr, int htr) {
		l = new JList<Chambre>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		dlm = new DefaultListModel<Chambre>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JScrollPane createListVoyage(DefaultListModel<Voyage> dlm, JList<Voyage> l, int lgr, int htr) {
		l = new JList<Voyage>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		dlm = new DefaultListModel<Voyage>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JPanel createOneButton(ActionListener al, String label) {
		JPanel b = new JPanel();
		//b.setLayout(new GridLayout(1,1));
		//b.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton bSaveBooking = new JButton(label);
		b.add(bSaveBooking);
		bSaveBooking.addActionListener(al);
		return b;
	}
	
	public static JPanel createButtonsPair(ActionListener desel,ActionListener aff){
		JPanel buttonPair = new JPanel();
		// Deselectionner tout
		JButton bDeselCust = new JButton("Désélectionner tout");
		bDeselCust.addActionListener(desel);
		buttonPair.add(bDeselCust);
		// Afficher tout
		JButton bAffCust = new JButton("Afficher tout");
		bAffCust.addActionListener(aff);
		buttonPair.add(bAffCust);
		return buttonPair;
	}
	
	public static JPanel createButtonAddDelEd(ActionListener add, ActionListener del, ActionListener edit){
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton bAdd = new JButton("Ajouter");
		bAdd.addActionListener(add);
		JButton bDel = new JButton("Supprimer");
		bDel.addActionListener(del);
		JButton bEdit = new JButton("Modifier");
		bEdit.addActionListener(edit);
		return buttons;
	}
	
	public static JPanel createDateChooser(String label, JDateChooser dcDte){
		JPanel chooseDate = new JPanel();
		chooseDate.add(new JLabel(label));
		dcDte = new JDateChooser();
		dcDte.setPreferredSize(new Dimension(145,20));
		chooseDate.add(dcDte);
		return chooseDate;
	}
	
	public static JPanel createCBVille(String label, JComboBox<String> cb){
		JPanel CBVille = new JPanel();
		CBVille.add(new JLabel(label));
		String [] vs = {"","France - Lesquin","France - Paris","Belgique - Bruxelles", "R-U - Londres"};
		cb = new JComboBox<String>(vs);
		cb.setPreferredSize(new Dimension(145,20));
		CBVille.add(cb);
		return CBVille;
	}
	
	public static JPanel createGroupRadioButton(String label, JRadioButton b1,String l1, JRadioButton b2, String l2,Boolean avecFL) {
		JPanel gbPane = new JPanel();
		if (avecFL){
			gbPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		gbPane.add(InterfaceGraphique.createSubTitle(label));
		ButtonGroup bg = new ButtonGroup();
		b1 = new JRadioButton(l1);
		b2 = new JRadioButton(l2);
		bg.add(b1);
		bg.add(b2);
		gbPane.add(b1);
		gbPane.add(b2);
		return gbPane;
	}
	
	/** Main permettant de lancer le programme */
	public static void main(String[] args) {
		new InterfaceGraphique();
	}	
}
