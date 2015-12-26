package interfaceGraphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domaine.Ville;
import domaine.Vol;

public class TabVol extends JPanel{

	private JTextField tfSearchVilleDep;
	private DefaultListModel<Ville> dlmVillesDep;
	private JList<Ville> lVillesDep;
	private JTextField tfSearchVilleArr;
	private JList<Ville> lVillesArr;
	private DefaultListModel<Ville> dlmVillesArr;
	private JList<Vol> lVol;
	private DefaultListModel<Vol> dlmVol;
	private JTextField tfVilleDep;
	private JTextField tfJour;
	private JTextField tfHHeure;
	private JTextField tMnHeure;
	private JTextField tfHDurVol;
	private JTextField tfMnDurVol;
	private JTextField tfNbPers1Class;
	private JTextField tfTarif1Class;
	private JTextField tfTarif2Class;
	private JTextField tfNbPers2Class;
	private JTextField tfDelai;

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
		four.add(InterfaceGraphique.createInputBox("Ville de départ :", 20, tfVilleDep));
		four.add(InterfaceGraphique.createInputBox("Ville d'arrivée :", 20, tfVilleDep));
		four.add(InterfaceGraphique.createInputBox("Jour :", 20, tfJour));
		JPanel heure = new JPanel();
		heure.add(InterfaceGraphique.createInputBox("Heure : ", 5, tfHHeure));
		heure.add(InterfaceGraphique.createInputBox(": ", 5, tMnHeure));
		four.add(heure);
		JPanel durVol = new JPanel();
		durVol.add(InterfaceGraphique.createInputBox("Durée de vol : ", 5, tfHDurVol));
		durVol.add(InterfaceGraphique.createInputBox(": ", 5, tfMnDurVol));
		four.add(durVol);
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 1ère classe : ", 2, tfNbPers1Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 1ère classe : ", 10, tfTarif1Class));
		four.add(InterfaceGraphique.createInputBox("Nombre de passagers en 2ème classe : ", 10, tfNbPers2Class));
		four.add(InterfaceGraphique.createInputBox("Tarif en 2ème classe : ", 10, tfTarif2Class));
		four.add(InterfaceGraphique.createInputBox("Délai d'annulation :", 10, tfDelai));
		return four;
	}

	private JPanel createPaneThree() {
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three,BoxLayout.Y_AXIS));
		three.add(InterfaceGraphique.createSubTitle("Jour et heure des vols :"));
		three.add(InterfaceGraphique.createListVol(dlmVol, lVol, 60, 200));
		three.add(InterfaceGraphique.createOneButton(new DeselVolListener(), "Déselectionner tout"));
		return three;
	}

	private JPanel createPaneTwo() {
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two,BoxLayout.Y_AXIS));
		two.add(InterfaceGraphique.createSubTitle("Ville d'arrivée :"));
		two.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleArr, new SearchVilleArrListener()));
		two.add(InterfaceGraphique.createListVilles(dlmVillesArr, lVillesArr, 60, 200));
		two.add(InterfaceGraphique.createButtonsPair(new DeselVilleArrListener(), new AffVilleArrListener()));
		return two;
	}

	private JPanel createPaneOne() {
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one,BoxLayout.Y_AXIS));
		one.add(InterfaceGraphique.createSubTitle("Ville de départ :"));
		one.add(InterfaceGraphique.createSimpleSearch(tfSearchVilleDep, new SearchVilleDepListener()));
		one.add(InterfaceGraphique.createListVilles(dlmVillesDep, lVillesDep, 60, 200));
		one.add(InterfaceGraphique.createButtonsPair(new DeselVilleDepListener(), new AffVilleDepListener()));
		//one.add(InterfaceGraphique.createOneButton(new DeselVilleDepListener(), "Déselectionner tout"));
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
}
