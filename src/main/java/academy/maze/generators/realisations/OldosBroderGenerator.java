package academy.maze.generators.realisations;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Point;
import academy.maze.generators.Generator;
import academy.maze.generators.IGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Алгоритм Олдоса-Бродера 1. Выбирается случайная точка 2. Пока все точки не помечены как посещенные, выбирается
 * случайная соседняя точка, помечается текущей и, если она не была посещена, то посещается и убирается стена между ней
 * и предыдущей.
 */
public class OldosBroderGenerator extends Generator implements IGenerator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        int arrayWidth = height * 2 + 1;
        int arrayHeight = width * 2 + 1;
        CellType[][] result = generateArray(arrayWidth, arrayHeight);
        List<Point> visitedPoints = new ArrayList<>();

        // Выбираем случайную точку
        Point point = new Point(1 + 2 * random.nextInt(height), 1 + 2 * random.nextInt(width));
        visitedPoints.add(point);

        while (visitedPoints.size() != height * width) {
            List<Point> neighbours = getNeighbours(point, arrayWidth, arrayHeight);

            if (!neighbours.isEmpty()) {
                int r = random.nextInt(neighbours.size());
                Point nextPoint = neighbours.get(r);
                // Если точка не была посещена, то помечаем как посещенную и убираем стену.
                if (!visitedPoints.contains(nextPoint)) {
                    int wallX = point.x() + (nextPoint.x() - point.x()) / 2;
                    int wallY = point.y() + (nextPoint.y() - point.y()) / 2;
                    result[wallX][wallY] = CellType.PATH;

                    point = nextPoint;
                    visitedPoints.add(point);

                    // Иначе делаем только что случайно выбранную точку текущей.
                } else {
                    point = nextPoint;
                }
            }
        }
        return new Maze(result);
    }
}
