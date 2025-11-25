package academy.data.processors;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;
import java.util.Map;

/** Класс для строкового представления лабиринта. */
public class MazeRenderer {
    /**
     * Строковое представление нерешенного лабиринта.
     *
     * @param maze лабиринт
     * @param unicode если true, то лабиринт состоит их #, иначе из псевдографики.
     * @return строковое представление лабиринта
     */
    public static String render(Maze maze, boolean unicode) {
        return renderAll(maze, null, unicode);
    }

    /**
     * Строковое представление нерешенного лабиринта.
     *
     * @param maze лабиринт
     * @param path путь - решение лабиринта.
     * @param unicode если true, то лабиринт состоит их #, иначе из псевдографики.
     * @return строковое представление лабиринта
     */
    public static String render(Maze maze, Path path, boolean unicode) {
        return renderAll(maze, path, unicode);
    }

    private static final int[][] directions =
            new int[][] {new int[] {0, 1}, new int[] {1, 0}, new int[] {0, -1}, new int[] {-1, 0}};

    private static String renderAll(Maze maze, Path path, boolean unicode) {
        StringBuilder result = new StringBuilder();
        Map<Integer, Character> chars = CharStorage.CHARS;

        CellType[][] cells = maze.cells();
        int width = cells.length;
        int height = cells[0].length;

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                CellType currentCell = cells[i][j];

                if (currentCell == CellType.PATH) {
                    result.append(CharStorage.EMPTY);
                } else if (!unicode) {
                    // добавляется стена - #
                    result.append(CharStorage.WALL);
                } else {
                    // считается четырехзначное число, представляющее, чем являются соседи.
                    int neighbors = 0;
                    for (int[] d : directions) {
                        // если сосед выходит за грани поля, то считаем текущую цифру 0.
                        if (0 <= i + d[0] && i + d[0] < width && 0 <= j + d[1] && j + d[1] < height) {
                            // сосед - пустая клетка, значит цифра 1.
                            if (cells[i + d[0]][j + d[1]] == CellType.PATH) {
                                neighbors += 1;
                                // сосед - стена, значит цифра 2.
                            } else {
                                neighbors += 2;
                            }
                        }
                        neighbors *= 10;
                    }
                    neighbors /= 10;
                    // На этом этапе получили четырехзначное число, где:
                    // первая цифра - правый сосед, вторая - нижний, третья - левый, четвертая - верхний.
                    // Если в хранилище символов есть такой ключ, то добавляем соответствующий символ.
                    if (chars.containsKey(neighbors)) {
                        result.append(chars.get(neighbors));
                    } else {
                        result.append(')');
                    }
                }
            }
            result.append(CharStorage.NEW);
        }
        // в том случае, если у нас решенный лабиринт, заменяем клетки в итоговой строке на обозначения дороги.
        if (path != null && path.points().length > 0) {
            Point[] pathPoints = path.points();
            int rowLength = height + 1;
            for (Point point : pathPoints) {
                result.setCharAt(point.x() * rowLength + point.y(), CharStorage.ROAD);
            }
            Point start = pathPoints[0];

            // добавляем символы начала и конца пути.
            Point end = pathPoints[pathPoints.length - 1];
            result.setCharAt(start.x() * rowLength + start.y(), CharStorage.START);
            result.setCharAt(end.x() * rowLength + end.y(), CharStorage.END);
        }
        return result.toString().trim();
    }

    private MazeRenderer() {}
}
