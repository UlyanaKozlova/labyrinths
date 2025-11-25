package academy.data.processors.factories;

import academy.maze.generators.*;
import academy.maze.generators.realisations.BinaryTreeGenerator;
import academy.maze.generators.realisations.DFSGenerator;
import academy.maze.generators.realisations.OldosBroderGenerator;
import academy.maze.generators.realisations.PrimsGenerator;

public class GeneratorFactory {
    public static IGenerator create(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Генератор не введен!");
        }
        return switch (type.toLowerCase()) {
            case "dfs" -> new DFSGenerator();
            case "prim" -> new PrimsGenerator();
            case "oldosbroder" -> new OldosBroderGenerator();
            case "binarytree" -> new BinaryTreeGenerator();
            default -> throw new IllegalArgumentException("Несуществующий генератор!");
        };
    }
}
