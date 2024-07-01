package ch.sbb.life;

import java.util.Objects;
import java.util.Set;

public class CellPosition {
    private final int x;
    private final int y;

    private CellPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<CellPosition> neighbours() {
        return Set.of(pos(x - 1, y - 1), pos(x, y - 1), pos(x + 1, y - 1),
                pos(x - 1, y), pos(x + 1, y),
                pos(x - 1, y + 1), pos(x, y + 1), pos(x + 1, y + 1));
    }

    public static CellPosition pos(int x, int y) {
        return new CellPosition(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(x=" + x +
                ",y=" + y +
                ')';
    }
}
