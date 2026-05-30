import java.util.*;

public class VOR {

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int Voronoi(int[][] boardVoronoi, ArrayList<Snake> snakes, int mySnakeIndex, int zombieExpansionLimit) {
        Queue<VORCoordinate> queue = new LinkedList<>();
        int myCellCount = 0;
        int unclaimedCells = 0;

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                if (boardVoronoi[i][j] == -1) {
                    unclaimedCells++;
                }
            }
        }

        for (Snake snake : snakes) {
            queue.add(new VORCoordinate(snake.head.row, snake.head.col, snake.id, snake.isZombie, 0));
        }

        while (!queue.isEmpty() && unclaimedCells > 0) {
            VORCoordinate currentNode = queue.poll();

            if (currentNode.isZombie && currentNode.distance >= zombieExpansionLimit) {
                continue;
            }

            for (int[] direction : directions) {
                int newRow = currentNode.row + direction[0];
                int newCol = currentNode.col + direction[1];

                if (newRow < 0 || newRow >= boardVoronoi.length || newCol < 0 || newCol >= boardVoronoi[0].length) {
                    continue;
                }

                if (boardVoronoi[newRow][newCol] == -1) {
                    boardVoronoi[newRow][newCol] = currentNode.snakeId;
                    unclaimedCells--;

                    if (currentNode.snakeId == mySnakeIndex) {
                        myCellCount++;
                    }

                    queue.add(new VORCoordinate(newRow, newCol, currentNode.snakeId, currentNode.isZombie, currentNode.distance + 1));
                }
            }
        }

        int zombieId = 11;
        if (unclaimedCells > 0) {
            for (int i = 0; i < boardVoronoi.length; i++) {
                for (int j = 0; j < boardVoronoi[0].length; j++) {
                    if (boardVoronoi[i][j] == -1) {
                        boardVoronoi[i][j] = zombieId;
                    }
                }
            }
        }

        return myCellCount;
    }

    public static void resetBoard(int[][] originalBoard, int[][] targetBoard) {
        for (int i = 0; i < originalBoard.length; i++) {
            System.arraycopy(originalBoard[i], 0, targetBoard[i], 0, originalBoard[i].length);
        }
    }



}
