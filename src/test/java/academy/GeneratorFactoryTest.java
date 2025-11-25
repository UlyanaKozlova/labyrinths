package academy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import academy.data.processors.factories.GeneratorFactory;
import org.junit.jupiter.api.Test;

/** Тесты для класса фабрики генераторов лабиринтов. */
public class GeneratorFactoryTest {
    @Test
    void testCreate_ValidTypes() {
        assertNotNull(GeneratorFactory.create("dfs"));
        assertNotNull(GeneratorFactory.create("prim"));
        assertNotNull(GeneratorFactory.create("oldosbroder"));
        assertNotNull(GeneratorFactory.create("binarytree"));
        assertNotNull(GeneratorFactory.create("DFS"));
        assertNotNull(GeneratorFactory.create("Prim"));
    }

    @Test
    void testCreate_NullType() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> GeneratorFactory.create(null));
        assertEquals("Генератор не введен!", exception.getMessage());
    }

    @Test
    void testCreate_InvalidType() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> GeneratorFactory.create("invalid_generator"));
        assertEquals("Несуществующий генератор!", exception.getMessage());
    }

    @Test
    void testCreate_EmptyString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> GeneratorFactory.create(""));
        assertEquals("Несуществующий генератор!", exception.getMessage());
    }

    @Test
    void testCreate_WhitespaceString() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> GeneratorFactory.create("   "));
        assertEquals("Несуществующий генератор!", exception.getMessage());
    }
}
