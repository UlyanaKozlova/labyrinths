package academy.maze.solvers;

import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;

/** Решатель лабиринта */
public interface ISolver {

    /**
     * Решение лабиринта. Если путь не найден, то возвращается путь с длиной 0.
     *
     * @param maze лабиринт.
     * @param start начальная точка.
     * @param end конечная точка.
     * @return путь в лабиринте.
     */
    Path solve(Maze maze, Point start, Point end);

    /**
     * Решение лабиринта с учетом весов клеток. Если путь не найден, то возвращается путь с длиной 0.
     *
     * @param maze лабиринт.
     * @param start начальная точка.
     * @param end конечная точка.
     * @param useWeights использовать ли веса клеток.
     * @return путь в лабиринте.
     */
    default Path solve(Maze maze, Point start, Point end, boolean useWeights) {
        return solve(maze, start, end);
    }
}
