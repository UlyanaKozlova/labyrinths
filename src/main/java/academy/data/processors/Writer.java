package academy.data.processors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/** Класс для записи в файл. */
public class Writer {
    private final String maze;

    public Writer(String maze) {
        this.maze = maze;
    }

    // при вызове без параметров - вывод в консоль
    public void write() {
        String normalized = normalizeLineEndings(maze);
        System.out.print(normalized);
    }

    // при вызове с параметром - запись в файл
    public void write(String fileName) {
        try {
            if (fileName != null && fileName.contains(".txt") && fileName.length() > 4) {
                Path path = Path.of(fileName);
                Path parent = path.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.writeString(path, normalizeLineEndings(maze) + "\n", StandardCharsets.UTF_8);
            } else {
                System.err.println("Неверное имя файла!");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String normalizeLineEndings(String text) {
        return text.replace("\r\n", "\n").replace("\r", "\n");
    }
}
