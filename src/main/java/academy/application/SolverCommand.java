package academy.application;

import academy.MazeService;
import picocli.CommandLine;

@CommandLine.Command(
        name = "solve",
        description = "Solve a maze with specified algorithm and points.",
        mixinStandardHelpOptions = true)
public class SolverCommand implements Runnable {
    @CommandLine.Option(
            names = {"--algorithm", "-a"},
            description = "Solver algorithm: astar, dijkstra, wave")
    String algorithm;

    @CommandLine.Option(
            names = {"--file"},
            description = "Input maze file name")
    String inputFile;

    @CommandLine.Option(
            names = {"--start", "-s"},
            description = "Start point in format 'x,y' 0<x<2height+1, 0<y<2width+1")
    String start;

    @CommandLine.Option(
            names = {"--end", "-e"},
            description = "End point in format 'x,y' 0<x<height, 0<y<width")
    String end;

    @CommandLine.Option(
            names = {"--output", "-o"},
            description = "Output file name")
    String outputFile;

    @CommandLine.Option(
            names = {"--unicode", "-u"},
            description = "How string is displayed (true - unicode, false - #)",
            defaultValue = "false")
    boolean unicode;

    @CommandLine.Option(
            names = {"--weights", "-w"},
            description = "Use random weights to show finding of optimal path for astar and dijkstra",
            defaultValue = "false")
    boolean randomWeights;

    @Override
    public void run() {

        try {
            if (randomWeights && "wave".equals(algorithm)) {
                throw new IllegalArgumentException("Волновой алгоритм не поддерживает случайные веса!");
            }
            MazeService.solveMaze(algorithm, inputFile, start, end, outputFile, unicode, randomWeights);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
