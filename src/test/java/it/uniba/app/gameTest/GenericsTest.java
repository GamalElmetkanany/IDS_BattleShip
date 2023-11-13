package it.uniba.app.gameTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.util.Scanner;
import it.uniba.app.game.Generics;

/**
 * Classe di test per la classe Generics.
 * Contiene i test per i metodi della classe Generics.
 * @author gamal
 */
class GenericsTest {

    private Scanner scanner;

    /**
     * Preparazione dello scanner con un input specifico prima di ogni test.
     */
    @BeforeEach
    void setupReadInput() {
        String testInput = "Test input";
        InputStream inputStream = new ByteArrayInputStream(testInput.getBytes(StandardCharsets.UTF_8));
        scanner = new Scanner(inputStream, StandardCharsets.UTF_8);
    }

    /**
     * Test per il metodo readInput della classe Generics.
     * Verifica che l'input dell'utente corrisponda all'input atteso.
     */
    @Test
    @DisplayName("Test di lettura dell'input")
    void testReadInput() {
        String expected = "Test input";
        String actual = Generics.readInput(scanner);
        assertEquals(expected, actual, "L'input dell'utente non corrisponde");
    }

    /**
     * Caso di test per verificare la seconda parte intera dell'input (valido).
     */
    @Test
    @DisplayName("Test di verifica sulla seconda parte intera dell'input (valido)")
    void testCheckCompoundStringWithValidInput() {
        String[] input = {"hello", "123"};
        boolean result = Generics.checkCompoundString(input);
        Assertions.assertTrue(result, "La stringa acquisita dovrebbe essere considerata corretta");
    }

    /**
     * Caso di test per verificare la seconda parte intera dell'input (non valido).
     */
    @Test
    @DisplayName("Test di verifica sulla seconda parte intera dell'input (non valido)")
    void testCheckCompoundStringWithInvalidInput() {
        String[] input = {"hello", "world"};
        boolean result = Generics.checkCompoundString(input);
        Assertions.assertFalse(result, "La stringa acquisita non dovrebbe essere considerata corretta");
    }

    /**
     * Caso di test per verificare la seconda parte intera dell'input (input insufficiente).
     */
    @Test
    @DisplayName("Test di verifica sulla seconda parte intera dell'input (input insufficiente)")
    void testCheckCompoundStringWithInsufficientInput() {
        String[] input = {"hello"};
        boolean result = Generics.checkCompoundString(input);
        Assertions.assertFalse(result, "La stringa acquisita non dovrebbe essere considerata corretta");
    }

}
