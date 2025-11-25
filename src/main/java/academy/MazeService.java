package academy;

import academy.data.processors.MazeRenderer;
import academy.data.processors.Reader;
import academy.data.processors.Writer;
import academy.data.processors.factories.GeneratorFactory;
import academy.data.processors.factories.SolverFactory;
import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeService {
    private MazeService() {}

    public static void generateMaze(String algorithm, int height, int width, String fileName, boolean unicode) {
        // генерация лабиринта
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Размеры лабиринта должны быть положительными!");
        }
        Maze maze = GeneratorFactory.create(algorithm).generate(height, width);

        // вывод лабиринта
        String mazeText = MazeRenderer.render(maze, unicode);
        Writer writer = new Writer(mazeText);
        if (fileName != null) {
            writer.write(fileName);
        } else {
            writer.write();
        }
    }

    public static void solveMaze(
            String algorithm,
            String inputFile,
            String start,
            String end,
            String outputFile,
            boolean unicode,
            boolean randomWeights) {
        Point startPoint = Reader.parsePoint(start);
        Point endPoint = Reader.parsePoint(end);
        Maze oldMaze = new Reader(inputFile).readMaze();
        Maze maze = oldMaze;

        // удаление стен, если будут учитываться случайные веса.
        if (randomWeights) {
            if ("wave".equals(algorithm)) {
                throw new IllegalArgumentException("Невозможно использовать случайные веса для волнового алгоритма!");
            }
            maze = removeSomeWalls(oldMaze);
        }

        if (startPoint.x() < 0
                || startPoint.y() < 0
                || endPoint.x() < 0
                || endPoint.y() < 0
                || startPoint.x() >= maze.cells().length
                || startPoint.y() >= maze.cells()[0].length
                || endPoint.x() >= maze.cells().length
                || endPoint.y() >= maze.cells()[0].length) {
            throw new IllegalArgumentException("Координаты точки должны соответствовать размеру лабиринта!");
        }
        if (maze.cells()[startPoint.x()][startPoint.y()] == CellType.WALL
                || maze.cells()[endPoint.x()][endPoint.y()] == CellType.WALL) {
            throw new IllegalArgumentException("Путь не может начинаться или заканчиваться стеной!");
        }

        Path path;
        if (randomWeights) {
            path = SolverFactory.create(algorithm).solve(maze, startPoint, endPoint, true);
        } else {
            path = SolverFactory.create(algorithm).solve(maze, startPoint, endPoint);
        }

        Writer writer = new Writer(MazeRenderer.render(oldMaze, path, unicode));
        if (outputFile != null) {
            writer.write(outputFile);
        } else {
            writer.write();
        }
    }

    /**
     * Метод удаляющий 20 процентов стен кроме боковых для использования случайных весов.
     *
     * @param maze лабиринт.
     * @return лабиринт из 80 процентов исходных стен.
     */
    private static Maze removeSomeWalls(Maze maze) {
        CellType[][] originalCells = maze.cells();

        CellType[][] newCells = new CellType[originalCells.length][];
        for (int i = 0; i < originalCells.length; i++) {
            newCells[i] = originalCells[i].clone();
        }

        // массив стен, которые можно выкинуть
        List<Point> wallPoints = new ArrayList<>();
        for (int i = 1; i < newCells.length - 1; i++) {
            for (int j = 1; j < newCells[i].length - 1; j++) {
                if (newCells[i][j] == CellType.WALL) {
                    wallPoints.add(new Point(i, j));
                }
            }
        }

        int wallsToRemove = (int) (wallPoints.size() * 0.2);
        Collections.shuffle(wallPoints);

        for (int i = 0; i < wallsToRemove && i < wallPoints.size(); i++) {
            Point wall = wallPoints.get(i);
            newCells[wall.x()][wall.y()] = CellType.PATH;
        }
        return new Maze(newCells);
    }
}
