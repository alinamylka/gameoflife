package ch.sbb.life;


import java.util.List;

public enum CellStatus {
    ALIVE {
        @Override
        protected CellStatus nextStatus(long aliveNeighbours) {
            return aliveNeighbours == 2 || aliveNeighbours == 3 ? ALIVE : DEAD;
        }
    }, DEAD {
        @Override
        protected CellStatus nextStatus(long aliveNeighbours) {
            return aliveNeighbours == 3 ? ALIVE : DEAD;
        }
    };

    protected abstract CellStatus nextStatus(long aliveNeighbours);

    public CellStatus nextStatus(List<CellStatus> neighbours) {
        return nextStatus(neighbours.stream().filter(status -> status == ALIVE).count());
    }

}
