package academy.maze.solvers;

import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;

/** Волновой алгоритм. Находит кратчайший путь, распространяя волну от начальной точки, при одинаковых весах. */
public class WaveSolver extends Solver implements ISolver {
    @Override
    public Path solve(Maze maze, Point start, Point end) {
        int[][] cells = prepareMaze(maze, start);
        for (int iteration = 0; iteration < cells.length * cells[0].length; iteration++) {
            for (int i = 1; i < cells.length - 1; i++) {
                for (int j = 1; j < cells[i].length - 1; j++) {
                    if (isAPath(new Point(i, j), maze)) {
                        // если в соседе записано большее число, то перезаписывается на текущий путь +1
                        for (int[] direction : directions) {
                            int neighbourX = i + direction[0];
                            int neighbourY = j + direction[1];
                            if (isAPath(new Point(neighbourX, neighbourY), maze)
                                    && cells[i][j] + weight < cells[neighbourX][neighbourY]) {
                                cells[neighbourX][neighbourY] = cells[i][j] + weight;
                            }
                        }
                    }
                }
            }
        }

        if (cells[end.x()][end.y()] == notReached) {
            return new Path(new Point[0]);
        }

        Point[] path = new Point[cells[end.x()][end.y()] + 1];
        path[cells[end.x()][end.y()]] = end;
        Point current = end;

        for (int i = path.length - 2; i >= 0; i--) {
            for (int[] dir : directions) {
                // добавляем соседа, который на единицу ближе к старту, в путь
                if (cells[current.x() + dir[0]][current.y() + dir[1]] == cells[current.x()][current.y()] - weight) {
                    path[i] = new Point(current.x() + dir[0], current.y() + dir[1]);
                    break;
                }
            }
            current = path[i];
        }
        path[0] = start;
        return new Path(path);
    }

    @Override
    public Path solve(Maze maze, Point start, Point end, boolean useWeights) {
        if (useWeights) {
            throw new IllegalArgumentException("Для этого алгоритма нельзя поменять вес!");
        }
        return solve(maze, start, end);
    }
}
