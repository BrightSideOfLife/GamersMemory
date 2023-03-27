package de.aschoenberg.logic.csvHandling;

import de.aschoenberg.models.Game;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Einlesen, Schreiben und ggf Anlegen einer CSV-Datei
 *
 * @author Alena Schönberg created at 11.09.20
 */
public class CsvFileHandler {
	
	//region 0.Konstanten
	private static final String FILE_LOCATION       = "src/de/aschoenberg/resources/csvFile";
	private static final String FILE_NAME_GAME      = "/gameList";
	private static final String FILE_TYPE_CSV       = ".csv";
	private static final String FULL_FILE_PATH_GAME = FILE_LOCATION + FILE_NAME_GAME + FILE_TYPE_CSV;
	//endregion
	
	//region 1. Decl. and Init Attribute
	private static CsvFileHandler instance;
	//endregion
	
	//region 2. Konstruktor
	
	/**
	 * Standardkostruktor
	 */
	private CsvFileHandler() {
		// Nichts zu tun
	}
	//endregion
	
	//region 3. Getter und Setter
	
	/**
	 * Singleton, liefert (und ggf initialisiert)
	 * die einige Instanz dieser Klasse
	 *
	 * @return instance : {@link CsvFileHandler} : einzige Instanz
	 */
	public static synchronized CsvFileHandler getInstance() {
		if (instance == null) {
			instance = new CsvFileHandler();
		}
		return instance;
	}
	//endregion
	
	//region Speichern der Spiele
	
	/**
	 * Speichert die übergebene Spiele-Liste in eine CSV-Datei
	 *
	 * @param gameListToSave : {@link List} {@link Game}: Spiele-Liste
	 */
	public void saveGameListToCsvFile(List<Game> gameListToSave) {
		
		// Writer anlegen um Datei zu schreiben
		BufferedWriter bufferedWriterOut = null;
		
		try {
			
			// Ordnerstruktur
			File fileDirectoryToSaveIn = new File(FILE_LOCATION);
			
			// Prüfen, ob es den Ordner bereits gibt, wenn nicht diesen anlegen
			if (!fileDirectoryToSaveIn.exists()) {
				if (fileDirectoryToSaveIn.mkdir()) {
					System.out.println("Ordner: " + fileDirectoryToSaveIn.getName() + " wurde erfolgreich angelegt.");
				}
			}
			
			// Neus Dateiobjekt erzeugen
			File fileToSave = new File(FULL_FILE_PATH_GAME);
			
			// Schreiben in die Datei vorbereiten
			FileOutputStream fos = new FileOutputStream(fileToSave, false);
			OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
			bufferedWriterOut = new BufferedWriter(osw);
			
			// Alle Spiele durchlaufen und in die Datei schreiben
			for (Game g : gameListToSave) {
				bufferedWriterOut.write(g.getAllObjectAttributesAsCsvLine());
			}
			
		} catch (Exception e) {
			// alle Fehler ggf fangen und ausgeben
			e.printStackTrace();
		} finally {
			if (bufferedWriterOut != null) {
				try {
					// Inhalt aus dem Zwischenspeicher in die Datei schreiben und Verbindung schließen
					bufferedWriterOut.close();
				} catch (IOException e) {
					// alle Fehler ggf fangen und ausgeben
					e.printStackTrace();
				}
			}
		}
		
		
	}
	//endregion
	
	//region Lesen der Spiele
	
	/**
	 * Liest alle Spiele aus der CSV-Datei aus
	 * und generiert eine Liste daraus
	 *
	 * @return gameListFromCsvFile : {@link List} {@link Game} : Spiele-Liste
	 */
	public List<Game> readGameListFromCsvFile() {
		
		// neue Spiele-Liste
		List<Game> gameListFromCsvFile = new ArrayList<>();
		
		// Reader anlegen um die Datei auszulesen
		BufferedReader in = null;
		
		try {
			
			// Datei anhand der Ordnerstruktur laden
			File fileToRead = new File(FULL_FILE_PATH_GAME);
			
			if (fileToRead.exists()) {
				
				// Lesen vorbereiten
				FileInputStream fis = new FileInputStream(fileToRead);
				InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
				in = new BufferedReader(isr);
				
				// end-of-file
				boolean eof = false;
				
				while (!eof) {
					
					// Zeile lesen
					String strReadCsvLine = in.readLine();
					
					// Prüfen ob das Ende der Datei erreicht ist
					if (strReadCsvLine == null) {
						eof = true;
					} else {
						
						// neues Spiel anlegen, mit Attributen aus der CSV füllen und zur akteullen Liste hinzufügen
						Game singleGameFromFile = new Game();
						singleGameFromFile.setAllCsvAttributesToThisObject(strReadCsvLine);
						gameListFromCsvFile.add(singleGameFromFile);
						
					}
					
				}
			}
			
			
		} catch (Exception e) {
			// alle Fehler ggf fangen und ausgeben
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					// Verbindung schließen
					in.close();
				}
				
			} catch (IOException e) {
				// alle Fehler ggf fangen und ausgeben
				e.printStackTrace();
			}
		}
		
		return gameListFromCsvFile;
	}
	//endregion
}
