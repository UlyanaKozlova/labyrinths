package academy.maze.generators;

import academy.maze.dto.Maze;

/** Генератор лабиринта */
public interface IGenerator {

    /**
     * Генерирует лабиринт.
     *
     * @param height ширина лабиринта.
     * @param width высота лабиринта.
     * @return лабиринт
     * @throws IllegalArgumentException если невозможно сгенерировать лабиринт.
     */
    Maze generate(int height, int width);
}
