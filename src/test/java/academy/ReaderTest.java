package academy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import academy.data.processors.Reader;
import academy.maze.dto.Point;
import org.junit.jupiter.api.Test;

/** Тесты для класса Reader. */
public class ReaderTest {
    @Test
    void testConstructor_NullFileName() {
        Reader reader = new Reader(null);
        assertNotNull(reader);
    }

    @Test
    void testReadMaze_FileNotFound() {
        Reader reader = new Reader("nonexistent_file.txt");

        RuntimeException exception = assertThrows(RuntimeException.class, reader::readMaze);
        assertTrue(exception.getMessage().contains("Ошибка при чтении файла"));
    }

    @Test
    void testParsePoint_EmptyString() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> Reader.parsePoint(""));
        assertTrue(exception.getMessage().contains("Invalid point format"));
    }

    @Test
    void testParsePoint_InvalidFormat() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Reader.parsePoint("1,2,3"));
        assertTrue(exception.getMessage().contains("Invalid point format"));
    }

    @Test
    void testParsePoint_InvalidFormatSingleNumber() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Reader.parsePoint("123"));
        assertTrue(exception.getMessage().contains("Invalid point format"));
    }

    @Test
    void testParsePoint_NonNumericValues() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> Reader.parsePoint("abc,def"));
        assertTrue(exception.getMessage().contains("Invalid point format"));
    }

    @Test
    void testParsePoint_WithSpaces() {
        Point point = Reader.parsePoint(" 1 , 2 ");
        assertEquals(1, point.x());
        assertEquals(2, point.y());
    }

    @Test
    void testParsePoint_NegativeNumbers() {
        Point point = Reader.parsePoint("-1,-2");
        assertEquals(-1, point.x());
        assertEquals(-2, point.y());
    }

    @Test
    void testParsePoint_ValidFormats() {
        assertEquals(new Point(1, 2), Reader.parsePoint("1,2"));
        assertEquals(new Point(10, 20), Reader.parsePoint("10,20"));
        assertEquals(new Point(0, 0), Reader.parsePoint("0,0"));
    }
}
