package de.aschoenberg.logic.dbHandling;

import de.aschoenberg.models.ABaseModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Definiert alle Eigenschaften
 * und Methoden sowie Funktionen vor
 * die benoetigt werden um auf
 * Daten einer Datenbanktabelle zuzugreifen,
 * diese zu aendern oder zu loeschen.
 *
 * (Wiederverwendung mit freundlicher Genemigung von Rudolf Histel)
 * Created by rHistel
 */
public abstract class ADao extends ASqlKeyWords {
	//region 0.Konstanten
	
	/**
	 * Primaerschluessel aller Tabellen dieses Projekts
	 * ansonsten koennnte man auch hier PRIMARY KEY als Namen
	 * verwenden.
	 */
	protected static final String COL_NAME_ID                    = "_id";
	protected static final String COL_NAME_ID_INC_COL_BACK_TICKS = CHAR_COL_BACK_TICK + COL_NAME_ID + CHAR_COL_BACK_TICK;
	
	//endregion
	
	//region 1. Decl. and Init Attribute
	protected String strTableName;
	//endregion
	
	//region 2. Konstruktoren
	
	/**
	 * STandard Konstruktor zum direkten setzen
	 *
	 * @param strTableName : {@link String} : Name der Db Tabelle
	 */
	protected ADao(String strTableName) {
		this.strTableName = strTableName;
	}
	
	//endregion
	
	//region 3. CRUD Operationen

//	/**
//	 * Fuert ein CREATE TABEL Statement aus
//	 *
//	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
//	 */
//	public abstract void createDbTbl(Connection dbRwConnection);
//
//	/**
//	 * Fuert ein DROP Table Statement aus
//	 *
//	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
//	 */
//	public abstract void dropDbTbl(Connection dbRwConnection);
//
//	/**
//	 * Wenn Spalten geaendert verschoben oder geleoscht werden
//	 * muss hier ein passendes SQL-Statement ausgefuerht werden.
//	 * Solche strukturellen Veraenderungen sollte mann niemals im
//	 * Live-Betrieb machen. Hinzufuegen einer Spalte geht alles andere nicht.
//	 *
//	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
//	 */
//	public abstract void alterDbTbl(Connection dbRwConnection);
//	/**
//	 * Fuehrt in der Regel eine Sicherung der alten Tabellendaten druch, meist
//	 * mittels einer {@link List}. Danach wird ein {@link ADbTblDao#dropDbTbl(Connection)}
//	 * und im Anschluss ein {@link ADbTblDao#createDbTbl(Connection)} ausgefuehrt.
//	 * Das neue CREATE Statement enthaelt die neue Struktur der Tabelle.
//	 * Danach werden die gesicherten Daten wieder in die neue Tabelle die meist genauso
//	 * heist wie vorherige wieder eingefuegt. Ein sogennter Dreieckstausch wurde vollzogen
//	 *
//	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
//	 */
//	public abstract void updateDbTbl(Connection dbRwConnection);
	
	/**
	 * Fuegt einen einzelen Datensatz in die Datebanktabelle ein
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToInsertIntoDbTable : {@link ABaseModel} : Model welches eingefuegt werden soll
	 */
	public abstract void insertDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToInsertIntoDbTable);
	
	/**
	 * Fuegt eine Liste von Datensaetzen in die Datebanktabelle ein
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToInsertIntoDbTable : {@link ABaseModel} : Models welches eingefuegt werden soll
	 */
	public abstract void insertDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToInsertIntoDbTable);
	
	/**
	 * Aendert einen einzelen Datensatz in der Datebanktabelle
	 *
	 * @param dbRwConnection           : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelToUpdateIntoDbTable : {@link ABaseModel} : Model welches geaendert werden soll
	 */
	public abstract void updateDataRecordIntoDbTbl(Connection dbRwConnection, ABaseModel modelToUpdateIntoDbTable);
	
	/**
	 * Aendert eine Liste von Datensaetzen in der Datebanktabelle
	 *
	 * @param dbRwConnection            : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param modelsToUpdateIntoDbTable : {@link ABaseModel} : Models welches geaendert werden soll
	 */
	public abstract void updateDataRecordsIntoDbTbl(Connection dbRwConnection, List<? extends ABaseModel> modelsToUpdateIntoDbTable);
	
	/**
	 * Gibt alle Datensaetze eine Datenbanktabelle als {@link List} zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 *
	 * @return allDataRecordsFromDbTbl : {@link List} Objects extended from {@link ABaseModel} : Liste aller Datensaetze
	 */
	public abstract List<? extends ABaseModel> getAllDataRecordsFromDbTbl(Connection dbRwConnection);
	
	
	/**
	 * Gibt einen bestimmten Datensatz einer Datenbanktabelle zurueck
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 *
	 * @return specificDataRecordFoundById : {@link ABaseModel}  oder abgeleitet davon: Gesuchtes Objekt oder null,
	 * sollte es dieses nicht geben
	 */
	public abstract ABaseModel getSpecificDataRecordFromDbTblById(Connection dbRwConnection, int iId);
	
	
	/**
	 * Loescht einen bestimmten Datensatz aus der Datenbanktabelle
	 *
	 * @param dbRwConnection : {@link Connection} : Db-Connection mit Schreib und Lesezugriff
	 * @param iId            : int : Id des Objektes welches in der DbTabelle geloscht werden soll
	 */
	public abstract void deleteDataRecordInDbTblById(Connection dbRwConnection, int iId);
	//endregion
	
	//region 4. Hilfsmethoden und Funktionen
	
	/**
	 * Nimmt die Ergebnismenge und formt ein konkretes Model daraus
	 *
	 * @param currentResultSet : {@link ResultSet} : Ergebnismenge der aktuellen Abfrage
	 *
	 * @return aBaseModel : {@link ABaseModel} : Model abgeleitet von der Basisklasse
	 *
	 * @throws SQLException : {@link SQLException}
	 */
	public abstract ABaseModel getModelFromResultSet(ResultSet currentResultSet) throws SQLException;
	//endregion
}
