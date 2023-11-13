package it.uniba.app.game;
import java.util.Scanner;

import it.uniba.app.colours.Colours;
import it.uniba.app.grid.GameGrid;

/**
 * <p>Generics e' una classe <<Boundary>>.
 * Questa classe presenta l'implementazione di alcuni metodi generali.</p>
 * Questi sono resi tutti pubblici al fine di renderli utilizzabili
 * da una maggiore vastità di classi
 * @author Daniele Grandolfo, dgrandolfo4
 * @author gamal, GamalElmetkanany
 * @author rosanna, RosannaFracchiolla
 * @author matteo, CapoMatteoJr
 * @author agnese, aDaddabbo20
 * @author maria, mariaforte02
 */
public final class Generics {
    /**
     * Indica la volontà di stampa a video di un avviso di selezione già avvenuta.
     */
    public static final int CASO_COMM_SETTATO = 0;
    /**
     * Indica la volontà di stampa a video di un avviso di mancata selezione del livello.
     */
    public static final int CASO_SET_LEVEL_TO_PLAY = 1;
    /**
     * Indica la volontà di stampa a video di un avviso di decisione errata.
     */
    public static final int CASO_ERRORE = 2;
    /**
     * Indica la volontà di stampa a video della domanda di uscita dal gioco.
     */
    public static final int CASO_USCITA_GIOCO = 3;
    /**
     * Indica la volontà di stampa a video di un avviso di griglia vuota.
     */
    public static final int CASO_GRIGLIA_VUOTA = 4;
    /**
     * Indica la volontà di stampa a video di un avviso di tempo pari a 0.
     */
    public static final int CASO_TEMPO_NON_SETTATO = 5;
    /**
     * Indica la volontà di stampa a video di un avviso di ripere il settaggio delle impostazioni.
     */
    public static final int CASO_IMPOSTAZIONI_ERRATE = 6;
    /**
     * Indica la volontà di stampa a video di un avviso di uscita dal gioco per aver colpito tutte le navi.
     */
    public static final int CASO_VITTORIA = 7;
    /**
     * Indica la volontà di stampa a video di un avviso di uscita dal gioco per esaurito i tentativi disponibili.
     */
    public static final int CASO_SCONFITTA = 8;
    /**
     * Indica la volontà di stampa a video di un avviso di uscita dal gioco per esaurito i tentativi disponibili.
     */
    public static final int CASO_GIOCO_NON_INIZIATO = 9;
    /**
     * Indica l'altezza di default del terminale.
     */
    public static final int TERMINAL_HEIGHT = 100;

    /**
     * Generics costruttore.
     */
    private Generics() {
    }

    /**
     * Permette all'utente di fornire un input.
     * @param scanner {@link Scanner} oggetto
     * @return L'input dall'utente
     */
    public static String readInput(final Scanner scanner) {
        String input = "";
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Questo metodo permette di uscire dal gioco.
     */
    static void exit() {
        final int temp = 1000;
        System.out.print("Uscita dal gioco");
        try {
            Thread.sleep(temp);
            System.out.print(".");
            Thread.sleep(temp);
            System.out.print(".");
            Thread.sleep(temp);
            System.out.print(".");
            System.out.println();
        } catch (InterruptedException e) {
            System.err.println("Errore nella gestione del tempo");
        }
     }

    /**
     * Permette all'utente di visualizzare i comandi disponibili all'inizio e durante il gioco.
     * @param gameGrid {@link GameGrid} oggetto, contiene le informazioni del gioco da caricare e salvare.
     * @param game {@link PlayGame} oggetto, il quale contiene l'implementazione del gioco.
     */
    public static void command(final Scanner scanner, final GameGrid gameGrid, final PlayGame game,
                               final boolean console) {
        if (console) {
            pauseConsole(scanner);
            clearConsole();
        }

        System.out.println(Colours.ANSI_BLUE_BACKGROUND + "ELENCO COMANDI DISPONIBILI:" + Colours.ANSI_RESET);
        System.out.println("/help");
        System.out.println("/mostranavi");
        if (game.getLevelGame() != null && game.getAttempts() != 0) {
            System.out.println("/mostralivello");
        }
        if (game.getMaxTime() != 0) {
            System.out.println("/mostratempo");
        }
        if (!gameGrid.getIsStarted()) {
            System.out.println("/standard - /large - /extralarge");
            System.out.println("/facile - /medio - /difficile");
            System.out.println("/facile <numero> - /medio <numero> - /difficile <numero>");
            System.out.println("/tentativi <numero>");
            System.out.println("/tempo <numero>");
            System.out.println("/gioca");
        } else {
            System.out.println("/attacca");
            System.out.println("/svelagriglia");
            System.out.println("/mostragriglia");
            System.out.println("/mostratentativi");
            System.out.println("/abbandona");
        }
        System.out.println("/esci");
        System.out.print("> ");
    }

    /**
     * Metodo che stampa errori e stampe di gioco.
     * @param n numero della stampa da visualizzare in output.
     */
    public static void prints(final int n) {
        switch (n) {
            case CASO_COMM_SETTATO:
                System.out.println("\nComando gia' settato! RIPETERE");
                break;
            case CASO_SET_LEVEL_TO_PLAY:
                System.out.println("\nATTENZIONE: prima di giocare, è necessario settare il livello di gioco");
                break;
            case CASO_ERRORE:
                System.out.println("\nDECISIONE ERRATA! RIPETERE");
                break;
            case CASO_USCITA_GIOCO:
                System.out.println("\nVuoi uscire dal gioco ?");
                System.out.println("Premere si/no: ");
                System.out.print("> ");
                break;
            case CASO_GRIGLIA_VUOTA:
                System.out.println("\nATTENZIONE: La griglia è vuota!");
                break;
            case CASO_TEMPO_NON_SETTATO:
                System.out.println("\nATTENZIONE: Tempo non ancora settato!");
                break;
            case CASO_IMPOSTAZIONI_ERRATE:
                System.out.println("\nHAI SCELTO DI NON ACCETTARE LE IMPOSTAZIONI, INSERIRLE NUOVAMENTE.");
                break;
            case CASO_VITTORIA:
                System.out.println("\nHAI VINTO! HAI AFFONDATO TUTTE LE NAVI.");
                break;
            case CASO_SCONFITTA:
                System.out.println("\nHAI PERSO! HAI ESAURITO I TENTATIVI DISPONIBILI.");
                break;
            case CASO_GIOCO_NON_INIZIATO:
                System.out.println("\nATTENZIONE, NON HAI ANCORA INIZIATO IL GIOCO");
                break;
            default:
                System.out.println("\nATTENZIONE. CASO NON RICONOSCIUTO");
                break;
        }
    }

    /**
     * Metodo per mettere in pausa la console in attesa di risposta dall'utente.
     */
    public static void pauseConsole(final Scanner scanner) {
        System.out.print("\nPremere invio per continuare. . .");
        scanner.nextLine();
    }

    /**
     * Metodo che consente di pulire la console.
     */
    public static void clearConsole() {
        for (int i = 0; i < TERMINAL_HEIGHT; ++i) {
            System.out.println();
        }
    }

    /**
    * Metodo per mostrare a video l'help che illustra i comandi disponibili.
    */
    public static void commandHelp(final Scanner scanner) {
        System.out.println("TUTTI I COMANDI:");
        System.out.println("/help : Mostra tutti i comandi disponibili del gioco ");
        System.out.println("/standard : Imposta la dimensione della griglia 10x10 ");
        System.out.println("/large : Imposta la dimensione della griglia 18x18 ");
        System.out.println("/extralarge : Imposta la dimensione della griglia 26x26 ");
        System.out.println("/facile : Imposta il livello di difficolta' su 'facile'"
               + " settando i tentativi ad un massimo di 50 ");
        System.out.println("/facile <numero> : Imposta il livello di difficolta' su 'facile'"
               + " con il numero dei tentativi indicato ");
        System.out.println("/medio : Imposta il livello di difficolta' su 'medio'"
               + " settando i tentativi ad un massimo di 30 ");
        System.out.println("/medio <numero> : Imposta il livello di difficolta' su 'medio'"
               + " con il numero dei tentativi indicato ");
        System.out.println("/difficile : Imposta il livello di difficolta' su 'difficile'"
               + " settando i tentativi ad un massimo di 10 ");
        System.out.println("/difficile <numero> : Imposta il livello di difficolta' su 'difficile'"
               + " con il numero dei tentativi indicato ");
        System.out.println("/tentativi <numero> : Imposta il numero indicato"
               + " come la quantita' massima di tentativi falliti ");
        System.out.println("/tempo <numero> : Permette di impostare un minutaggio massimo di gioco ");
        System.out.println("/mostranavi : Mostra il tipo di navi disponibili all'interno del gioco ");
        System.out.println("/mostralivello : Mostra il livello di difficoltà corrente ");
        System.out.println("/mostratempo : Mostra il tempo residuo al termine della partita ");
        System.out.println("/mostratentativi: Mostra il numero di tentativi già effettuati, il numero di "
               + "tentativi falliti e il numero massimo di tentativi falliti");
        System.out.println("/gioca : Avvia il gioco inserendo le navi nella griglia in modo casuale ");
        System.out.println("/svelagriglia : Mostra la dispozione delle navi nella griglia di gioco ");
        System.out.println("/mostragriglia : Mostra la griglia con le navi colpite e affondate");
        System.out.println("/attacca : Permette di effettuare un attacco dopo aver richiesto le coordinate ");
        System.out.println("/abbandona : Permette di abbandonare la partita in corso");
        System.out.println("/esci : Termina il programma ");
    }

    /**
    * Metodo che permette di stampare a video il titolo di battaglia navale con una figura.
    */
   public static void welcomeGameBattleship() {
        System.out.println(Colours.ANSI_RED_BACKGROUND
               + " ________  ________  ________  ___  __    _______           ________  ________ "
               + " _________  _________  ___       _______   ________  ___  ___  ___  ________   \r\n"
               + "|\\   ____\\|\\   __  \\|\\   ____\\|\\  \\|\\  \\ |\\  ___ \\         |\\   __  \\|\\  "
               + " __  \\|\\___   ___\\\\___   ___\\\\  \\     |\\  ___ \\ |\\   ____\\|\\  \\|\\  \\|\\  "
               + "\\|\\   __  \\  \r\n"
               + "\\ \\  \\___|\\ \\  \\|\\  \\ \\  \\___|\\ \\  \\/  /|\\ \\   __/|        \\ \\  \\|\\ /\\"
               + " \\  \\|\\  \\|___ \\  \\_\\|___ \\  \\_\\ \\  \\    \\ \\   __/|\\ \\  \\___|\\ \\  \\\\\\"
               + "  \\ \\  \\ \\  \\|\\  \\ \r\n"
               + " \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\    \\ \\   ___  \\ \\  \\_|/__       \\ \\   __  \\ "
               + "\\   __  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\    \\ \\  \\_|/_\\ \\_____  \\ \\   __  "
               + "\\ \\  \\ \\   ____\\\r\n"
               + "  \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\____\\ \\  \\\\ \\  \\ \\  \\_|\\ \\       \\ \\  "
               + "\\|\\  \\ \\  \\ \\  \\   \\ \\  \\     \\ \\  \\ \\ \\  \\____\\ \\  \\_|\\ \\|____|\\  "
               + "\\ \\  \\ \\  \\ \\  \\ \\  \\___|\r\n"
               + "   \\ \\_______\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\       \\ \\_______"
               + "\\ \\__\\ \\__\\   \\ \\__\\     \\ \\__\\ \\ \\_______\\ \\_______\\____\\_\\  \\ \\__\\ "
               + "\\__\\ \\__\\ \\__\\   \r\n"
               + "    \\|_______|\\|_______|\\|_______|\\|__| \\|__|\\|_______|        \\|_______|\\|__|\\|__|"
               + "    \\|__|      \\|__|  \\|_______|\\|_______|\\_________\\|__|\\|__|\\|__|\\|__|   \r\n"
               + "                                                                                            "
               + "                                \\|_________|                      " + Colours.ANSI_RESET);

       System.out.println("\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠤⠴⠶⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⣾⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠂⠉⡇⠀⠀⠀⢰⣿⣿⣿⣿⣧⠀⠀⢀⣄⣀⠀⠀⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⢠⣶⣶⣷⠀⠀⠀⠸⠟⠁⠀⡇⠀⠀⠀⠀⠀⢹⠀⠀⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠘⠟⢹⣋⣀⡀⢀⣤⣶⣿⣿⣿⣿⣿⡿⠛⣠⣼⣿⡟⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⣴⣾⣿⣿⣿⣿⢁⣾⣿⣿⣿⣿⣿⣿⡿⢁⣾⣿⣿⣿⠁⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⢸⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿⣿⣿⠿⠇⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠳⣤⣙⠟⠛⢻⠿⣿⠸⣿⣿⣿⣿⣿⣿⣿⣇⠘⠉⠀⢸⠀⢀⣠⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠈⠻⣷⣦⣼⠀⠀⠀⢻⣿⣿⠿⢿⡿⠿⣿⡄⠀⠀⣼⣷⣿⣿⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣶⣄⡈⠉⠀⠀⢸⡇⠀⠀⠉⠂⠀⣿⣿⣿⣧⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣷⣤⣀⣸⣧⣠⣤⣴⣶⣾⣿⣿⣿⡿⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠟⠛⠉⠀⠀⠀⠀\r\n"
               + "\t\t\t\t\t\t\t⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
   }


     /**
      * Metodo che permette di stampare a video una descrizione del gioco di battaglia navale.
      */
   public static void welcomeGame() {
       System.out.print(Colours.ANSI_RED_BACKGROUND + "\n\nBenvenuto nel gioco!\r\n" + Colours.ANSI_RESET);
       System.out.print(
         "BATTLESHIP Single player, e' una versione del classico gioco della Battaglia Navale che ti permettera'"
       + "di giocare contro il tuo computer.\r\nAll'avvio di una partita,"
       + " verranno posizionate casualmente le navi avversarie. Hai a disposizione due griglie modificabili "
       + "(standard - large - extralarge):\nuna conterra'"
       + " la formazione delle tue navi, l'altra segnera' i colpi da te effettuati e il loro riscontro sulle navi "
       + "avversarie. \n"
       + "Puoi utilizzare quattro tipologie di navi.\r\n"
       + "Per vincere devi riuscire ad individuare le posizioni delle navi avversarie ed affondarle tutte.\n"
       + "E'concesso un numero "
       + "massimo di tentativi, modificabile in base al livello di difficolta' che hai selezionato e "
       + "un tempo massimo di gioco.\r\n"
       + "Perdi se finisce il tempo massimo a diposizione o se e esaurisci il numero massimo di tentavivi."
       + "Buona fortuna!\r\n");
   }

    /**
    * Metodo per stampare a video i tipi di nave disponibili con relative informazioni nel seguente ordine:
    * nome della nave, simbolo, numero di esemplari utilizzabili.
    */
   public static void typeShip() {
       System.out.print(String.format(Colours.ANSI_RED_BACKGROUND + "%1s", "- Cacciatorpediniere"));
       System.out.print(String.format("%10s", "⊠⊠"));
       System.out.println(String.format("%20s", "esemplari: 4" + Colours.ANSI_RESET));
       System.out.print(String.format(Colours.ANSI_GREEN_BACKGROUND + "%1s", "- Incrociatore"));
       System.out.print(String.format("%18s", "⊠⊠⊠ "));
       System.out.println(String.format("%23s", "esemplari: 3" + Colours.ANSI_RESET));
       System.out.print(String.format(Colours.ANSI_PURPLE_BACKGROUND + "%1s", "- Corrazzata"));
       System.out.print(String.format("%20s", "⊠⊠⊠⊠"));
       System.out.println(String.format("%18s", "esemplari: 2" + Colours.ANSI_RESET));
       System.out.print(String.format(Colours.ANSI_YELLOW_BACKGROUND + "%1s", "- Portaerei"));
       System.out.print(String.format("%22s", "⊠⊠⊠⊠⊠"));
       System.out.println(String.format("%17s", "esemplari: 1" + Colours.ANSI_RESET));
   }

    /**
     * Questo metodo controlla se la stringa acquisita è composta da una seconda parte intera.
     * @param comm Stringa aquisita da tastiera.
     * @return true se la stringa acquisita è corretta , false altrimenti.
     */
    public static boolean checkCompoundString(final String[] comm) {
        if (comm.length > 1 && comm[1].matches("\\d+")) {
            return true;
        }
        return false;
    }

}

