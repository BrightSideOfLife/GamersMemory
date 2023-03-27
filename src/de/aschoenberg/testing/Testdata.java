package de.aschoenberg.testing;

import de.aschoenberg.models.Game;
import de.aschoenberg.gui.AppTexts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stellt generische Testdaten zur Verfügung
 *
 * @author Alena Schönberg created at 08.09.20
 */

public class Testdata {
	
	//region 0.Konstanten
	private static final int AMOUNT_OF_TEST_GAMES = 10;
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Konstruktor
	//endregion
	
	//region 3. Testdaten
	
	/**
	 * Stellt eine Liste aus gemerischen Games
	 * zu Testzwecken zur Verfügung
	 *
	 * @return theTestGames : {@link List<Game>} : generische Spiele
	 */
	public static List<Game> getGameTestdata() {
		
		List<Game> theTestGames = new ArrayList<>();
		
		for (int index = 0; index < AMOUNT_OF_TEST_GAMES; index++) {
			
			// EXPLICIT CAST !!
			int iRaiting = Integer.parseInt(AppTexts.COMBOBOX_RATING_LIST.get(index % 5));
			
			// Daten generieren
			Game newGame = new Game(AppTexts.GUI_DISPLAY_GAME_OFFICIAL_NAME + index,
					iRaiting,
					new ArrayList<>(Arrays.asList(AppTexts.CHOICE_WHO_HAS_IT_FIVE, AppTexts.CHOICE_WHO_HAS_IT_MORE)),
					new ArrayList<>(Arrays.asList(AppTexts.CHOICE_PLAYERS_ONE, AppTexts.CHOICE_PLAYERS_TWO, AppTexts.CHOICE_PLAYERS_THREE)));
			
			newGame.setId(index);
			
			newGame.setAuthor(AppTexts.GUI_DISPLAY_GAME_AUTHOR + index);
			newGame.setPublisher(AppTexts.GUI_DISPLAY_GAME_PUBLISHER + index);
			
			newGame.setEstimation(AppTexts.GUI_DISPLAY_GAME_ESTIMATION + index + AppTexts.CRLF_STR
								+ AppTexts.GUI_DISPLAY_GAME_ESTIMATION + index );
			
			newGame.setIdealAmountOfPlayers(new ArrayList<>(Arrays.asList(AppTexts.CHOICE_PLAYERS_TWO, AppTexts.CHOICE_PLAYERS_THREE)));
			
			// EXPLICIT CAST !!
			newGame.setRecommendedAge(Integer.parseInt(AppTexts.COMBOBOX_RECOMMENDED_AGE_LIST.get(index % 7)));
			
			newGame.setPlayingTime(AppTexts.GUI_DISPLAY_GAME_PLAYING_TIME + index);
			newGame.setHowOftenPlayed(AppTexts.GUI_DISPLAY_GAME_HOW_OFTEN_PLAYED + index);
			
			// Hinzufügen
			theTestGames.add(newGame);
		}
		
		return theTestGames;
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	//endregion
	
}
