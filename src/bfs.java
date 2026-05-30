import java.util.LinkedList;
import java.util.Queue;
import java.util.HashMap;
import java.util.Map;

public class bfs {

    private final char[][] maze;
    private final boolean[][] visited;
    private final Map<Integer, Integer> parent;
    private final int m;
    private final int n;
    private final Coordinate source;
    private final Coordinate goal;
    private final int[] xMove = {1, 0, -1, 0};
    private final int[] yMove = {0, -1, 0, 1};

    public bfs(char[][] maze, Coordinate source, Coordinate goal) {
        this.maze = maze;
        this.source = source;
        this.goal = goal;
        this.m = 50;
        this.n = 50;
        this.visited = new boolean[50][50];
        this.parent = new HashMap<>();
    }

    public Coordinate bfs() {
        Queue<Coordinate> q = new LinkedList<>();
        q.add(source);
        visited[source.row][source.col] = true;

        while (!q.isEmpty()) {
            Coordinate current = q.poll();
            int currentRow = current.row;
            int currentCol = current.col;

            if (currentRow == goal.row && currentCol == goal.col) {
                return current;
            }

            for (int i = 0; i < 4; i++) {
                int newRow = currentRow + xMove[i];
                int newCol = currentCol + yMove[i];

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol] && maze[newRow][newCol] != 'x' && maze[newRow][newCol] != 'k') {
                    visited[newRow][newCol] = true;
                    parent.put(newRow * n + newCol, currentRow * n + currentCol);
                    q.add(new Coordinate(newRow, newCol));
                }
            }
        }
        return null;
    }

    public int getNextMove() {
        Coordinate next = bfs();
        if (next == null) return -1;

        // Backtrack to find the next move
        int currentRow = next.row;
        int currentCol = next.col;

        while (parent.containsKey(currentRow * n + currentCol)) {

            int parentIndex = parent.get(currentRow * n + currentCol);
            int parentRow = parentIndex / n;
            int parentCol = parentIndex % n;


            if (parentRow == source.row && parentCol == source.col) {
                break;
            }


            currentRow = parentRow;
            currentCol = parentCol;
        }



        if (currentRow == source.row - 1){
            return 0; // Up
        }

        if (currentRow == source.row + 1){
            return 1; // Down
        }


        if (currentRow == source.row) {

            if (currentCol == source.col - 1) {
                return 2; // Left
            }
            if (currentCol == source.col + 1) {
                return 3; // Right
            }

        }


        return -1;
    }
}
