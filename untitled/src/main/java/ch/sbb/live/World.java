package ch.sbb.live;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class World {
    private final Map<CellPosition, CellStatus> population;

    public World(Map<CellPosition, CellStatus> population) {
        this.population = population;
    }

    public World step() {
        return new World(population.entrySet()
                .stream()
                .map(entry -> Map.entry(entry.getKey(), entry.getValue().nextStatus(neighbourCellStatus(entry.getKey()))))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    private List<CellStatus> neighbourCellStatus(CellPosition position) {
        return position.neighbours()
                .stream()
                .map(population::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(population, world.population);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(population);
    }

    @Override
    public String toString() {
        return population.toString();
    }
}
