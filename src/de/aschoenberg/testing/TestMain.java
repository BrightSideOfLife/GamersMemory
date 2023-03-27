package de.aschoenberg.testing;

import de.aschoenberg.logic.csvHandling.CsvFileHandler;
import de.aschoenberg.models.Game;
import de.aschoenberg.gui.AppTexts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Backend-Tests per Komandozeilenausgabe
 *
 * TODO: Im produktiven Einsatz würde man hier automatisierte Unit-Tests
 * verwenden, diese Tests dienen vor allem der initialen Prüfung des Models!
 *
 * @author Alena Schönberg created at 08.09.20
 */

public class TestMain {
	
	//region 0.Konstanten
	private static final String HORIZONTAL_LINE = "=======================================";
	private static final String SEPARATION_LINE = "\n\n" + HORIZONTAL_LINE;
	//endregion
	
	//region 1. Decl. and Init Attribute
	//endregion
	
	//region 2. Main
	
	/**
	 * Einstiegspunkt in die Konsolen-Tests
	 * @param strInitList : {@link String[]} : wird nicht verwendet
	 */
	public static void main(String[] strInitList) {
		
		runGameModelTest();
		
		runGameCsvHandlingTest();
	}
	//endregion
	
	//region 3. Testaufrufe Game
	
	/**
	 * Methode testet das {@link Game}-Model:
	 * dessen Konstruktoren
	 * dessen Equal, Hash und toString
	 * dessen getDisplay... Funktionen
	 */
	public static void runGameModelTest(){
		
		// Standardkonstruktor + get/set der Pflichtwerte (toString)
		System.out.println(SEPARATION_LINE);
		System.out.println("Standardkonst.");
		System.out.println(HORIZONTAL_LINE);
		
		Game newGameOne = new Game();
		
		System.out.println(newGameOne.toString());
		
		newGameOne.setOfficialName("Galaxy Trucker");
		newGameOne.setRating(5);
		newGameOne.setWhoHasIt(new ArrayList<>(Arrays.asList(AppTexts.CHOICE_WHO_HAS_IT_ONE, AppTexts.CHOICE_WHO_HAS_IT_TWO)));
		newGameOne.setRecommendedAge(12);
		newGameOne.setRecommendedAmountOfPlayers(new ArrayList<>(Arrays.asList(AppTexts.CHOICE_PLAYERS_TWO, AppTexts.CHOICE_PLAYERS_THREE, AppTexts.CHOICE_PLAYERS_FOUR)));
		newGameOne.setIdealAmountOfPlayers(new ArrayList<>(Arrays.asList(AppTexts.CHOICE_PLAYERS_FOUR)));
		newGameOne.setPlayingTime("30 Minuten pro Spieler");
		
		System.out.println(newGameOne.toString());
		
		
		// Display Texts
		System.out.println(SEPARATION_LINE);
		System.out.println("Display Texts");
		System.out.println(HORIZONTAL_LINE);
		
		System.out.println(AppTexts.getRatingForDisplay(newGameOne.getRating()));
		System.out.println(AppTexts.getRecommendedAgeForDisplay(newGameOne.getRecommendedAge()));
		System.out.println(AppTexts.getWhoHasItForDisplay(newGameOne.getWhoHasIt()));
		System.out.println(AppTexts.getAmountOfPlayersForDisplay(newGameOne.getIdealAmountOfPlayers()));
		System.out.println(AppTexts.getAmountOfPlayersForDisplay(newGameOne.getRecommendedAmountOfPlayers()));
		
		
		// 1. Überladener Konstr. + restliche get/set (toString)
		System.out.println(SEPARATION_LINE);
		System.out.println("Überladener Konstr.");
		System.out.println(HORIZONTAL_LINE);
		
		int iIdentifier = 99;
		
		Game newGameTwo = new Game(AppTexts.GUI_DISPLAY_GAME_OFFICIAL_NAME + iIdentifier,
				iIdentifier % 5,
				new ArrayList<>(Arrays.asList(AppTexts.CHOICE_WHO_HAS_IT_TWO)),
				new ArrayList<>(Arrays.asList(AppTexts.CHOICE_PLAYERS_ONE, AppTexts.CHOICE_PLAYERS_TWO, AppTexts.CHOICE_PLAYERS_THREE, AppTexts.CHOICE_PLAYERS_FOUR, AppTexts.CHOICE_PLAYERS_FIVE)));
		
		newGameTwo.setId(iIdentifier);
		
		newGameTwo.setAuthor(AppTexts.GUI_DISPLAY_GAME_AUTHOR + iIdentifier);
		newGameTwo.setPublisher(AppTexts.GUI_DISPLAY_GAME_PUBLISHER + iIdentifier);
		
		newGameTwo.setEstimation(AppTexts.GUI_DISPLAY_GAME_ESTIMATION + iIdentifier);
		
		newGameTwo.setIdealAmountOfPlayers(new ArrayList<>(Arrays.asList(3, 4)));
		newGameTwo.setRecommendedAge(iIdentifier);
		
		newGameTwo.setPlayingTime(AppTexts.GUI_DISPLAY_GAME_PLAYING_TIME + iIdentifier);
		newGameTwo.setHowOftenPlayed(AppTexts.GUI_DISPLAY_GAME_HOW_OFTEN_PLAYED + iIdentifier);
		
		System.out.println(newGameTwo.toString());
		
	}
	
	/**
	 * Methode testet das CSV Handling {@link CsvFileHandler}
	 */
	public static void runGameCsvHandlingTest(){
		
		// Testdaten
		List<Game> theTestGames = Testdata.getGameTestdata();
		
		// Kontrollausgabe
		System.out.println(SEPARATION_LINE);
		System.out.println("CSV-original");
		System.out.println(HORIZONTAL_LINE);
		for(Game g : theTestGames){
			System.out.println(g.toString());
		}
		
		CsvFileHandler.getInstance().saveGameListToCsvFile(theTestGames);
		theTestGames = CsvFileHandler.getInstance().readGameListFromCsvFile();
		
		// Kontrollausgabe
		System.out.println(SEPARATION_LINE);
		System.out.println("CSV-import");
		System.out.println(HORIZONTAL_LINE);
		for(Game g : theTestGames){
			System.out.println(g.toString());
		}
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	//endregion
	
}
