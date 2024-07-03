package ch.sbb.life;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
                .mapToObj(x -> IntStream.range(0, height).mapToObj(y -> activeCellPos(x, y)).filter(Optional::isPresent).map(Optional::get))
                .flatMap(stream -> stream).collect(Collectors.toSet());
        return new World(nextActiveCells, width, height);
    }

    private Optional<CellPosition> activeCellPos(int x, int y) {
        CellPosition pos = CellPosition.pos(x, y);
        List<CellStatus> neighbours = pos.neighbours()
                .stream()
                .map(neighbour -> neighbour.toStatus(aliveCells))
                .collect(Collectors.toList());
        return pos.toStatus(aliveCells)
                .nextStatus(neighbours)
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
