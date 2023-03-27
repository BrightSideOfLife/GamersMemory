package de.aschoenberg.main;

import de.aschoenberg.gui.GuiController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Einspiegspunkt in die FX-Applikation
 *
 * @author Alena Schönberg created at 07.09.20
 */

public class Main extends Application {
    
    /**
     * Initialisiert und öffnet die javaFX-GUI
     * @param primaryStage : {@link Stage} : Default, jfx Komponente
     */
    @Override
    public void start(Stage primaryStage){
    
        // MainStage initialisieren
        GuiController.getInstance().setPrimaryStage(primaryStage);
    
        // Spiele-Scene öffnen
       GuiController.getInstance().showGamesGui();
       
    }
    
    /**
     * Einstiegspunkt in die Applikation
     * @param strInitList : {@link String[]} : Parameter
     */
    public static void main(String[] strInitList) {
        
        // javaFX-Methode zur Anzeige der GUI
        launch(strInitList);
    }
}
