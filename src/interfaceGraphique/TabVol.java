package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domaine.Ville;
import domaine.Vol;

public class TabVol extends JPanel{

	private JTextField tfSearchVilleDep = new JTextField(15);
	private DefaultListModel<Ville> dlmVillesDep;
	private JList<Ville> lVillesDep;
	private JTextField tfSearchVilleArr = new JTextField(15);
	private JList<Ville> lVillesArr;
	private DefaultListModel<Ville> dlmVillesArr;
	private JList<Vol> lVol;
	private DefaultListModel<Vol> dlmVol;
	private JTextField tfVilleDep = new JTextField(20);
	private JTextField tfVilleAr = new JTextField(20);
	private JTextField tfJour = new JTextField(20);
	private JTextField tfHHeure = new JTextField(2);
	private JTextField tMnHeure = new JTextField(2);
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
		midPane.add(createPaneOne());
		midPane.add(createPaneTwo());
		midPane.add(createPaneThree());
		midPane.add(createPaneFour());
	}

	private JPanel createPaneFour() {
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four,BoxLayout.Y_AXIS));
		four.add(InterfaceGraphique.createSubTitle("Le vol :"));
		four.add(InterfaceGraphique.createInputBox("Ville de d�part :", tfVilleDep));
		four.add(InterfaceGraphique.createInputBox("Ville d'arriv�e :", tfVilleAr ));
		four.add(InterfaceGraphique.createInputBox("Jour :", tfJour));
		JPanel heure = new JPanel();
		heure.add(new JLabel("Heure :"));
		heure.add(tfHHeure);
		heure.add(new JLabel(":"));
		heure.add(tMnHeure);
		//heure.add(InterfaceGraphique.createInputBox("Heure : ",tfHHeure));
		//heure.add(InterfaceGraphique.createInputBox(": ", tMnHeure));
		four.add(heure);
		JPanel durVol = new JPanel();
		durVol.add(new JLabel("Dur�e de vol :"));
		durVol.add(tfHDurVol);
		durVol.add(new JLabel(":"));
		durVol.add(tfMnDurVol);
		//durVol.add(InterfaceGraphique.createInputBox("Dur�e de vol : ", tfHDurVol));
		//durVol.add(InterfaceGraphique.createInputBox(": ", tfMnDurVol));
		four.add(durVol);
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 1�re classe : ", tfNbPers1Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 1�re classe : ", tfTarif1Class));
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 2�me classe : ", tfNbPers2Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 2�me classe : ", tfTarif2Class));
		four.add(InterfaceGraphique.createInputBox("D�lai d'annulation :", tfDelai));
		four.add(InterfaceGraphique.createButtonAddDelEd(new addVolListener(), new delVolListener(), new editVolListener()));
		return four;
	}

	private JPanel createPaneThree() {
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three,BoxLayout.Y_AXIS));
		three.add(InterfaceGraphique.createSubTitle("Jour et heure des vols :"));
		three.add(InterfaceGraphique.createListVol(dlmVol, lVol, 60, 200));
		three.add(InterfaceGraphique.createOneButton(new DeselVolListener(), "D�selectionner tout"));
		return three;
	}

	private JPanel createPaneTwo() {
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two,BoxLayout.Y_AXIS));
		two.add(InterfaceGraphique.createSubTitle("Ville d'arriv�e :"));
		two.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleArr, new SearchVilleArrListener()));
		two.add(InterfaceGraphique.createListVilles(dlmVillesArr, lVillesArr, 60, 200));
		two.add(InterfaceGraphique.createButtonsPair(new DeselVilleArrListener(), new AffVilleArrListener()));
		return two;
	}

	private JPanel createPaneOne() {
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one,BoxLayout.Y_AXIS));
		one.add(InterfaceGraphique.createSubTitle("Ville de d�part :"));
		one.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleDep, new SearchVilleDepListener()));
		one.add(InterfaceGraphique.createListVilles(dlmVillesDep, lVillesDep, 60, 200));
		one.add(InterfaceGraphique.createButtonsPair(new DeselVilleDepListener(), new AffVilleDepListener()));
		//one.add(InterfaceGraphique.createOneButton(new DeselVilleDepListener(), "D�selectionner tout"));
		//one.add(InterfaceGraphique.createOneButton(new AffVilleDepListener(), "Afficher tout"));
		return one;
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
}
