package academy.maze.generators.realisations;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Point;
import academy.maze.generators.Generator;
import academy.maze.generators.IGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Генератор Прима. */
public class PrimsGenerator extends Generator implements IGenerator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        int arrayWidth = height * 2 + 1;
        int arrayHeight = width * 2 + 1;
        int amount = height * width;
        CellType[][] result = generateArray(arrayWidth, arrayHeight);
        List<Point> visitedPoints = new ArrayList<>();

        // Случайно каждой точке дается свой вес, где вес - индекс в возвращаемом массиве.
        List<Point> allPoints = getAllPoints(arrayWidth, arrayHeight);
        Collections.shuffle(allPoints);
        Point[] pointsWithWeight = allPoints.toArray(new Point[0]);

        // Множество со случайной первой ячейкой.
        Point[] currentSet = new Point[amount];
        int r = random.nextInt(amount);
        currentSet[r] = pointsWithWeight[r];
        int currentSetCount = 1;

        while (currentSetCount > 0) {
            int maxWeight = getMaxWeight(currentSet);
            Point currentPoint = currentSet[maxWeight];
            visitedPoints.add(currentPoint);
            List<Point> notVisitedNeighbours =
                    getNotVisitedNeighbours(getNeighbours(currentPoint, arrayWidth, arrayHeight), visitedPoints);
            // если у случайно выбранной ячейки нет не посещённых соседей, то она удаляется из множества.
            if (notVisitedNeighbours.isEmpty()) {
                currentSet[maxWeight] = null;
                currentSetCount--;
                // иначе выбирается посещённый сосед, стена между ними убирается, выбранный сосед добавляется в
                // множество.
            } else {
                Point nextPoint = notVisitedNeighbours.get(random.nextInt(notVisitedNeighbours.size()));
                int wallX = currentPoint.x() + (nextPoint.x() - currentPoint.x()) / 2;
                int wallY = currentPoint.y() + (nextPoint.y() - currentPoint.y()) / 2;
                result[wallX][wallY] = CellType.PATH;
                currentSet[indexOf(pointsWithWeight, nextPoint)] = nextPoint;
                currentSetCount++;
                visitedPoints.add(nextPoint);
            }
        }
        return new Maze(result);
    }

    private int getMaxWeight(Point[] pointsWithWeight) {
        for (int i = pointsWithWeight.length - 1; i >= 0; i--) {
            if (pointsWithWeight[i] != null) {
                return i;
            }
        }
        return 0;
    }

    private int indexOf(Point[] array, Point point) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(point)) {
                return i;
            }
        }
        return -1;
    }
}
