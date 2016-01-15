package interfaceGraphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.org.apache.bcel.internal.generic.Select;

import domaine.Ligne;
import domaine.Ville;
import fabrique.FabriqueLigne;
import metier.GestionLigne;
import metier.GestionVol;

public class TabVol extends JPanel{

	private JTextField tfSearchVilleDep = new JTextField(15);
	private DefaultListModel<Ville> dlmVillesDep = new DefaultListModel<Ville>();
	private JList<Ville> lVillesDep = new JList<Ville>();
	private JTextField tfSearchVilleArr = new JTextField(15);
	private JList<Ville> lVillesArr = new JList<Ville>();
	private DefaultListModel<Ville> dlmVillesArr = new DefaultListModel<Ville>();
	private JList<Ligne> lLigne = new JList<Ligne>();
	private DefaultListModel<Ligne> dlmLigne = new DefaultListModel<Ligne>();
	private JTextField tfVilleDep = new JTextField(20);
	private JTextField tfVilleAr = new JTextField(20);
	private JTextField tfJour = new JTextField(20);
	private JCheckBox cbLundi = new JCheckBox("Lundi");
	private JCheckBox cbMardi = new JCheckBox("Mardi");
	private JCheckBox cbMercredi = new JCheckBox("Mercredi");
	private JCheckBox cbJeudi = new JCheckBox("Jeudi");
	private JCheckBox cbVendredi = new JCheckBox("Vendredi");
	private JCheckBox cbSamedi = new JCheckBox("Samedi");
	private JCheckBox cbDimanche = new JCheckBox("Dimanche");
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
		//four.add(InterfaceGraphique.createInputBox("Jour :", tfJour));
		four.add(this.createGroupCheckBox());
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
		three.add(InterfaceGraphique.createListLigne(dlmLigne, lLigne, 60, 440, new lstLignesListener()));
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
	
	public DefaultListModel<Ville> getDlmVilleDep(){
		return dlmVillesDep;
	}
	
	public DefaultListModel<Ville> getDlmVilleArr(){
		return dlmVillesArr;
	}
	
	private JPanel createGroupCheckBox(){
		Font f = new Font("Calibri", Font.PLAIN, 14);
		Color c = new Color(95, 73, 122);
		JPanel cb = new JPanel();
		cb.setLayout(new BoxLayout(cb, BoxLayout.Y_AXIS));
		JPanel cbOne = new JPanel();
		cbOne.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("Jours de vol : ");
		label.setFont(f);
		label.setForeground(c);
		cbOne.add(label);
		cbLundi.setFont(f);
		cbLundi.setForeground(c);
		cbOne.add(cbLundi);
		cbMardi.setForeground(c);
		cbMardi.setFont(f);
		cbOne.add(cbMardi);
		cbMercredi.setForeground(c);
		cbMercredi.setFont(f);
		cbOne.add(cbMercredi);
		JPanel cbTwo = new JPanel();
		cbOne.setLayout(new FlowLayout(FlowLayout.LEFT));
		cbJeudi.setForeground(c);
		cbJeudi.setFont(f);
		cbTwo.add(cbJeudi);
		cbVendredi.setForeground(c);
		cbVendredi.setFont(f);
		cbTwo.add(cbVendredi);
		cbSamedi.setForeground(c);
		cbSamedi.setFont(f);
		cbTwo.add(cbSamedi);
		cbDimanche.setForeground(c);
		cbDimanche.setFont(f);
		cbTwo.add(cbDimanche);
		cb.add(cbOne);
		cb.add(cbTwo);
		return cb;
	}
	
	private class SearchVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// !!!!!!!!!!!!!!!!!!! manque requete
			
		}
	}
	
	private class DeselVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lVillesDep.clearSelection();
			lVillesArr.clearSelection();
			lLigne.clearSelection();
			tfVilleDep.setText("");
			tfVilleAr.setText("");
			tfJour.setText("");
			tfHHeure.setText("");
			tfMnHeure.setText("");
			tfHDurVol.setText("");
			tfMnDurVol.setText("");
			tfNbPers1Class.setText("");
			tfNbPers2Class.setText("");
			tfTarif1Class.setText("");
			tfTarif2Class.setText("");
			tfDelai.setText("");
		}
	}
	
	private class AffVilleDepListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			InterfaceGraphique.addAllVillesIntoOneList(dlmVillesDep);			
		}
	}
	
	private class SearchVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// !!!!!!!!!!! manque requete
		}
	}
	
	public class DeselVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lVillesArr.clearSelection();
			lLigne.clearSelection();
			tfVilleDep.setText("");
			tfVilleAr.setText("");
			tfJour.setText("");
			tfHHeure.setText("");
			tfMnHeure.setText("");
			tfHDurVol.setText("");
			tfMnDurVol.setText("");
			tfNbPers1Class.setText("");
			tfNbPers2Class.setText("");
			tfTarif1Class.setText("");
			tfTarif2Class.setText("");
			tfDelai.setText("");
		}
	}
	
	public class AffVilleArrListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			InterfaceGraphique.addAllVillesIntoOneList(dlmVillesArr);
		}
	}
	
	private class DeselVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			lLigne.clearSelection();
			tfVilleDep.setText("");
			tfVilleAr.setText("");
			tfJour.setText("");
			tfHHeure.setText("");
			tfMnHeure.setText("");
			tfHDurVol.setText("");
			tfMnDurVol.setText("");
			tfNbPers1Class.setText("");
			tfNbPers2Class.setText("");
			tfTarif1Class.setText("");
			tfTarif2Class.setText("");
			tfDelai.setText("");
		}
	}
	
	private class addVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Ville vDep = lVillesDep.getSelectedValue();
			Ville vArr = lVillesArr.getSelectedValue();
			String jours = "";
			if(cbLundi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbMardi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbMercredi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbJeudi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbVendredi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbSamedi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbDimanche.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			System.out.println(jours);
			GestionLigne.ajouterLigne(vDep.getId_ville(), vArr.getId_ville(), jours, Integer.parseInt(tfHHeure.getText()), Integer.parseInt(tfMnHeure.getText()), Integer.parseInt(tfHDurVol.getText()), Integer.parseInt(tfMnDurVol.getText()), Integer.parseInt(tfNbPers1Class.getText()), Integer.parseInt(tfNbPers1Class.getText()), Integer.parseInt(tfNbPers2Class.getText()), Integer.parseInt(tfTarif2Class.getText()), Integer.parseInt(tfDelai.getText()));	
		}
	}
	
	private class editVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Ville vDep = lVillesDep.getSelectedValue();
			Ville vArr = lVillesArr.getSelectedValue();
			String jours = "";
			if(cbLundi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbMardi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbMercredi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbJeudi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbVendredi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbSamedi.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			if(cbDimanche.isSelected()){
				jours = jours + "1";
			}
			else {
				jours = jours + "0";
			}
			GestionLigne.modifierLigne(vDep.getId_ville(), vArr.getId_ville(), jours, Integer.parseInt(tfHHeure.getText()), Integer.parseInt(tfMnHeure.getText()), Integer.parseInt(tfHDurVol.getText()), Integer.parseInt(tfMnDurVol.getText()), Integer.parseInt(tfNbPers1Class.getText()), Integer.parseInt(tfNbPers1Class.getText()), Integer.parseInt(tfNbPers2Class.getText()), Integer.parseInt(tfTarif2Class.getText()), Integer.parseInt(tfDelai.getText()));
		}
	}
	
	private class delVolListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Ligne l = lLigne.getSelectedValue();
			GestionLigne.supprimerligne(l.getId_Ligne());
		}
	}
	
	private class lstVilleDepListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if(!lVillesDep.isSelectionEmpty()){
				dlmVillesArr.clear();
				InterfaceGraphique.addAllVillesIntoOneList(dlmVillesArr);
				Ville v = lVillesDep.getSelectedValue();
				dlmVillesArr.removeElement(v);
				tfVilleDep.setText(v.getNom());
			}
			else {
				dlmVillesArr.clear();
			}
		}
	}
	
	private class lstVilleArrListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (!lVillesArr.isSelectionEmpty()){
				dlmLigne.clear();
				Ville vDep = lVillesDep.getSelectedValue();
				Ville vArr = lVillesArr.getSelectedValue();
				FabriqueLigne fl = FabriqueLigne.getInstance();
				ArrayList<Ligne> lLig = fl.getLignesAvecVilleDepartEtArrivee(vDep.getId_ville(),vArr.getId_ville()); //GestionVol.getVolsAvecVilleDepartEtArriveeParPrix(vDep.getId_ville(), vArr.getId_ville());
				InterfaceGraphique.addLignesIntoList(dlmLigne, lLig);
				tfVilleAr.setText(vArr.getNom());
			}
			else {
				dlmLigne.clear();
			}
		}
	}
	
	private class lstLignesListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!lLigne.isSelectionEmpty()){
				tfHHeure.setText("");
				tfMnHeure.setText("");
				tfHDurVol.setText("");
				tfMnDurVol.setText("");
				tfNbPers1Class.setText("");
				tfNbPers2Class.setText("");
				tfTarif1Class.setText("");
				tfTarif2Class.setText("");
				tfDelai.setText("");
				cbLundi.setSelected(false);
				cbMardi.setSelected(false);
				cbMercredi.setSelected(false);
				cbJeudi.setSelected(false);
				cbVendredi.setSelected(false);
				cbSamedi.setSelected(false);
				cbDimanche.setSelected(false);
				Ligne l = lLigne.getSelectedValue();
				String jours = l.getJour();
				System.out.println(jours);
				for (int i = 0; i < jours.length()-1; i++) {
					if (jours.charAt(i)=='1'){
						switch (i) {
						case 0:
							System.out.println(i + "-> lundi vol");
							cbLundi.setSelected(true);
							break;
						case 1:
							System.out.println(i + "-> mardi vol");
							cbMardi.setSelected(true);
							break;
						case 2:
							System.out.println(i + "-> mercredi vol");
							cbMercredi.setSelected(true);
							break;
						case 3:
							System.out.println(i + "-> jeudi vol");
							cbJeudi.setSelected(true);
							break;
						case 4:
							System.out.println(i + "-> vendredi vol");
							cbVendredi.setSelected(true);
							break;
						case 5:
							System.out.println(i + "-> samedi vol");
							cbSamedi.setSelected(true);
							break;
						case 6:
							System.out.println(i + "-> dimanche vol");
							cbDimanche.setSelected(true);
							break;
						default:
							break;
						}
					}
				}
				tfHHeure.setText(String.valueOf(l.getHeureDepart()));
				tfMnHeure.setText(String.valueOf(l.getMinDepart()));
				tfHDurVol.setText(String.valueOf(l.getHeureDuree()));
				tfMnDurVol.setText(String.valueOf(l.getMinDuree()));
				tfNbPers1Class.setText(String.valueOf(l.getNbPassagersFirstClass()));
				tfNbPers2Class.setText(String.valueOf(l.getNbPassagersSecondClass()));
				tfTarif1Class.setText(String.valueOf(l.getPriceFirstClass()));
				tfTarif2Class.setText(String.valueOf(l.getPriceSecondClass()));
				tfDelai.setText(String.valueOf(l.getDelaiAnnulation()));
			}
			else {
				dlmLigne.clear();
			}
		}
	}
	
	private class GenerateLinesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			GestionVol.genererLesVolsDeLaSemaine();
		}
	}
}
