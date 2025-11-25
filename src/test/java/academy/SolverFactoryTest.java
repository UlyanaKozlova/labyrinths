package academy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import academy.data.processors.factories.SolverFactory;
import org.junit.jupiter.api.Test;

public class SolverFactoryTest {
    @Test
    void testCreate_ValidTypes() {
        assertNotNull(SolverFactory.create("astar"));
        assertNotNull(SolverFactory.create("dijkstra"));
        assertNotNull(SolverFactory.create("wave"));
        assertNotNull(SolverFactory.create("AStar"));
        assertNotNull(SolverFactory.create("DIJKSTRA"));
    }

    @Test
    void testCreate_NullType() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> SolverFactory.create(null));
        assertEquals("Не указан алгоритм!", exception.getMessage());
    }

    @Test
    void testCreate_InvalidType() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> SolverFactory.create("invalid_solver"));
        assertEquals("Несуществующий алгоритм!", exception.getMessage());
    }

    @Test
    void testCreate_EmptyString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> SolverFactory.create(""));
        assertEquals("Несуществующий алгоритм!", exception.getMessage());
    }

    @Test
    void testCreate_WhitespaceString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> SolverFactory.create("   "));
        assertEquals("Несуществующий алгоритм!", exception.getMessage());
    }
}
