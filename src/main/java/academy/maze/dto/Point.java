package academy.maze.dto;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Координаты точки
 *
 * @param x
 * @param y
 */
public record Point(int x, int y) {
    @Override
    public @NotNull String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point(int x1, int y1))) return false;
        return x == x1 && y == y1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
