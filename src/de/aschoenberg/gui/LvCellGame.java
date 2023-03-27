package de.aschoenberg.gui;

import de.aschoenberg.models.Game;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * Diese Klasse beschreibt eine anzeigbare Zelle der
 * {@link ListView}
 *
 * @author Alena Schönberg created at 09.09.20
 */

public class LvCellGame extends ListCell<Game> {
	
	//region 0.Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Konstruktor
	//endregion
	
	//region 3. Update
	
	/**
	 * Erstellt eine Zeile aud dem mitgelieferten {@link Game}.
	 * Der Aufruf erfolgt von/aus der {@link ListView} automatisch.
	 *
	 * @param gameForCell : {@link Game} : Default (Objekt, das angezeigt werden soll)
	 * @param empty : boolean : Default (Prüft auf leeren Eintrag)
	 */
	@Override
	protected void updateItem(Game gameForCell, boolean empty) {
		
		// Superklasse verarbeitet das Item
		super.updateItem(gameForCell, empty);
		
		// Prüfung, ob das Element angezeigt werden kann
		// (es gibt Fehler, wenn es ein leeres Element ist oder bereits generiertes)
		if ((empty) || (gameForCell == null)) {
			// Aufräumen: Zeile leeren
			this.setText(null);
			this.setGraphic(null);
		} else {
			// Inhalt der Zelle schreiben
			this.setText(gameForCell.getOfficialName() + " "
					+ AppTexts.getRatingForDisplay(gameForCell.getRating()) + AppTexts.CRLF_STR
					+ AppTexts.GUI_BREADCRUMB_STR + AppTexts.GUI_DISPLAY_GAME_WHO_HAS_IT + " "
					+ AppTexts.getWhoHasItForDisplay(gameForCell.getWhoHasIt()) + AppTexts.CRLF_STR
					+ AppTexts.GUI_BREADCRUMB_STR + AppTexts.GUI_DISPLAY_GAME_RECOMMENDED_AMOUNT_OF_PLAYERS  + " "
					+ AppTexts.getAmountOfPlayersForDisplay(gameForCell.getRecommendedAmountOfPlayers()) + AppTexts.CRLF_STR
					+ AppTexts.GUI_BREADCRUMB_STR + AppTexts.GUI_DISPLAY_GAME_PLAYING_TIME  + " "
					+ gameForCell.getPlayingTime());
		}
		
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	//endregion
	
}
