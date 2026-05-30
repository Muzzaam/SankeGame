
public class Coordinate{

    public int row, col;

    public Coordinate(int row, int col) {


        this.row = row;
        this.col = col;
    }

    public Coordinate(String point, String split) {

        String[] coords = point.split(split);
        this.row = Integer.parseInt(coords[1]);
        this.col = Integer.parseInt(coords[0]);
    }


}