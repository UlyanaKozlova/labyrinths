package academy.data.processors;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Point;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Класс для чтения лабиринта из файла */
public class Reader {
    private final String fileName;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    public Maze readMaze() {
        List<String> lines;
        Maze maze;
        try {
            Path path = Path.of(fileName);

            if (!Files.exists(path) && fileName.startsWith("/")) {
                path = Path.of(
                        "." + fileName.replace("/", FileSystems.getDefault().getSeparator()));
            }
            lines = Files.readAllLines(path);
            if (lines.isEmpty()) {
                throw new IllegalArgumentException("Файл пустой");
            } else {
                maze = parseMaze(lines);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка при чтении файла: " + fileName);
        }
        return maze;
    }

    private Maze parseMaze(List<String> lines) {
        List<Character> wallsChars = CharStorage.WALLS_CHARS;
        CellType[][] cells = new CellType[lines.size()][lines.getFirst().length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                char c = lines.get(i).charAt(j);
                if (wallsChars.contains(c)) {
                    cells[i][j] = CellType.WALL;
                } else if (c == ' ') {
                    cells[i][j] = CellType.PATH;
                } else {
                    throw new IllegalArgumentException("Неизвестный символ: " + c);
                }
            }
        }
        return new Maze(cells);
    }

    public static Point parsePoint(String pointStr) {
        try {
            String[] parts = pointStr.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid point format: " + pointStr + ", expected format: x,y");
            }
            return new Point(Integer.parseInt(parts[0].trim()), Integer.parseInt(parts[1].trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid point format: " + pointStr + ", expected format: x,y");
        }
    }
}
