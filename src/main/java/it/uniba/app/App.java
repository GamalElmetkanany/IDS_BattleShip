package it.uniba.app;
import java.util.Scanner;
import it.uniba.app.grid.GameGrid;
import it.uniba.app.game.Generics;
import it.uniba.app.game.PlayGame;

/**
 * Classe che permette la funzionalità regolare del gioco di Battaglia Navale.
 * @author Daniele Grandolfo, dgrandolfo4
 * @author gamal, GamalElmetkanany
 * @author rosanna, RosannaFracchiolla
 * @author matteo, CapoMatteoJr
 * @author agnese, aDaddabbo20
 * @author maria, mariaforte02
 */
public final class App {
    private App() { }

    /**
     * Metodo di prova per appTest.
     * @return Scritta di benvenuti nel gioco.
     */
    public static String getGreeting() {
        return "BENVENUTI NEL GIOCO DELLA BATTAGLIA NAVALE";
    }

    /**
     * Metodo avviato all'esecuzione del programma.
     * @param args Contiene gli argomenti della linea di comando
     */
    public static void main(final String[] args) {
        GameGrid gameGrid = new GameGrid();
        PlayGame game = new PlayGame("/facile", PlayGame.ATTEMPTS_EASY);
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Generics.welcomeGameBattleship();
        Generics.welcomeGame();

        if (args.length > 0) {
            if (args[0].equals("-h") || args[0].equals("--help")) {
                Generics.commandHelp(scanner);
            }
        }

        boolean flagMenu = true;
        boolean replay = true;
        while (flagMenu) {
            if (!replay) {
                gameGrid = new GameGrid();
                game = new PlayGame("/facile", PlayGame.ATTEMPTS_EASY);
            }
            if (game.checkFinishGame(gameGrid)) {
                flagMenu = false;
            } else {
                Generics.command(scanner, gameGrid, game, true);
                String input = Generics.readInput(scanner);
                String[] splittedCommand = input.split(" ");
                String command = splittedCommand[0];

                switch (command) {
                    case "/help":
                        Generics.welcomeGame();
                        Generics.commandHelp(scanner);
                        break;
                    case "/esci":
                        flagMenu = game.exitGame(scanner);
                        break;
                    case "/facile":
                    case "/medio":
                    case "/difficile":
                        game.setLevelSettings(splittedCommand, gameGrid);
                        break;
                    case "/standard":
                    case "/large":
                    case "/extralarge":
                        game.setGridSizeSetting(command, gameGrid);
                        break;
                    case "/mostralivello":
                        game.showLevel();
                        break;
                    case "/mostranavi":
                        Generics.typeShip();
                        break;
                    case "/gioca":
                        game.startGame(gameGrid);
                        break;
                    case "/svelagriglia":
                        gameGrid.printGrid(true);
                        break;
                    case "/tempo":
                        game.setMaxTime(splittedCommand);
                        break;
                    case "/mostratempo":
                        game.showTime();
                        break;
                    case "/tentativi":
                        game.commandAttempts(splittedCommand, gameGrid);
                        break;
                    case "/attacca":
                        game.attackShip(gameGrid, scanner);
                        break;
                    case "/abbandona":
                        replay = game.quitGame(gameGrid);
                        break;
                    case "/mostratentativi":
                        game.showAttempt(gameGrid);
                        break;
                    case "/mostragriglia":
                        game.printAttackGrid();
                        break;
                    default:
                        Generics.prints(Generics.CASO_ERRORE);
                        break;
                }
            }
        }
        scanner.close();
        System.exit(0);
    }
}
