package academy;

import static academy.maze.dto.CellType.PATH;
import static academy.maze.dto.CellType.WALL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.dto.Path;
import academy.maze.dto.Point;
import academy.maze.solvers.AStarSolver;
import academy.maze.solvers.DijkstraSolver;
import academy.maze.solvers.WaveSolver;
import org.junit.jupiter.api.Test;

/** Тесты для проверки решения лабиринтов разными алгоритмами. */
public class SolversTests {

    @Test
    void testWaveSolver_FindPath() {
        WaveSolver solver = new WaveSolver();
        Maze maze = createSimpleMaze();
        Point start = new Point(1, 1);
        Point end = new Point(3, 3);

        Path path = solver.solve(maze, start, end);

        assertNotNull(path);
        assertTrue(path.points().length > 0, "Path should not be empty");
        assertEquals(start, path.points()[0], "Path should start at start point");
        assertEquals(end, path.points()[path.points().length - 1], "Path should end at end point");
    }

    @Test
    void testDijkstraSolver_OptimalPath() {
        DijkstraSolver solver = new DijkstraSolver();
        Maze maze = createMultiPathMaze();
        Point start = new Point(1, 1);
        Point end = new Point(5, 5);

        Path path = solver.solve(maze, start, end);

        assertTrue(path.points().length > 0);
    }

    @Test
    void testAStarSolver_NoPath() {
        AStarSolver solver = new AStarSolver();
        Maze maze = createImpossibleMaze();
        Point start = new Point(1, 1);
        Point end = new Point(3, 3);

        Path path = solver.solve(maze, start, end);

        assertEquals(0, path.points().length);
    }

    @Test
    void testSolvers_InvalidPoints() {
        WaveSolver solver = new WaveSolver();
        Maze maze = createSimpleMaze();

        assertThrows(Exception.class, () -> solver.solve(maze, new Point(-1, -1), new Point(1, 1)));
    }

    private Maze createSimpleMaze() {
        CellType[][] cells = {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PATH, PATH, PATH, WALL},
            {WALL, WALL, WALL, PATH, WALL},
            {WALL, PATH, PATH, PATH, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };
        return new Maze(cells);
    }

    private Maze createImpossibleMaze() {
        CellType[][] cells = {
            {WALL, WALL, WALL, WALL, WALL},
            {WALL, PATH, WALL, PATH, WALL},
            {WALL, WALL, WALL, PATH, WALL},
            {WALL, PATH, WALL, PATH, WALL},
            {WALL, WALL, WALL, WALL, WALL}
        };
        return new Maze(cells);
    }

    private Maze createMultiPathMaze() {
        CellType[][] cells = {
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {WALL, PATH, PATH, PATH, PATH, PATH, WALL},
            {WALL, WALL, WALL, PATH, WALL, PATH, WALL},
            {WALL, PATH, PATH, PATH, WALL, PATH, WALL},
            {WALL, PATH, WALL, PATH, PATH, PATH, WALL},
            {WALL, PATH, PATH, PATH, WALL, PATH, WALL},
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL}
        };
        return new Maze(cells);
    }
}
