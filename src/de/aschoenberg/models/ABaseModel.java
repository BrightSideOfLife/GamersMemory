package de.aschoenberg.models;

import java.util.Objects;

/**
 * Abstrakte Model Klasse
 * Definiert allgemeine Konstanten, Atribute und
 * Funktionen/Methoden für alle Kind-Klassen (Models)
 *
 * @author Alena Schönberg created at 08.09.20
 */

public abstract class ABaseModel {
	
	//region 0.Konstanten
	
	// Default Values
	protected static final int               DEFAULT_VAL_INT       = -1;
	protected static final String            DEFAULT_VAL_STR       = "<empty>";
	protected static final String            DEFAULT_VAL_EMPTY_STR = "";
	
	// CSV-Handling
	protected static final int CSV_INDEX_FOR_ID = 0;
	
	/*
	 * Sonderformat als CSV-Trennzeichen um im Freitext-Feld taEstimation/strEstimation
	 * dem User mehr möglichkeiten zur Textgestaltung zu lassen
	 * (Zeilenumbrüche und Semikolon soll verwendbar sein)
	 */
	protected static final String CSV_SPLITTER_ATTRIBUTES = "#;#";
	protected static final String CSV_SPLITTER_CRLF       = "#CRLF#";
	protected static final String CSV_SPLITTER_CHOICES    = ",";
	//endregion
	
	//region 1. Decl. and Init Attribute
	private int iId;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 */
	public ABaseModel() {
		iId = DEFAULT_VAL_INT;
	}
	
	/**
	 * 1. Überladener Konstruktor
	 *
	 * @param iId : int : Identifier
	 */
	public ABaseModel(int iId) {
		this.iId = iId;
	}
	//endregion
	
	//region 3. Getter und Setter
	
	public int getId() {
		return iId;
	}
	
	public void setId(int iId) {
		this.iId = iId;
	}
	
	/**
	 * Diese Methode generiert ein Objekt der aufrufenden
	 * (Kind-) Klasse aus den Angaben einer CSV-Zeile (mittels
	 * #CSV_SPLITTER getrennte Attribute)
	 *
	 * @param strCsvLine : {@link String} : CSV-Zeile mit Object-Attributen
	 */
	public abstract void setAllCsvAttributesToThisObject(String strCsvLine);
	
	/**
	 * Diese Funktion generiert aus dem aufrufenden Objekt der (Kind-) Klasse
	 * eine mittels #CSV_SPLITTER getrennte CSV-Zeile
	 *
	 * @return : {@link String} : CSV-Zeile (Attribute mit #CSV_SPLITTER getrennt)
	 */
	public abstract String getAllObjectAttributesAsCsvLine();
	
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	@Override
	public boolean equals(Object objectIn) {
		if (this == objectIn) return true;
		if (!(objectIn instanceof ABaseModel)) return false;
		ABaseModel that = (ABaseModel) objectIn;
		return this.iId == that.iId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(iId);
	}
	
	@Override
	public String toString() {
		return "ABaseModel{" +
				"iId=" + iId +
				'}';
	}
	
	//endregion
	
}
