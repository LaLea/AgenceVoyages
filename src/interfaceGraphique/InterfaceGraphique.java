package interfaceGraphique;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JDateChooser;

import domaine.Categorie;
import domaine.Chambre;
import domaine.Client;
import domaine.Hotel;
import domaine.Ville;
import domaine.Vol;
import domaine.Voyage;
import fabrique.FabriqueClient;
import fabrique.FabriqueVille;

/**
 *
 * @author Lea Vanelle
 */
public class InterfaceGraphique extends JFrame {
	
	/** Le cadre de la fenetre */
	private JFrame frame;
	private static TabReservation tReserv = new TabReservation();
	private static TabHotel tHotel = new TabHotel();
	private static TabClient tClient = new TabClient();
	private static TabVoyage tVoy = new TabVoyage();
	private static TabVol tVol = new TabVol();
	
		
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
        tabs.addTab("Reservation", tReserv);
        tabs.addTab("Gestion des hotels", tHotel);
        tabs.addTab("Gestion des clients", tClient);
        tabs.addTab("Gestion des voyages", tVoy);
        tabs.addTab("Gestion des vols", tVol); // gestion des lignes et du planning des lignes
        
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
	
	public static JPanel createInputBox(String label, JTextField tf){
		JPanel content = new JPanel();
		JLabel txt = new JLabel(label);
		Font f = new Font("Calibri", Font.PLAIN, 14);
		txt.setFont(f);
		txt.setForeground(new Color(95, 73, 122));
		content.add(txt);
		content.add(tf);
		return content;
	}
	
	public static JPanel createHeureInputBox(String label, JTextField tfHeure, JTextField tfMinute){
		JPanel heure = new JPanel();
		JLabel h = new JLabel(label);
		Font f = new Font("Calibri", Font.PLAIN, 14);
		h.setFont(f);
		h.setForeground(new Color(95, 73, 122));
		heure.add(h);
		heure.add(tfHeure);
		JLabel p = new JLabel(":");
		p.setFont(f);
		p.setForeground(new Color(95, 73, 122));
		heure.add(p);
		heure.add(tfMinute);
		return heure;
	}
	
	public static JPanel createTitle(String label){
		JPanel t = new JPanel();
		t.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel title = new JLabel(label);
		Font f = new Font("Calibri", Font.BOLD, 25);
		title.setFont(f);
		title.setForeground(new Color(95, 73, 122));
		//title.setForeground(new Color(149, 185, 51));
		t.add(title);
		return t;
	}
	
	public static JPanel createSubTitle(String label){
		JPanel subTitle = new JPanel();
		JLabel txt = new JLabel(label);
		Font f = new Font("Calibri", Font.BOLD, 16);
		txt.setFont(f);
		//txt.setForeground(new Color(149, 185, 51));
		txt.setForeground(new Color(95, 73, 122));
		subTitle.add(txt);
		subTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		return subTitle;
	}
	
	public static JPanel createSearchCust(JTextField tf,  JComboBox<String> cb, DefaultListModel<Client> dlm, JList<Client> l){
		Color c = new Color(195, 220, 126);
		JPanel search = new JPanel();
		search.add(tf);
		cb.addItem("Prénom");
		cb.addItem("Nom");
		cb.setFont(new Font("Calibri", Font.PLAIN, 12));
		search.add(cb);
		JButton bSearch = new JButton("Rechercher");
		bSearch.setBackground(c);
		bSearch.addActionListener(new SearchCustListener(cb, tf, dlm, l));
		search.add(bSearch);
		return search;
	}
	
	public static JPanel createSimpleSearch(JTextField tf, ActionListener al){
		JPanel search = new JPanel();
		tf = new JTextField(15);
		search.add(tf);
		JButton bSearch = new JButton("Rechercher");
		bSearch.setBackground(new Color(195, 220, 126));
		bSearch.addActionListener(al);
		search.add(bSearch);
		return search;
	}
	
	// Liste des vols
	public static JScrollPane createListVol(DefaultListModel<Vol> dlm, JList<Vol> l, int lgr, int htr){
		//l = new JList<Vol>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Vol>();
		l.setModel(dlm);
		return sp;
	}
	
	// Liste des clients
	public static JScrollPane createListCust(DefaultListModel<Client> dlm, JList<Client> l, int lgr, int htr, ListSelectionListener lstListener){
		l.addListSelectionListener(lstListener);
		JScrollPane sp = new JScrollPane(l);
		//sp.setFont(new Font("Calibri", Font.PLAIN, 14));
		l.setFont(new Font("Calibri", Font.PLAIN, 14));
		sp.setPreferredSize(new Dimension(lgr, htr));
		l.setModel(dlm);
		return sp;
	}
	
	public static void addAllClientsIntoOneList(DefaultListModel<Client> dlmCust){
		FabriqueClient fc = FabriqueClient.getInstance();
		ArrayList<Client> lesClients = fc.allClients();
		for (Client client : lesClients) {
			dlmCust.addElement(client);
		}
	}
	
	public static void addAllClientsIntoAllList(){
		FabriqueClient fc = FabriqueClient.getInstance();
		ArrayList<Client> lesClients = fc.allClients();
		for (Client client : lesClients) {
			addClientIntoAllList(client);
		}
	}
	
	public static void addClientIntoAllList(Client c){
		tClient.getDlmCust().addElement(c);
		tVoy.getDlmCust().addElement(c);
		tReserv.getDlmCust().addElement(c);
	}
	
	public static void delClientIntoAllList(Client c){
		tVoy.getDlmCust().removeElement(c);
		tClient.getDlmCust().removeElement(c);
		tReserv.getDlmCust().removeElement(c);
	}
	
	// Liste des hotels
	public static JScrollPane createListHotel(DefaultListModel<Hotel> dlm, JList<Hotel> l, int lgr, int htr, ListSelectionListener lstListener) {
		//l = new JList<Hotel>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Hotel>();
		l.setModel(dlm);
		l.addListSelectionListener(lstListener);
		return sp;
	}
	
	public static void addHotelsIntoOneList(ArrayList<Hotel> lstHotels, DefaultListModel<Hotel> dlm){
		for (Hotel hotel : lstHotels) {
			dlm.addElement(hotel);
		}
	}

	// Liste des catégories
	public static JScrollPane createListCat(DefaultListModel<Categorie> dlm, JList<Categorie> l, int lgr, int htr) {
		//l = new JList<Categorie>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Categorie>();
		l.setModel(dlm);
		return sp;
	}
	
	// Liste des villes
	public static JScrollPane createListVilles(DefaultListModel<Ville> dlm, JList<Ville> l, int lgr, int htr, ListSelectionListener lsl) {
		//l = new JList<Ville>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Ville>();
		l.addListSelectionListener(lsl);
		l.setModel(dlm);
		return sp;
	}
	
	public static void addAllVillesIntoOneList(DefaultListModel<Ville> dlmVille){
		FabriqueVille fv = FabriqueVille.getInstance();
		ArrayList<Ville> lesVilles = fv.allVilles();
		for (Ville ville : lesVilles) {
			dlmVille.addElement(ville);
		}
	}
	
	public static void addAllVillesIntoAllList(){
		FabriqueVille fv = FabriqueVille.getInstance();
		ArrayList<Ville> lesVilles = fv.allVilles();
		for (Ville ville : lesVilles) {
			addVilleIntoAllList(ville);
		}
	}
	
	public static void addVilleIntoAllList(Ville v){
		tHotel.getDlmVille().addElement(v);
		tVol.getDlmVille().addElement(v);
	}
	
	public static void delVilleIntoAllList(Ville v){
		tHotel.getDlmVille().removeElement(v);
		tVol.getDlmVille().removeElement(v);
	}
	
	// Liste des chambres
	public static JScrollPane createListChbres(DefaultListModel<Chambre> dlm, JList<Chambre> l, int lgr, int htr) {
		//l = new JList<Chambre>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Chambre>();
		l.setModel(dlm);
		return sp;
	}
	
	// Liste des voyages
	public static JScrollPane createListVoyage(DefaultListModel<Voyage> dlm, JList<Voyage> l, int lgr, int htr) {
		//l = new JList<Voyage>();
		JScrollPane sp = new JScrollPane(l);
		sp.setPreferredSize(new Dimension(lgr, htr));
		//dlm = new DefaultListModel<Voyage>();
		l.setModel(dlm);
		return sp;
	}
	
	public static JPanel createOneButton(ActionListener al, String label) {
		JPanel b = new JPanel();
		JButton bSaveBooking = new JButton(label);
		bSaveBooking.setBackground(new Color(195, 220, 126));
		//MyButton bSaveBooking = new MyButton(label);
		b.add(bSaveBooking);
		bSaveBooking.addActionListener(al);
		return b;
	}
	
	public static JPanel createButtonsPair(ActionListener desel,ActionListener aff){
		Color c = new Color(195, 220, 126);
		JPanel buttonPair = new JPanel();
		// Deselectionner tout
		//MyButton bDeselCust = new MyButton("Désélectionner tout");
		JButton bDeselCust = new JButton("Désélectionner tout");
		bDeselCust.setBackground(c);
		bDeselCust.addActionListener(desel);
		buttonPair.add(bDeselCust);
		// Afficher tout
		//MyButton bAffCust = new MyButton("Afficher tout");
		JButton bAffCust = new JButton("Afficher tout");
		bAffCust.setBackground(c);
		bAffCust.addActionListener(aff);
		buttonPair.add(bAffCust);
		return buttonPair;
	}
	
	public static JPanel createButtonAddDelEd(ActionListener add, ActionListener del, ActionListener edit){
		Color c = new Color(195, 220, 126);
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		//MyButton bAdd = new MyButton("Ajouter");
		JButton bAdd = new JButton("Ajouter");
		bAdd.setBackground(c);
		bAdd.addActionListener(add);
		buttons.add(bAdd);
		//MyButton bDel = new MyButton("Supprimer");
		JButton bDel = new JButton("Supprimer");
		bDel.setBackground(c);
		bDel.addActionListener(del);
		buttons.add(bDel);
		//MyButton bEdit = new MyButton("Modifier");
		JButton bEdit = new JButton("Modifier");
		bEdit.setBackground(c);
		bEdit.addActionListener(edit);
		buttons.add(bEdit);
		return buttons;
	}
	
	public static JPanel createButtonAddDel(ActionListener add, ActionListener del){
		Color c = new Color(195, 220, 126);
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
		//MyButton bAdd = new MyButton("Ajouter");
		JButton bAdd = new JButton("Ajouter");
		bAdd.setBackground(c);
		bAdd.addActionListener(add);
		buttons.add(bAdd);
		//MyButton bDel = new MyButton("Supprimer");
		JButton bDel = new JButton("Supprimer");
		bDel.setBackground(c);
		bDel.addActionListener(del);
		buttons.add(bDel);
		return buttons;
	}
	
	public static JPanel createDateChooser(String label, JDateChooser dcDte){
		JPanel chooseDate = new JPanel();
		JLabel txt = new JLabel(label);
		Font f = new Font("Calibri", Font.PLAIN, 14);
		txt.setFont(f);
		txt.setForeground(new Color(95, 73, 122));
		chooseDate.add(txt);
		dcDte.setPreferredSize(new Dimension(145,20));
		dcDte.setBackground(new Color(195, 220, 126));
		chooseDate.add(dcDte);
		return chooseDate;
	}
	
	public static JPanel createCBVille(String label, JComboBox<String> cb){
		JPanel CBVille = new JPanel();
		JLabel txt = new JLabel(label);
		Font f = new Font("Calibri", Font.PLAIN, 14);
		txt.setFont(f);
		txt.setForeground(new Color(95, 73, 122));
		CBVille.add(txt);
		String [] vs = {"","France - Lesquin","France - Paris","Belgique - Bruxelles", "R-U - Londres"};
		cb = new JComboBox<String>(vs);
		cb.setFont(new Font("Calibri",Font.PLAIN,12));
		cb.setPreferredSize(new Dimension(145,20));
		//cb.setBackground(new Color(195, 220, 126));
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
		b1.setFont(new Font("Calibri", Font.PLAIN, 12));
		b1.setForeground(new Color(95, 73, 122));
		b2.setFont(new Font("Calibri", Font.PLAIN, 12));
		b2.setForeground(new Color(95, 73, 122));
		bg.add(b1);
		bg.add(b2);
		gbPane.add(b1);
		gbPane.add(b2);
		return gbPane;
	}
	
	/** Main permettant de lancer le programme */
	public static void main(String[] args) {
		InterfaceGraphique ig = new InterfaceGraphique();
		addAllClientsIntoAllList();
		addAllVillesIntoAllList();
	}

	
}
