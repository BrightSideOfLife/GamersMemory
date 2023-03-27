package de.aschoenberg.models;

import de.aschoenberg.gui.AppTexts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Model-Klasse: Definiert alle Spiel-Objekte
 * <ul>
 *     <li>Attribut Deklaration</li>
 *     <li>Standard- (Default) und Überlagerte (Themengruppierte) Konstruktoren</li>
 *     <li>Getter und Setter: Standardattribute</li>
 *     <li>Getter und Setter: Beautifier für die Array-Anzeige z.B. in der GUI</li>
 *     <li>Getter und Setter: CSV-Handling der Arrtibute</li>
 *     <li>Hilfsfunktionen</li>
 * </ul>
 *
 * @author Alena Schönberg created at 08.09.20
 */

public class Game extends ABaseModel {
	
	//region 0.Konstanten
	// CSV-Handling
	private static final int CSV_INDEX_FOR_OFFICIAL_NAME                 = 1;
	private static final int CSV_INDEX_FOR_AUTHOR                        = 2;
	private static final int CSV_INDEX_FOR_PUBLISHER                     = 3;
	private static final int CSV_INDEX_FOR_RATING                        = 4;
	private static final int CSV_INDEX_FOR_ESTIMATION                    = 5;
	private static final int CSV_INDEX_FOR_WHO_HAS_IT                    = 6;
	private static final int CSV_INDEX_FOR_RECOMMENDED_AGE               = 7;
	private static final int CSV_INDEX_FOR_IDEAL_AMOUNT_OF_PLAYERS       = 8;
	private static final int CSV_INDEX_FOR_RECOMMENDED_AMOUNT_OF_PLAYERS = 9;
	private static final int CSV_INDEX_FOR_PLAYING_TIME                  = 10;
	private static final int CSV_INDEX_FOR_HOW_OFTEN_PLAYED              = 11;
	//endregion
	
	//region 1. Decl. and Init Attribute
	// Verlags-Infos
	private String strOfficialName;
	private String strAuthor;
	private String strPublisher;
	
	// Bewertungen und Status
	private int          iRating;
	private String       strEstimation;
	private List<String> strListWhoHasIt;
	private String       strHowOftenPlayed;
	
	// Personen- und Zeitangaben
	private int           iRecommendedAge;
	private List<Integer> iListIdealAmountOfPlayers;
	private List<Integer> iListRecommendedAmountOfPlayers;
	private String        strPlayingTime;
	
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonstruktor
	 */
	public Game() {
		super();
		this.strOfficialName = ABaseModel.DEFAULT_VAL_STR;
		this.strAuthor = ABaseModel.DEFAULT_VAL_EMPTY_STR; // Optional, darf leer bleiben
		this.strPublisher = ABaseModel.DEFAULT_VAL_EMPTY_STR; // Optional, darf leer bleiben
		this.iRating = ABaseModel.DEFAULT_VAL_INT;
		this.strEstimation = ABaseModel.DEFAULT_VAL_EMPTY_STR; // Optional, darf leer bleiben
		this.strListWhoHasIt = new ArrayList<>();
		this.iRecommendedAge = ABaseModel.DEFAULT_VAL_INT;
		this.iListIdealAmountOfPlayers = new ArrayList<>();
		this.iListRecommendedAmountOfPlayers = new ArrayList<>();
		this.strPlayingTime = ABaseModel.DEFAULT_VAL_STR;
		this.strHowOftenPlayed = ABaseModel.DEFAULT_VAL_EMPTY_STR; // Optional, darf leer bleiben
	}
	
	/**
	 * 1. Überladener Konstruktor: Setzt nur die ID initial
	 *
	 * @param iId : int             : Identifier
	 */
	public Game(int iId) {
		this();
		this.setId(iId);
	}
	
	/**
	 * 2. Überladener Konstruktor: Setzt alle Plichtfelder
	 *
	 * @param strOfficialName                 : {@link String}  : Name laut Verlag (z.B. Agricola)
	 * @param iRating                         : int             : Bewertung (z.B. "4" Sterne)
	 * @param strListWhoHasIt                 : {@link List}{@link String}  : Status des Besitz (z.B. Alena, Chris, Familie)
	 * @param iListRecommendedAmountOfPlayers : {@link List}{@link Integer}  : Mit wie vielen Spielern spielbar?
	 */
	public Game(String strOfficialName, int iRating, List<String> strListWhoHasIt, List<Integer> iListRecommendedAmountOfPlayers) {
		this();
		this.strOfficialName = strOfficialName;
		this.iRating = iRating;
		this.strListWhoHasIt = strListWhoHasIt;
		this.iListRecommendedAmountOfPlayers = iListRecommendedAmountOfPlayers;
	}
	
	/**
	 * 3. Überladener Konstruktor: Setzt die ID und die Mehrfachnennungen (Checkbox-Dialoge)
	 *
	 * @param iId                             : int             : Identifier
	 * @param strListWhoHasIt                 : {@link List}{@link String}  : Status des Besitz (z.B. Alena, Chris, Familie)
	 * @param iListRecommendedAmountOfPlayers : {@link List}{@link Integer}  : Mit wie vielen Spielern spielbar?
	 * @param iListIdealAmountOfPlayers       : {@link List}{@link Integer}  : Mit wie vielen Spielern optimal spielbar?
	 */
	public Game(int iId, List<String> strListWhoHasIt, List<Integer> iListRecommendedAmountOfPlayers, List<Integer> iListIdealAmountOfPlayers) {
		this();
		this.setId(iId);
		this.strListWhoHasIt = strListWhoHasIt;
		this.iListRecommendedAmountOfPlayers = iListRecommendedAmountOfPlayers;
		this.iListIdealAmountOfPlayers = iListIdealAmountOfPlayers;
	}
	//endregion
	
	//region 3. Getter und Setter
	
	public String getOfficialName() {
		return this.strOfficialName;
	}
	
	public void setOfficialName(String strOfficialName) {
		this.strOfficialName = strOfficialName;
	}
	
	public String getAuthor() {
		return this.strAuthor;
	}
	
	public void setAuthor(String strAuthor) {
		this.strAuthor = strAuthor;
	}
	
	public String getPublisher() {
		return this.strPublisher;
	}
	
	public void setPublisher(String strPublisher) {
		this.strPublisher = strPublisher;
	}
	
	public int getRating() {
		return this.iRating;
	}
	
	public void setRating(int iRating) {
		this.iRating = iRating;
	}
	
	public String getEstimation() {
		return this.strEstimation;
	}
	
	public void setEstimation(String strEstimation) {
		this.strEstimation = strEstimation;
	}
	
	public List<String> getWhoHasIt() {
		return strListWhoHasIt;
	}
	
	public void setWhoHasIt(List<String> strListWhoHasIt) {
		this.strListWhoHasIt = strListWhoHasIt;
	}
	
	/**
	 * Schreibt den String aus der DB und CSV-Speicherung
	 * als einzelne Attribute in das Objekt
	 *
	 * @param strArrayForAttribute
	 */
	public void setSplitterStringToWhoHasItList(String strArrayForAttribute) {
		if (!strArrayForAttribute.isEmpty()) {
			for (String s : strArrayForAttribute.split(CSV_SPLITTER_CHOICES)) {
				this.strListWhoHasIt.add(s);
			}
		}
	}
	
	/**
	 * Liefert String für DB und CSV-Speicherung
	 *
	 * @return splitterString : {@link String} : Liste als String
	 */
	public String getWhoHasItSplitterList() {
		String splitterString = "";
		
		for (String s : this.strListWhoHasIt) {
			if (s.equals(this.strListWhoHasIt.get(0))) {
				splitterString += s; // Erstes Element ohne Choices-Splitter
			} else {
				splitterString += CSV_SPLITTER_CHOICES + s;
			}
		}
		
		return splitterString;
	}
	
	public String getHowOftenPlayed() {
		return this.strHowOftenPlayed;
	}
	
	public void setHowOftenPlayed(String strHowOftenPlayed) {
		this.strHowOftenPlayed = strHowOftenPlayed;
	}
	
	public int getRecommendedAge() {
		return this.iRecommendedAge;
	}
	
	public void setRecommendedAge(int iRecommendedAge) {
		this.iRecommendedAge = iRecommendedAge;
	}
	
	public List<Integer> getIdealAmountOfPlayers() {
		return iListIdealAmountOfPlayers;
	}
	
	public void setIdealAmountOfPlayers(List<Integer> iListIdealAmountOfPlayers) {
		this.iListIdealAmountOfPlayers = iListIdealAmountOfPlayers;
	}
	
	/**
	 * Schreibt den String aus der DB und CSV-Speicherung
	 * als einzelne Attribute in das Objekt
	 *
	 * @param strArrayForAttribute : {@link String} : Liste als Text
	 */
	public void setSplitterStringToIdealAOPList(String strArrayForAttribute) {
		if (!strArrayForAttribute.isEmpty()) {
			for (String s : strArrayForAttribute.split(CSV_SPLITTER_CHOICES)) {
				// EXPLICIT CAST !!
				this.iListIdealAmountOfPlayers.add(Integer.parseInt(s));
			}
		}
	}
	
	/**
	 * Liefert String für DB und CSV-Speicherung
	 *
	 * @return splitterString : {@link String} : Liste als Text
	 */
	public String getIdealAmountOfPlayersSplitterList() {
		String splitterString = "";
		for (Integer i : this.iListIdealAmountOfPlayers) {
			if (i.equals(this.iListIdealAmountOfPlayers.get(0))) {
				splitterString += i; // Erstes Element ohne Choices-Splitter
			} else {
				splitterString += CSV_SPLITTER_CHOICES + i;
			}
		}
		return splitterString;
	}
	
	public List<Integer> getRecommendedAmountOfPlayers() {
		return iListRecommendedAmountOfPlayers;
	}
	
	public void setRecommendedAmountOfPlayers(List<Integer> iListRecommendedAmountOfPlayers) {
		this.iListRecommendedAmountOfPlayers = iListRecommendedAmountOfPlayers;
	}
	
	
	/**
	 * Schreibt den String aus der DB und CSV-Speicherung
	 * als einzelne Attribute in das Objekt
	 *
	 * @param strArrayForAttribute : {@link String} : Liste als Text
	 */
	public void setSplitterStringToRecommendedAOPList(String strArrayForAttribute) {
		if (!strArrayForAttribute.isEmpty()) {
			for (String s : strArrayForAttribute.split(CSV_SPLITTER_CHOICES)) {
				// EXPLICIT CAST !!
				this.iListRecommendedAmountOfPlayers.add(Integer.parseInt(s));
			}
		}
	}
	
	/**
	 * Liefert String für DB und CSV-Speicherung
	 *
	 * @return splitterString : {@link String} : Liste als Text
	 */
	public String getRecommendedAmountOfPlayersSplitterList() {
		String splitterString = "";
		for (Integer i : this.iListRecommendedAmountOfPlayers) {
			if (i.equals(this.iListRecommendedAmountOfPlayers.get(0))) {
				splitterString += i; // Erstes Element ohne Choices-Splitter
			} else {
				splitterString += CSV_SPLITTER_CHOICES + i;
			}
		}
		return splitterString;
	}
	
	public String getPlayingTime() {
		return strPlayingTime;
	}
	
	public void setPlayingTime(String strPlayingTime) {
		this.strPlayingTime = strPlayingTime;
	}
	
	/**
	 * Diese Methode generiert ein Objekt der aufrufenden
	 * (Kind-) Klasse aus den Angaben einer CSV-Zeile (mittels
	 * #CSV_SPLITTER getrennte Attribute)
	 *
	 * @param strCsvLine : {@link String} : CSV-Zeile mit Object-Attributen
	 */
	@Override
	public void setAllCsvAttributesToThisObject(String strCsvLine) {
		
		if (strCsvLine == null) {
			
			//leere CSV ignorieren
			System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
			
		} else {
			
			// Umbruch entfernen
			String strCsvLineWhithoutCrl = strCsvLine.replace(AppTexts.CRLF_STR, "");
			
			// CSV in Array umwandeln
			String[] strArrayCSV = strCsvLineWhithoutCrl.split(CSV_SPLITTER_ATTRIBUTES);
			
			
			// Daten in das Objekt schreiben
			
			// EXPLICIT CAST !!
			this.setId(Integer.parseInt(strArrayCSV[CSV_INDEX_FOR_ID]));
			
			this.strOfficialName = strArrayCSV[CSV_INDEX_FOR_OFFICIAL_NAME];
			this.strAuthor = strArrayCSV[CSV_INDEX_FOR_AUTHOR];
			this.strPublisher = strArrayCSV[CSV_INDEX_FOR_PUBLISHER];
			
			// EXPLICIT CAST !!
			this.iRating = Integer.parseInt(strArrayCSV[CSV_INDEX_FOR_RATING]);
			
			// Umbrüche im Frei-Text wiederherstellen
			String strEstimationCrlf = strArrayCSV[CSV_INDEX_FOR_ESTIMATION];
			this.strEstimation = strEstimationCrlf.replace(CSV_SPLITTER_CRLF, AppTexts.CRLF_STR);
			
			// Mehrfachnennungen
			setSplitterStringToWhoHasItList(strArrayCSV[CSV_INDEX_FOR_WHO_HAS_IT]);
			
			// EXPLICIT CAST !!
			this.iRecommendedAge = Integer.parseInt(strArrayCSV[CSV_INDEX_FOR_RECOMMENDED_AGE]);
			
			// Mehrfachnennungen
			setSplitterStringToIdealAOPList(strArrayCSV[CSV_INDEX_FOR_IDEAL_AMOUNT_OF_PLAYERS]);
			
			// Mehrfachnennungen
			setSplitterStringToRecommendedAOPList(strArrayCSV[CSV_INDEX_FOR_RECOMMENDED_AMOUNT_OF_PLAYERS]);
			
			this.strPlayingTime = strArrayCSV[CSV_INDEX_FOR_PLAYING_TIME];
			this.strHowOftenPlayed = strArrayCSV[CSV_INDEX_FOR_HOW_OFTEN_PLAYED];
		}
	}
	
	/**
	 * Diese Funktion generiert aus dem aufrufenden Objekt der (Kind-) Klasse
	 * eine mittels #CSV_SPLITTER getrennte CSV-Zeile
	 *
	 * @return : {@link String} : CSV-Zeile (Attribute mit #CSV_SPLITTER getrennt)
	 */
	@Override
	public String getAllObjectAttributesAsCsvLine() {
		
		String strCsvLine = "";
		
		// Alle Objekt-Daten separiert auflisten
		
		// EXPLICIT CAST !!
		strCsvLine += this.getId() + CSV_SPLITTER_ATTRIBUTES;
		
		strCsvLine += this.strOfficialName + CSV_SPLITTER_ATTRIBUTES;
		strCsvLine += this.strAuthor + CSV_SPLITTER_ATTRIBUTES;
		strCsvLine += this.strPublisher + CSV_SPLITTER_ATTRIBUTES;
		
		// EXPLICIT CAST !!
		strCsvLine += this.iRating + CSV_SPLITTER_ATTRIBUTES;
		
		// Umbrüche im Frei-Text müssen ersetzt werden
		strCsvLine += this.strEstimation.replace(AppTexts.CRLF_STR, CSV_SPLITTER_CRLF) + CSV_SPLITTER_ATTRIBUTES;
		
		// Mehrfachauswahl
		strCsvLine += getWhoHasItSplitterList() + CSV_SPLITTER_ATTRIBUTES;
		
		// EXPLICIT CAST !!
		strCsvLine += this.iRecommendedAge + CSV_SPLITTER_ATTRIBUTES;
		
		// Mehrfachauswahl
		strCsvLine += getIdealAmountOfPlayersSplitterList() + CSV_SPLITTER_ATTRIBUTES;
		
		// Mehrfachauswahl
		strCsvLine += getRecommendedAmountOfPlayersSplitterList() + CSV_SPLITTER_ATTRIBUTES;
		
		strCsvLine += this.strPlayingTime + CSV_SPLITTER_ATTRIBUTES;
		strCsvLine += this.strHowOftenPlayed;
		
		// neue Zeile für nächstes Objekt
		strCsvLine += AppTexts.CRLF_STR;
		
		return strCsvLine;
		
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Game)) return false;
		if (!super.equals(o)) return false;
		Game game = (Game) o;
		return iRating == game.iRating &&
				iRecommendedAge == game.iRecommendedAge &&
				strOfficialName.equals(game.strOfficialName) &&
				Objects.equals(strAuthor, game.strAuthor) &&
				Objects.equals(strPublisher, game.strPublisher) &&
				Objects.equals(strEstimation, game.strEstimation) &&
				strListWhoHasIt.equals(game.strListWhoHasIt) &&
				Objects.equals(iListIdealAmountOfPlayers, game.iListIdealAmountOfPlayers) &&
				iListRecommendedAmountOfPlayers.equals(game.iListRecommendedAmountOfPlayers) &&
				strPlayingTime.equals(game.strPlayingTime) &&
				strHowOftenPlayed.equals(game.strHowOftenPlayed);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(),
				strOfficialName,
				strAuthor,
				strPublisher,
				iRating,
				strEstimation,
				strListWhoHasIt,
				iRecommendedAge,
				iListIdealAmountOfPlayers,
				iListRecommendedAmountOfPlayers,
				strPlayingTime,
				strHowOftenPlayed);
	}
	
	@Override
	public String toString() {
		return "Game{" + super.toString() + AppTexts.CRLF_STR +
				"strOfficialName='" + strOfficialName + '\'' + AppTexts.CRLF_STR +
				", strAuthor='" + strAuthor + '\'' + AppTexts.CRLF_STR +
				", strPublisher='" + strPublisher + '\'' + AppTexts.CRLF_STR +
				", iRating=" + iRating + AppTexts.CRLF_STR +
				", strEstimation='" + strEstimation + '\'' + AppTexts.CRLF_STR +
				", strWhoHasIt='" + strListWhoHasIt + '\'' + AppTexts.CRLF_STR +
				", iRecommendedAge=" + iRecommendedAge + AppTexts.CRLF_STR +
				", strIdealAmountOfPlayers='" + iListIdealAmountOfPlayers + '\'' + AppTexts.CRLF_STR +
				", strRecommendedAmountOfPlayers='" + iListRecommendedAmountOfPlayers + '\'' + AppTexts.CRLF_STR +
				", strPlayingTime='" + strPlayingTime + '\'' + AppTexts.CRLF_STR +
				", strHowOftenPlayed='" + strHowOftenPlayed + '\'' + AppTexts.CRLF_STR +
				'}';
	}
	
	//endregion
	
}
