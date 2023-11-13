package it.uniba.app.ship;
/**
 * Coordinates e' una classe <<Control>>.
 * Rappresenta le coordinate di una cella sulla griglia.
 * @author gamal, GamalElmetkanany
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * Costruisce un oggetto Coordinates con le coordinate specificate.
     *
     * @param r la coordinata x
     * @param c la coordinata y
     */
    public Coordinates(final int r, final int c) {
        this.x = r;
        this.y = c;
    }

    /**
     * Restituisce la coordinata x.
     *
     * @return la coordinata x
     */
    public int getX() {
        return x;
    }

    /**
     * Restituisce la coordinata y.
     *
     * @return la coordinata y
     */
    public int getY() {
        return y;
    }
}

