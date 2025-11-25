package academy.data.processors.factories;

import academy.maze.solvers.AStarSolver;
import academy.maze.solvers.DijkstraSolver;
import academy.maze.solvers.ISolver;
import academy.maze.solvers.WaveSolver;

public class SolverFactory {
    public static ISolver create(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Не указан алгоритм!");
        }
        return switch (type.toLowerCase()) {
            case "astar" -> new AStarSolver();
            case "dijkstra" -> new DijkstraSolver();
            case "wave" -> new WaveSolver();
            default -> throw new IllegalArgumentException("Несуществующий алгоритм!");
        };
    }
}
