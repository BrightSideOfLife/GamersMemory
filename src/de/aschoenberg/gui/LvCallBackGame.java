package de.aschoenberg.gui;

import de.aschoenberg.models.Game;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * Generiert die Zellen in der {@link ListView}
 *
 * @author Alena Schönberg created at 09.09.20
 */

public class LvCallBackGame implements Callback<ListView<Game>, ListCell<Game>> {
	
	//region 0.Konstanten
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Konstruktor
	//endregion
	
	//region 3. Call
	
	/**
	 * Wird automatisch von der ListView bzw dem {@link LvCallBackGame}-Objekt
	 * aufgerufen um eine Zeile in der {@link ListView} zu erstellen.
	 *
	 * @param gameListView : {@link ListView} : ListView zur Anzeige
	 * @return lvCellOfGame : {@link ListCell} : eine Zeile der ListView
	 */
	@Override
	public ListCell<Game> call(ListView<Game> gameListView) {
		return new LvCellGame();
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	//endregion
	
}
