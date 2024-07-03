package ch.sbb.life;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import static ch.sbb.life.CellPosition.pos;
import static ch.sbb.life.CellStatus.ALIVE;
import static ch.sbb.life.CellStatus.DEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorldTest {
    @Test
    void a_living_cell_without_any_living_neighbour_dies() {
        // given
        Set<CellPosition> population = new HashSet<>();
        population.add(pos(4, 4));
        World world = new World(population, 6, 6);

        // when
        World result = world.step();

        // then
        assertEquals(result, new World(Set.of(), 6, 6));
    }


    @Test
    void only_four_neighbours() {
        // given
        Set<CellPosition> population = new HashSet<>();
        population.add(pos(0, 0));
        population.add(pos(0, 1));
        population.add(pos(1, 0));
        World world = new World(population, 2, 2);
        // when
        World result = world.step();

        // then
        Set<CellPosition> expectedPopulation = new HashSet<>();
        expectedPopulation.add(pos(0, 0));
        expectedPopulation.add(pos(0, 1));
        expectedPopulation.add(pos(1, 0));
        expectedPopulation.add(pos(1, 1));
        World expected = new World(expectedPopulation, 2, 2);

        assertEquals(expected, result);
    }

    @Test
    void a_dead_cell_with_three_living_neighbour_lives() {
        // given
        Set<CellPosition> population = new HashSet<>();
        population.add(pos(2, 2));
        population.add(pos(3, 2));
        population.add(pos(4, 2));
        World world = new World(population, 6, 6);
        // when
        World result = world.step();

        // then
        Set<CellPosition> expectedPopulation = new HashSet<>();
        expectedPopulation.add(pos(3, 1));
        expectedPopulation.add(pos(3, 2));
        expectedPopulation.add(pos(3, 3));
        World expected = new World(expectedPopulation, 6, 6);

        assertEquals(expected, result);
    }


    @Test
    void a_dead_cell_with_three_living_neighbour_lives_small_world() {
        // given
        Set<CellPosition> population = new HashSet<>();
        population.add(pos(0, 0));
        population.add(pos(1, 0));
        population.add(pos(2, 0));
        World world = new World(population, 3, 2);
        // when
        World result = world.step();

        // then
        Set<CellPosition> expectedPopulation = new HashSet<>();
        expectedPopulation.add(pos(1, 0));
        expectedPopulation.add(pos(1, 1));
        World expected = new World(expectedPopulation, 3, 2);

        assertEquals(result, expected);
    }
}