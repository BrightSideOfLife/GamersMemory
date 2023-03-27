package de.aschoenberg.logic.dbHandling;

import de.aschoenberg.models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Zugriff auf die Datenbank als Singleton
 *
 * @author Alena Schönberg created at 14.09.20
 */

public class DbHandler {
	
	//region 0.Konstanten
	private static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	
	private static final String DB_SERVER_IP_ADDRESS = "127.0.0.1";
	private static final String DB_NAME              = "/db_gamers_memory";
	private static final String DB_CONNECTION_URL    = "jdbc:mariadb://" + DB_SERVER_IP_ADDRESS + DB_NAME;
	
	// TODO: Achtung!!! Im Produktivbetrieb NIE User und PW fest hinterlegen
	// Diese Lösung dient nur einer lokale Testumgebung
	private static final String DB_USER_NAME = "root";
	private static final String DB_USER_PW   = "";
	//endregion
	
	//region 1. Decl. and Init Attribute
	private static DbHandler instance;
	
	private DaoGame currentDaoGame;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 */
	private DbHandler() {
		// DaoGame init
		currentDaoGame = new DaoGame();
	}
	
	//endregion
	
	//region 3. Getter und Setter
	
	/**
	 * Singelton, liefert (und ggf initialisiert)
	 * die einzige Instanz dieser Klasse
	 *
	 * @return instance : {@link DbHandler} : einzige Instanz
	 */
	public static synchronized DbHandler getInstance() {
		if (instance == null) {
			instance = new DbHandler();
		}
		return instance;
	}
	
	//endregion
	
	//region 4. Datenbank Connection
	
	/**
	 * Stellt eine Datenbankverbindung her, mit Lese-(r) und Schreibrechten(w),
	 * und gibt diese zurück bzw null, wenn die Verbindung nicht erstellt werden konnte
	 *
	 * @return rwDbConnection : {@link Connection} : Verbindung zur Datenbank (rw-Recht)
	 */
	private Connection getRwDbConnection() {
		Connection rwDbConnection = null;
		
		try {
			
			// JDBC driver anlegen
			Class.forName(JDBC_DRIVER);
			
			// Connection öffnen
			rwDbConnection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_USER_PW);
			
		} catch (SQLException sqlEx) {
			// SQL-Fehler ggf fangen und ausgeben
			sqlEx.printStackTrace();
		} catch (ClassNotFoundException e) {
			// Fehler wenn die Klasse nicht gefunden wird ggf fangen und ausgeben
			e.printStackTrace();
		}
		
		return rwDbConnection;
	}
	//endregion
	
	//region 5. CRUD-Operationen
	
	/**
	 * DB-Insert: Speichert ein Spiel in die Datenbank
	 *
	 * @param gameToInsert : {@link Game} : Spiel, das gespeichert werden soll
	 */
	public void insertGame(Game gameToInsert) {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen des Inserts
		if (dbRwConnection != null) {
			this.currentDaoGame.insertDataRecordIntoDbTbl(dbRwConnection, gameToInsert);
		}
	}
	
	/**
	 * DB-Insert: Speichert ein Liste von Spielen in die Datenbank
	 *
	 * @param gameListToInsert : {@link List} {@link Game} : Liste von Spielen, die gespeichert werden sollen
	 */
	public void insertGameList(List<Game> gameListToInsert) {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen der Inserts
		if (dbRwConnection != null) {
			this.currentDaoGame.insertDataRecordsIntoDbTbl(dbRwConnection, gameListToInsert);
		}
	}
	
	/**
	 * DB-Update: Ändert ein Spiel in die Datenbank
	 *
	 * @param gameToUpdate : {@link Game} : Spiel, das geändert werden soll
	 */
	public void updateGame(Game gameToUpdate) {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen des Inserts
		if (dbRwConnection != null) {
			this.currentDaoGame.updateDataRecordIntoDbTbl(dbRwConnection, gameToUpdate);
		}
	}
	
	/**
	 * DB-Update: Ändert ein Liste von Spielen in die Datenbank
	 *
	 * @param gameListToUpdate : {@link List} {@link Game} : Liste von Spielen, die geändert werden sollen
	 */
	public void updateGameList(List<Game> gameListToUpdate) {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen der Updates
		if (dbRwConnection != null) {
			this.currentDaoGame.updateDataRecordsIntoDbTbl(dbRwConnection, gameListToUpdate);
		}
	}
	
	/**
	 * DB-Select: Liefert die gesamte Liste an Spielen, die in der
	 * Datenbank gespeichert sind
	 *
	 * @return getGameListFromDb : {@link List} {@link Game} : Liste aller Spiele
	 */
	public List<Game> getGameListFromDb() {
		
		// init
		List<Game> gameListFromSelect = new ArrayList<>();
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen der Updates
		if (dbRwConnection != null) {
			// EXPLICIT CAST !!
			gameListFromSelect = (List<Game>) this.currentDaoGame.getAllDataRecordsFromDbTbl(dbRwConnection);
		}
		return gameListFromSelect;
	}
	
	/**
	 * DB-Select: Liefert ein Spiel, anhand der ID aus der Datenbank zurück
	 *
	 * @param iID : int : gesuchte ID
	 * @return gameFromSelect : {@link Game} : Spiel zur ID
	 */
	public Game getGameFromDb(int iID) {
		
		// init
		Game gameFromSelect = new Game();
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen der Updates
		if (dbRwConnection != null) {
			// EXPLICIT CAST !!
			gameFromSelect = (Game) this.currentDaoGame.getSpecificDataRecordFromDbTblById(dbRwConnection, iID);
		}
		return gameFromSelect;
	}
	
	/**
	 * DB-Delete: Löscht ein Spiel, anhand der ID
	 */
	public void deleteGame(int iID) {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen des Delete
		if (dbRwConnection != null) {
			this.currentDaoGame.deleteDataRecordInDbTblById(dbRwConnection, iID);
		}
	}
	
	/**
	 * DB-Delete: Löscht alle gespeicherten Spiele
	 */
	public void deleteGameList() {
		
		// Neue Verbindung erstellen
		Connection dbRwConnection = this.getRwDbConnection();
		
		// Sicherheitscheck und ggf ausführen des Deletes
		if (dbRwConnection != null) {
			this.currentDaoGame.deleteDataRecordsInDbTbl(dbRwConnection);
		}
	}
	//endregion
	
}
