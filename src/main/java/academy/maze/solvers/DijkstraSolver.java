package academy.maze.solvers;

import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/** Алгоритм Дейкстры. Находит кратчайший путь с возможностью задавать разные веса, используя приоритетную очередь. */
public class DijkstraSolver extends Solver implements ISolver {

    // если флаг для весов не передается, то устанавливается false и по умолчанию вес
    // принимается за 1.
    @Override
    public Path solve(Maze maze, Point start, Point end) {
        return solve(maze, start, end, false);
    }

    public Path solve(Maze maze, Point start, Point end, boolean useWeights) {
        // расстояния
        int[][] distances = prepareMaze(maze, start);
        // предки
        Point[][] previous = new Point[distances.length][distances[0].length];
        // очередь для вершин с приоритетом по расстоянию
        PriorityQueue<int[]> pointsWithDistance = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        distances[start.x()][start.y()] = 0;
        pointsWithDistance.offer(new int[] {0, start.x(), start.y()});
        previous[start.x()][start.y()] = null;
        while (!pointsWithDistance.isEmpty()) {
            // элемент с минимальным расстоянием
            int[] current = pointsWithDistance.poll();
            int distance = current[0];
            int x = current[1];
            int y = current[2];

            if (distance > distances[x][y]) {
                continue;
            }

            for (int[] direction : directions) {
                Point neighbour = new Point(x + direction[0], y + direction[1]);
                if (!isAPath(neighbour, maze)) {
                    continue;
                }

                // обновление расстояний, если новое расстояние < записанного в матрице
                int newDistance = distances[x][y] + getWeight(useWeights);
                if (newDistance < distances[neighbour.x()][neighbour.y()]) {
                    distances[neighbour.x()][neighbour.y()] = newDistance;
                    previous[neighbour.x()][neighbour.y()] = new Point(x, y);
                    pointsWithDistance.offer(new int[] {newDistance, neighbour.x(), neighbour.y()});
                }
            }
        }
        if (distances[end.x()][end.y()] == notReached) {
            return new Path(new Point[0]);
        }
        List<Point> path = new ArrayList<>();
        Point current = end;
        while (current != null) {
            path.add(current);
            current = previous[current.x()][current.y()];
        }
        Collections.reverse(path);
        return new Path(path.toArray(new Point[0]));
    }
}
