package ch.sbb.live;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static ch.sbb.live.CellPosition.pos;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CellPositionTest {
    @Test
    public void testNeighbours() {
        assertEquals(pos(1, 1).neighbours(),
                Set.of(pos(0, 0), pos(1, 0), pos(2, 0),
                        pos(0, 1), pos(2, 1),
                        pos(0, 2), pos(1, 2), pos(2, 2)));
    }
}