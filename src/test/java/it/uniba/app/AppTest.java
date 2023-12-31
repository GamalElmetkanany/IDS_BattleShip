package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Main test class of the application.
 */
class AppTest {
    /**
     * Test the getGreeting method of the App class.
     */
    @Test
    void appHasAGreeting() {
        assertNotNull(App.getGreeting(), "app should have a greeting");
    }
}
