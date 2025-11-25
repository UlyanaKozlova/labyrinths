package academy.maze.generators.realisations;

import academy.maze.dto.CellType;
import academy.maze.dto.Maze;
import academy.maze.generators.Generator;
import academy.maze.generators.IGenerator;
import java.util.Random;

/**
 * Алгоритм Бинарного дерева. 1. выбирается начальная точка. 2. пока не пройдены все точки, для текущей точки случайно
 * выбираем убрать стену сверху или справа от нее.
 */
public class BinaryTreeGenerator extends Generator implements IGenerator {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        int arrayWidth = height * 2 + 1;
        int arrayHeight = width * 2 + 1;
        CellType[][] result = generateArray(arrayWidth, arrayHeight);
        for (int i = 1; i < arrayWidth; i += 2) {
            for (int j = 1; j < arrayHeight; j += 2) {
                if (i != 1 || j != arrayHeight - 2) {
                    if (i == 1) {
                        result[i][j + 1] = CellType.PATH;
                    } else if (j == arrayHeight - 2) {
                        result[i - 1][j] = CellType.PATH;
                    } else if (random.nextInt(2) == 0) {
                        result[i - 1][j] = CellType.PATH;
                    } else {
                        result[i][j + 1] = CellType.PATH;
                    }
                }
            }
        }
        return new Maze(result);
    }
}
