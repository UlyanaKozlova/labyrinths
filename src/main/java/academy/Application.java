package academy;

import academy.application.GeneratorCommand;
import academy.application.SolverCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "maze-app",
        version = "Maze 1.0",
        mixinStandardHelpOptions = true,
        description = "Maze generator and solver CLI application.",
        subcommands = {GeneratorCommand.class, SolverCommand.class})
public class Application implements Runnable {
    public static void main(String[] args) {
        System.setProperty("line.separator", "\n");
        int exitCode = new CommandLine(new Application()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        CommandLine.usage(this, System.out);
    }
}
