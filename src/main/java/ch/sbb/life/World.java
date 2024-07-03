package ch.sbb.life;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ch.sbb.life.CellPosition.pos;

public class World {
    private final Set<CellPosition> aliveCells;
    private final int width;
    private final int height;

    public World(Set<CellPosition> alivePopulation, int width, int height) {
        this.aliveCells = alivePopulation;
        this.width = width;
        this.height = height;
    }

    public World step() {
        final Set<CellPosition> nextActiveCells = IntStream.range(0, width)
                .mapToObj(x -> IntStream.range(0, height).mapToObj(y -> activeCellPos(pos(x, y))).filter(Optional::isPresent).map(Optional::get))
                .flatMap(stream -> stream).collect(Collectors.toSet());
        return new World(nextActiveCells, width, height);
    }

    private Optional<CellPosition> activeCellPos(CellPosition pos) {
        return pos.toStatus(aliveCells)
                .nextStatus(pos.neighbourStatus(aliveCells))
                .activeCellPosition(pos);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(aliveCells, world.aliveCells);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(aliveCells);
    }

    @Override
    public String toString() {
        return aliveCells.toString();
    }
}
