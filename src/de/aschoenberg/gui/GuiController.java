package de.aschoenberg.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Singleton, initialisiert und startet die javaFX-Bühne
 *
 * @author Alena Schönberg created at 09.09.20
 */

public class GuiController {
	
	//region 0.Konstanten
	private static final String LOCATION_GAME_SCENE_LAYOUT_FXML = "../resources/layout/game_scene_layout.fxml";
	private static final int    GAMES_SCENE_WIDTH               = 1230;
	private static final int    GAMES_SCENE_HEIGHT              = 680;
	//endregion
	
	//region 1. Decl. and Init Attribute
	/**
	 * Singleton, es gibt nur eine Instanz dieser Klasse
	 */
	private static GuiController instance;
	
	/**
	 * "Bühne", um Elemente der {@link javafx.scene.Scene} anzuzeigen
	 */
	private Stage primaryStage;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkonsturktor
	 * privat, da Singleton
	 */
	private GuiController() {
		// Nichts zu tun
	}
	
	//endregion
	
	//region 3. Getter und Setter
	
	/**
	 * Liefert das einzigen Objekts dieser Klasse (synchroniesiert)
	 *
	 * @return instance {@link GuiController} : Instanz dieser Klasse
	 */
	public static synchronized GuiController getInstance() {
		if (instance == null) {
			instance = new GuiController();
		}
		return instance;
	}
	
	//endregion
	
	//region Primary Stage
	
	/**
	 * Setzt die jfx-Bühne
	 *
	 * @param primaryStage : {@link Stage} : jfx-Bühne
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	//endregion
	
	//region Scenes
	
	/**
	 * Läd die Parameter zur Spiele-Scene
	 * und zeigt diese auf der "Bühne" an
	 */
	public void showGamesGui() {
		
		try {
			
			// Layout der fxml laden
			Parent root = FXMLLoader.load(getClass().getResource(LOCATION_GAME_SCENE_LAYOUT_FXML));
			
			// Höhe und Breite der Scene setzen
			Scene newScene = new Scene(root, GAMES_SCENE_WIDTH, GAMES_SCENE_HEIGHT);
			primaryStage.setScene(newScene);
			
			// Titel der Scene setzen
			primaryStage.setTitle(AppTexts.APP_TITLE_STR);
			
			// Vorhang auf
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//endregion
	
}
