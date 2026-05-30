import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Drawing {

    public static void drawSnake(String snake, char[][] board, int num, boolean isZombie) {

        char x = 'x';


        String[] snakePoints = snake.split(" ");

        int  SnakeLength = snakePoints.length;

        for (int i = 0; i < SnakeLength - 1; i++) {
            String[] first = snakePoints[i].split(",");
            String[] second = snakePoints[i + 1].split(",");

            int x1 = Integer.parseInt(first[0]);
            int y1 = Integer.parseInt(first[1]);
            int x2 = Integer.parseInt(second[0]);
            int y2 = Integer.parseInt(second[1]);


            drawLine(board, x1, y1, x2, y2, x);
        }
    }


    public static void drawSnakeFloodFIll(String snake, int[][] board, int num, boolean isZombie) {




        String[] snakePoints = snake.split(" ");

        int  SnakeLength = snakePoints.length;

        for (int i = 0; i < SnakeLength - 1; i++) {
            String[] first = snakePoints[i].split(",");
            String[] second = snakePoints[i + 1].split(",");

            int x1 = Integer.parseInt(first[0]);
            int y1 = Integer.parseInt(first[1]);
            int x2 = Integer.parseInt(second[0]);
            int y2 = Integer.parseInt(second[1]);


            drawLineFLoodFill(board, x1, y1, x2, y2, num);
        }
    }

    public static void drawLineFLoodFill(int[][] board, int x1, int y1, int x2, int y2, int num) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                board[j][i] = num;
            }
        }
    }

    public static void drawLine(char[][] board, int x1, int y1, int x2, int y2, char index) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                board[j][i] = index;
            }
        }
    }

    public static void drawObstacle(char[][] board, int x1, int y1, int x2, int y2) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                board[j][i] = 'x';
            }
        }
    }

    public static void drawObstacleFloodFill(int[][] board, int x1, int y1, int x2, int y2) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);


        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                board[j][i] = 40;
            }
        }
    }

    public static void removeHelmets (ArrayList<Snake> Snakes, int mySnakeNum, char[][] myMatrix ){

        int[] xMove = {1, 0, -1, 0};
        int[] yMove = {0, -1, 0, 1};

        for (Snake snake : Snakes) {
            if (snake.id != mySnakeNum) {
                for (int k = 0; k < 4; k++) {
                    int helmetRow = snake.head.row + xMove[k];
                    int helmetCol = snake.head.col + yMove[k];

                    if (helmetRow >= 0 && helmetRow < 50 && helmetCol >= 0 && helmetCol < 50 && myMatrix[helmetRow][helmetCol] == 'k') {
                        myMatrix[helmetRow][helmetCol] = '.';
                    }
                }
            }
        }

    }



}