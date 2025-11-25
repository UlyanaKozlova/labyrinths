package academy.maze.solvers;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Point;
import java.util.Random;

public abstract class Solver implements ISolver {
    protected int[][] directions =
            new int[][] {new int[] {-1, 0}, new int[] {1, 0}, new int[] {0, -1}, new int[] {0, 1}};
    protected int wallValue = -1;
    protected int startValue = 0;
    protected int notReached = Integer.MAX_VALUE - 1000;
    protected int weight = 1;

    protected boolean isAPath(Point point, Maze maze) {
        return maze.cells()[point.x()][point.y()] != CellType.WALL;
    }

    protected final Random random = new Random();

    protected int[][] prepareMaze(Maze maze, Point start) {
        CellType[][] cells = maze.cells();
        int[][] result = new int[cells.length][cells[0].length];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == CellType.WALL) {
                    result[i][j] = wallValue;
                } else if (cells[i][j] == CellType.PATH) {
                    result[i][j] = notReached;
                }
            }
        }
        result[start.x()][start.y()] = startValue;
        return result;
    }

    /**
     * Метод для генерации случайного веса точки для доп задачи
     *
     * @param useWeights нужно ли менять вес
     * @return 1 при решении лабиринта с равными весами или случайный вес от 1 до 5.
     */
    protected int getWeight(boolean useWeights) {
        if (!useWeights) {
            return weight;
        }

        return 1 + random.nextInt(5);
    }
}
