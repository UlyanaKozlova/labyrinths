package academy.maze.solvers;

import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** A* алгоритм. */
public class AStarSolver extends Solver implements ISolver {

    // если флаг для весов не передается, то устанавливается false и по умолчанию вес
    // принимается за 1.
    @Override
    public Path solve(Maze maze, Point start, Point end) {
        return solve(maze, start, end, false);
    }

    public Path solve(Maze maze, Point start, Point end, boolean useWeights) {
        Map<Point, Integer> distanceFromStart = new HashMap<>();
        Map<Point, Integer> allDistance = new HashMap<>();
        Map<Point, Point> previous = new HashMap<>();

        Map<Point, Integer> notVisited = new HashMap<>();
        Map<Point, Boolean> visited = new HashMap<>();

        distanceFromStart.put(start, startValue);
        allDistance.put(start, getDistance(start, end));
        notVisited.put(start, allDistance.get(start));

        while (!notVisited.isEmpty()) {
            // не посещенная точка с минимальным общим расстоянием
            Point currentPoint = getMinDistance(notVisited);

            if (currentPoint.equals(end)) {
                return getPath(previous, currentPoint);
            }
            notVisited.remove(currentPoint);
            visited.put(currentPoint, true);

            for (int[] direction : directions) {
                Point neighbour = new Point(currentPoint.x() + direction[0], currentPoint.y() + direction[1]);
                if (!isAPath(neighbour, maze) || visited.containsKey(neighbour)) {
                    continue;
                }
                // новое расстояние до этой точки через текущую
                int g = distanceFromStart.get(currentPoint) + getWeight(useWeights);
                if (!notVisited.containsKey(neighbour) || g < distanceFromStart.getOrDefault(neighbour, notReached)) {
                    previous.put(neighbour, currentPoint);
                    distanceFromStart.put(neighbour, g);
                    allDistance.put(neighbour, g + getDistance(neighbour, end));

                    if (!notVisited.containsKey(neighbour)) {
                        notVisited.put(neighbour, allDistance.get(neighbour));
                    }
                }
            }
        }
        return new Path(new Point[0]);
    }

    private int getDistance(Point a, Point b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    private Path getPath(Map<Point, Point> cameFrom, Point currentPoint) {
        List<Point> path = new ArrayList<>();
        path.add(currentPoint);

        while (cameFrom.containsKey(currentPoint)) {
            currentPoint = cameFrom.get(currentPoint);
            path.add(currentPoint);
        }
        Collections.reverse(path);
        return new Path(path.toArray(new Point[0]));
    }

    // точка с минимальным расстоянием от начальной
    private Point getMinDistance(Map<Point, Integer> map) {
        return map.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("Empty map"))
                .getKey();
    }
}
