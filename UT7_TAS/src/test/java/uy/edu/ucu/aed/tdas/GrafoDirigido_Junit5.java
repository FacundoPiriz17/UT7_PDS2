package uy.edu.ucu.aed.tdas;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for implemented methods.
 */
public class GrafoDirigido_Junit5
{
    String instanceVariable;

    @BeforeEach
    public void setUp() {
        // Initialize any resources or objects needed for the tests
        instanceVariable = "Value before test";
    }

    @AfterEach
    public void tearDown() {
        // Release any resources or clean up after the tests
        instanceVariable = null;
    }

    /**
     * Sample test in JUnit 5
     */
    @Test
    public void shouldAnswerWithTrueInJUnit5Test()
    {
        assertTrue(instanceVariable != null);
    }
}