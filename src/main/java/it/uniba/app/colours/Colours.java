package it.uniba.app.colours;

/**
 * Colours è una classe <<Boundary>>.
 * Questa classe presenta codici ANSI che permettono la stampa di colori.
 * @author Daniele Grandolfo, dgrandolfo4
 * @author gamal, GamalElmetkanany
 * @author rosanna, RosannaFracchiolla
 * @author matteo, CapoMatteoJr
 * @author agnese, aDaddabbo20
 * @author maria, mariaforte02
 */
public final class Colours {
    /**
     * Codice ANSI per resettare i codici ANSI utilizzati precedentemente.
     */
    public static final String ANSI_RESET = "\u001B[0m";
    /**
     * Codice ANSI per ottenere un testo di colore nero.
     */
    public static final String ANSI_BLACK = "\u001B[30m";
    /**
     * Codice ANSI per ottenere un testo di colore rosso.
     */
    public static final String ANSI_RED = "\u001B[31m";
    /**
     * Codice ANSI per ottenere un testo di colore verde.
     */
    public static final String ANSI_GREEN = "\u001B[32m";
    /**
     * Codice ANSI per ottenere un testo di colore giallo.
     */
    public static final String ANSI_YELLOW = "\u001B[33m";
    /**
     * Codice ANSI per ottenere un testo di colore blu.
     */
    public static final String ANSI_BLUE = "\u001B[34m";
    /**
     * Codice ANSI per ottenere un testo di colore viola.
     */
    public static final String ANSI_PURPLE = "\u001B[35m";
    /**
     * Codice ANSI per ottenere un testo di colore azzurro.
     */
    public static final String ANSI_CYAN = "\u001B[36m";
    /**
     * Codice ANSI per ottenere un testo di colore bianco.
     */
    public static final String ANSI_WHITE = "\u001B[37m";
    /**
     * Codice ANSI per ottenere un background di colore nero.
     */
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    /**
     * Codice ANSI per ottenere un background di colore rosso.
     */
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    /**
     * Codice ANSI per ottenere un background di colore verde.
     */
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    /**
     * Codice ANSI per ottenere un background di colore giallo.
     */
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    /**
     * Codice ANSI per ottenere un background di colore blu.
     */
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    /**
     * Codice ANSI per ottenere un background di colore viola.
     */
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
     /**
      * Codice ANSI per ottenere un background di colore azzurro.
      */
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    /**
     * Codice ANSI per ottenere un background di colore bianco.
     */
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private Colours() {
    }
}
