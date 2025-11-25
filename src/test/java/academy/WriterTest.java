package academy;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import academy.data.processors.Writer;
import org.junit.jupiter.api.Test;

/** Тесты для класса Writer. */
public class WriterTest {
    @Test
    void testConstructor_NullMaze() {
        Writer writer = new Writer(null);
        assertNotNull(writer);
    }

    @Test
    void testConstructor_EmptyMaze() {
        Writer writer = new Writer("");
        assertNotNull(writer);
    }

    @Test
    void testWrite_WithNullFileName() {
        Writer writer = new Writer("Test maze");
        assertDoesNotThrow(() -> writer.write(null));
    }

    @Test
    void testWrite_WithEmptyFileName() {
        Writer writer = new Writer("Test maze");
        assertDoesNotThrow(() -> writer.write(""));
    }

    @Test
    void testWrite_WithInvalidFileName() {
        Writer writer = new Writer("Test");
        assertDoesNotThrow(() -> writer.write("maze"));
        assertDoesNotThrow(() -> writer.write("maze.json"));
        assertDoesNotThrow(() -> writer.write(".txt"));
    }
}
