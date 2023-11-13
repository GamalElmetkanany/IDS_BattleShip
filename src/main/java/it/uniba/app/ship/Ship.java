package it.uniba.app.ship;
/**
 * Ship e' una classe <<Entity>>.
 * Questa classe rappresenta le navi di gioco.
 * La classe implementa Serializable per consentire la serializzazione degli oggetti.
 * @author gamal, GamalElmetkanany
 */
public class Ship {
    private String nameShip;
    private int sizeShip;
    private int quantityShip;
    private char symbolShip;

/**
 * Questo metodo inizializza la nave di gioco.
 * @param name nome della nave di gioco.
 * @param size dimensione della nave di gioco.
 * @param quantity quantita' della nave presente nel gioco.
 * @param symbol simobolo che identifica la nave nella griglia del gioco.
 */
    public Ship(final String name, final int size, final int quantity, final char symbol) {
        this.nameShip = name;
        this.sizeShip = size;
        this.quantityShip = quantity;
        this.symbolShip = symbol;
    }

/**
 * Questo metodo acquisisce il nome della nave di gioco.
 * @return nameShip, nome della nave di gioco.
 */
    public String getName() {
       return nameShip;
    }

 /**
 * Questo metodo acquisisce la dimensione della nave di gioco.
 * @return sizeShip, dimensione della nave di gioco.
 */
    public int getSize() {
        return sizeShip;
    }

/**
* Questo metodo acquisisce il numero della nave di gioco.
* @return quanntityShip, quantita' della nave di gioco.
*/
    public int getQuantity() {
        return quantityShip;
    }

    /**
     * Imposta la quantità della nave con il valore specificato.
     *
     * @param num la nuova quantità da assegnare alla nave
     */
    public void setQuantity(final int num) {
        this.quantityShip = num;
    }

/**
* Questo metodo acquisisce il simbolo della nave di gioco.
* @return symbolShip, simbolo della nave di gioco.
*/
    public char getSymbol() {
        return symbolShip;
    }
}
