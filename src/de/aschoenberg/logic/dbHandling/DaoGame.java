package de.aschoenberg.logic.dbHandling;

import de.aschoenberg.models.ABaseModel;
import de.aschoenberg.models.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Stellt alle Insert, Update, Delete und Select Statements
 * für {@link Game}-Objekte zur Verfügung
 * <p>
 * Update 01.10.2020: Anders als in der Schulung gelernt verwende
 * ich hier Prepared Statements (keine SQL-Injection möglich)
 * https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html
 * statt die SQL-Queries als String zusammenzubauen
 * z.B. dbStatementToExecute.execute("SELECT * FROM ...")
 *
 * @author Alena Schönberg created at 14.09.20
 */

public class DaoGame extends ADao {
	
	//region 0.Konstanten
	// Tabellenname
	private static final String TBL_NAME_GAME = "gameList";
	
	// Spaltennamen
	private static final String COL_NAME_GAME_ID                    = "gameId";
	private static final String COL_NAME_GAME_ID_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_GAME_ID
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_OFFICIAL_NAME                    = "officialName";
	private static final String COL_NAME_OFFICIAL_NAME_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_OFFICIAL_NAME
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_AUTHOR                    = "author";
	private static final String COL_NAME_AUTHOR_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_AUTHOR
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_PUBLISHER                    = "publisher";
	private static final String COL_NAME_PUBLISHER_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_PUBLISHER
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_RATING                    = "rating";
	private static final String COL_NAME_RATING_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_RATING
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_ESTIMATION                    = "estimation";
	private static final String COL_NAME_ESTIMATION_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_ESTIMATION
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_WHO_HAS_IT                    = "whoHasIt";
	private static final String COL_NAME_WHO_HAS_IT_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_WHO_HAS_IT
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_RECOMMENDED_AGE                    = "recommendedAge";
	private static final String COL_NAME_RECOMMENDED_AGE_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_RECOMMENDED_AGE
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_IDEAL_AMOUNT_OF_PLAYERS                    = "idealAmountOfPlayers";
	private static final String COL_NAME_IDEAL_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_IDEAL_AMOUNT_OF_PLAYERS
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS                    = "recommendedAmountOfPlayers";
	private static final String COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_PLAYING_TIME                    = "playingTime";
	private static final String COL_NAME_PLAYING_TIME_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_PLAYING_TIME
			+ CHAR_COL_BACK_TICK;
	
	private static final String COL_NAME_HOW_OFTEN_PLAYED                    = "howOftenPlayed";
	private static final String COL_NAME_HOW_OFTEN_PLAYED_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK
			+ COL_NAME_HOW_OFTEN_PLAYED
			+ CHAR_COL_BACK_TICK;
	
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 */
	public DaoGame() {
		
		// Tabllennamen setzen
		super(TBL_NAME_GAME);
		
	}
	//endregion
	
	//region 3. CRUD
	
	/**
	 * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable) {
		
		if (modelToInsertIntoDbTable instanceof Game) {
			
			// EXPLICIT CAST !!
			Game gameToInsert = (Game) modelToInsertIntoDbTable;
			
			
			try {
				// Db Connection wird vom DdHandler geöffnet
				
				// SQL-Statement generieren und ausführen
				getSqlStmtGameInsert(gameToInsert, dbRwConnection);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (dbRwConnection != null) {
					// Verbindung schließen
					try {
						dbRwConnection.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
	 */
	@Override
	public void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable) {
		
		// Prüfen, ob Daten zum Insert vorhanden sind
		if (modelsToInsertIntoDbTable.isEmpty()) {
			// Wenn nicht: Abbrechen
			return;
		}
		
		try {
			
			// Alle Datensätze durchlaufen
			for (ABaseModel a : modelsToInsertIntoDbTable) {
				
				if (a instanceof Game) {
					
					// EXPLICIT CAST !!
					Game gameToInsert = (Game) a;
					
					// Db Connection wird vom DdHandler geöffnet
					
					// SQL-Statement generieren und ausführen
					getSqlStmtGameInsert(gameToInsert, dbRwConnection);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Aendert einen einzelen Datensatz in der Datebanktabelle
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable) {
		
		if (modelToUpdateIntoDbTable instanceof Game) {
			
			// Init
			Game gameToUpdate = (Game) modelToUpdateIntoDbTable;
			
			try {
				// Db Connection wird vom DdHandler geöffnet
				
				// SQL-Statement generieren und ausführen
				doSqlGameUpdate(gameToUpdate, dbRwConnection);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (dbRwConnection != null) {
					// Verbindung schließen
					try {
						dbRwConnection.close();
					} catch (SQLException sqlEx) {
						sqlEx.printStackTrace();
					}
				}
			}
			
		}
	}
	
	/**
	 * Aendert eine Liste von Datensaetzen in der Datebanktabelle
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
	 */
	@Override
	public void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable) {
		
		// Prüfen, ob Daten zum Update vorhanden sind
		if (modelsToUpdateIntoDbTable.isEmpty()) {
			// Wenn nicht: Abbrechen
			return;
		}
		
		try {
			// Alle Datensätze durchlaufen
			for (ABaseModel a : modelsToUpdateIntoDbTable) {
				
				if (a instanceof Game) {
					
					// Init
					Game gameToUpdate = (Game) a;
					
					// Db Connection wird vom DdHandler geöffnet
					
					// SQL-Statement generieren und ausführen
					doSqlGameUpdate(gameToUpdate, dbRwConnection);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
	 */
	@Override
	public List<? extends ABaseModel> getAllDataRecordsFromDbTbl(Connection dbRwConnection) {
		
		// Init
		List<Game>        gameListFromDbTable = new ArrayList<>();
		PreparedStatement preparedStatement   = null;
		
		try {
			
			// Db Connection wird vom DdHandler geöffnet
			
			// SQL-Statement generieren
			// SQL: SELECT * FROM `gameList` ORDER BY `officilaName`,`rating`;
			preparedStatement = dbRwConnection.prepareStatement(
					SELECT_TBL + this.strTableName +
							ORDER_BY_CONDITION + COL_NAME_OFFICIAL_NAME_INC_COL_BACK_TICKS + CHAR_COMMA +
							COL_NAME_RATING_INC_COL_BACK_TICKS + CHAR_SEMICOLON);
			
			// SQL-Statement ausführen
			ResultSet resultSetFromExecutedQuery = preparedStatement.executeQuery();
			
			// Ergebnismenge durchlaufen
			while (resultSetFromExecutedQuery.next()) {
				
				// Spiele auslesen
				// EXPLICIT CAST !!
				Game GameFromDbTable = (Game) this.getModelFromResultSet(resultSetFromExecutedQuery);
				
				// zur Liste hinzufügen
				gameListFromDbTable.add(GameFromDbTable);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (preparedStatement != null) {
				// Statement schließen
				try {
					preparedStatement.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
		return gameListFromDbTable;
	}
	
	/**
	 * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Spiel-ID
	 * @return specificDataRecordFoundById : {@link ABaseModel}  oder abgeleitet davon: Gesuchtes Objekt oder null,
	 * sollte es dieses nicht geben
	 */
	@Override
	public ABaseModel getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId) {
		
		// Init
		Game              gameFromDbTable   = new Game();
		PreparedStatement preparedStatement = null;
		
		try {
			
			// Db Connection wird vom DdHandler geöffnet
			
			// SQL-Statement generieren
			// SQL: SELECT * FROM `gameList` WHERE `gameId` = 2
			preparedStatement = dbRwConnection.prepareStatement(
					SELECT_TBL + this.strTableName
							+ WHERE_CONDITION + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_SEMICOLON);
			
			// Wert setzen
			preparedStatement.setInt(1, iId);
			
			// SQL-Statement ausführen
			ResultSet resultSetFromExecutedQuery = preparedStatement.executeQuery();
			
			// Ergebnismenge auslesen
			if (resultSetFromExecutedQuery.first()) {
				
				// Spiele auslesen
				// EXPLICIT CAST !!
				gameFromDbTable = (Game) this.getModelFromResultSet(resultSetFromExecutedQuery);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (preparedStatement != null) {
				// Statement schließen
				try {
					preparedStatement.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
		
		return gameFromDbTable;
	}
	
	/**
	 * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
	 */
	@Override
	public void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId) {
		
		// init
		PreparedStatement preparedStatement = null;
		
		try {
			
			// init
			dbRwConnection.setAutoCommit(false);
			
			// Db Connection wird vom DdHandler geöffnet
			
			// SQL-Statement generieren
			// SQL: DELETE FROM `gameList` WHERE `gameId` = 0
			preparedStatement = dbRwConnection.prepareStatement(
					DELETE_FROM_TBL + this.strTableName
							+ WHERE_CONDITION + COL_NAME_GAME_ID_INC_COL_BACK_TICKS
							+ EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_SEMICOLON);
			
			// Wert setzen
			preparedStatement.setInt(1, iId);
			
			// SQL-Statement ausführen
			preparedStatement.execute();
			dbRwConnection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (preparedStatement != null) {
				// Statement schließen
				try {
					preparedStatement.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * Loescht alle Datensaetze aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 */
	public void deleteDataRecordsInDbTbl(Connection dbRwConnection) {
		
		// Init
		PreparedStatement preparedStatement = null;
		
		try {
			
			// init
			dbRwConnection.setAutoCommit(false);
			
			// Db Connection wird vom DdHandler geöffnet
			
			// SQL-Statement generieren
			// SQL: DELETE FROM `gameList`;
			preparedStatement = dbRwConnection.prepareStatement(
					DELETE_FROM_TBL + this.strTableName + CHAR_SEMICOLON);
			
			// SQL-Statement ausführen
			preparedStatement.execute();
			dbRwConnection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if (preparedStatement != null) {
				// Statement schließen
				try {
					preparedStatement.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
			
			if (dbRwConnection != null) {
				// Verbindung schließen
				try {
					dbRwConnection.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
	 *
	 * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
	 * @return aBaseModel : {@link ABaseModel} : Model abgeleitet von der Basisklasse
	 * @throws SQLException : {@link SQLException}
	 */
	@Override
	public ABaseModel getModelFromResultSet(ResultSet currentResultSet) throws SQLException {
		
		// Init
		Game gameToGenerate = new Game();
		
		// Spaltenindizes ermitteln
		int iColumnIndexId                         = currentResultSet.findColumn(COL_NAME_GAME_ID);
		int iColumnIndexOfficialName               = currentResultSet.findColumn(COL_NAME_OFFICIAL_NAME);
		int iColumnIndexAuthor                     = currentResultSet.findColumn(COL_NAME_AUTHOR);
		int iColumnIndexPublisher                  = currentResultSet.findColumn(COL_NAME_PUBLISHER);
		int iColumnIndexRating                     = currentResultSet.findColumn(COL_NAME_RATING);
		int iColumnIndexEstimation                 = currentResultSet.findColumn(COL_NAME_ESTIMATION);
		int iColumnIndexWhoHasIt                   = currentResultSet.findColumn(COL_NAME_WHO_HAS_IT);
		int iColumnIndexRecommendedAge             = currentResultSet.findColumn(COL_NAME_RECOMMENDED_AGE);
		int iColumnIndexIdealAmountOfPlayers       = currentResultSet.findColumn(COL_NAME_IDEAL_AMOUNT_OF_PLAYERS);
		int iColumnIndexRecommendedAmountOfPlayers = currentResultSet.findColumn(COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS);
		int iColumnIndexPlayingTime                = currentResultSet.findColumn(COL_NAME_PLAYING_TIME);
		int iColumnIndexHowOftenPlayed             = currentResultSet.findColumn(COL_NAME_HOW_OFTEN_PLAYED);
		
		// Daten auslesen und zuweisen
		gameToGenerate.setId(currentResultSet.getInt(iColumnIndexId));
		gameToGenerate.setOfficialName(currentResultSet.getString(iColumnIndexOfficialName));
		gameToGenerate.setAuthor(currentResultSet.getString(iColumnIndexAuthor));
		gameToGenerate.setPublisher(currentResultSet.getString(iColumnIndexPublisher));
		gameToGenerate.setRating(currentResultSet.getInt(iColumnIndexRating));
		gameToGenerate.setEstimation(currentResultSet.getString(iColumnIndexEstimation));
		gameToGenerate.setSplitterStringToWhoHasItList(currentResultSet.getString(iColumnIndexWhoHasIt));
		gameToGenerate.setRecommendedAge(currentResultSet.getInt(iColumnIndexRecommendedAge));
		gameToGenerate.setSplitterStringToIdealAOPList(currentResultSet.getString(iColumnIndexIdealAmountOfPlayers));
		gameToGenerate.setSplitterStringToRecommendedAOPList(currentResultSet.getString(iColumnIndexRecommendedAmountOfPlayers));
		gameToGenerate.setPlayingTime(currentResultSet.getString(iColumnIndexPlayingTime));
		gameToGenerate.setHowOftenPlayed(currentResultSet.getString(iColumnIndexHowOftenPlayed));
		
		return gameToGenerate;
	}
	
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	/**
	 * Baut das SQL-Satement für den Insert aller Attribute
	 *
	 * @param gameToInsert   : {@link Game} : Spiel, das gespeichert werden soll
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 */
	private void getSqlStmtGameInsert(Game gameToInsert, Connection dbRwConnection) throws SQLException {
		
		// init
		PreparedStatement preparedStatement = null;
		
		/*
		 * SQL (Id-autoinkrement):
		 * INSERT INTO `gameList`(  `officialName`, `author`,`publisher`,
		 *                          `rating`, `estimation`, `whoHasIt`,
		 *                          `recommendedAge`, `idealAmountOfPlayers`,
		 *                          `recommendedAmountOfPlayers`, `playingTime`)
		 * VALUES ( 'Kodama', 'Daniel Solis', 'Kosmos', 4, 'Erst einmal gespielt.',
		 *          'Spielemesse', 12, '3-4 Spieler', '2-4 Spieler', '25 Minuten');
		 */
		
		String sqlStatement = INSERT_TBL + this.strTableName
				+ CHAR_OPEN_BRACKET
				+ COL_NAME_OFFICIAL_NAME_INC_COL_BACK_TICKS + CHAR_COMMA // 1
				+ COL_NAME_AUTHOR_INC_COL_BACK_TICKS + CHAR_COMMA // 2
				+ COL_NAME_PUBLISHER_INC_COL_BACK_TICKS + CHAR_COMMA // 3
				+ COL_NAME_RATING_INC_COL_BACK_TICKS + CHAR_COMMA // 4 - int-Value!
				+ COL_NAME_ESTIMATION_INC_COL_BACK_TICKS + CHAR_COMMA // 5
				+ COL_NAME_WHO_HAS_IT_INC_COL_BACK_TICKS + CHAR_COMMA // 6
				+ COL_NAME_RECOMMENDED_AGE_INC_COL_BACK_TICKS + CHAR_COMMA // 7 - int-Value!
				+ COL_NAME_IDEAL_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS + CHAR_COMMA // 8
				+ COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS + CHAR_COMMA // 9
				+ COL_NAME_PLAYING_TIME_INC_COL_BACK_TICKS + CHAR_COMMA // 10
				+ COL_NAME_HOW_OFTEN_PLAYED_INC_COL_BACK_TICKS // 11
				+ CHAR_CLOSE_BRACKET
				+ VALUES_OPERATOR
				+ CHAR_OPEN_BRACKET
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER + CHAR_COMMA
				+ CHAR_PLACE_HOLDER
				+ CHAR_CLOSE_BRACKET_SEMICOLON;
		try {
			
			// Prepare
			dbRwConnection.setAutoCommit(false);
			preparedStatement = dbRwConnection.prepareStatement(sqlStatement);
			
			// Werte setzen
			preparedStatement.setString(1, gameToInsert.getOfficialName());
			preparedStatement.setString(2, gameToInsert.getAuthor());
			preparedStatement.setString(3, gameToInsert.getPublisher());
			preparedStatement.setInt(4, gameToInsert.getRating());
			preparedStatement.setString(5, gameToInsert.getEstimation());
			preparedStatement.setString(6, gameToInsert.getWhoHasItSplitterList());
			preparedStatement.setInt(7, gameToInsert.getRecommendedAge());
			preparedStatement.setString(8, gameToInsert.getIdealAmountOfPlayersSplitterList());
			preparedStatement.setString(9, gameToInsert.getRecommendedAmountOfPlayersSplitterList());
			preparedStatement.setString(10, gameToInsert.getPlayingTime());
			preparedStatement.setString(11, gameToInsert.getHowOftenPlayed());
			
			// Ausführen
			preparedStatement.executeUpdate();
			dbRwConnection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			dbRwConnection.setAutoCommit(true);
		}
	}
	
	/**
	 * Baut das SQL-Satement für den Update aller Attribute
	 *
	 * @param gameToUpdate   : {@link Game} : Spiel, das geändert werden soll
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 */
	private void doSqlGameUpdate(Game gameToUpdate, Connection dbRwConnection) throws SQLException {
		
		// init
		PreparedStatement preparedStatement = null;
		
		/*
		 * SQL:
		 * UPDATE `gameList`
		 * SET  `officialName`='Kodama', `author`='Daniel Solis',`publisher`='Kosmos',
		 *      `rating`=4, `estimation`=`Erst einmal gespielt.`, `whoHasIt`='Spielemesse',
		 *      `recommendedAge`=10,`idealAmountOfPlayers`='3-4 Personen',
		 *      `recommendedAmountOfPlayers`='2-4 Spieler',`playingTime`='25 Minuten'
		 * WHERE `gameId` = 0
		 */
		
		String sqlStatement = UPDATE_TBL + this.strTableName
				+ SET_OPERATOR
				
				+ COL_NAME_OFFICIAL_NAME_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 1
				+ COL_NAME_AUTHOR_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 2
				+ COL_NAME_PUBLISHER_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA  // 3
				+ COL_NAME_RATING_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 4 - int-value!
				+ COL_NAME_ESTIMATION_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 5
				+ COL_NAME_WHO_HAS_IT_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 6
				+ COL_NAME_RECOMMENDED_AGE_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 7  - int-value!
				+ COL_NAME_IDEAL_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 8
				+ COL_NAME_RECOMMENDED_AMOUNT_OF_PLAYERS_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 9
				+ COL_NAME_PLAYING_TIME_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_COMMA // 10
				+ COL_NAME_HOW_OFTEN_PLAYED_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER // 11
				
				+ WHERE_CONDITION + COL_NAME_GAME_ID_INC_COL_BACK_TICKS + EQUALS_OPERATOR_INC_PLACE_HOLDER + CHAR_SEMICOLON; // 12
		
		try {
			
			// Prepare
			dbRwConnection.setAutoCommit(false);
			preparedStatement = dbRwConnection.prepareStatement(sqlStatement);
			
			// Werte setzen
			preparedStatement.setString(1, gameToUpdate.getOfficialName());
			preparedStatement.setString(2, gameToUpdate.getAuthor());
			preparedStatement.setString(3, gameToUpdate.getPublisher());
			preparedStatement.setInt(4, gameToUpdate.getRating());
			preparedStatement.setString(5, gameToUpdate.getEstimation());
			preparedStatement.setString(6, gameToUpdate.getWhoHasItSplitterList());
			preparedStatement.setInt(7, gameToUpdate.getRecommendedAge());
			preparedStatement.setString(8, gameToUpdate.getIdealAmountOfPlayersSplitterList());
			preparedStatement.setString(9, gameToUpdate.getRecommendedAmountOfPlayersSplitterList());
			preparedStatement.setString(10, gameToUpdate.getPlayingTime());
			preparedStatement.setString(11, gameToUpdate.getHowOftenPlayed());
			
			preparedStatement.setInt(12, gameToUpdate.getId());
			
			// Ausführen
			preparedStatement.executeUpdate();
			dbRwConnection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			dbRwConnection.setAutoCommit(true);
		}
	}
	//endregion
	
}