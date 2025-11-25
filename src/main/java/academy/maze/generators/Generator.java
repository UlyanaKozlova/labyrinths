package academy.maze.generators;

import academy.maze.dto.CellType;
import academy.maze.dto.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Generator implements IGenerator {
    /** Генерирует массив с максимальным количеством стен (между всеми точками). */
    protected CellType[][] generateArray(int width, int height) {
        CellType[][] generatedArray = new CellType[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                generatedArray[i][j] = (i % 2 == 0 || j % 2 == 0) ? CellType.WALL : CellType.PATH;
            }
        }
        return generatedArray;
    }

    protected ArrayList<Point> getNeighbours(Point point, int width, int height) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int x = point.x();
        int y = point.y();
        if (x + 2 < width) {
            neighbors.add(new Point(x + 2, y));
        }
        if (x - 2 > 0) {
            neighbors.add(new Point(x - 2, y));
        }
        if (y + 2 < height) {
            neighbors.add(new Point(x, y + 2));
        }
        if (y - 2 > 0) {
            neighbors.add(new Point(x, y - 2));
        }
        return neighbors;
    }

    protected List<Point> getNotVisitedNeighbours(ArrayList<Point> neighbors, List<Point> visited) {
        List<Point> notVisitedNeighbors = new ArrayList<>();
        for (Point neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                notVisitedNeighbors.add(neighbor);
            }
        }
        return notVisitedNeighbors;
    }

    protected List<Point> getAllPoints(int arrayWidth, int arrayHeight) {
        List<Point> notVisitedPoints = new ArrayList<>();
        for (int i = 0; i < arrayWidth; i++) {
            for (int j = 0; j < arrayHeight; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    notVisitedPoints.add(new Point(i, j));
                }
            }
        }
        return notVisitedPoints;
    }
}
