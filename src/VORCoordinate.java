public class VORCoordinate extends Coordinate {

    public int snakeId;
    public boolean isZombie;
    public int distance;

    public VORCoordinate(int row, int col, int snakeId, boolean isZombie, int distance) {
        super(row, col);

        this.snakeId = snakeId;
        this.isZombie = isZombie;
        this.distance = distance;
    }
}
