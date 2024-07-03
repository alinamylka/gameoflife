package ch.sbb.life;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public enum CellStatus {
    ALIVE {
        @Override
        protected CellStatus nextStatus(long aliveNeighbours) {
            return aliveNeighbours == 2 || aliveNeighbours == 3 ? ALIVE : DEAD;
        }

        public Optional<CellPosition> activeCellPosition(CellPosition pos) {
            return Optional.of(pos);
        }
    }, DEAD {
        @Override
        protected CellStatus nextStatus(long aliveNeighbours) {
            return aliveNeighbours == 3 ? ALIVE : DEAD;
        }

        @Override
        public Optional<CellPosition> activeCellPosition(CellPosition pos) {
            return Optional.empty();
        }
    };

    protected abstract CellStatus nextStatus(long aliveNeighbours);

    public CellStatus nextStatus(List<CellStatus> neighbours) {
        return nextStatus(neighbours.stream().filter(status -> status == ALIVE).count());
    }

    public abstract Optional<CellPosition> activeCellPosition(CellPosition pos);
}
