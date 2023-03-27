package de.aschoenberg.gui;

import de.aschoenberg.logic.csvHandling.CsvFileHandler;
import de.aschoenberg.logic.dbHandling.DbHandler;
import de.aschoenberg.models.Game;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

/**
 * Funktionalität der GUI (ListView und Edit-Bereich)
 * <ul>
 *     <li>Init der Liste mit gespeicherten Daten</li>
 *     <li>Handling der selektierbaren Listen-Eintröge</li>
 *     <li>Listeneinträge neu laden</li>
 *     <li>Init der TextFields mit Daten des #currentGame's</li>
 *     <li>Create, Update und Delete</li>
 * </ul>
 *
 * @author Alena Schönberg created at 07.09.20
 */

public class GameSceneController implements Initializable {
	
	//region 0.Konstanten
	public static final double ALERT_DIALOG_MIN_WIDTH = 700.00D;
	
	private static final String CHOICES_WHO_HAS_IT_STR          = "choicesWhoHasIt";
	private static final String CHOICES_PLAYERS_RECOMMENDED_STR = "choicesPlayerAmountRecommended";
	private static final String CHOICES_PLAYERS_IDEAL_STR       = "choicesPlayerAmountIdeal";
	
	//endregion
	
	//region 1. Decl. and Init Attribute
	
	// Liest View
	@FXML
	private ListView<Game> lvGameList;
	
	private LvCallBackGame lvCallBackGame;
	private List<Game>     currentGameList;
	
	
	// Edit-Bereich
	@FXML
	private TextField        txtOfficialName;
	@FXML
	private TextField        txtAuthor;
	@FXML
	private TextField        txtPublisher;
	@FXML
	private ComboBox<String> cobRating;
	@FXML
	private TextArea         txtaEstimation;
	@FXML
	private Button           chbWhoHasIt;
	@FXML
	private TextField        txtHowOftenPlayed;
	@FXML
	private ComboBox<String> cbRecommendedAge;
	@FXML
	private Button           chbIdealAmountOfPlayers;
	@FXML
	private Button           chbRecommendedAmountOfPlayers;
	@FXML
	private TextField        txtPlayingTime;
	
	private Game currentGame;
	
	
	//endregion
	
	//region 2. Init VistView
	
	/**
	 * Init: Laden der Initialen Daten zur Anzeige in der GUI
	 *
	 * @param url            : Default (wird beim ersten Aufruf gesetzt)
	 * @param resourceBundle : Default (wird beim ersten Aufruf gesetzt)
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		// init Spieleliste
		this.currentGameList = DbHandler.getInstance().getGameListFromDb();
		
		// ComboBox Init
		this.cobRating.getItems().addAll(AppTexts.COMBOBOX_RATING_LIST);
		this.cobRating.setValue("");
		this.cbRecommendedAge.getItems().addAll(AppTexts.COMBOBOX_RECOMMENDED_AGE_LIST);
		this.cbRecommendedAge.setValue("");
		
		// ListView initalisieren und mit Daten füllen
		this.initializeLvGameList();
	}
	
	
	/**
	 * Initalisiert die {@link ListView} mit
	 * deren Funktionsweise und {@link Game}-Daten
	 */
	private void initializeLvGameList() {
		
		// vertikal scrollen ermöglichen
		this.lvGameList.setOrientation(Orientation.VERTICAL);
		
		// CallBack (einzelne Zellen generieren)
		this.lvCallBackGame = new LvCallBackGame();
		
		// ListView mit Spiele-Daten befüllen
		this.updateListViewContent();
		
		// Listener (bei Auswahl einer ListView-Zelle)
		this.lvGameList.getSelectionModel()
				.selectedItemProperty()
				.addListener(this::onCellClick);
	}
	
	//endregion
	
	//region 3. Funktionalität
	
	/**
	 * Listener Event für die ListView-Zellen:
	 * Füllt den Edit-Bereich (Eingabefelder) mit den Daten
	 * der angeklickten Zelle - des dort angezeigten {@link Game}-Objekts
	 *
	 * @param observableGame       : {@link ObservableValue} : Default onCellClick
	 * @param previousSelectedGame : {@link Game} : Default onCellClick
	 * @param currentSelectedGame  : {@link Game} : Default onCellClick
	 */
	private void onCellClick(ObservableValue<? extends Game> observableGame, Game previousSelectedGame, Game currentSelectedGame) {
		
		// Nur ausführen, wenn eine neue Zeile geklickt wurde
		if (previousSelectedGame == null || !previousSelectedGame.equals(currentSelectedGame)) {
			
			// Edit-Bereich zurücksetzen (Vor der Zuweisung des
			// #currentGame, da dieses dort ebenfalls geleert wird!)
			clearAllGuiEntries();
			
			// aktuelles Spiel bereitstellen
			this.currentGame = currentSelectedGame;
			
			// Edit-Bereich füllen
			setCurrentGameAttributesToGui();
		}
	}
	
	/**
	 * TextFields mit Daten füllen wenn ein Spiel selektiert wurde
	 */
	private void setCurrentGameAttributesToGui() {
		
		if (this.currentGame != null) {
			
			this.txtOfficialName.setText(this.currentGame.getOfficialName());
			
			// Optionales Feld
			if (!this.currentGame.getAuthor().isEmpty()) {
				this.txtAuthor.setText(this.currentGame.getAuthor());
			}
			
			// Optionales Feld
			if (!this.currentGame.getPublisher().isEmpty()) {
				this.txtPublisher.setText(this.currentGame.getPublisher());
			}
			
			// EXPLICIT CAST !!
			this.cobRating.setValue(Integer.toString(this.currentGame.getRating()));
			
			// Optionales Feld
			if (!this.currentGame.getEstimation().isEmpty()) {
				this.txtaEstimation.setText(this.currentGame.getEstimation());
			}
			
			this.chbWhoHasIt.setText(AppTexts.getWhoHasItForDisplay(this.currentGame.getWhoHasIt()));
			
			// Optionales Feld
			if (!this.currentGame.getHowOftenPlayed().isEmpty()) {
				this.txtHowOftenPlayed.setText(this.currentGame.getHowOftenPlayed());
			}
			
			// Optionales Feld
			if (this.currentGame.getRecommendedAge() >= 0) {
				// EXPLICIT CAST !!
				this.cbRecommendedAge.setValue(Integer.toString(this.currentGame.getRecommendedAge()));
			}
			
			this.chbIdealAmountOfPlayers.setText(AppTexts.getAmountOfPlayersForDisplay(this.currentGame.getIdealAmountOfPlayers()));
			this.chbRecommendedAmountOfPlayers.setText(AppTexts.getAmountOfPlayersForDisplay(this.currentGame.getRecommendedAmountOfPlayers()));
			this.txtPlayingTime.setText(this.currentGame.getPlayingTime());
			
		}
		
	}
	
	/**
	 * Öffnet Dialog zur Auswahl (Checkboxen)
	 * des Feldes "Wer hat das Spiel?"
	 */
	@FXML
	public void choicesWhoHasIt() {
		raiseAlertWithChoices(AppTexts.GUI_DISPLAY_GAME_WHO_HAS_IT, CHOICES_WHO_HAS_IT_STR);
	}
	
	/**
	 * Öffnet Dialog zur Auswahl (Checkboxen)
	 * des Feldes "Empfohlene Spieleranzahl"
	 */
	@FXML
	public void choicesPlayerAmountRecommended() {
		raiseAlertWithChoices(AppTexts.GUI_DISPLAY_GAME_RECOMMENDED_AMOUNT_OF_PLAYERS, CHOICES_PLAYERS_RECOMMENDED_STR);
	}
	
	/**
	 * Öffnet Dialog zur Auswahl (Checkboxen)
	 * des Feldes "Mögliche Spieleranzahl"
	 */
	@FXML
	public void choicesPlayerAmountIdeal() {
		raiseAlertWithChoices(AppTexts.GUI_DISPLAY_GAME_IDEAL_AMOUNT_OF_PLAYERS, CHOICES_PLAYERS_IDEAL_STR);
	}
	
	/**
	 * Funktionalität zum Download der CSV-Dtei:
	 * Speichert die aktuelle Spiele-Liste die CSV-Datei.
	 * Anschießend wird dem User eine Meldung angezeigt.
	 */
	@FXML
	public void downloadCsv() {
		
		// CSV speichern
		CsvFileHandler.getInstance().saveGameListToCsvFile(this.currentGameList);
		
		// Meldung an User, das Download erfolgt ist
		raiseAlert(Alert.AlertType.INFORMATION, AppTexts.ALERT_CSV_DOWNLOAD_HEADER_STR, AppTexts.ALERT_CSV_DOWNLOAD_BODY_STR);
		
	}
	
	/**
	 * Funktionalität zum Upload der CSV-Daten:
	 * Liest die CSV ein und aktualisiert die ListView.
	 * Anschießend wird dem User eine Meldung angezeigt.
	 */
	@FXML
	public void uploadCsv() {
		
		// Datenbank-Daten löschen
		DbHandler.getInstance().deleteGameList();
		
		// CSV Daten in die Datenbank übernehmen
		DbHandler.getInstance().insertGameList(CsvFileHandler.getInstance().readGameListFromCsvFile());
		
		// Eingabefelder leeren
		clearAllGuiEntries();
		
		// ListView aktualisieren
		updateListViewContent();
		
		// Meldung an User, das Upload erfolgt ist
		raiseAlert(Alert.AlertType.INFORMATION, AppTexts.ALERT_CSV_UPLOAD_HEADER_STR, AppTexts.ALERT_CSV_UPLOAD_BODY_STR);
	}
	
	/**
	 * Funktionalität zum Ändern oder Anlegen eines {@link Game}-Objekts:
	 * Die eigetragenen Daten werden validiert (ggf entsprechende Meldungen
	 * angezeigt) und wenn korrekt gespeichert. Die GUI-Felder und
	 * das #currentGame werden anschließend geleert, das Fenster
	 * der secondaryStage (Edit) wird geschlossen.
	 */
	@FXML
	public void updateOrCreateGame() {
		
		// Validierung
		String strValidationError = validateGuiEntries();
		
		if (strValidationError.isEmpty()) {
			
			// Alle Daten ins currentGame-Objekt übertragen
			setAllGuiEntriesToCurrentGame();
			
			// Prüfen, ob geändertes oder ein neues (Default ID -1) Objekt
			if (this.currentGame.getId() < 0) {
				
				// Objekt in die Datenbank speichern
				DbHandler.getInstance().insertGame(this.currentGame);
				
			} else {
				
				// Objekt in der Datenbank ändern
				DbHandler.getInstance().updateGame(this.currentGame);
				
			}
			
			// Selektierung in der ListView aufheben
			this.lvGameList.getSelectionModel().clearSelection();
			
			// Eingabefelder leeren
			clearAllGuiEntries();
			
			// ListView aktualisieren
			updateListViewContent();
			
		} else {
			// Validierungsfehler anzeigen
			raiseAlert(Alert.AlertType.ERROR, AppTexts.ALERT_VALIDATION_HEADER_STR,
					String.format(AppTexts.ALERT_VALIDATION_BODY_STR, strValidationError));
		}
	}
	
	/**
	 * Prüft, ob die GUI-Eingaben im richtigen Format zum
	 * speichern vorliegen. Stellt entsprechende Fehlermeldungen
	 * für nicht valide Eingaben bereit
	 *
	 * @return : {@link String} : leer oder Fehlermelungen
	 */
	private String validateGuiEntries() {
		
		String strValidationErrors = "";
		
		if (this.txtOfficialName == null || this.txtOfficialName.getText().isEmpty()) {
			strValidationErrors += AppTexts.GUI_BREADCRUMB_STR
					+ AppTexts.GUI_DISPLAY_GAME_OFFICIAL_NAME + " "
					+ AppTexts.VALIDATION_ERROR_NOT_OPTIONAL_STR + AppTexts.CRLF_STR;
		}
		
		if (this.cobRating == null || this.cobRating.getValue().isEmpty()) {
			strValidationErrors += AppTexts.GUI_BREADCRUMB_STR
					+ AppTexts.GUI_DISPLAY_GAME_RATING + " "
					+ AppTexts.VALIDATION_ERROR_NOT_OPTIONAL_STR + AppTexts.CRLF_STR;
		}
		
		if (this.chbWhoHasIt == null || this.chbWhoHasIt.getText().isEmpty()) {
			strValidationErrors += AppTexts.GUI_BREADCRUMB_STR
					+ AppTexts.GUI_DISPLAY_GAME_WHO_HAS_IT + " "
					+ AppTexts.VALIDATION_ERROR_NOT_OPTIONAL_STR + AppTexts.CRLF_STR;
		}
		
		if (this.chbRecommendedAmountOfPlayers == null || this.chbRecommendedAmountOfPlayers.getText().isEmpty()) {
			strValidationErrors += AppTexts.GUI_BREADCRUMB_STR
					+ AppTexts.GUI_DISPLAY_GAME_RECOMMENDED_AMOUNT_OF_PLAYERS + " "
					+ AppTexts.VALIDATION_ERROR_NOT_OPTIONAL_STR + AppTexts.CRLF_STR;
		}
		
		if (this.txtPlayingTime == null || this.txtPlayingTime.getText().isEmpty()) {
			strValidationErrors += AppTexts.GUI_BREADCRUMB_STR
					+ AppTexts.GUI_DISPLAY_GAME_PLAYING_TIME + " "
					+ AppTexts.VALIDATION_ERROR_NOT_OPTIONAL_STR + AppTexts.CRLF_STR;
		}
		
		// Alle weiteren TextFields sind optionale Freitexte!
		
		return strValidationErrors;
	}
	
	
	/**
	 * Funktionalität zum Abbrechen der Bearbeitung:
	 * Es werden keine Daten Gespeichert oder Gelöscht,
	 * die GUI-Felder und das #currentGame werden geleert.
	 * Das Fenster der secondaryStage (Edit) wird geschlossen.
	 */
	@FXML
	public void cancelGame() {
		
		// Eingabefelder leeren
		clearAllGuiEntries();
		
		// Selektierung in der ListView aufheben
		this.lvGameList.getSelectionModel().clearSelection();
		
		/* ListView aktualisieren
		   (nur die Selektierung des aktuellen Elementes aufzuheben
		   funktioniert nicht wenn in den Checkbox-Alert-Angaben
		   etwas verändert wurde)
		*/
		updateListViewContent();
		
	}
	
	/**
	 * Funktionalität zum Löschen eines {@link Game}-Objekts:
	 * Das aktuelle Objekt wird gelöscht (Dobble-Check mit Alert-Window).
	 * Die GUI-Felder und das #currentGame werden anschließend geleert,
	 * das Fenster der secondaryStage (Edit) wird geschlossen.
	 */
	@FXML
	public void deleteGame() {
		
		if (this.currentGame != null
				&& raiseAlert(Alert.AlertType.CONFIRMATION, AppTexts.ALERT_DELETE_HEADER_STR, AppTexts.ALERT_DELETE_BODY_STR)) {
			
			// Objekt in der Datenbank löschen
			DbHandler.getInstance().deleteGame(this.currentGame.getId());
			
			// Selektierung in der ListView aufheben
			this.lvGameList.getSelectionModel().clearSelection();
			
			// Eingabefelder leeren
			clearAllGuiEntries();
			
			// ListView aktualisieren
			updateListViewContent();
		}
	}
	//endregion
	
	//region Hilfsmethoden und Funktionen
	
	/**
	 * Lädt die Spiel-Daten in der ListView neu
	 */
	private void updateListViewContent() {
		
		// Daten aus der Datenbank neu laden
		this.currentGameList = DbHandler.getInstance().getGameListFromDb();
		
		// Observable generieren und der ListView zuweisen
		ObservableList<Game> userObservableList = FXCollections.observableList(this.currentGameList);
		this.lvGameList.setItems(userObservableList);
		
		// Daten in die ListView schreiben
		this.lvGameList.setCellFactory(this.lvCallBackGame);
	}
	
	/**
	 * Zurücksetzen aller GUI-Eingabefelder (Edit-Bereich) auf Null.
	 * Und zurücksetzen des #currentGame auf null.
	 */
	private void clearAllGuiEntries() {
		
		// Hilfsattribut zurücksetzen
		this.currentGame = null;
		
		// alle GUI-Eingabefelder zurücksetzen
		this.txtOfficialName.setText("");
		this.txtAuthor.setText("");
		this.txtPublisher.setText("");
		this.cobRating.setValue("");
		this.txtaEstimation.setText("");
		this.chbWhoHasIt.setText("");
		this.cbRecommendedAge.setValue("");
		this.chbIdealAmountOfPlayers.setText("");
		this.chbRecommendedAmountOfPlayers.setText("");
		this.txtPlayingTime.setText("");
		this.txtHowOftenPlayed.setText("");
		
	}
	
	/**
	 * Übernimmt alle GUI-Eingaben in das aktuelle
	 * Objekt #currentGame der Spiele-Klasse
	 */
	private void setAllGuiEntriesToCurrentGame() {
		
		// leeres Spiel initialisieren
		if (this.currentGame == null) {
			// ohne ID zum Speichern eines neuen Spieles
			this.currentGame = new Game();
		} else {
			// mit ID zum ändern eines bestehenden Spieles
			this.currentGame = new Game(this.currentGame.getId(),
					this.currentGame.getWhoHasIt(),
					this.currentGame.getRecommendedAmountOfPlayers(),
					this.currentGame.getIdealAmountOfPlayers());
		}
		
		// Objekt mit Daten aus der GUi befüllen
		
		this.currentGame.setOfficialName(this.txtOfficialName.getText());
		this.currentGame.setAuthor(this.txtAuthor.getText());
		this.currentGame.setPublisher(this.txtPublisher.getText());
		this.currentGame.setRating(Integer.parseInt(this.cobRating.getValue()));
		this.currentGame.setEstimation(this.txtaEstimation.getText());
		
		// this.currentGame.setWhoHasIt wird durch den Checkbox-Dialog (Alert) geändert
		
		// Optionales Feld
		if (!this.cbRecommendedAge.getValue().isEmpty()) {
			// EXPLICIT CAST !!
			this.currentGame.setRecommendedAge(Integer.parseInt(this.cbRecommendedAge.getValue()));
		}
		
		// this.currentGame.setIdealAmountOfPlayers wird durch den Checkbox-Dialog (Alert) geändert
		// this.currentGame.setRecommendedAmountOfPlayers wird durch den Checkbox-Dialog (Alert) geändert
		
		this.currentGame.setPlayingTime(this.txtPlayingTime.getText());
		this.currentGame.setHowOftenPlayed(this.txtHowOftenPlayed.getText());
		
	}
	
	/**
	 * Lässt ein Alert-Window erscheinen mit den entsprechend der
	 * Parameter übergebenen Texte und der Alert-Art
	 * Wenn es ein Bestätigungs-Dialog (CONFIRMATION) ist, wird boolean-false
	 * zurück gegeben sollte dort nicht OK geklickt werden!
	 *
	 * @param alertType           : {@link Alert.AlertType} : CONFIRMATION/ERROR/INFORMATION
	 * @param strValidationHeader : {@link String} : Header Text
	 * @param srtValidatioBody    : {@link String} : Body Text
	 * @return isConfirmed        : boolean : true-Bestätigt, false-Abgebrochen
	 */
	private boolean raiseAlert(Alert.AlertType alertType, String strValidationHeader, String srtValidatioBody) {
		
		boolean result = true;
		
		Alert alert = new Alert(alertType);
		
		// Texte setzen
		alert.setTitle(strValidationHeader);
		alert.setHeaderText(null);
		alert.setContentText(srtValidatioBody);
		
		// Breite setzen
		alert.getDialogPane().setMinWidth(ALERT_DIALOG_MIN_WIDTH);
		
		// Show & Wait
		if (alertType == Alert.AlertType.CONFIRMATION
				&& alert.showAndWait().filter(t -> t == ButtonType.OK).isPresent()) {
			result = true; // Die Bestätigung der Eingabe wurde geklickt
		} else if (alertType == Alert.AlertType.CONFIRMATION) {
			result = false; // Die Bestätigung der Eingabe wurde abgebrochen
		} else {
			alert.showAndWait(); // Alerts ohne Bestätigungs-Button
		}
		
		return result;
	}
	
	/**
	 * Alert-Dialog, ermöglicht Mehrfachauswahl für ein Attribut
	 *
	 * @param strTitle   : {@link String} :
	 * @param strContext : {@link String} :
	 */
	public void raiseAlertWithChoices(String strTitle, String strContext) {
		
		// Alert
		Alert alertWithChoices = new Alert(Alert.AlertType.CONFIRMATION);
		
		// Texte setzen
		alertWithChoices.setTitle(strTitle);
		alertWithChoices.setHeaderText(null);
		
		// Body erstellen
		VBox dialogPaneContent = new VBox();
		dialogPaneContent.spacingProperty().setValue(10);
		
		Label label = new Label(AppTexts.PLEASE_CHOSE_STR);
		
		CheckBox checkBoxOne   = new CheckBox();
		CheckBox checkBoxTwo   = new CheckBox();
		CheckBox checkBoxThree = new CheckBox();
		CheckBox checkBoxFour  = new CheckBox();
		CheckBox checkBoxFive  = new CheckBox();
		CheckBox checkBoxMore  = new CheckBox();
		
		// Checkboxen init
		switch (strContext) {
			case CHOICES_WHO_HAS_IT_STR:
				
				// Beschriftung der Checkbox
				checkBoxOne.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_ONE);
				checkBoxTwo.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_TWO);
				checkBoxThree.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_THREE);
				checkBoxFour.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_FOUR);
				checkBoxFive.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_FIVE);
				checkBoxMore.textProperty().set(AppTexts.CHOICE_WHO_HAS_IT_MORE);
				
				// Vorhandene Einträge selektieren
				if (this.currentGame != null) {
					for (String s : this.currentGame.getWhoHasIt()) {
						switch (s) {
							case AppTexts.CHOICE_WHO_HAS_IT_ONE -> checkBoxOne.setSelected(true);
							case AppTexts.CHOICE_WHO_HAS_IT_TWO -> checkBoxTwo.setSelected(true);
							case AppTexts.CHOICE_WHO_HAS_IT_THREE -> checkBoxThree.setSelected(true);
							case AppTexts.CHOICE_WHO_HAS_IT_FOUR -> checkBoxFour.setSelected(true);
							case AppTexts.CHOICE_WHO_HAS_IT_FIVE -> checkBoxFive.setSelected(true);
							case AppTexts.CHOICE_WHO_HAS_IT_MORE -> checkBoxMore.setSelected(true);
							default -> System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
						}
					}
				}
				break;
			
			case CHOICES_PLAYERS_RECOMMENDED_STR:
				
				// Beschriftung der Checkbox
				checkBoxOne.textProperty().set(String.format("%s Person", AppTexts.CHOICE_PLAYERS_ONE));
				checkBoxTwo.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_TWO));
				checkBoxThree.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_THREE));
				checkBoxFour.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_FOUR));
				checkBoxFive.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_FIVE));
				checkBoxMore.textProperty().set(String.format("%s Personen und mehr", AppTexts.CHOICE_PLAYERS_MORE));
				
				// Vorhandene Einträge selektieren
				if (this.currentGame != null) {
					for (Integer i : this.currentGame.getRecommendedAmountOfPlayers()) {
						switch (i) {
							case AppTexts.CHOICE_PLAYERS_ONE -> checkBoxOne.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_TWO -> checkBoxTwo.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_THREE -> checkBoxThree.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_FOUR -> checkBoxFour.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_FIVE -> checkBoxFive.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_MORE -> checkBoxMore.setSelected(true);
							default -> System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
						}
					}
				}
				break;
			
			case CHOICES_PLAYERS_IDEAL_STR:
				
				// Beschriftung der Checkbox
				checkBoxOne.textProperty().set(String.format("%s Person", AppTexts.CHOICE_PLAYERS_ONE));
				checkBoxTwo.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_TWO));
				checkBoxThree.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_THREE));
				checkBoxFour.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_FOUR));
				checkBoxFive.textProperty().set(String.format("%s Personen", AppTexts.CHOICE_PLAYERS_FIVE));
				checkBoxMore.textProperty().set(String.format("%s Personen und mehr", AppTexts.CHOICE_PLAYERS_MORE));
				
				// Vorhandene Einträge selektieren
				if (this.currentGame != null) {
					for (Integer i : this.currentGame.getIdealAmountOfPlayers()) {
						switch (i) {
							case AppTexts.CHOICE_PLAYERS_ONE -> checkBoxOne.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_TWO -> checkBoxTwo.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_THREE -> checkBoxThree.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_FOUR -> checkBoxFour.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_FIVE -> checkBoxFive.setSelected(true);
							case AppTexts.CHOICE_PLAYERS_MORE -> checkBoxMore.setSelected(true);
							default -> System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
						}
					}
				}
				break;
			
			default:
				System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
		}
		
		// Checkboxen zum Alert hinzufügen
		dialogPaneContent.getChildren().addAll(label,
				checkBoxOne, checkBoxTwo, checkBoxThree, checkBoxFour, checkBoxFive, checkBoxMore);
		alertWithChoices.getDialogPane().setContent(dialogPaneContent);
		
		// Show And Wait
		if (alertWithChoices.showAndWait().filter(t -> t == ButtonType.OK).isPresent()) {
			
			// Wenn die Checkbox-Auswahl bestätigt wird, dann auslesen
			if (this.currentGame == null) {
				this.currentGame = new Game();
			}
			List<String>  stringList = new ArrayList<>();
			List<Integer> intList    = new ArrayList<>();
			
			switch (strContext) {
				case CHOICES_WHO_HAS_IT_STR:
					if (checkBoxOne.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_ONE);
					}
					if (checkBoxTwo.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_TWO);
					}
					if (checkBoxThree.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_THREE);
					}
					if (checkBoxFour.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_FOUR);
					}
					if (checkBoxFive.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_FIVE);
					}
					if (checkBoxMore.isSelected()) {
						stringList.add(AppTexts.CHOICE_WHO_HAS_IT_MORE);
					}
					break;
				default:  // Player-Amount
					if (checkBoxOne.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_ONE);
					}
					if (checkBoxTwo.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_TWO);
					}
					if (checkBoxThree.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_THREE);
					}
					if (checkBoxFour.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_FOUR);
					}
					if (checkBoxFive.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_FIVE);
					}
					if (checkBoxMore.isSelected()) {
						intList.add(AppTexts.CHOICE_PLAYERS_MORE);
					}
			}
			
			// und übernehmen, aber noch nicht speichern
			
			switch (strContext) {
				case CHOICES_WHO_HAS_IT_STR:
					this.currentGame.setWhoHasIt(stringList);
					this.chbWhoHasIt.setText(AppTexts.getWhoHasItForDisplay(this.currentGame.getWhoHasIt()));
					break;
				case CHOICES_PLAYERS_RECOMMENDED_STR:
					this.currentGame.setRecommendedAmountOfPlayers(intList);
					this.chbRecommendedAmountOfPlayers.setText(AppTexts.getAmountOfPlayersForDisplay(this.currentGame.getRecommendedAmountOfPlayers()));
					break;
				case CHOICES_PLAYERS_IDEAL_STR:
					this.currentGame.setIdealAmountOfPlayers(intList);
					this.chbIdealAmountOfPlayers.setText(AppTexts.getAmountOfPlayersForDisplay(this.currentGame.getIdealAmountOfPlayers()));
					break;
				default:
					System.out.println(AppTexts.ERROR_NO_SUCH_VALUE_STR);
			}
		}
	}
	//endregion
	
}

