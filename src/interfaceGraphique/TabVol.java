package interfaceGraphique;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domaine.Ville;
import domaine.Vol;

public class TabVol extends JPanel{

	private JTextField tfSearchVilleDep = new JTextField(15);
	private DefaultListModel<Ville> dlmVillesDep = new DefaultListModel<Ville>();
	private JList<Ville> lVillesDep = new JList<Ville>();
	private JTextField tfSearchVilleArr = new JTextField(15);
	private JList<Ville> lVillesArr = new JList<Ville>();
	private DefaultListModel<Ville> dlmVillesArr = new DefaultListModel<Ville>();
	private JList<Vol> lVol = new JList<Vol>();
	private DefaultListModel<Vol> dlmVol = new DefaultListModel<Vol>();
	private JTextField tfVilleDep = new JTextField(20);
	private JTextField tfVilleAr = new JTextField(20);
	private JTextField tfJour = new JTextField(20);
	private JTextField tfHHeure = new JTextField(2);
	private JTextField tfMnHeure = new JTextField(2);
	private JTextField tfHDurVol = new JTextField(2);
	private JTextField tfMnDurVol = new JTextField(2);
	private JTextField tfNbPers1Class = new JTextField(2);
	private JTextField tfTarif1Class = new JTextField(10);
	private JTextField tfTarif2Class = new JTextField(10);
	private JTextField tfNbPers2Class = new JTextField(2);
	private JTextField tfDelai = new JTextField(10);
	
	public TabVol(){
		super();
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(InterfaceGraphique.createTitle("Gestion des vols, du planning des vols."));
		JPanel midPane = new JPanel();
		add(midPane);
		midPane.setAlignmentY(BOTTOM_ALIGNMENT);
		midPane.add(createPaneOne());
		midPane.add(createPaneTwo());
		midPane.add(createPaneThree());
		midPane.add(createPaneFour());
	}

	private JPanel createPaneFour() {
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four,BoxLayout.Y_AXIS));
		four.add(InterfaceGraphique.createSubTitle("Le vol :"));
		four.add(InterfaceGraphique.createInputBox("Ville de départ :", tfVilleDep));
		four.add(InterfaceGraphique.createInputBox("Ville d'arrivée :", tfVilleAr ));
		four.add(InterfaceGraphique.createInputBox("Jour :", tfJour));
		four.add(InterfaceGraphique.createHeureInputBox("Heure : ", tfHHeure, tfMnHeure));
		four.add(InterfaceGraphique.createHeureInputBox("Durée de vol : ", tfHDurVol, tfMnDurVol));
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 1ère classe : ", tfNbPers1Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 1ère classe : ", tfTarif1Class));
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 2ème classe : ", tfNbPers2Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 2ème classe : ", tfTarif2Class));
		four.add(InterfaceGraphique.createInputBox("Délai d'annulation :", tfDelai));
		four.add(InterfaceGraphique.createButtonAddDelEd(new addVolListener(), new delVolListener(), new editVolListener()));
		four.add(InterfaceGraphique.createOneButton(new GenerateLinesListener(), "Générer les lignes"));
		return four;
	}

	private JPanel createPaneThree() {
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three,BoxLayout.Y_AXIS));
		three.add(InterfaceGraphique.createSubTitle("Jour et heure des vols :"));
		three.add(InterfaceGraphique.createListVol(dlmVol, lVol, 60, 440));
		three.add(InterfaceGraphique.createOneButton(new DeselVolListener(), "Déselectionner tout"));
		return three;
	}

	private JPanel createPaneTwo() {
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two,BoxLayout.Y_AXIS));
		two.add(InterfaceGraphique.createSubTitle("Ville d'arrivée :"));
		two.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleArr, new SearchVilleArrListener()));
		two.add(InterfaceGraphique.createListVilles(dlmVillesArr, lVillesArr, 60, 400, new lstVilleArrListener()));
		two.add(InterfaceGraphique.createButtonsPair(new DeselVilleArrListener(), new AffVilleArrListener()));
		return two;
	}

	private JPanel createPaneOne() {
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one,BoxLayout.Y_AXIS));
		one.add(InterfaceGraphique.createSubTitle("Ville de départ :"));
		one.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleDep, new SearchVilleDepListener()));
		one.add(InterfaceGraphique.createListVilles(dlmVillesDep, lVillesDep, 60, 400, new lstVilleDepListener()));
		one.add(InterfaceGraphique.createButtonsPair(new DeselVilleDepListener(), new AffVilleDepListener()));
		return one;
	}
	
	public DefaultListModel<Ville> getDlmVille(){
		return dlmVillesDep;
	}
	
	private class SearchVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class AffVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class SearchVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class DeselVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class AffVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DeselVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class addVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class editVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class delVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class lstVilleDepListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class lstVilleArrListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class GenerateLinesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
