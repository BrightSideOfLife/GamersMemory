package de.aschoenberg.gui;

import java.util.Arrays;
import java.util.List;

/**
 * Alle Klartexte der Anwendung
 * als Konstanten zur einheitlichen Verwendung
 *
 * @author Alena Schönberg created at 07.09.20
 */

public class AppTexts {
	
	//region 0.Konstanten
	
	// Allgemeines
	public static final String APP_TITLE_STR           = "The Gamer's Memory";
	public static final String ERROR_NO_SUCH_VALUE_STR = "Lost in Space...";
	
	// Sonderzeichen
	public static final String CRLF_STR = "\n";
	
	// Checkbox Auswahl
	public static final int CHOICE_PLAYERS_ONE   = 1;
	public static final int CHOICE_PLAYERS_TWO   = 2;
	public static final int CHOICE_PLAYERS_THREE = 3;
	public static final int CHOICE_PLAYERS_FOUR  = 4;
	public static final int CHOICE_PLAYERS_FIVE  = 5;
	public static final int CHOICE_PLAYERS_MORE  = 6;
	
	public static final String CHOICE_WHO_HAS_IT_ONE   = "Chris";
	public static final String CHOICE_WHO_HAS_IT_TWO   = "Familie";
	public static final String CHOICE_WHO_HAS_IT_THREE = "Freundeskeis";
	public static final String CHOICE_WHO_HAS_IT_FOUR  = "Spieleverein";
	public static final String CHOICE_WHO_HAS_IT_FIVE  = "Messe";
	public static final String CHOICE_WHO_HAS_IT_MORE  = "Andere";
	
	// ComboBox-Auswahl
	public static final List<String> COMBOBOX_RATING_LIST          = Arrays.asList("1", "2", "3", "4", "5");
	public static final List<String> COMBOBOX_RECOMMENDED_AGE_LIST = Arrays.asList("18", "12", "10", "8", "7", "6", "4", "0");
	public static final String       PLEASE_CHOSE_STR              = "Bitte wählen";
	
	// GUI-Eingabe-Elemente Game-Scene
	// Achtung: in fxml manuell hinterleg
	public static final String GUI_DISPLAY_GAME_OFFICIAL_NAME                 = "Offizieller Name:* ";
	public static final String GUI_DISPLAY_GAME_AUTHOR                        = "Autor:";
	public static final String GUI_DISPLAY_GAME_PUBLISHER                     = "Verlagsname: ";
	public static final String GUI_DISPLAY_GAME_RATING                        = "Bewertung (X Sterne):* ";
	public static final String GUI_DISPLAY_GAME_ESTIMATION                    = "eigene Einschätzung und Meinung:";
	public static final String GUI_DISPLAY_GAME_WHO_HAS_IT                    = "Wer hat das Spiel?* ";
	public static final String GUI_DISPLAY_GAME_RECOMMENDED_AGE               = "Empfohlenes Alter (ab X Jahren): ";
	public static final String GUI_DISPLAY_GAME_IDEAL_AMOUNT_OF_PLAYERS       = "Empfohlene Spieleranzahl: ";
	public static final String GUI_DISPLAY_GAME_RECOMMENDED_AMOUNT_OF_PLAYERS = "Mögliche Spieleranzahl:* ";
	public static final String GUI_DISPLAY_GAME_PLAYING_TIME                  = "Spieldauer:* ";
	public static final String GUI_DISPLAY_GAME_HOW_OFTEN_PLAYED              = "Wie oft gespielt? ";
	
	// GUI Klartexte
	public static final String RATING_STAR_STR     = "\u2606";
	public static final String RECOMMENDED_AGE_STR = "ab %d Jahren";
	
	public static final String GUI_BREADCRUMB_STR = "\t> ";
	
	// Alert
	public static final String ALERT_DELETE_HEADER_STR = "Achtung";
	public static final String ALERT_DELETE_BODY_STR   = "Möchtest Du diesen Eintrag wirklich löschen?";
	
	public static final String ALERT_VALIDATION_HEADER_STR = "Fehlerhafte Eingabe";
	public static final String ALERT_VALIDATION_BODY_STR   = "Folgende Eingabe(n) sind nicht im korrekten Format:\n\n%s";
	
	public static final String ALERT_CSV_DOWNLOAD_HEADER_STR = "Information Download";
	public static final String ALERT_CSV_DOWNLOAD_BODY_STR   = "Die aktuelle Spiele-Liste wurde in die CSV gespeichert.";
	
	public static final String ALERT_CSV_UPLOAD_HEADER_STR = "Information Upload";
	public static final String ALERT_CSV_UPLOAD_BODY_STR   = "Die Spiele wurden aus der CSV-Datei geladen.";
	
	public static final String VALIDATION_ERROR_NOT_OPTIONAL_STR = "Es muss ein Wert eingegenen werden.";
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Konstruktor
	//endregion
	
	//region 3. Getter und Setter
	/**
	 * Generiert aus der Bewertungs-Zahl
	 * einen anzeigbaren Text für den User / für die Listen-GUI
	 *
	 * @param iStarCounter : int : Anzahl der Sterne die generiert werden soll
	 * @return strBeautifulRating : {@link String} : Bewertung als Text
	 */
	public static String getRatingForDisplay(int iStarCounter) {
		
		String strStars = "";
		
		for (int i = 0; i < iStarCounter; i++) {
			strStars += AppTexts.RATING_STAR_STR;
		}
		return strStars;
	}
	
	
	/**
	 * Generiert aus der Liste #strListWhoHasIt
	 * einen Text zur Anzeige in der GUI
	 *
	 * @param strListWhoHasIt : {@link List}{@link String} : Attribut-Liste
	 * @return displayString : {@link String} : Liste als String
	 */
	public static String getWhoHasItForDisplay(List<String> strListWhoHasIt) {
		
		String displayString;
		switch (strListWhoHasIt.size()) {
			case 0:
				displayString = "";
				break;
			case 1:
				displayString = strListWhoHasIt.get(0);
				break;
			case 2:
				displayString = strListWhoHasIt.get(0) + " und " + strListWhoHasIt.get(1);
				break;
			case 3:
				displayString = strListWhoHasIt.get(0) + ", " + strListWhoHasIt.get(1) + " und " + strListWhoHasIt.get(2);
				break;
			default:
				displayString = strListWhoHasIt.get(0) + ", " + strListWhoHasIt.get(1) + " und weitere";
				break;
		}
		
		return displayString;
	}
	
	/**
	 * Generiert aus der Zahl "Empfohlenes Alter"
	 * einen anzeigbaren Text für den User / für die Listen-GUI
	 *
	 * @param iRecommendedAge : int : Attribut empfohlenes Alter
	 * @return strBeautifulRecommendedAge : {@link String} : Empfohlenes Alter als Text
	 */
	public static String getRecommendedAgeForDisplay(int iRecommendedAge) {
		// EXPLICIT CAST !!
		return String.format(AppTexts.RECOMMENDED_AGE_STR, iRecommendedAge);
	}
	
	
	/**
	 * Generiert aus der Liste #iListRecommendedAmountOfPlayers (isIdeal = false)
	 * oder  #iListIdealAmountOfPlayers (isIdeal = true)
	 * einen Text zur Anzeige in der GUI
	 *
	 * @param integerList : {@link List}{@link Integer} : Recommended- oder IdealPlayerAmount-Liste
	 * @return displayString : {@link String} : Liste als String
	 */
	public static String getAmountOfPlayersForDisplay(List<Integer> integerList) {
		
		String displayString;
		
		switch (integerList.size()) {
			case 0:
				displayString = "";
				break;
			case 1:
				displayString = integerList.get(0) + " Person/en";
				break;
			case 2:
				displayString = integerList.get(0) + " und " + integerList.get(1) + " Personen";
				break;
			default:
				displayString = "Zwischen " + integerList.get(0) + " und " + integerList.get(integerList.size() - 1) + " Personen";
				break;
		}
		if (!integerList.isEmpty() && integerList.get(integerList.size() - 1) == CHOICE_PLAYERS_MORE) {
			displayString += " und mehr";
		}
		
		return displayString;
	}
	
	//endregion
	
	//region Hilfsmethoden und Funktionen
	//endregion
	
}
