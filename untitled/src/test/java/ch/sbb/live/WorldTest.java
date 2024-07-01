package ch.sbb.live;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static ch.sbb.live.CellPosition.pos;
import static ch.sbb.live.CellStatus.ALIVE;
import static ch.sbb.live.CellStatus.DEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {
    @Test
    void a_living_cell_without_any_living_neighbour_dies() {
        // given
        Map<CellPosition, CellStatus> population = population(6, 6, DEAD);
        population.put(pos(4, 4), ALIVE);
        World world = new World(population);

        // when
        World result = world.step();

        // then
        assertEquals(result, deadWorld(6, 6));
    }

    @Test
    void a_dead_cell_with_three_living_neighbour_lives() {
        // given
        Map<CellPosition, CellStatus> population = population(6, 6, DEAD);
        population.put(pos(2, 2), ALIVE);
        population.put(pos(3, 2), ALIVE);
        population.put(pos(4, 2), ALIVE);
        World world = new World(population);
        // when
        World result = world.step();

        // then
        Map<CellPosition, CellStatus> expectedPopulation = population(6, 6, DEAD);
        expectedPopulation.put(pos(3, 1), ALIVE);
        expectedPopulation.put(pos(3, 2), ALIVE);
        expectedPopulation.put(pos(3, 3), ALIVE);
        World expected = new World(expectedPopulation);

        assertEquals(expected, result);
    }

    @Test
    void only_four_neighbours() {
        // given
        Map<CellPosition, CellStatus> population = population(2, 2, DEAD);
        population.put(pos(0, 0), ALIVE);
        population.put(pos(0, 1), ALIVE);
        population.put(pos(1, 0), ALIVE);
        World world = new World(population);
        // when
        World result = world.step();

        // then
        Map<CellPosition, CellStatus> expectedPopulation = population(2, 2, ALIVE);
        World expected = new World(expectedPopulation);

        assertEquals(expected, result);
    }

    @Test
    void a_dead_cell_with_three_living_neighbour_lives_small_world() {
        // given
        Map<CellPosition, CellStatus> population = population(3, 2, DEAD);
        population.put(pos(0, 0), ALIVE);
        population.put(pos(1, 0), ALIVE);
        population.put(pos(2, 0), ALIVE);
        World world = new World(population);
        // when
        World result = world.step();

        // then
        Map<CellPosition, CellStatus> expectedPopulation = population(3, 2, DEAD);
        expectedPopulation.put(pos(1, 0), ALIVE);
        expectedPopulation.put(pos(1, 1), ALIVE);
        World expected = new World(expectedPopulation);

        assertEquals(result, expected);
    }

    private World deadWorld(int width, int height) {
        return new World(population(width, height, DEAD));
    }

    private static Map<CellPosition, CellStatus> population(int width, int height, CellStatus cellStatus) {
        Map<CellPosition, CellStatus> population = new HashMap<>();
        IntStream.range(0, width).forEach(x -> IntStream.range(0, height).forEach(y -> population.put(pos(x, y), cellStatus)));
        return population;
    }
}