package academy.application;

import academy.MazeService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "generate",
        description = "Generate a maze with specified algorithm and dimensions.",
        mixinStandardHelpOptions = true)
public class GeneratorCommand implements Runnable {
    @CommandLine.Option(
            names = {"--algorithm", "-a"},
            description = "Generator algorithm: dfs, prim, oldosbroder, binarytree")
    String algorithm;

    @CommandLine.Option(
            names = {"--height", "-h"},
            description = "Maze height")
    int height;

    @CommandLine.Option(
            names = {"--width", "-w"},
            description = "Maze width")
    int width;

    @CommandLine.Option(
            names = {"--output", "-o"},
            description = "Output file name")
    String fileName;

    @CommandLine.Option(
            names = {"--unicode", "-u"},
            description = "How string is displayed (true - unicode, false - #)",
            defaultValue = "false")
    boolean unicode;

    public void run() {

        try {
            MazeService.generateMaze(algorithm, height, width, fileName, unicode);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
