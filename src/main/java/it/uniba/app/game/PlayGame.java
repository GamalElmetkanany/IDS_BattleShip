package it.uniba.app.game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;

import it.uniba.app.grid.GameGrid;
import it.uniba.app.ship.Ship;
import it.uniba.app.ship.Coordinates;
import it.uniba.app.colours.Colours;



/**
 * <p>PlayGame e' una classe <<Control>>.
 * Permette l'implementazione per la modifica dei valori di gioco.</p>
 * @author Daniele Grandolfo, dgrandolfo4
 * @author gamal, GamalElmetkanany
 * @author Agnese D'Addabbo, aDaddabbo20
 */
public class PlayGame {
    /**
     * Valore di {@link PlayGame#maxFailedAttempts} con il livello "Facile", tentativi di default.
     */
    public static final int ATTEMPTS_EASY = 50;
    /**
     * Valore di {@link PlayGame#maxFailedAttempts} con il livello "Medio".
     */
    public static final int ATTEMPTS_NORMAL = 30;
    /**
     * Valore di {@link PlayGame#maxFailedAttempts} con il livello "Difficile".
     */
    public static final int ATTEMPTS_DIFFICULT = 10;
    /**
     * <p>Indica il livello di gioco scelto dall'utente, inizialmente di default è facile.</p>
     */
    private String levelGame;
    /**
     * <p>Indica il numero di tentativi totali di cui l'utente dispone, di default impostato a 50.</p>
     * Varia in base al {@link PlayGame#levelGame}
     */
    private int maxFailedAttempts;
    /**
     *<p>Indica il tempo massimo di gioco che varia in base alla scelta del giocatore.
     */
    private int newTime = 0;
    /**
     * Indica il tempo in millisecondi equivalenti a un minuto.
     */
    public static final int TIME_MILLISECONDS = 60000;
    /**
     * Indica l'operazione di trasformazione dei minuti in millisecondi.
     */
    private static final int MINUTES_TO_MILLISECONDS = 60 * 1000;
    /**
     * Indica da quanti secondi è composto un minuto.
     */
    private static final int MINUTE = 60;
    /**
     * Orario di inizio del gioco.
     */
    private LocalTime startTime = null;

    /**
     * Durata totale del gioco.
     */
    private Duration gameTime = null;
    /**
     * Griglia di attacco utilizzata durante il gioco.
     */
    private GameGrid attackGrid = new GameGrid();
    /**
     * Lista di coordinate.
     */
    private List<Coordinates> coord = null;

    /**
     * Numero di tentativi di colpi mancati durante il gioco.
     */
    private int missedShots = 0;
    /**
     * Numero di tentativi di colpo effettuati durante il gioco.
     */
    private int totalAttempt = 0;


    /**
     * Costruttore di {@link PlayGame}.
     * @param level Livello di gioco: {@link PlayGame#levelGame}
     * @param attempts Numero di tentativi:{@link PlayGame#maxFailedAttempts}
     */

    public PlayGame(final String level, final int attempts) {
        this.levelGame = level;
        this.maxFailedAttempts = attempts;
    }

    /**
     * Metodo getter per {@link PlayGame#levelGame}.
     * @return Il livello di gioco
     */
    public String getLevelGame() {
        if (levelGame != null) {
            return levelGame.replace("/", "");
        }
        return levelGame;
    }

    /**
     * Metodo setter per {@link PlayGame#levelGame}.
     * @param level Livello di gioco
     */
    public void setLevelGame(final String level) {
        this.levelGame = level;
    }

    /**
     * Metodo setter per {@link PlayGame#maxFailedAttempts}.
     * @param attempts Numero massimo di tentativi
     */
    public void setAttempts(final int attempts) {
        this.maxFailedAttempts = attempts;
    }

    /**
     * Metodo getter per {@link PlayGame#maxFailedAttempts}.
     * @return Il numero massimo di tentativi
     */
    public int getAttempts() {
        return maxFailedAttempts;
    }

    /**
     * Questo metodo chiede conferma, successivamente esce dal gioco nel caso sia positiva.
    * @param scanner Oggetto di tipo {@link Scanner}
    * */
    public boolean exitGame(final Scanner scanner) {
        while (true) {
            Generics.prints(Generics.CASO_USCITA_GIOCO);
            String choiceExit = Generics.readInput(scanner);
            if (choiceExit.equals("si")) {
                Generics.exit();
                return false;
            } else if (choiceExit.equals("no")) {
                return true;
            }
        }
    }

    /**
     * Modifica il livello di gioco desiderato dall'utente e quindi imposta il numero massimo di tentativi.
     * @param level Livello di gioco: {@link PlayGame#levelGame}
     * @return true se l'input dell'utente è corretto, altrimenti false
     */
    public boolean setLevelAndAttempts(final String level) {
        int attempts = this.getAttempts();
        if (attempts == 0) {
            switch (level) {
                case "/facile":
                    attempts = ATTEMPTS_EASY;
                    break;
                case "/medio":
                    attempts = ATTEMPTS_NORMAL;
                    break;
                case "/difficile":
                    attempts = ATTEMPTS_DIFFICULT;
                    break;
                default:
                    attempts = Integer.MIN_VALUE;
                    break;
            }
        }

        this.setLevelGame(level);
        this.setAttempts(attempts);
        String msg = "Livello del gioco: " + this.getLevelGame();
        msg += "\nNumero tentativi: " + this.getAttempts();
        msg += "\nIMPOSTATI CORRETTAMENTE.";
        System.out.println(msg);
        return true;
    }

    /**
     * Modifica il livello di gioco desiderato dall'utente e quindi imposta il numero massimo di tentativi.
     * @param level livello di gioco
     * @param attempts numero massimo di tentativi falliti
     */
    public void setLevelAndAttempts(final String level, final int attempts) {
        this.setLevelGame(level);
        this.setAttempts(attempts);
        String msg = "Livello del gioco: " + this.getLevelGame();
        msg += "\nNumero tentativi: " + this.getAttempts();
        msg += "\nIMPOSTATI CORRETTAMENTE.";
        System.out.println(msg);
    }

    /**
     * Questo metodo imposta il livello del gioco e i tentativi falliti nella classe GameInfo.
     * @param splittedInput valore inserito da tastiera dall'utente.
     * @param gameGrid {@link GameGrid} oggetto, il quale contiene la griglia di gioco.
     */
    public void setLevelSettings(final String[] splittedInput, final GameGrid gameGrid) {
        if (gameGrid.getIsStarted()) {
            Generics.prints(Generics.CASO_COMM_SETTATO);
        } else {
            if (!Generics.checkCompoundString(splittedInput)) {
                setAttempts(0);
                setLevelAndAttempts(splittedInput[0]);
            } else {
                int attempts = Integer.parseInt(splittedInput[1]);
                setLevelAndAttempts(splittedInput[0], attempts);
            }
        }
    }

    /**
     * Modifica il livello di gioco desiderato dall'utente e quindi imposta il numero massimo di tentativi.
     * @param sizeCommand La grandezza desiderata della griglia
     * @param gameGrid La griglia di gioco
     * @return La griglia di gioco
     */
    public boolean setGridSizeSetting(final String sizeCommand, final GameGrid gameGrid) {
        if (gameGrid.getIsStarted()) {
            Generics.prints(Generics.CASO_COMM_SETTATO);
            return false;
        } else {
            int currentGridSize = gameGrid.getGridSize();
            switch (sizeCommand) {
                case "/standard":
                    currentGridSize = gameGrid.getStandardGridSize();
                    break;
                case "/large":
                    currentGridSize = gameGrid.getLargeGridSize();
                    break;
                case "/extralarge":
                    currentGridSize = gameGrid.getExtralargeGridSize();
                    break;
                default:
                    Generics.prints(Generics.CASO_ERRORE);
                    break;
            }

            gameGrid.setGridSize(currentGridSize);
            attackGrid.setGridSize(currentGridSize);
            System.out.println("OK");
            return true;
        }
    }

    /**
     * Questo metodo chiede conferma all'utente di un avviso passato nei parametri.
     * @param string Stringa che identifica un avviso durante il gioco.
     * @return true se la conferma è positiva, false altrimenti.
     */
    private static boolean choice(final String string) {
        while (true) {
            try {
                System.out.println(string);
                System.out.print("Premere y o n: ");
                Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());
                String confirmChoice = Generics.readInput(scanner).toLowerCase();

                if (confirmChoice.equals("n")) {
                    return false;
                } else if (confirmChoice.equals("y")) {
                    return true;
                }
                System.out.println("ATTENZIONE. Valore errato, ripetere.");
            } catch (InputMismatchException e) {
                System.out.println("Errore di input. Ripetere.");
            }
        }
    }

    /**
     * Questo metodo mostra le impostazioni prima di giocare e chiede conferma se si vogliono accettare o no.
     * @return true se sono state accettate, false altrimenti.
     */
    boolean acceptSettings(final GameGrid gameGrid) {
        System.out.println("\n\n");
        System.out.println("GRANDEZZA DELLA GRIGLIA: " + gameGrid.getGridSize() + "x" + gameGrid.getGridSize());
        System.out.println("LIVELLO SCELTO: " + getLevelGame());
        System.out.println("MAX TENTATIVI FALLITI SCELTI: " + getAttempts());
        System.out.println("TEMPO MASSIMO DI GIOCO SCELTO: " + getMaxTime() / TIME_MILLISECONDS + " minuti");

        boolean value = choice("\n\nCONFERMI QUESTE IMPOSTAZIONI ? ");
        return value;
    }
    /**
     * Questo metodo avvia il gioco verificando se c'e' un salvataggio , se cosi' fosse , carica la classe GameInfo,
     * altrimenti inserisce le navi nella griglia in posizioni casuali.
     * @param gameGrid {@link GameGrid} oggetto, il quale contiene la griglia di gioco.
     */
    public void startGame(final GameGrid gameGrid) {
        if (gameGrid.getIsStarted()) {
                Generics.prints(Generics.CASO_COMM_SETTATO);
        } else {
            if (this.getLevelGame() != null && this.getAttempts() != 0) {
                if (getMaxTime() != 0) {
                    if (acceptSettings(gameGrid)) {
                        gameGrid.createGrid();
                        gameGrid.shipInsertion();
                        gameGrid.printEmptyGrid();
                        attackGrid.createGrid();

                        maxTime(getMaxTime(), gameGrid);
                        startTime = LocalTime.now();
                        gameTime = Duration.ofMinutes(getMaxTime() / TIME_MILLISECONDS);
                    } else {
                        setAttempts(ATTEMPTS_EASY);
                        setLevelGame("/facile");
                        this.newTime = 0;
                        gameGrid.setGridSize(gameGrid.getStandardGridSize());
                        attackGrid.setGridSize(gameGrid.getStandardGridSize());
                        Generics.prints(Generics.CASO_IMPOSTAZIONI_ERRATE);
                    }
                } else {
                    Generics.prints(Generics.CASO_TEMPO_NON_SETTATO);
                }
            } else {
                Generics.prints(Generics.CASO_SET_LEVEL_TO_PLAY);
            }
        }
    }


    /**
     * Questo metodo mostra il livello di gioco che il giocatore ha selezionato.
     */
    public void showLevel() {
        levelGame = this.getLevelGame();
        maxFailedAttempts = this.getAttempts();

        if (levelGame != null && maxFailedAttempts != 0) {
            System.out.println("LIVELLO GIOCO: " + levelGame
                             + "\nTENTATIVI: " + maxFailedAttempts);
        } else {
            Generics.prints(Generics.CASO_ERRORE);
        }
     }

    /**
     * Questo metodo controlla constantemente il tempo di gioco trascorso appena avviato
     * e nel caso sia finito, il programma termina.
     * @param newTimeOut Indica il tempo massimo di gioco che varia in base alla scelta del giocatore.
     */
    void maxTime(final int newTimeOut, final GameGrid gameGrid) {
        if (getMaxTime() != 0) {
           System.out.println("\nHAI INIZIATO IL GIOCO.\nHAI A DISPOSIZIONE "
           + newTimeOut / TIME_MILLISECONDS + " MINUTI.");
        }
        System.out.println("");
        Timer timer = new Timer(); // Crea un nuovo timer
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setMissedShots(0);
                gameGrid.setNumberShips(GameGrid.getNumShips());
                newTime = 0;

                gameGrid.setIsStarted(false);
                attackGrid = new GameGrid();
                PlayGame game = new PlayGame("/facile", ATTEMPTS_EASY);

                Generics.clearConsole();
                System.out.println("ATTENZIONE, TEMPO DI GIOCO ESAURITO.\n\n");
                Generics.command(new Scanner(System.in, StandardCharsets.UTF_8.name()), gameGrid, game, false);
            }
        };
        timer.schedule(task, newTimeOut); // Imposta il nuovo timeout
    }

    /**
     * Questo metodo imposta il valore scelto dal giocatore come tempo massimo di gioco.
     * @param comm Indica il valore del tempo da trasformare in intero e da converire in minuti.
     */
    public void setMaxTime(final String[] comm) {
        if (!Generics.checkCompoundString(comm)) {
            Generics.prints(Generics.CASO_ERRORE);
        } else {
            int time = Integer.parseInt(comm[1]);
            this.newTime = time * MINUTES_TO_MILLISECONDS; // Converte il tempo in minuti
            System.out.println("\nTEMPO DI GIOCO IMPOSTATO CORRETTAMENTE.");
        }
    }

    /**
     * Questo metodo acquisisce il valore del tempo massimo di gioco.
     * @return newTime tempo massimo di gioco.
     */
    int getMaxTime() {
        return this.newTime;
    }

    /**
     *Questo metodo calcola i minuti trascorsi e rimanenti dall'inizio del gioco.
     */
    public void showTime() {
        if (getMaxTime() == 0) {
            Generics.prints(Generics.CASO_TEMPO_NON_SETTATO);
        } else {
              if (startTime == null) {
                int remainingMinutesGame = getMaxTime() / TIME_MILLISECONDS;

                System.out.println("\nTEMPO DI GIOCO TRASCORSO: 0 MINUTI E 0 SECONDI.");
                System.out.println("TEMPO DI GIOCO RIMANENTE: " + remainingMinutesGame + " MINUTI E 0 SECONDI.");
              } else {
                LocalTime currentTime = LocalTime.now();
                Duration elapsedTime = Duration.between(startTime, currentTime);
                Duration remainingTime = gameTime.minus(elapsedTime);

                long elapsedMinutes = elapsedTime.toMinutes();
                long elapsedSeconds = elapsedTime.toSeconds() % MINUTE;
                long remainingMinutes = remainingTime.toMinutes();
                long remainingSeconds = remainingTime.toSeconds() % MINUTE;

                System.out.println("\nTEMPO DI GIOCO TRASCORSO: " + elapsedMinutes + " MINUTI E "
                + elapsedSeconds + " SECONDI.");
                System.out.println("TEMPO DI GIOCO RIMANENTE: " + remainingMinutes + " MINUTI E "
                + remainingSeconds + " SECONDI.");
              }
        }
    }

    /**
     * Questo metodo setta il massimo dei tentativi falliti al livello di gioco corrente, acquisendoli da tastiera,
     * si possono settare solo se il livello è già stato settato.
     * @param splittedInput valore inserito da tastiera dall'utente.
     * @param gameGrid {@link GameGrid} oggetto, il quale contiene la griglia di gioco.
     */
    public void commandAttempts(final String[] splittedInput, final GameGrid gameGrid) {
        if (gameGrid.getIsStarted()) {
            Generics.prints(Generics.CASO_COMM_SETTATO);
        } else {
            if (Generics.checkCompoundString(splittedInput)) {
                int attempts = Integer.parseInt(splittedInput[1]);
                setLevelAndAttempts(getLevelGame(), attempts);
            } else {
                Generics.prints(Generics.CASO_ERRORE);
            }
        }
    }

    /**
     * Ottiene le coordinate dall'utente tramite input da console.
     * Le coordinate sono rappresentate da una lettera maiuscola (colonna) e un numero (riga),
     * entrambi separati da un trattino. Ad esempio: B-4.
     *
     * @param scanner lo scanner per l'input dell'utente.
     * @return un array di interi contenente le coordinate [riga, colonna].
     * @throws IllegalArgumentException se il formato delle coordinate non è valido.
     */
    public int[] getCoordinates(final Scanner scanner) {
        int[] coordinates = new int[2];

        while (true) {
            System.out.println("Inserire le coordinate (Es. B-4):");
            System.out.print("> ");
            String input = scanner.nextLine();

            // Divido la stringa in due parti utilizzando il trattino come separatore
            String[] parts = input.split("-");

            if (parts.length != 2) {
                System.out.println("Formato delle coordinate non valido. Riprova.");
                continue;
            }

            // Estraggo la riga e la colonna come stringhe
            String columnStr = parts[0].trim();
            String rowStr = parts[1].trim();

            try {
                // Verifico se la colonna è una lettera valida
                if (columnStr.length() != 1 || !Character.isLetter(columnStr.charAt(0))) {
                    throw new IllegalArgumentException("La colonna deve essere una singola lettera.");
                }

                // Calcolo la colonna (A = 1, B = 2, ecc.)
                int column = Character.toUpperCase(columnStr.charAt(0)) - 'A';
                coordinates[1] = column;

                // Verifico se la riga è un numero valido
                int row = Integer.parseInt(rowStr) - 1;
                if (row < 0 || row > attackGrid.getNumRows()) {
                    throw new IllegalArgumentException("La riga deve essere un numero compreso tra 1 e "
                + attackGrid.getNumRows() + ".");
                }
                coordinates[0] = row;

                break;
            } catch (NumberFormatException e) {
                System.out.println("Formato delle coordinate non valido. Riprova.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return coordinates;
    }

    /**
     * Verifica se le coordinate specificate corrispondono a una nave presente nella griglia di gioco.
     * Restituisce true se tutte le celle della nave sono state colpite ('C') nella griglia degli attacchi,
     * altrimenti restituisce false.
     *
     * @param gameGrid la griglia di gioco contenente le navi e i relativi posizionamenti.
     * @param row la riga delle coordinate da verificare.
     * @param col la colonna delle coordinate da verificare.
     * @return true se tutte le celle della nave sono state colpite, false altrimenti.
     */
    public boolean checkCoordinates(final GameGrid gameGrid, final int row, final int col) {
        Map<Integer, List<Coordinates>> coordinatesMap = gameGrid.getCoordinatesShips();

        //scorro sulle chiavi presenti nel dizionario
        for (Map.Entry<Integer, List<Coordinates>> entry : coordinatesMap.entrySet()) {

           //assegno a valueList, la lista valore della chiave corrente
           List<Coordinates> valueList = entry.getValue();

            //scorro sulla coppia delle coordinate presenti in ogni indice della lista
            for (Coordinates coordinate : valueList) {

                //se le coordinate sono uguali, ho trovato la nave di riferimento
                if (row == coordinate.getX() && col == coordinate.getY()) {

                    //assegno ad una lista temporanea, la lista che contiene le coordinate della nave di riferimento
                    List<Coordinates> tempCoordinates = valueList;

                    //scorro sulla griglia attraverso le coordinate presenti nella lista temporanea e se trovo una cella
                    //che non è stata ancora colpita, la nave non è affondata
                    for (Coordinates el : tempCoordinates) {
                        if (attackGrid.getGridValue(el.getX(), el.getY()) != 'C') {
                            return false;
                        }
                    }
                    this.coord = tempCoordinates;
                }
            }
        }
        return true;
    }

    /**
     * Verifica se la nave corrispondente alle coordinate specificate è affondata.
     * La nave è considerata affondata se tutte le sue celle sono state colpite ('C') nella griglia degli attacchi.
     *
     * @param gameGrid la griglia di gioco contenente le navi e i relativi posizionamenti.
     * @param row la riga delle coordinate della cella da verificare.
     * @param col la colonna delle coordinate della cella da verificare.
     * @return true se la nave è affondata, false altrimenti.
     */
    boolean isShipSunk(final GameGrid gameGrid, final int row, final int col) {
        return checkCoordinates(gameGrid, row, col);
    }

    /**
     * Effettua l'attacco a una cella specifica della griglia di gioco.
     * Verifica se la cella corrisponde a una nave, se è stata già colpita o se è solo acqua.
     * Aggiorna la griglia degli attacchi e fornisce un feedback all'utente.
     * @param gameGrid la griglia di gioco contenente le navi e i relativi posizionamenti.
     * @param scanner lo scanner per l'input dell'utente.
     */
    public void attackShip(final GameGrid gameGrid, final Scanner scanner) {

        if (gameGrid.getIsStarted()) {
            int[] coordXY = getCoordinates(scanner);
            int row = coordXY[0];
            int col = coordXY[1];

            //aggiorno i tentativi effettuati
            setTotalAttempt(getTotalAttempt() + 1);

            // Controllo la cella corrispondente
            if (gameGrid.getGridValue(row, col) == ' ') {
                attackGrid.setGridValue(row, col, 'X');
                legendHitShips(1, row, col, gameGrid);
                setMissedShots(getMissedShots() + 1);

            } else if (attackGrid.getGridValue(row, col) != 'C' && attackGrid.getGridValue(row, col) != 'A'
                       && attackGrid.getGridValue(row, col) != 'X') {
                attackGrid.setGridValue(row, col, 'C');
                if (isShipSunk(gameGrid, row, col)) {
                    //aggiorno il conteggio delle navi disponibili
                    gameGrid.setNumberShips(gameGrid.getNumberShips() - 1);

                    //aggiorno la griglia degli attacchi
                    legendHitShips(2, row, col, gameGrid);
                    for (Coordinates el : this.coord) {
                        attackGrid.setGridValue(el.getX(), el.getY(), 'A');
                    }

                } else {
                    legendHitShips(1 + 2, row, col, gameGrid);
                }
            } else {
                System.out.println("\nCELLA CON UNA NAVE PRESENTE, GIA' COLPITA.");
            }

            System.out.println("\nGRIGLIA CON GLI ATTACCHI EFFETTUATI:");
            attackGrid.printGrid(false);

            LocalTime currentTime = LocalTime.now();
            Duration elapsedTime = Duration.between(startTime, currentTime);
            System.out.println("TEMPO DI GIOCO TRASCORSO: " + elapsedTime.toMinutes() + " MINUTI E "
                    + elapsedTime.toSeconds() % MINUTE + " SECONDI.");

            System.out.println("\nNUMERO DI TENTATIVI GIA' EFFETTUATI: " + getTotalAttempt());
        } else {
             Generics.prints(Generics.CASO_GIOCO_NON_INIZIATO);
        }
    }

    /**
     * Gestisce l'impatto su una nave specifica e visualizza il relativo messaggio sulla console.
     *
     * @param value     il valore che rappresenta l'esito dell'impatto
     * (1 = acqua, 2 = nave affondata, altri valori = nave colpita)
     * @param r la riga della griglia di gioco in cui si è verificato l'impatto
     * @param c la colonna della griglia di gioco in cui si è verificato l'impatto
     * @param gameGrid  l'oggetto GameGrid che contiene la griglia di gioco e la lista delle navi
     */
    void legendHitShips(final int value, final int r, final int c, final GameGrid gameGrid) {
        char tempSymbolShip = gameGrid.getGridValue(r, c);
        List<Ship> tempShips = gameGrid.getCopyList();
        Ship ship = checkShips(gameGrid, tempSymbolShip);

        if (value == 1) {
            System.out.println(Colours.ANSI_CYAN_BACKGROUND + "\nACQUA" + Colours.ANSI_RESET);
        } else if (value == 2) {
            System.out.println(Colours.ANSI_PURPLE_BACKGROUND + "\nNAVE: " + ship.getName() + " -> " + ship.getSymbol()
            + " - COLPITA E AFFONDATA." + Colours.ANSI_RESET);
            ship.setQuantity(ship.getQuantity() - 1);
        } else {
            System.out.println(Colours.ANSI_PURPLE_BACKGROUND + "\nNAVE: " + ship.getName() + " -> " + ship.getSymbol()
            + " - COLPITA." + Colours.ANSI_RESET);
        }

        System.out.println("\n\nLISTA NAVI RIMANENTI");
        for (Ship el : tempShips) {
            System.out.println("NOME: " + el.getName() + " - SIMBOLO: " + el.getSymbol()
            + " - QUANTITA' ATTUALE: " + el.getQuantity());
        }
    }

    /**
     * Verifica se esiste una nave corrispondente al simbolo specificato nella griglia di gioco.
     *
     * @param gameGrid     l'oggetto GameGrid che contiene la griglia di gioco e la lista delle navi
     * @param tempSymbol   il simbolo della nave da cercare
     * @return la nave corrispondente al simbolo specificato, o null se non viene trovata alcuna corrispondenza
     */
    Ship checkShips(final GameGrid gameGrid, final char tempSymbol) {
        List<Ship> tempListShips = gameGrid.getCopyList();

        for (Ship elList : tempListShips) {
            if (elList.getSymbol() == tempSymbol) {
                return elList;
            }
        }
        return null;
    }

    /**
     * Restituisce il numero di tentativi di colpo effettuati.
     *
     * @return il numero di tentativi di colpo effettuati.
     */
    public int getMissedShots() {
        return missedShots;
    }

    /**
     * Imposta il numero di tentativi di colpo effettuati.
     *
     * @param attempted il numero di tentativi di colpo effettuati da impostare.
     */
    public void setMissedShots(final int attempted) {
        this.missedShots = attempted;
    }

     /**
     * Verifica se il gioco è terminato, controllando se sono soddisfatte le condizioni di vittoria o sconfitta.
     *
     * @param gameGrid la griglia di gioco contenente le navi e i relativi posizionamenti.
     * @return true se il gioco è terminato, false altrimenti.
     */
    public boolean checkFinishGame(final GameGrid gameGrid) {
       if (getMissedShots() == getAttempts() || gameGrid.getNumberShips() == 0) {
            Generics.prints(Generics.CASO_SCONFITTA);
            return true;
       } else {
           return false;
       }
    }

    /**
     * Termina la sessione di gioco.
     * Se il gioco non è ancora iniziato, visualizza un messaggio che indica che il gioco non è ancora iniziato.
     * In caso contrario, chiede al giocatore di confermare se desidera abbandonare il gioco.
     * Se confermato, reimposta le impostazioni del gioco e stampa la griglia di gioco iniziale.
     *
     * @param gameGrid L'oggetto della griglia di gioco che rappresenta lo stato del gioco.
     */
    public boolean quitGame(final GameGrid gameGrid) {
        if (!gameGrid.getIsStarted()) {
            Generics.prints(Generics.CASO_GIOCO_NON_INIZIATO);
        } else {
            if (choice("\nVUOI ABBANDONARE QUESTA PARTITA ?")) {
                return false;
            } else {
                System.out.println("HAI SCELTO DI CONTINUARE.");
            }
        }
        return true;
    }

    /**
     * Metodo che restituisce la griglia delle navi attaccate.
     * */
    public void printAttackGrid() {
        if (attackGrid.getIsStarted()) {
            attackGrid.printGrid(false);
        } else {
            Generics.prints(Generics.CASO_GIOCO_NON_INIZIATO);
        }
    }

    /**
     * Restituisce il numero totale di tentativi effettuati.
     *
     * @return Il numero totale di tentativi effettuati.
     */
    public int getTotalAttempt() {
        return totalAttempt;
    }

    /**
     * Imposta il numero totale di tentativi effettuati.
     *
     * @param total Il numero totale di tentativi effettuati da impostare.
     */
    public void setTotalAttempt(final int total) {
        this.totalAttempt = total;
    }

    /**
     * Mostra il resoconto dei tentativi di gioco sulla console.
     * Stampa il numero massimo di tentativi falliti, il numero totale di tentativi effettuati
     * e il numero di tentativi falliti.
     *
     * @param gameGrid la griglia di gioco
     */
    public void showAttempt(final GameGrid gameGrid) {
        if (gameGrid.getIsStarted()) {
            System.out.println("\nNUMERO MASSIMO DI TENTATIVI FALLITI: " + getAttempts());
            System.out.println("\nNUMERO DI TENTATIVI EFFETTUATI: " + getTotalAttempt());
            System.out.println("\nNUMERO DI TENTATIVI FALLITI: " + getMissedShots());
        } else {
            Generics.prints(Generics.CASO_GIOCO_NON_INIZIATO);
        }
    }

    /**
    * Metodo setter per la griglia di attacco.
    * @param attack griglia di attacco
    */
    public void setAttackGrid(final GameGrid attack) {
        GameGrid attackCopy = new GameGrid();

        try {
            attackCopy = attack.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        this.attackGrid = attackCopy;
        attackGrid.printGrid(false);
    }
}
