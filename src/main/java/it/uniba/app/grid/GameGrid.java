package it.uniba.app.grid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

import it.uniba.app.game.Generics;
import it.uniba.app.ship.Coordinates;
import it.uniba.app.ship.Ship;
import it.uniba.app.colours.Colours;

/**
 * <p>GameGrid e' una classe <<Entity>>.
 * Permette l'inizializzazione della griglia di gioco.</p>
 * @author Daniele Grandolfo, dgrandolfo4
 * @author gamal, GamalElmetkanany
 */
public class GameGrid implements Cloneable {
    /**
     * Valore rappresentante la quantità di righe nella griglia con grandezza standard.
     */
    private static final int STANDARD_GRID_SIZE = 10;
    /**
     * Valore rappresentante la quantità di righe nella griglia con grandezza large.
     */
    private static final int LARGE_GRID_SIZE = 18;
    /**
     * Valore rappresentante la quantità di righe nella griglia con grandezza extralarge.
     */
    private static final int EXTRALARGE_GRID_SIZE = 26;
    /**
     * Valore rappresentante grandezza della griglia.
     */
    private int gridSize = STANDARD_GRID_SIZE;
    /**
     * Valore della riga da cui la legenda comincia nell'intento di stampa.
     */
    private static final int LEGEND_START_ROW = 3;
    /**
     * Valore rappresentante il simbolo identificativo della nave Cacciatorpediniere.
     */
    private static final char CACCIATORPEDINIERE_SYMBOL = '@';
    /**
     * Valore rappresentante il simbolo identificativo della nave Incrociatore.
     */
    private static final char INCROCIATORE_SYMBOL = '&';
    /**
     * Valore rappresentante il simbolo identificativo della nave Corrazzata.
     */
    private static final char CORAZZATA_SYMBOL = '%';
    /**
     * Valore rappresentante il simbolo identificativo della nave Portaerei.
     */
    private static final char PORTAEREI_SYMBOL = '$';
    /**
     * Valore rappresentante il simbolo identificativo della nave colpita.
     */
    private static final char COLPITA_SYMBOL = 'C';
    /**
     * Valore rappresentante il simbolo identificativo della nave affondata.
     */
    private static final char AFFONDATA_SYMBOL = 'A';
    /**
     * Valore rappresentante il simbolo identificativo della nave mancata.
     */
    private static final char MANCATA_SYMBOL = 'X';
    /**
     * La griglia di gioco.
     */
    private char[][] grid;
    /**
     * Valore indicante l'inizializzazione della griglia.
     */
    private boolean isStarted = false;
    /**
     * Valore che indica l'acquisizione di un numero random per l'inserimento delle navi.
     */
    private Random rand;
    /**
     * Numero totale di navi presenti nel gioco.
     */
    private static final int NUM_SHIPS = 10;
    /**
     * Numero di navi rimanenti nel gioco.
     * Viene inizializzato con il valore di {@link #NUM_SHIPS}.
     */
    private int numberShips = getNumShips();
    /**
     * Dizionario che contiene la coppia chiave-lista delle coordinate di tutte le celle di una singola nave.
     * La chiave rappresenta l'ID della nave, mentre la lista contiene le coordinate delle celle occupate dalla nave.
     */
    private Map<Integer, List<Coordinates>> coordinatesShips = new HashMap<>();
    /**
     * Copia della lista delle navi.
     */
    private List<Ship> copyList = new ArrayList<>();


    /**
     * GameGrid costruttore.
     */
    public GameGrid() {
        rand = new Random();
    }

     /**
      * Questo metodo assegna il valore passato all'attributo {@link GameGrid#isStarted}.
      * @param isGridStarted valore identificante l'inizializzazione della griglia.
      */
     public void setIsStarted(final boolean isGridStarted) {
         this.isStarted = isGridStarted;
     }

     /**
      * Questo metodo assegna il valore passato all'attributo {@link GameGrid#gridSize}.
      * @param size La grandezza desiderata della griglia.
      */
     public void setGridSize(final int size) {
         this.gridSize = size;
     }

     /**
      * Crea una matrice con la grandezza desiderata dall'utente.
      */
     public void createGrid() {
         setIsStarted(true);
         grid = new char[gridSize][gridSize];
         for (int row = 0; row < gridSize; row++) {
             for (int col = 0; col < gridSize; col++) {
                 grid[row][col] = ' ';
             }
         }
     }

     /**
      * Questo metodo acquisice il valore di {@link GameGrid#STANDARD_GRID_SIZE}.
      */
     public int getStandardGridSize() {
         return STANDARD_GRID_SIZE;
     }

     /**
      * Questo metodo acquisice il valore di {@link GameGrid#LARGE_GRID_SIZE}.
      */
     public int getLargeGridSize() {
         return LARGE_GRID_SIZE;
     }

     /**
      * Questo metodo acquisice il valore di {@link GameGrid#EXTRALARGE_GRID_SIZE}.
      */
     public int getExtralargeGridSize() {
         return EXTRALARGE_GRID_SIZE;
     }

     /**
      * Questo metodo acquisice il valore di {@link GameGrid#gridSize}.
      */
     public int getGridSize() {
         return this.gridSize;
     }

     /**
      * Questo metodo acquisisce la griglia di gioco corrente.
      * @return copy valore che indica la copia della griglia di gioco.
      */
     public char[][] getGrid() {
            char[][] copy = new char[gridSize][gridSize];
            for (int row = 0; row < gridSize; row++) {
                for (int col = 0; col < gridSize; col++) {
                    copy[row][col] = grid[row][col];
                }
            }
            return copy;
      }

     /**
      * Metodo che permette di essere a conoscenza dell'inizializzazione della griglia.
      * @return Il valore di {@link #isStarted}
      */
     public boolean getIsStarted() {
         return this.isStarted;
     }

     /**
      * Metodo getter per l'acquisizione del numero delle righe.
      * @return NUM_ROWS numero delle righe della griglia.
      */
     public int getNumRows() {
         return gridSize;
     }

     /**
      * Metodo getter per l'acquisizione del numero delle colonne.
      * @return NUM_ROWS numero delle colonne della griglia.
      */
     public int getNumCols() {
         return gridSize;
     }

    /**
     * Metodo che stampa la griglia di gioco vuota.
     */
    public void printEmptyGrid() {
        System.out.print("   ");
        for (int col = 0; col < gridSize; col++) {
            System.out.print((char) ('A' + col) + " ");
        }
        System.out.println();

        for (int row = 0; row < gridSize; row++) {
            System.out.printf("%2d|", row + 1);
            for (int col = 0; col < gridSize; col++) {
                System.out.print(Colours.ANSI_CYAN + "~" + Colours.ANSI_RESET + "|");
            }
            System.out.println();
        }
    }

    /**
     * <p>Metodo che stampa a video la griglia contenente le navi.</p>
     * Permette di visualizzare la posizione delle navi
     * all'interno della griglia di gioco.
     * @param legend valore che identifica la legenda da stampare, se è vero stampa la legenda della griglia di gioco
     * con le navi inserite, altrimenti stampa la legenda della griglia degli attacchi
     */
    public void printGrid(final boolean legend) {
       if (!getIsStarted()) {
            Generics.prints(Generics.CASO_GRIGLIA_VUOTA);
       } else {
            System.out.print("   ");
            for (int col = 0; col < gridSize; col++) {
                System.out.print((char) ('A' + col) + " ");
            }
            System.out.println();
            for (int row = 0; row < gridSize; row++) {
                System.out.printf("%2d|", row + 1);
                for (int col = 0; col < gridSize; col++) {
                   printColouredShip(grid[row][col]);
                }
                if (legend) {
                    if (row == LEGEND_START_ROW) {
                       System.out.print(String.format("%70s", Colours.ANSI_RED_BACKGROUND
                               + CACCIATORPEDINIERE_SYMBOL + Colours.ANSI_RESET + " -> Cacciatorpediniere"));
                    } else if (row == LEGEND_START_ROW + 1) {
                       System.out.print(String.format("%64s", Colours.ANSI_GREEN_BACKGROUND
                               + INCROCIATORE_SYMBOL + Colours.ANSI_RESET + " -> Incrociatore"));
                    } else if (row == LEGEND_START_ROW + 2) {
                       System.out.print(String.format("%61s", Colours.ANSI_PURPLE_BACKGROUND
                               + CORAZZATA_SYMBOL + Colours.ANSI_RESET + " -> Corazzata"));
                    } else if (row == LEGEND_START_ROW + LEGEND_START_ROW) {
                       System.out.print(String.format("%61s", Colours.ANSI_YELLOW_BACKGROUND
                               + PORTAEREI_SYMBOL + Colours.ANSI_RESET + " -> Portaerei"));
                    }
                } else {
                   if (row == LEGEND_START_ROW + 1) {
                       System.out.print(String.format("%64s", Colours.ANSI_RED_BACKGROUND
                               + "C" + Colours.ANSI_RESET + " -> Colpita"));
                    } else if (row == LEGEND_START_ROW + 2) {
                       System.out.print(String.format("%66s", Colours.ANSI_PURPLE_BACKGROUND
                               + "A" + Colours.ANSI_RESET + " -> Affondata"));
                    } else if (row == LEGEND_START_ROW + LEGEND_START_ROW) {
                        System.out.print(String.format("%64s", Colours.ANSI_YELLOW_BACKGROUND
                                + "X" + Colours.ANSI_RESET + " -> Mancata"));
                     }
                }
                System.out.println();
            }
       }
    }

    /**
     * Stampa un simbolo di nave colorato sulla console, in base al simbolo fornito.
     * Utilizza il simbolo della nave fornito per determinare il colore di sfondo appropriato.
     *
     * @param shipSymbol il simbolo della nave da stampare
     */
    private void printColouredShip(final char shipSymbol) {
        switch (shipSymbol) {
            case CACCIATORPEDINIERE_SYMBOL:
                System.out.print(Colours.ANSI_RED_BACKGROUND + CACCIATORPEDINIERE_SYMBOL);
                break;
            case INCROCIATORE_SYMBOL:
                System.out.print(Colours.ANSI_GREEN_BACKGROUND + INCROCIATORE_SYMBOL);
                break;
            case CORAZZATA_SYMBOL:
                System.out.print(Colours.ANSI_PURPLE_BACKGROUND + CORAZZATA_SYMBOL);
                break;
            case PORTAEREI_SYMBOL:
                System.out.print(Colours.ANSI_YELLOW_BACKGROUND + PORTAEREI_SYMBOL);
                break;
            case COLPITA_SYMBOL:
                System.out.print(Colours.ANSI_RED_BACKGROUND + COLPITA_SYMBOL);
                break;
            case AFFONDATA_SYMBOL:
                System.out.print(Colours.ANSI_PURPLE_BACKGROUND + AFFONDATA_SYMBOL);
                break;
            case MANCATA_SYMBOL:
                System.out.print(Colours.ANSI_YELLOW + MANCATA_SYMBOL);
                break;
            default:
                System.out.print(Colours.ANSI_CYAN + "~");
                break;
        }
        System.out.print(Colours.ANSI_RESET + "|");
    }

    /**
     * Metodo getter per {@link GameGrid#grid}.
     * @return valore della griglia nella riga i e colonna j.
     */
    public char getGridValue(final int i, final int j) {
        return grid[i][j];
    }

    /**
     * Metodo setter per {@link GameGrid#grid}.
     * @param i posizione della riga.
     * @param j posizione della colonna.
     * @param value valore da inserire in posizione i e j.
     */
    public void setGridValue(final int i, final int j, final char value) {
         grid[i][j] = value;
    }

    /**
     * Metodo che consente di caricare una lista di navi differenti di grandezza e quantità.
     * @return Una {@link List} di {@link Ship}
     */
    private List<Ship> loadShips() {
        List<Ship> ships = new ArrayList<>();
        final int sizeShip1 = 2;
        final int quantityShip1 = 4;
        ships.add(new Ship("Cacciatorpediniere", sizeShip1, quantityShip1, '@'));
        final int sizeShip2 = 3;
        final int quantityShip2 = 3;
        ships.add(new Ship("Incrociatore", sizeShip2, quantityShip2, '&'));
        final int sizeShip3 = 4;
        final int quantityShip3 = 2;
        ships.add(new Ship("Corazzata", sizeShip3, quantityShip3, '%'));
        final int sizeShip4 = 5;
        final int quantityShip4 = 1;
        ships.add(new Ship("Portaerei", sizeShip4, quantityShip4, '$'));

        return ships;
    }

    /**
     * Metodo che inserisce le navi in maniera casuale nella griglia.
     * @return gameGrid, griglia di gioco con le navi inserite in maniera casuale.
     */
    public final char[][] shipInsertion() {
        int key = 1;

        List<Ship> ships = loadShips();
        Random randN = this.rand;

        for (Ship ship : ships) {
            int size = ship.getSize();
            int quantity = ship.getQuantity();
            while (quantity > 0) {
                int rowRand = randN.nextInt(gridSize);
                int colRand = randN.nextInt(gridSize);
                boolean horizRand = rand.nextBoolean();
                boolean value = false;
                for (int i = 0; i < size; i++) {
                    int row = rowRand + (horizRand ? 0 : i);
                    int col = colRand + (horizRand ? i : 0);
                    if (row >= gridSize || col >= gridSize || getGridValue(row, col) != ' ') {
                        value = true;
                        break;
                    }
                }
                if (!value) {
                    //creo una nuova lista per ogni nave
                    List<Coordinates> coordinates = new ArrayList<>();
                    for (int i = 0; i < size; i++) {
                        int row = rowRand + (horizRand ? 0 : i);
                        int col = colRand + (horizRand ? i : 0);
                        setGridValue(row, col, ship.getSymbol());
                        //inserisco le coordinate della nave esaminata in una lista
                        try {
                            coordinates.add(new Coordinates(row, col));
                        } catch (Exception e) {
                            System.out.println("Inserimento delle coordinate della nave nella lista non riuscito.");
                        }
                    }
                   //inserisco la lista creata contentente le coordinate delle celle di una nave, in un dizionario
                    try {
                        this.coordinatesShips.put(key, coordinates);
                    } catch (Exception e) {
                        System.out.println("Inserimento delle coordinate della nave nel dizionario non riuscito.");
                    }
                    key++;
                    quantity--;
                }
            }
        }
        setCopyList(ships);
        return getGrid();
    }

    /**
     * Restituisce il dizionario contenente le coordinate delle celle di tutte le navi.
     *
     * @return il dizionario delle coordinate delle navi
     */
    public Map<Integer, List<Coordinates>> getCoordinatesShips() {
        return createCoordinatesShipsCopy();
    }

    /**
     * Restituisce la copia del dizionario contenente le coordinate delle celle di tutte le navi.
     *
     * @return il dizionario la copia delle coordinate delle navi
     */
    private Map<Integer, List<Coordinates>> createCoordinatesShipsCopy() {
        Map<Integer, List<Coordinates>> copy = new HashMap<>();

        for (Map.Entry<Integer, List<Coordinates>> entry : coordinatesShips.entrySet()) {
            int key = entry.getKey();
            List<Coordinates> value = entry.getValue();
            copy.put(key, new ArrayList<>(value));
        }

        return copy;
    }

    /**
     * Restituisce il numero di navi rimanenti.
     *
     * @return il numero di navi rimanenti
     */
    public int getNumberShips() {
        return this.numberShips;
    }

     /**
     * Imposta il numero di navi rimanenti.
     *
     * @param number il numero di navi rimanenti
     */
    public void setNumberShips(final int number) {
       this.numberShips = number;
    }

    /**
     * Restituisce il numero delle navi.
     *
     * @return il numero delle navi
     */
    public static int getNumShips() {
        return NUM_SHIPS;
    }

    /**
     * Restituisce una copia della lista di navi.
     *
     * @return una copia della lista di navi
     */
    public List<Ship> getCopyList() {
        return new ArrayList<>(copyList);
    }

    /**
     * Imposta la lista di navi con una nuova lista specificata.
     *
     * @param ships la nuova lista di navi da impostare
     */
    public void setCopyList(final List<Ship> ships) {
        this.copyList = new ArrayList<>(ships);
    }

    /**
     * metodo che ritorna la copia della griglia.
     * @return clone copia della griglia
     */
    @Override
    public GameGrid clone() throws CloneNotSupportedException {
        GameGrid clone = (GameGrid) super.clone();

        clone.grid = this.grid.clone();

        return clone;
    }

}
