/**
 * 
 */
package fabrique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Tchioben
 *
 */
public class BDDConnection {

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

	
	
	/**
	 * retourne la categorie avec ce nom et cet identifiant d'hotel correspondant
	 * @param idHotel 
	 * @param nom about the categorie
	 * @return
	 */
	public static  ResultSet selectCategorie(int idHotel, String nom){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Categorie where IDHotel = ? and Nom= ?);");
			stmt.setInt(1, idHotel);
			stmt.setString(2, nom);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}

	/**
	 * ajoute une categorie à un hotel et renvoie le id de la categorie
	 * @param id_hotel l'id de l'hotel auquel on veut rajouter la categorie
	 * @param nom de la categorie
	 * @param capacite le nombre de place dans la chambre
	 * @param tarif le prix
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unused")
	public static int addCategorie(int id_hotel, String nom, int capacite,float tarif) throws SQLException {
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectCategorie(id_hotel, nom);
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
			try { 
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Categorie(IDHotel,Nom,Capacite,Tarif) values (?,?,?,?)");
				stmt.setInt(1, id_hotel);
				stmt.setString(2, nom);
				stmt.setInt(3, capacite);
				stmt.setFloat(4, tarif);
				resultat = BDDConnection.selectCategorie(id_hotel, nom);
				resultat.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultat.findColumn("ID_Categorie");
	}
	
	/**
	 * supprime la categorie de la base grace à son id
	 * @param id_categorie
	 */
	public static void deleteCategore( int id_categorie){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Categorie where ID_Categorie=?");
				stmt.clearParameters();
				stmt.setInt(1,id_categorie);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	

	public static  ResultSet selectChambre(int id_hotel,int id_categorie,int numero){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Chambre where IDHotel=? and IDCategorie=? and numero= ?);");
			stmt.setInt(1,id_hotel );
			stmt.setInt(2, id_categorie);
			stmt.setInt(3, numero);			
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}


	@SuppressWarnings("unused")
	public static int addChambre(int id_hotel,int id_categorie,int numero) throws SQLException{
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectChambre(id_hotel,id_categorie,numero);
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
			try { 
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Client(ID_Client,IDVilleOrigine,Nom,Prenom,DateNaissance) values (?,?,?,?,?)");
				stmt.setInt(1,id_hotel );
				stmt.setInt(2, id_categorie);
				stmt.setInt(3, numero);	
				stmt.executeQuery();
				resultat = BDDConnection.selectChambre(id_hotel,id_categorie,numero);
				resultat.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultat.findColumn("ID_Client");
	}
	

	public static void deleteChambre(int id_chambre){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Chambre where ID_Chambre=?");
				stmt.clearParameters();
				stmt.setInt(1,id_chambre);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	
	
	/**
	 * retourne le select avec son nom et prenom
	 * @param nom son nom
	 * @param prenom son prenom
	 * @return son identifiant
	 */
	public static  ResultSet selectClient(String nom, String prenom){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Client where Nom = ? and Prenom= ?);");
			stmt.setString(1, nom);
			stmt.setString(2, prenom);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
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
	@SuppressWarnings("unused")
	public static int addClient( String nom, String prenom, int id_ville, String date) {
		try {
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectClient(nom,prenom);
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Client(ID_Client,IDVilleOrigine,Nom,Prenom,DateNaissance) values (?,?,?,?,?)");
				stmt.setString(1, nom );
				stmt.setString(2, prenom);
				stmt.setInt(3, id_ville);
				stmt.setDate(4,java.sql.Date.valueOf(date));
				stmt.execute();
				resultat = BDDConnection.selectClient(nom,prenom);
				resultat.next();
		}
		return resultat.findColumn("ID_Client");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	/**
	 * supprime le client de la base grace à son id
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
				e.printStackTrace();
			}
	}
	
	

	public static  ResultSet selectHotel(int id_ville, String nom){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Hotel where Nom = ? and IDVille= ?);");
			stmt.setString(1, nom);
			stmt.setInt(2, id_ville);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}


	@SuppressWarnings("unused")
	public static int addHotel(int id_ville, String nom) throws SQLException{
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectHotel(id_ville,nom);
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
			try { 
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Hotel(IDVille,Nom) values (?,?)");
				stmt.setInt(1, id_ville );
				stmt.setString(2, nom);
				stmt.execute();
				resultat = BDDConnection.selectHotel(id_ville,nom);
				resultat.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultat.findColumn("ID_Client");
	}
	

	public static void deleteHotel(int id_hotel){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Hotel where ID_Hotel=?");
				stmt.clearParameters();
				stmt.setInt(1,id_hotel);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static  ResultSet selectReserversationChambre(int id_chambre){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from ReserversationChambre where IDChambre= ?);");
			stmt.setInt(2, id_chambre);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
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
				e.printStackTrace();
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
				e.printStackTrace();
			}
	}
	
	public static  ResultSet selectVille(String nom, String pays ){
		PreparedStatement stmt;
		ResultSet categorie = null;
		try {
			BDDConnection.getInstance();
			stmt = c.prepareStatement("select * from Ville where Nom=? and Pays=?);");
			stmt.setString(1, nom);
			stmt.setString(2, pays);
			categorie = stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categorie;
	}


	@SuppressWarnings("unused")
	public static int addVille(String nom, String pays ) throws SQLException{
		PreparedStatement stmt;
		ResultSet resultat = BDDConnection.selectVille( nom, pays );
		resultat.next();
		if (resultat==null){ //si le retour du select est vide alors il doit le creer
			try { 
				BDDConnection.getInstance();
				stmt = c.prepareStatement("insert into Ville(Nom,Pays) values (?,?)");
				stmt.setString(1, nom);
				stmt.setString(2, pays);
				stmt.execute();
				resultat = BDDConnection.selectVille(nom,pays);
				resultat.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultat.findColumn("ID_Ville");
	}
	

	public static void deleteVille(String nom, String pays){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from Ville where ID_Ville=?");
				stmt.clearParameters();		
				stmt.setString(1, nom);
				stmt.setString(2, pays);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return 0;
	}
	

	public static void deleteVoyage(int id_reserv_vol, int id_reserv_chambre, int id_client ){
			try {
				BDDConnection.getInstance();
				PreparedStatement stmt = c.prepareStatement("delete from YVoyage where IDReservationVol=? and IDReservationChambre=? and IDClient = ?");
				stmt.clearParameters();		
				stmt.setInt(1, id_reserv_vol);
				stmt.setInt(2, id_reserv_chambre);
				stmt.setInt(3, id_client);
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
