package it.uniba.app.gameTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.nio.charset.StandardCharsets;

import it.uniba.app.game.PlayGame;
import it.uniba.app.grid.GameGrid;
import it.uniba.app.ship.Coordinates;





/**
 * Classe di test per la classe PlayGame.
 * Contiene i test per i metodi della classe PlayGame.
 * @author gamal, GamalElmetkanany
 */
class PlayGameTest {
    private PlayGame playGame;

    @BeforeEach
    void setUpExitGame() {
        playGame = new PlayGame("/facile", PlayGame.ATTEMPTS_EASY);
    }

    @Test
    @DisplayName("Test di uscita dal gioco (conferma negativa)")
    void testExitGameReturnsTrueWhenChoiceExitIsNo() {
        ByteArrayInputStream in = new ByteArrayInputStream("no".getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name());

        boolean result = playGame.exitGame(scanner);

        assertTrue(result, "Il risultato dovrebbe essere vero (true)");
    }

    @Test
    @DisplayName("Test di uscita dal gioco (conferma non valida)")
    void testExitGameReturnsTrueWhenChoiceExitIsInvalid() {
        String invalidInput = "altro valore";
        String validInput = "no";
        ByteArrayInputStream inputStream = new ByteArrayInputStream((invalidInput
               + System.lineSeparator() + validInput).getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());

        boolean result = playGame.exitGame(scanner);

        assertTrue(result, "Il risultato dovrebbe essere vero (true)");
    }

    @Test
    @DisplayName("Test di settaggio del livello facile")
    void testSetLevelAndAttemptsEasy() {
        PlayGame game = new PlayGame("livello", 0);
        assertTrue("Impossibile impostare il livello e i tentativi su '/facile'", game.setLevelAndAttempts("/facile"));
    }

    @Test
    @DisplayName("Test di controllo del livello facile")
    void testLevelGameIsEasy() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/facile");
        assertEquals("Livello errato dopo l'impostazione su '/facile'", "facile", game.getLevelGame());
    }

    @Test
    @DisplayName("Test di controllo del numero di tentativi per il livello facile")
    void testAttemptsForEasyLevel() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/facile");
        assertEquals("Numero di tentativi errato per il livello '/facile'", PlayGame.ATTEMPTS_EASY, game.getAttempts());
    }

    @Test
    @DisplayName("Test di settaggio del livello medio")
    void testSetLevelAndAttemptsNormal() {
        PlayGame game = new PlayGame("livello", 0);
        assertTrue("Impossibile impostare il livello e i tentativi su '/medio'", game.setLevelAndAttempts("/medio"));
    }

    @Test
    @DisplayName("Test di controllo del livello medio")
    void testLevelGameIsNormal() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/medio");
        assertEquals("Livello errato dopo l'impostazione su '/medio'", "medio", game.getLevelGame());
    }

    @Test
    @DisplayName("Test di controllo del numero di tentativi per il livello medio")
    void testAttemptsForNormalLevel() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/medio");
        assertEquals("Numero di tentativi errato per il livello '/medio'",
                     PlayGame.ATTEMPTS_NORMAL, game.getAttempts());
    }

    @Test
    @DisplayName("Test di settaggio del livello difficile")
    void testSetLevelAndAttemptsDifficult() {
        PlayGame game = new PlayGame("livello", 0);
        assertTrue("Impossibile impostare il livello e i tentativi su '/difficile'",
                   game.setLevelAndAttempts("/difficile"));
    }

    @Test
    @DisplayName("Test di controllo del livello difficile")
    void testLevelGameIsDifficult() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/difficile");
        assertEquals("Livello errato dopo l'impostazione su '/difficile'", "difficile", game.getLevelGame());
    }

    @Test
    @DisplayName("Test di controllo del numero di tentativi per il livello difficile")
    void testAttemptsForDifficultLevel() {
        PlayGame game = new PlayGame("livello", 0);
        game.setLevelAndAttempts("/difficile");
        assertEquals("Numero di tentativi errato per il livello '/difficile'",
                     PlayGame.ATTEMPTS_DIFFICULT, game.getAttempts());
    }
    @Test
    @DisplayName("Test di settaggio della dimensione della griglia su '/standard'")
    void testSetGridSizeSettingStandard() {
        GameGrid gameGrid = new GameGrid();
        boolean result = playGame.setGridSizeSetting("/standard", gameGrid);
        assertTrue("La dimensione della griglia non è stata impostata correttamente su '/standard'", result);
    }

    @Test
    @DisplayName("Test di controllo della dimensione della griglia su '/standard'")
    void testCheckGridSizeStandard() {
        GameGrid gameGrid = new GameGrid();
        playGame.setGridSizeSetting("/standard", gameGrid);
        assertEquals("La dimensione della griglia non corrisponde alla dimensione standard",
                     gameGrid.getStandardGridSize(), gameGrid.getGridSize());
    }

    @Test
    @DisplayName("Test di settaggio della dimensione della griglia su '/large'")
    void testSetGridSizeSettingLarge() {
        GameGrid gameGrid = new GameGrid();
        boolean result = playGame.setGridSizeSetting("/large", gameGrid);
        assertTrue("La dimensione della griglia non è stata impostata correttamente su '/large'", result);
    }

    @Test
    @DisplayName("Test di controllo della dimensione della griglia su '/large'")
    void testCheckGridSizeLarge() {
        GameGrid gameGrid = new GameGrid();
        playGame.setGridSizeSetting("/large", gameGrid);
        assertEquals("La dimensione della griglia non corrisponde alla dimensione large",
                     gameGrid.getLargeGridSize(), gameGrid.getGridSize());
    }

    @Test
    @DisplayName("Test di settaggio della dimensione della griglia su '/extralarge'")
    void testSetGridSizeSettingExtraLarge() {
        GameGrid gameGrid = new GameGrid();
        boolean result = playGame.setGridSizeSetting("/extralarge", gameGrid);
        assertTrue("La dimensione della griglia non è stata impostata correttamente su '/extralarge'", result);
    }

    @Test
    @DisplayName("Test di controllo della dimensione della griglia su '/extralarge'")
    void testCheckGridSizeExtraLarge() {
        GameGrid gameGrid = new GameGrid();
        playGame.setGridSizeSetting("/extralarge", gameGrid);
        assertEquals("La dimensione della griglia non corrisponde alla dimensione extralarge",
                     gameGrid.getExtralargeGridSize(), gameGrid.getGridSize());
    }

    @Test
    @DisplayName("Test di avvio del gioco con impostazioni valide")
     void testStartGameWithValidSettings() throws UnsupportedEncodingException {
        String[] testTime = {"tempo", "10"};
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("livello", 1);

        game.setLevelGame("/facile");
        game.setMaxTime(testTime);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream("y".getBytes(StandardCharsets.UTF_8));
        System.setOut(new PrintStream(outputStream, true, "UTF-8"));
        System.setIn(inputStream);

        game.startGame(gameGrid);

        System.setOut(System.out);
        System.setIn(System.in);

        assertTrue("Il gioco non è stato avviato correttamente", gameGrid.getIsStarted());
    }

    @Test
    @DisplayName("Test di avvio del gioco senza impostare il livello")
    void testStartGameWithoutSettingLevel() {
        String[] testTime = {"tempo", "10"};
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("livello", 0);

        game.setMaxTime(testTime);
        game.startGame(gameGrid);

        assertFalse("Il gioco è stato avviato nonostante il livello non sia stato impostato", gameGrid.getIsStarted());
    }

    @Test
    @DisplayName("Test di avvio del gioco senza impostare il numero di tentativi")
    void testStartGameWithoutSettingAttempts() {
        String[] testTime = {"tempo", "10"};
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("livello", 0);

        game.setLevelGame("/facile");
        game.setMaxTime(testTime);
        game.startGame(gameGrid);

        assertFalse("Il gioco è stato avviato nonostante il numero di tentativi non sia stato impostato",
                    gameGrid.getIsStarted());
    }

    @Test
    @DisplayName("Test di avvio del gioco senza impostare il tempo massimo")
    void testStartGameWithoutSettingMaxTime() {
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("livello", 1);

        game.setLevelGame("/facile");
        game.startGame(gameGrid);

        assertFalse("Il gioco è stato avviato nonostante il tempo massimo non sia stato impostato",
                    gameGrid.getIsStarted());
    }

    @Test
    @DisplayName("Test di avvio del gioco con impostazioni errate")
    void testStartGameWithWrongSettings() {
        String[] testTime = {"tempo", "10"};
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("livello", 0);

        game.setLevelGame("/facile");
        game.setMaxTime(testTime);
        game.startGame(gameGrid);

        assertFalse("Il gioco è stato avviato nonostante le impostazioni siano errate", gameGrid.getIsStarted());
    }

    @Test
    @DisplayName("Test di coordinate corrette")
    void testGetCoordinatesWithValidInput() {
        PlayGame game = new PlayGame("/facile", 1);
        String input = "B-4\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());

        int[] coordinates = game.getCoordinates(scanner);
        final int n = 3;

        assertArrayEquals("Le coordinate ottenute non corrispondono all'input", new int[]{n, 1}, coordinates);
    }

    @Test
    @DisplayName("Test nave non affondata")
    void testCheckCoordinatesShipNotSunk() {
        final int n = 10;
        // Crea una griglia di gioco
        GameGrid gameGrid = new GameGrid();
        gameGrid.setGridSize(n);
        gameGrid.createGrid();

        // Crea una griglia di attacco
        GameGrid attackGrid = new GameGrid();
        attackGrid.setGridSize(n);
        attackGrid.createGrid();

        // Inserisci una nave di dimensione 3 nella griglia di gioco
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new Coordinates(0, 0));
        coordinatesList.add(new Coordinates(0, 1));
        coordinatesList.add(new Coordinates(0, 2));
        gameGrid.getCoordinatesShips().put(1, coordinatesList);

        // Imposta lo stato delle celle nella griglia di attacco
        attackGrid.setGridValue(0, 0, 'C');
        attackGrid.setGridValue(0, 1, 'C');
        attackGrid.setGridValue(0, 2, 'S'); // Cella non colpita

        // Crea un'istanza di PlayGame
        PlayGame game = new PlayGame("/facile", 1);
        game.setAttackGrid(attackGrid);

        // Chiama il metodo checkCoordinates e verifica il risultato
        boolean isSunk = game.checkCoordinates(gameGrid, 0, 0);
        assertTrue(isSunk, "La nave non dovrebbe essere affondata");
    }

    @Test
    @DisplayName("Test nave affondata")
    void testCheckCoordinatesShipSunk() {
        final int n = 10;
        // Crea una griglia di gioco
        GameGrid gameGrid = new GameGrid();
        gameGrid.setGridSize(n);
        gameGrid.createGrid();

        // Crea una griglia di attacco
        GameGrid attackGrid = new GameGrid();
        attackGrid.setGridSize(n);
        attackGrid.createGrid();

        // Inserisci una nave di dimensione 3 nella griglia di gioco
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new Coordinates(0, 0));
        coordinatesList.add(new Coordinates(0, 1));
        coordinatesList.add(new Coordinates(0, 2));
        gameGrid.getCoordinatesShips().put(1, coordinatesList);

        // Imposta lo stato delle celle nella griglia di attacco
        attackGrid.setGridValue(0, 0, 'C');
        attackGrid.setGridValue(0, 1, 'C');
        attackGrid.setGridValue(0, 2, 'C'); // Cella colpita

        // Crea un'istanza di PlayGame
        PlayGame game = new PlayGame("/facile", 1);
        game.setAttackGrid(attackGrid);

        // Chiama il metodo checkCoordinates e verifica il risultato
        boolean isSunk = game.checkCoordinates(gameGrid, 0, 0);
        assertTrue(isSunk, "La nave dovrebbe essere affondata");
    }

    @BeforeEach
    void setUpcheckFinishGame() {
        final int n = 50;
        playGame = new PlayGame("Facile", n);
    }

    @Test
    @DisplayName("Test finishGame con navi e tantativi uguali e tentativi falliti uguali a 0")
    void testCheckFinishGameNoMissedShotsNoRemainingShipsReturnsFalse() {
        final int n = 10;
        GameGrid gameGrid = new GameGrid();
        gameGrid.setNumberShips(n);
        playGame.setMissedShots(0);
        playGame.setAttempts(n);

        boolean result = playGame.checkFinishGame(gameGrid);

        Assertions.assertFalse(result, "Dovrebbe ritornare false");
    }

    @Test
    @DisplayName("Test finishGame con tentativi falliti uguali al massimo dei tentativi")
    void testCheckFinishGameMissedShotsEqualToAttemptsReturnsTrue() {
        final int n = 3;
        final int n1 = 5;
        GameGrid gameGrid = new GameGrid();
        gameGrid.setNumberShips(n);
        playGame.setMissedShots(n1);
        playGame.setAttempts(n1);

        boolean result = playGame.checkFinishGame(gameGrid);

        Assertions.assertTrue(result, "Dovrebbe ritornare true");
    }

    @Test
    @DisplayName("Test finishGame con numero navi rimanenti uguali a 0 e tentativi "
                 + "falliti diversi al massimo dei tentativi")
    void testCheckFinishGameNumberShipsEqualToZeroReturnsTrue() {
        final int n = 0;
        final int n1 = 5;
        GameGrid gameGrid = new GameGrid();
        gameGrid.setNumberShips(n);
        playGame.setMissedShots(n);
        playGame.setAttempts(n1);

        boolean result = playGame.checkFinishGame(gameGrid);

        Assertions.assertTrue(result, "Dovrebbe ritornare true");
    }

    @Test
    @DisplayName("Test finishGame con dati tutti diversi")
    void testCheckFinishGameMissedShotsLessThanAttemptsReturnsFalse() {
        final int n = 3;
        final int n1 = 5;
        final int n2 = 10;
        GameGrid gameGrid = new GameGrid();
        gameGrid.setNumberShips(n1);
        playGame.setMissedShots(n);
        playGame.setAttempts(n2);

        boolean result = playGame.checkFinishGame(gameGrid);

        Assertions.assertFalse(result, "Dovrebbe ritornare false");
    }

    @Test
    @DisplayName("Test showAttempt quando il gioco è iniziato")
    void testShowAttemptGameStarted() throws UnsupportedEncodingException {
        final int n = 10;
        final int n1 = 5;
        GameGrid gameGrid = new GameGrid();
        gameGrid.setIsStarted(true);

        PlayGame game = new PlayGame("/facile", 1);
        game.setAttempts(n);
        game.setTotalAttempt(n1);
        game.setMissedShots(2);

        // Cattura l'output della console
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream, true, "UTF-8");
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        game.showAttempt(gameGrid);

        // Ripristina l'output della console
        System.out.flush();
        System.setOut(originalOut);

        String consoleOutput = outputStream.toString("UTF-8");
        String expectedOutput = "\nNUMERO MASSIMO DI TENTATIVI FALLITI: 10\n"
                              + "\nNUMERO DI TENTATIVI EFFETTUATI: 5\n"
                              + "\nNUMERO DI TENTATIVI FALLITI: 2\n";

        String[] consoleLines = consoleOutput.split("\\r?\\n");
        String[] expectedLines = expectedOutput.split("\\r?\\n");

        for (int i = 0; i < expectedLines.length; i++) {
            assertEquals("Non sono uguali", expectedLines[i], consoleLines[i]);
        }
    }

    @Test
    @DisplayName("Test showAttempt quando il gioco non è iniziato")
    void testShowAttemptGameNotStarted() throws UnsupportedEncodingException {
        GameGrid gameGrid = new GameGrid();
        gameGrid.setIsStarted(false);

        PlayGame game = new PlayGame("/facile", 1);

        // Cattura l'output della console
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream, true, "UTF-8");
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        game.showAttempt(gameGrid);

        // Ripristina l'output della console
        System.out.flush();
        System.setOut(originalOut);

        String consoleOutput = outputStream.toString("UTF-8");
        String expectedOutput = "\nATTENZIONE, NON HAI ANCORA INIZIATO IL GIOCO\n";
        assertEquals("Non sono uguali", expectedOutput.trim(), consoleOutput.trim());
    }




}
