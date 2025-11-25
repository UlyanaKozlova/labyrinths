package academy.maze.generators.realisations;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Point;
import academy.maze.generators.Generator;
import academy.maze.generators.IGenerator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/** Поиск в глубину */
public class DFSGenerator extends Generator implements IGenerator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        int arrayWidth = height * 2 + 1;
        int arrayHeight = width * 2 + 1;
        CellType[][] result = generateArray(arrayWidth, arrayHeight);
        List<Point> visitedPoints = new ArrayList<>();
        List<Point> notVisitedPoints = getAllPoints(arrayWidth, arrayHeight);
        Deque<Point> stack = new ArrayDeque<>();

        Point point = new Point(1, 1);
        visitedPoints.add(point);
        notVisitedPoints.remove(point);

        // Пока не посетим всем точки.
        while (!notVisitedPoints.isEmpty()) {
            ArrayList<Point> neighbours = getNeighbours(point, arrayWidth, arrayHeight);
            List<Point> notVisitedNeighbours = getNotVisitedNeighbours(neighbours, visitedPoints);
            // Если есть не посещенные соседи, то посещаем какого-то из них.
            if (!getNotVisitedNeighbours(neighbours, visitedPoints).isEmpty()) {
                stack.push(new Point(point.x(), point.y()));

                Point nextPoint = notVisitedNeighbours.get(random.nextInt(notVisitedNeighbours.size()));
                int wallX = point.x() + (nextPoint.x() - point.x()) / 2;
                int wallY = point.y() + (nextPoint.y() - point.y()) / 2;
                result[wallX][wallY] = CellType.PATH;
                point = nextPoint;

                visitedPoints.add(point);
                notVisitedPoints.remove(point);
                // Если в стеке есть точки, то принимаем последнюю за текущую.
            } else if (!stack.isEmpty()) {
                point = stack.pop();

                // Иначе посещаем случайную не посещенную точку.
            } else {
                point = notVisitedPoints.get(random.nextInt(notVisitedPoints.size()));
                visitedPoints.add(point);

                notVisitedPoints.remove(point);
            }
        }
        return new Maze(result);
    }
}
