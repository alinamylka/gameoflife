package ch.sbb.life;

import org.junit.jupiter.api.Test;

import static ch.sbb.life.CellStatus.ALIVE;
import static ch.sbb.life.CellStatus.DEAD;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    public void livingCell() {
        assertSame(ALIVE.nextStatus(0), DEAD);
        assertSame(ALIVE.nextStatus(1), DEAD);
        assertSame(ALIVE.nextStatus(2), ALIVE);
        assertSame(ALIVE.nextStatus(3), ALIVE);
        assertSame(ALIVE.nextStatus(4), DEAD);
        assertSame(ALIVE.nextStatus(5), DEAD);
        assertSame(ALIVE.nextStatus(6), DEAD);
        assertSame(ALIVE.nextStatus(7), DEAD);
        assertSame(ALIVE.nextStatus(8), DEAD);
    }


    @Test
    public void deadCell() {
        assertSame(DEAD.nextStatus(1), DEAD);
        assertSame(DEAD.nextStatus(2), DEAD);
        assertSame(DEAD.nextStatus(3), ALIVE);
        assertSame(DEAD.nextStatus(4), DEAD);
        assertSame(DEAD.nextStatus(5), DEAD);
        assertSame(DEAD.nextStatus(6), DEAD);
        assertSame(DEAD.nextStatus(7), DEAD);
        assertSame(DEAD.nextStatus(8), DEAD);
    }
}