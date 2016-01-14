/**
 * 
 */
package fabrique;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author Lea Vannelle & Benoit Bailleul
 */
public class BDDConnection {

	private static final int PlaceRestante2emeClasse = 0;

	private static BDDConnection INSTANCE = null;
	
	private static Connection c;
	
	public BDDConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://172.18.15.22:3306/vanelle?user=vanelle&password=mouais^^030792");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Erreur classforname");
		} catch (SQLException e) {
			System.out.println("Erreur connection \n");
		}
	}
	
	public static BDDConnection getInstance(){
		if (INSTANCE == null){
			return new BDDConnection();
		}
		else {
			return INSTANCE;
		}
	}

	
	
	public static ResultSet laTable(String table){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet laTable = null;
		try {
			stmt = c.prepareStatement("select * from "+table);
			laTable = stmt.executeQuery();
		} catch (SQLException e) {
		}
		return laTable;
	}
	
	
	
	//CATEGORIE
	
	/**
	 * retourne la categorie avec ce nom et cet identifiant d'hotel correspondant
	 * @param idHotel 
	 * @param nom about the categorie
	 * @return
	 */
	private static  ResultSet selectCategorie(int idHotel, String nom){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			stmt = c.prepareStatement("select * from Categorie where IDHotel = ? and Nom= ?");
			stmt.setInt(1, idHotel);
			stmt.setString(2, nom);
			categorie = stmt.executeQuery();
			categorie.next();
		} catch (SQLException e) {
			return null;
		}
		return categorie;
	}

	/**
	 * ajoute une categorie dans la BDD si elle n'existe pas
	 * @param id_hotel
	 * @param nom
	 * @param capacite
	 * @param tarif
	 * @return idCategorie
	 */
	public static int addCategorie(int id_hotel, String nom, int capacite,float tarif,int delai) {
		BDDConnection.getInstance();
		int idCategorie = BDDConnection.getCategorie(id_hotel, nom);
		if (idCategorie == 0){
			try {
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Categorie(IDHotel,Nom,Capacite,Tarif,Delai) values (?,?,?,?,?)");
				stmt.setInt(1, id_hotel);
				stmt.setString(2, nom);
				stmt.setInt(3, capacite);
				stmt.setFloat(4, tarif);
				stmt.setInt(5, delai);
				stmt.executeUpdate();
				idCategorie = BDDConnection.getCategorie(id_hotel, nom);
			} catch (SQLException e) {
				return 0;
			}
		}
		return idCategorie;
	}
	
	
	/**
	 * recupere l'id de la categorie
	 * @param id_hotel l'id de l'hotel auquel on veut rajouter la categorie
	 * @param nom de la categorie
	 * @param capacite le nombre de place dans la chambre
	 * @param tarif le prix
	 * @return 0 si il y a eu un probleme
	 */
	public static int getCategorie(int id_hotel, String nom){
		ResultSet idCategorie= BDDConnection.selectCategorie(id_hotel,nom);
		try {
			return idCategorie.getInt(1);
		}
		catch (Exception e){
			return 0;
		}
	}
	
	
	public static ResultSet getCategorie(int idHotel) {
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			stmt = c.prepareStatement("select * from Categorie where IDHotel = ?");
			stmt.setInt(1, idHotel);
			categorie = stmt.executeQuery();
			//categorie.next();
		} catch (SQLException e) {
			return null;
		}
		return categorie;
	}
	
	/**
	 * permet de recuperer la ligne entiere d'une categorie grace � l'id 
	 * de la categorie
	 * @param idCategorie
	 * @return le ResultSet si la ligne existe sinon null, ( le next pour �tre 
	 * sur la ligne a deja ete realise
	 */
	public static ResultSet ligneCategorie(int idCategorie){
		BDDConnection.getInstance();
		try{
			PreparedStatement stmt = c.prepareStatement("select * from Categorie where ID_Categorie=?");
			stmt.setInt(1,idCategorie);
			ResultSet resultat = stmt.executeQuery();
			resultat.next();
			return resultat;
		}
		catch (SQLException e){
			return null;
		}
	}
	
	/**
	 * supprime la categorie de la base grace � son id
	 * @param id_categorie
	 */
	public static void deleteCategorie( int id_categorie){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Categorie where ID_Categorie=?");
				stmt.clearParameters();
				stmt.setInt(1,id_categorie);
				stmt.execute();
			} catch (SQLException e) {
			}
	}

	// FIN CATEGORIE
	
	
	
	
	// CHAMBRE
	
	public static  ResultSet selectChambre(int id_hotel,int id_categorie,int numero){
		PreparedStatement stmt;
		ResultSet ligneChambre = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Chambre where IDHotel=? and IDCategorie=? and Numero= ?");
			stmt.setInt(1,id_hotel );
			stmt.setInt(2, id_categorie);
			stmt.setInt(3, numero);			
			ligneChambre = stmt.executeQuery();
			ligneChambre.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneChambre;
	}
	
	public static  ResultSet selectChambreWithCategorie(int id_categorie){
		PreparedStatement stmt;
		ResultSet ligneChambre = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Chambre where IDCategorie=?");
			stmt.setInt(1, id_categorie);		
			ligneChambre = stmt.executeQuery();
			ligneChambre.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneChambre;
	}
	
	
	public static  ResultSet ligneChambre(int idChambre){
		PreparedStatement stmt;
		ResultSet ligneChambre = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Chambre where ID_Chambre= ?");
			stmt.setInt(1,idChambre );;			
			ligneChambre = stmt.executeQuery();
			ligneChambre.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneChambre;
	}

	public static ResultSet getChambre(int idHotel) {
		PreparedStatement stmt;
		ResultSet ligneChambre = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Chambre where IDHotel= ?");
			stmt.setInt(1,idHotel );;			
			ligneChambre = stmt.executeQuery();
			ligneChambre.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneChambre;
	}
	
	
	public static int getChambre(int id_hotel,int id_categorie,int numero){
		BDDConnection.getInstance();
		ResultSet ligneChambre = BDDConnection.selectChambre(id_hotel, id_categorie, numero);
		try {
			return ligneChambre.findColumn("ID_Chambre");
		}
		catch (SQLException e){
			return 0;
		}
	}

	/**
	 * permet de creer une chambre dans la base de donn�es, ne la cr�e pas 
	 * si elle existe d�j�
	 * @param id_hotel id_hotel
	 * @param id_categorie id_categorie
	 * @param numero numero de la chambre
	 * @return le numero de l'id_chambre que l'on vient de cr�er ou recuperer
	 * dans la base, 0 si il y a eu un quelconque probleme
	 */
	public static int addChambre(int id_hotel,int id_categorie,int numero){
		BDDConnection.getInstance();
		int idChambre = BDDConnection.getChambre(id_hotel, id_categorie, numero);
		try {			
			if (idChambre==0){
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Client(ID_Client,IDVilleOrigine,Nom,Prenom,DateNaissance) values (?,?,?,?,?)");
				stmt.setInt(1,id_hotel );
				stmt.setInt(2, id_categorie);
				stmt.setInt(3, numero);	
				stmt.executeQuery();
				idChambre=getChambre(id_hotel, id_categorie, numero);
				return idChambre;
			}
		}
		catch (SQLException e){
			return 0;
		}
		return idChambre;
	}
	
	/**
	 * supprime la chambre d'apres on id_chambre
	 * @param id_chambre id de la chambre
	 */
	public static void deleteChambre(int id_chambre){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Chambre where ID_Chambre=?");
				stmt.clearParameters();
				stmt.setInt(1,id_chambre);
				stmt.execute();
			} catch (SQLException e) {
			}
	}
	
	//FIN CHAMBRE
	
	
	
	//CLIENT
	/**
	 * retourne le select avec son nom et prenom
	 * @param nom son nom
	 * @param prenom son prenom
	 * @return son identifiant
	 */
	private static  ResultSet selectClient(String nom, String prenom){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet client = null;
		try {
			stmt = c.prepareStatement("select * from Client where Nom = ? and Prenom= ? order by Nom,Prenom");
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			client = stmt.executeQuery();
		} catch (SQLException e) {
		}
		return client;
	}
	
	
	
	/**
	 * retourne le select avec son nom et prenom
	 * @param nom son nom
	 * @param prenom son prenom
	 * @return son identifiant
	 */
	public static  ResultSet selectClientsParNom(String nom){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet client = null;
		try {
			stmt = c.prepareStatement("select * from Client where Nom=? order by Nom");
			stmt.setString(1,nom);
			client = stmt.executeQuery();
		} catch (SQLException e) {
		}
		return client;
	}
	
	
	/**
	 * retourne le select avec son nom et prenom
	 * @param nom son nom
	 * @param prenom son prenom
	 * @return son identifiant
	 */
	public static  ResultSet selectClientsParPrenom(String prenom){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet client = null;
		try {
			stmt = c.prepareStatement("select * from Client where Prenom=?");
			stmt.setString(1,prenom);
			client = stmt.executeQuery();
		} catch (SQLException e) {
		}
		return client;
	}

	/**
	 * permet de recuperer la ligne avec l'id correspondant
	 * @param id_client
	 * @return null si il y a eu un probleme avec la BDD, 
	 * sinon la ligne avec l'id correspondant
	 */
	public static ResultSet ligneClient(int id_client){
		BDDConnection.getInstance();
		try {
			PreparedStatement stmt;
			ResultSet ligneClient;
			stmt = c.prepareStatement("select * from Client where ID_Client=?");
			stmt.setInt(1, id_client);
			ligneClient = stmt.executeQuery();
			ligneClient.next();
			return ligneClient;
		}
		catch (SQLException e){
			return null;
		}
	}
	
	/**
	 * ajoute un client et renvoie son id
	 * @param nom
	 * @param prenom
	 * @param id_ville identifiant de la ville
	 * @param date de la forme string "2013-09-04"
	 * @return 0 if he have a problem
	 * @throws SQLException 
	 */
	public static int addClient( String nom, String prenom, int id_ville, java.util.Date date) {
		BDDConnection.getInstance();
		int idClient= BDDConnection.getClient(nom, prenom);
		if(idClient==0){
			try{
				PreparedStatement stmt;
				stmt= c.prepareStatement("insert into Client(IDVilleOrigine,Nom,Prenom,DateNaissance) values (?,?,?,?)");
				stmt.setInt(1, id_ville);
				stmt.setString(2, nom );
				stmt.setString(3, prenom);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				stmt.setDate(4,sqlDate);
				//stmt.setDate(4,java.sql.Date.valueOf(date));
				stmt.execute();
				idClient= BDDConnection.getClient(nom, prenom);
			}
			catch(SQLException e){
				return 0;
			}
		}
		return idClient;
	}	
	
	
	public static int addClient2( String nom, String prenom, int id_ville, Date date) {
		BDDConnection.getInstance();
		int idClient= BDDConnection.getClient(nom, prenom);
		if(idClient==0){
			try{
				PreparedStatement stmt;
				stmt= c.prepareStatement("insert into Client(IDVilleOrigine,Nom,Prenom,DateNaissance) values (?,?,?,?)");
				stmt.setInt(1, id_ville);
				stmt.setString(2, nom );
				stmt.setString(3, prenom);
				stmt.setDate(4,date);
				//stmt.setDate(4,java.sql.Date.valueOf(date));
				stmt.execute();
				idClient= BDDConnection.getClient(nom, prenom);
			}
			catch(SQLException e){
				return 0;
			}
		}
		return idClient;
	}

	/**
	 * permet de recuperer l'id d'un client
	 * @param nom
	 * @param prenom
	 * @return l'id du client sinon 0
	 */
	public static int getClient(String nom, String prenom){
		ResultSet idClient= BDDConnection.selectClient(nom, prenom);
		try {
			idClient.next();
			return idClient.getInt(1);
		}
		catch (SQLException e){
			return 0;
		}
	}
	
	
	
	/**
	 * supprime le client de la base grace � son id
	 * @param id_categorie
	 */
	public static void deleteClient(int id_client){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Client where ID_Client=?");
				stmt.clearParameters();
				stmt.setInt(1,id_client);
				stmt.execute();
			} catch (SQLException e) {
			}
	}
	
	
	// FIN CLIENT
	

	//HOTEL
	/**
	 * retourne la hotel avec ce nom et cet identifiant d'hotel correspondant
	 * @param idHotel 
	 * @param nom about the hotel
	 * @return
	 */
	private static  ResultSet selectHotel(int idVille, String nom){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet hotel = null;
		try {
			stmt = c.prepareStatement("select * from Hotel where IDVille = ? and Nom= ?");
			stmt.setInt(1, idVille);
			stmt.setString(2, nom);
			hotel = stmt.executeQuery();
			hotel.next();
		} catch (SQLException e) {
			return null;
		}
		return hotel;
	}
	
	/**
	 * permet de recuperer la ligne d'un hotel
	 * @param id_ville
	 * @param nom
	 * @return null si l'hotel n'existe pas en base
	 */
	public static  ResultSet ligneHotel(int id_hotel){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet ligneHotel = null;
		try {
			stmt = c.prepareStatement("select * from Hotel where ID_Hotel= ?");
			stmt.setInt(1, id_hotel);
			ligneHotel = stmt.executeQuery();
			ligneHotel.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneHotel;
	}

	/**
	 * permet de recuperer l'id d'un hotel
	 * @param id_ville
	 * @param nom
	 * @return l'id de l'hotel, 0 si il n'existe pas en base
	 */
	public static int getHotel(int id_ville,String nom){
		int idHotel;
		ResultSet ligneHotel = BDDConnection.selectHotel(id_ville, nom);
		try{
			idHotel=ligneHotel.getInt(1);
			return idHotel;
		}
		catch ( Exception e){
			return 0;
		}
	}
	
	/**
	 * permet de creer un hotel
	 * @param id_ville
	 * @param nom
	 * @return l'id de l'hotel cr�� ou r�cuper� sinon 0 si la base n'a pas reussi � le creer.
	 */
	public static int addHotel(int id_ville, String nom){
		BDDConnection.getInstance();
		int idHotel = BDDConnection.getHotel(id_ville, nom);
		if (idHotel == 0){ //si le retour du select est vide alors il doit le creer
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Hotel(IDVille,Nom) values (?,?)");
				stmt.setInt(1, id_ville );
				stmt.setString(2, nom);
				stmt.execute();
				idHotel = BDDConnection.getHotel(id_ville, nom);				
			} catch (SQLException e) {
				return 0;
			}
		}
		return idHotel;
	}
	
	/**
	 * permet de supprimer un hotel de la BDD grace a l'id
	 * @param id_hotel
	 */
	public static void deleteHotel(int id_hotel){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Hotel where ID_Hotel=?");
				stmt.clearParameters();
				stmt.setInt(1,id_hotel);
				stmt.execute();
			} catch (SQLException e) {
			}
	}
	
	
	/**
	 * permet de recuperer les hotel en fonction de la ville parametre
	 * @param idVille la ville dans laquelle se trouve les hotels
	 * @return un ResultSet comportant le resultat de la requete
	 */
	public static ResultSet getHotelWithVille(int idVille){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet hotel = null;
		try {
			stmt = c.prepareStatement("select * from Hotel where IDVille = ?");
			stmt.setInt(1, idVille);
			hotel = stmt.executeQuery();
			//hotel.next();
		} catch (SQLException e) {
			return null;
		}
		return hotel;
	}
	
	//FIN HOTEL


	
	//VILLE
	
	/**
	 * retourne la Hotel avec ce nom et cet identifiant d'hotel correspondant
	 * @param idHotel 
	 * @param nom about the Hotel
	 * @return
	 */
	private static  ResultSet selectVille(String nom,String pays){
		BDDConnection.getInstance();
		PreparedStatement stmt;
		ResultSet hotel = null;
		try {
			stmt = c.prepareStatement("select * from Ville where Nom = ? and Pays= ? order by Pays,Nom");
			stmt.setString(1, nom);
			stmt.setString(2, pays);
			hotel = stmt.executeQuery();
			hotel.next();
		} catch (SQLException e) {
			return null;
		}
		return hotel;
	}
	
	
	public static ResultSet getVille(String nom) {
		PreparedStatement stmt;
		ResultSet ligneVille = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ville where Nom=? order by Nom");
			stmt.setString(1, nom);
			ligneVille = stmt.executeQuery();
			ligneVille.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneVille;
	}
	
	
	/**
	 * permet de recuperer une ligne de la table Ville grace a son nom
	 * et pays
	 * @param nom
	 * @param pays
	 * @return la ligne de la ville sinon null
	 */
	public static  ResultSet ligneVille(int ID_Ville ){
		PreparedStatement stmt;
		ResultSet ligneVille = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ville where ID_Ville=?");
			stmt.setInt(1, ID_Ville);
			ligneVille = stmt.executeQuery();
			ligneVille.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneVille;
	}

	/**
	 * permet de recuperer id d'une ville grace a son nom et son pays
	 * @param nom
	 * @param pays
	 * @return l'id de la ville sinon 0
	 */
	public static int getVille(String nom, String pays){
		int idVille = 0;
		try {
			ResultSet ligneVille = BDDConnection.selectVille(nom, pays);
			idVille=ligneVille.getInt(1);
		}
		catch(Exception e){
			return 0;
		}
		return idVille;
	}
	
	/**
	 * permet d'ajouter une ville a la bdd
	 * @param nom
	 * @param pays
	 * @return l'identifiant de la base dans la BDD sinon 0
	 * @throws SQLException
	 */
	public static int addVille(String nom, String pays ){
		BDDConnection.getInstance();
		int idVille = BDDConnection.getVille(nom, pays);
		if (idVille==0){ //si le retour du select est vide alors il doit le creer
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Ville(Nom,Pays) values (?,?)");
				stmt.setString(1, nom);
				stmt.setString(2, pays);
				stmt.execute();
				idVille = BDDConnection.getVille(nom,pays);
			} catch (SQLException e) {
				return 0;
			}
		}
		return idVille;
	}
	
	/**
	 * supprime une ville de la base grace � son id
	 * @param nom
	 * @param pays
	 */
	public static void deleteVille(int idVille){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Ville where ID_Ville=?");
				stmt.clearParameters();		
				stmt.setInt(1, idVille);
				stmt.execute();
			} catch (SQLException e) {
			}
	}
	
	
	// FIN VILLE
	
	
	
	
	
	//RESERVATION CHAMBRE
	
	public static  ResultSet selectReserversationChambre(int id_chambre){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from ReserversationChambre where IDChambre= ?);");
			stmt.setInt(2, id_chambre);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
		}
		return categorie;
	}


	@SuppressWarnings("unused")
	public static int addChambre(int id_chambre) throws SQLException{
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectReserversationChambre(id_chambre);
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
			try { 
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Hotel(IDVille,Nom) values (?,?)");
				stmt.setInt(1, id_chambre );
				stmt.execute();
				resultat = BDDConnection.selectReserversationChambre(id_chambre);
				resultat.next();
			} catch (SQLException e) {
				
			}
		}
		return resultat.findColumn("ID_ReservationChambre");
	}
	

	public static void deleteReserversationChambre(int id_chambre){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from ReservationChambre where ID_ReservationChambre=?");
				stmt.clearParameters();
				stmt.setInt(1,id_chambre);
				stmt.execute();
			} catch (SQLException e) {
				
			}
	}
	
	
	
	// FIN RESERVATION CHAMBRE
	
	
	
	public static  ResultSet selectVoyage(int id_reserv_vol, int id_reserv_chambre, int id_client ){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Voyage where IDReservationVol=? and IDReservationChambre=? and IDClient = ?);");
			stmt.setInt(1, id_reserv_vol);
			stmt.setInt(2, id_reserv_chambre);
			stmt.setInt(3, id_client);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			
		}
		return categorie;
	}


	@SuppressWarnings("unused")
	public static int addVoyage(int id_reserv_vol, int id_reserv_chambre, int id_client  ){
		try {
			PreparedStatement stmt;
			ResultSet resultat = BDDConnection.selectVoyage(id_reserv_vol,id_reserv_chambre,id_client );
			resultat.next();
			if (resultat==null){ //si le retour du select est vide alors il doit le creer 
					BDDConnection.getInstance();
					stmt = c.prepareStatement("insert into Voyage(IDReservationVol,IDReservationChambre,IDClient ) values (?,?,?)");
					stmt.setInt(1, id_reserv_vol);
					stmt.setInt(2, id_reserv_chambre);
					stmt.setInt(3, id_client);
					stmt.execute();
					resultat = BDDConnection.selectVoyage(id_reserv_vol,id_reserv_chambre,id_client );
					resultat.next();
				}
			return resultat.findColumn("ID_Voyage");
		} catch (SQLException e) {
			
		}
		return 0;
	}
	

	public static void deleteVoyage(int id_reserv_vol, int id_reserv_chambre, int id_client ){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Voyage where IDReservationVol=? and IDReservationChambre=? and IDClient = ?");
				stmt.clearParameters();		
				stmt.setInt(1, id_reserv_vol);
				stmt.setInt(2, id_reserv_chambre);
				stmt.setInt(3, id_client);
				stmt.execute();
			} catch (SQLException e) {
				
			}
	}

	
	// VOL
	

	/**
	 * permet de recuperer une ligne de la table Vol grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Vol sinon null
	 */
	public static  ResultSet lesVolsAvecVilleDepartetArriveeParPrix(int ID_VilleD, int ID_VilleA ){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where IDVilleDepart=? and IDVilleArrivee=? order by Tarif1Classe desc");
			stmt.setInt(1, ID_VilleD);
			stmt.setInt(2, ID_VilleA);
			ligneVol = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}
	
	/**
	 * permet de recuperer une ligne de la table Vol grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Vol sinon null
	 */
	public static  ResultSet lesVolsAvecVilleDepartetArriveeParTemps(int ID_VilleD, int ID_VilleA ){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where IDVilleDepart=? and IDVilleArrivee=? order by Duree desc");
			stmt.setInt(1, ID_VilleD);
			stmt.setInt(2, ID_VilleA);
			ligneVol = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}
	
	/**
	 * permet de recuperer une ligne de la table Vol grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Vol sinon null
	 */
	public static  ResultSet lesVolsAvecVilleDepart(int ID_Ville ){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where IDVilleDepart=?");
			stmt.setInt(1, ID_Ville);
			ligneVol = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}

	/**
	 * permet de recuperer une ligne de la table Vol grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Vol sinon null
	 */
	public static  ResultSet lesVolsAvecVilleArrivee(int ID_Ville ){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where IDVilleArrivee=?");
			stmt.setInt(1, ID_Ville);
			ligneVol = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}
	
	
	public static  ResultSet selectVol(int ID_Vol ){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where ID_Vol=?");
			stmt.setInt(1, ID_Vol);
			ligneVol = stmt.executeQuery();
			ligneVol.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}
	

	
/**	public int getVolLigne (int id_villeDepart, int id_villeArrivee,
	String jours, int heure, int min, int heureDuree, int minDuree,
	int nb1ereClasse, float prix1ereClasse, int nb2emeClasse,
	float prix2emeClasse, int dureeAnnulation){
		PreparedStatement stmt;
		ResultSet ligneVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where `IDVilleDepart`=? and"
						+ "`IDVilleArrivee`= `Jour`, `Heure`, `Duree`, `NbPassgr1Classe`, "
						+ "`Tarif1Classe`, `NbPassr2Classe`, `Tarif2Classe`, `DelaiAnnulation`");
			stmt.setInt(1, ID_Vol);
			ligneVol = stmt.executeQuery();
			ligneVol.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneVol;
	}
	
	*/
	
	
	
	/**
	 * permet d'ajouter une Vol a la bdd
	 * @param nom
	 * @param pays
	 * @return l'identifiant de la base dans la BDD sinon 0
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public static int addVol(int id_villeDepart, int id_villeArrivee,
			String jours, int heure, int min, int heureDuree, int minDuree,
			int nb1ereClasse, float prix1ereClasse, int nb2emeClasse,
			float prix2emeClasse, int dureeAnnulation,
			int PlaceRestante1ereClasse,int PlaceRestante2emeClasse){
		BDDConnection.getInstance();
		int idVol = 0;//BDDConnection.getVol(nom, pays);
		if (idVol==0){ //si le retour du select est vide alors il doit le creer
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Vol(`IDVilleDepart`,"
						+ "`IDVilleArrivee`, `Jour`, `Heure`, `Duree`, `NbPassgr1Classe`, "
						+ "`Tarif1Classe`, `NbPassr2Classe`, `Tarif2Classe`, `DelaiAnnulation`, PlaceRestante1ereClasse, PlaceRestante2emeClasse)"
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, id_villeDepart);
				stmt.setInt(2, id_villeArrivee);
				stmt.setString(3, jours);
				stmt.setTime(4,new Time(heure,min,0));
				stmt.setTime(5,new Time(heureDuree,minDuree,0));
				stmt.setInt(6, nb1ereClasse);
				stmt.setFloat(7, prix1ereClasse);
				stmt.setInt(8, nb2emeClasse);
				stmt.setFloat(9, prix2emeClasse);
				stmt.setInt(10, dureeAnnulation);
				stmt.setInt(11, PlaceRestante1ereClasse);
				stmt.setInt(12, PlaceRestante2emeClasse);
				stmt.execute();
				//idVol = BDDConnection.getVol(nom,pays);
			} catch (SQLException e) {
				return 0;
			}
		}
		return idVol;
	}
	
	public static int addVol(int id_villeDepart, int id_villeArrivee,
			java.util.Date jours, Time heureDepart, Time heureArrivee,
			int nb1ereClasse, float prix1ereClasse, int nb2emeClasse,
			float prix2emeClasse, int dureeAnnulation,
			int placeRestante1ereClasse, int placeRestante2emeClasse) {
		BDDConnection.getInstance();
		int idVol = 0;//BDDConnection.getVol(nom, pays);
		if (idVol==0){ //si le retour du select est vide alors il doit le creer
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Vol(`IDVilleDepart`,"
						+ "`IDVilleArrivee`, `Jour`, `Heure`, `Duree`, `NbPassgr1Classe`, "
						+ "`Tarif1Classe`, `NbPassr2Classe`, `Tarif2Classe`, `DelaiAnnulation`, PlaceRestante1ereClasse, PlaceRestante2emeClasse)"
						+ " values (?,?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, id_villeDepart);
				stmt.setInt(2, id_villeArrivee);
				stmt.setDate(3, (Date) jours);
				stmt.setTime(4,heureDepart);
				stmt.setTime(5,heureArrivee);
				stmt.setInt(6, nb1ereClasse);
				stmt.setFloat(7, prix1ereClasse);
				stmt.setInt(8, nb2emeClasse);
				stmt.setFloat(9, prix2emeClasse);
				stmt.setInt(10, dureeAnnulation);
				stmt.setInt(11, placeRestante1ereClasse);
				stmt.setInt(12, PlaceRestante2emeClasse);
				stmt.execute();
				//idVol = BDDConnection.getVol(nom,pays);
			} catch (SQLException e) {
				return 0;
			}
		}
		return idVol;
	}	public static int addVol(int id_villeDepart, int id_villeArrivee,
			java.util.Date depart, java.util.Date arrivee, int nb1ereClasse,
			float prix1ereClasse, int nb2emeClasse, float prix2emeClasse,
			int dureeAnnulation, int placeRestante1ereClasse,
			int placeRestante2emeClasse2){
		// TODO Auto-generated method stub
		BDDConnection.getInstance();
		int idVol = 0;//BDDConnection.getVol(nom, pays);
		if (idVol==0){ //si le retour du select est vide alors il doit le creer
			try { 
				System.out.println(arrivee);
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Vol (`IDVilleDepart`, `IDVilleArrivee`, `Depart`, `Arrivee`,"
						+ " `NbPassgr1Classe`, `Tarif1Classe`, `NbPassr2Classe`,"
						+ " `Tarif2Classe`, `DelaiAnnulation`, `PlaceRestante1ereClasse`, `PlaceRestante2emeClasse`)"
						+ " values (?,?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, id_villeDepart);
				stmt.setInt(2, id_villeArrivee);
				stmt.setTimestamp(3, new Timestamp(depart.getTime()));
				stmt.setTimestamp(4 , new Timestamp(arrivee.getTime()));
				stmt.setInt(5, nb1ereClasse);
				stmt.setFloat(6, prix1ereClasse);
				stmt.setInt(7, nb2emeClasse);
				stmt.setFloat(8, prix2emeClasse);
				stmt.setInt(9, dureeAnnulation);
				stmt.setInt(10, placeRestante1ereClasse);
				stmt.setInt(11, placeRestante2emeClasse2);
				stmt.execute();
				//idVol = BDDConnection.getVol(nom,pays);
			} catch (SQLException e) {
				e.printStackTrace();
				return 0;
			}
		}
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idVol;
	}

	
	
	/**
	 * supprime une Vol de la base grace � son id
	 * @param nom
	 * @param pays
	 */
	public static void deleteVol(int idVol){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Vol where ID_Vol=?");
				stmt.clearParameters();		
				stmt.setInt(1, idVol);
				stmt.execute();
			} catch (SQLException e) {
			}
	}
	
	
	public static ResultSet getVol(int idDepart, int idArrivee,java.util.Date jours) {
		PreparedStatement stmt;
		ResultSet VolVol = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Vol where IDVilleDepart=? and IDVilleArrivee=? and Depart=?");
			stmt.setInt(1, idDepart);
			stmt.setInt(2, idArrivee);
			stmt.setTimestamp(3 , new Timestamp(jours.getTime()));
			VolVol = stmt.executeQuery();
			VolVol.next();
		} catch (SQLException e) {
			return null;
		}
		return VolVol;
	}
	
	

	public static void miseAJourNbPlace(int nbPersonneParCategorie, int classe,
			int idVol) {
		ResultSet leVol = BDDConnection.selectVol(idVol);
		try{
			leVol.next();
			if ( classe == 1) {
				int avant = leVol.getInt(12);
				BDDConnection.updateVol1ereClasse(idVol , (avant - nbPersonneParCategorie));
			}
			else {
				int avant = leVol.getInt(13);
				BDDConnection.updateVol2emeClasse(idVol, (avant - nbPersonneParCategorie));
			}
		}
		catch (Exception e){
			
		}
	}
	
	private static void updateVol2emeClasse(int idVol, int i) {
		PreparedStatement stmt;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("update Vol set `PlaceRestante1ereClasse`=? where ID_Vol=?");
			stmt.setInt(1, i);
			stmt.setInt(2, idVol);
			stmt.executeQuery();
			} catch (SQLException e) {
		}
		
	}

	private static void updateVol1ereClasse(int idVol, int i) {
		PreparedStatement stmt;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("update Vol set `PlaceRestante2emeClasse`=? where ID_Vol=?");
			stmt.setInt(1, i);
			stmt.setInt(2, idVol);
			stmt.executeQuery();
			} catch (SQLException e) {
		}
	}
	
	
	
	
	// FIN VOL
	
	
	
	//Reservation



	public static void ajouteReservation(int idClient, int idVol, int classe,
			Date dateVol, int idCategorie, Date dateReservationChambre, int nbPersonne,
			int idVolRetour, Date dateVolRetour) {
		BDDConnection.getInstance(); 
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Reservation(IDClient,IDVol,Classe,DateVol,IDCategorie,DateReservation,NombrePersonne,IDVolRetour,DateVolRetour) values (?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, idClient);
				stmt.setInt(2, idVol);
				stmt.setInt(3,classe);
				stmt.setDate(4, dateVol);
				stmt.setInt(5, idCategorie);
				stmt.setDate(6, dateReservationChambre);
				stmt.setInt(7,nbPersonne);
				stmt.setInt(8, idVolRetour);
				stmt.setDate(9, dateVolRetour);
				stmt.execute();
				//idVol = BDDConnection.getVol(nom,pays);
			} catch (SQLException e) {
			}
		
	}

	public static ResultSet selectReservation(int idPers) {
		PreparedStatement stmt;
		ResultSet lignes = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Reservation where IDClient=?");
			stmt.setInt(1, idPers);
			lignes = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return lignes;
	}

	public static Date getDateDuJour() {
		PreparedStatement stmt;
		ResultSet lignes = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select CURDATE()");
			lignes = stmt.executeQuery();
			lignes.next();
			return lignes.getDate(1);
		} catch (SQLException e) {
			return null;
	}
	}

	public static void supprimeReservation(int id_reservation) {
		try {
			BDDConnection.getInstance();
			PreparedStatement stmt = c.prepareStatement("delete from Reservation where ID_Reservation=?");
			stmt.clearParameters();		
			stmt.setInt(1, id_reservation);
			stmt.execute();
		} catch (SQLException e) {
		}
	}

	//FIN RESERVATION
	
	
	
	//LIGNE
	
	
	/**
	 * permet de recuperer une ligne de la table Ligne grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Ligne sinon null
	 */
	public static  ResultSet lesLignesAvecVilleDepartetArrivee(int ID_VilleD, int ID_VilleA ){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where IDVilleDepart=? and IDVilleArrivee=? order by Tarif1Classe desc");
			stmt.setInt(1, ID_VilleD);
			stmt.setInt(2, ID_VilleA);
			ligneLigne = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}
	
	/**
	 * permet de recuperer une ligne de la table Ligne grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Ligne sinon null
	 */
	public static  ResultSet lesLignesAvecVilleDepart(int ID_Ville ){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where IDVilleDepart=?");
			stmt.setInt(1, ID_Ville);
			ligneLigne = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}

	/**
	 * permet de recuperer une ligne de la table Ligne grace a l'id de la ville de d�part
	 * @param idVille ville de d�part
	 * @return la ligne de la Ligne sinon null
	 */
	public static  ResultSet lesLignesAvecVilleArrivee(int ID_Ville ){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where IDVilleArrivee=?");
			stmt.setInt(1, ID_Ville);
			ligneLigne = stmt.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}
	
	
	public static  ResultSet selectLigne(int ID_Ligne ){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where ID_Ligne=?");
			stmt.setInt(1, ID_Ligne);
			ligneLigne = stmt.executeQuery();
			ligneLigne.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}
	
	
	public static  ResultSet selectLigne(){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne");
			ligneLigne = stmt.executeQuery();
		} catch (SQLException e) {
			return ligneLigne;
		}
		return ligneLigne;
	}
	
	/**
	 * permet de recuperer id d'une Ligne grace a son nom et son pays
	 * @param nom
	 * @param pays
	 * @return l'id de la Ligne sinon 0
	 */
	public static int getLigne(int id_Ligne){
		int idLigne = 0;
		try {
			ResultSet ligneLigne = BDDConnection.selectLigne(id_Ligne);
			idLigne=ligneLigne.getInt(1);
		}
		catch(Exception e){
			return 0;
		}
		return idLigne;
	}
	
	
/**	public int getLigneLigne (int id_villeDepart, int id_villeArrivee,
	String jours, int heure, int min, int heureDuree, int minDuree,
	int nb1ereClasse, float prix1ereClasse, int nb2emeClasse,
	float prix2emeClasse, int dureeAnnulation){
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where `IDVilleDepart`=? and"
						+ "`IDVilleArrivee`= `Jour`, `Heure`, `Duree`, `NbPassgr1Classe`, "
						+ "`Tarif1Classe`, `NbPassr2Classe`, `Tarif2Classe`, `DelaiAnnulation`");
			stmt.setInt(1, ID_Ligne);
			ligneLigne = stmt.executeQuery();
			ligneLigne.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}
	
	*/
	
	
	
	/**
	 * permet d'ajouter une Ligne a la bdd
	 * @param nom
	 * @param pays
	 * @return l'identifiant de la base dans la BDD sinon 0
	 * @throws SQLException
	 */
	@SuppressWarnings("deprecation")
	public static int addLigne(int id_villeDepart, int id_villeArrivee,
			String jours, int heure, int min, int heureDuree, int minDuree,
			int nb1ereClasse, float prix1ereClasse, int nb2emeClasse,
			float prix2emeClasse, int dureeAnnulation){
		BDDConnection.getInstance();
		int idLigne = 0;//BDDConnection.getLigne(nom, pays);
		if (idLigne==0){ //si le retour du select est vide alors il doit le creer
			try { 
				PreparedStatement stmt;
				stmt = c.prepareStatement("insert into Ligne(`IDVilleDepart`,"
						+ "`IDVilleArrivee`, `Jour`, `Heure`, `Duree`, `NbPassgr1Classe`, "
						+ "`Tarif1Classe`, `NbPassr2Classe`, `Tarif2Classe`, `DelaiAnnulation`)"
						+ " values (?,?,?,?,?,?,?,?,?,?)");
				stmt.setInt(1, id_villeDepart);
				stmt.setInt(2, id_villeArrivee);
				stmt.setString(3, jours);
				stmt.setTime(4,new Time(heure,min,0));
				stmt.setTime(5,new Time(heureDuree,minDuree,0));
				stmt.setInt(6, nb1ereClasse);
				stmt.setFloat(7, prix1ereClasse);
				stmt.setInt(8, nb2emeClasse);
				stmt.setFloat(9, prix2emeClasse);
				stmt.setInt(10, dureeAnnulation);
				stmt.execute();
				//idLigne = BDDConnection.getLigne(nom,pays);
			} catch (SQLException e) {
				return 0;
			}
		}
		return idLigne;
	}
	
	/**
	 * supprime une Ligne de la base grace � son id
	 * @param nom
	 * @param pays
	 */
	public static void deleteLigne(int idLigne){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Ligne where ID_Ligne=?");
				stmt.clearParameters();		
				stmt.setInt(1, idLigne);
				stmt.execute();
			} catch (SQLException e) {
			}
	}

	public static ResultSet getLigne(int idDepart, int idArrivee) {
		PreparedStatement stmt;
		ResultSet ligneLigne = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ligne where IDVilleDepart=? and IDVilleArrivee=?");
			stmt.setInt(1, idDepart);
			stmt.setInt(2, idArrivee);
			ligneLigne = stmt.executeQuery();
			ligneLigne.next();
		} catch (SQLException e) {
			return null;
		}
		return ligneLigne;
	}


	
	
	// FIN Ligne
	
	
}
