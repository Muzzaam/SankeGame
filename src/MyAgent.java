import java.io.*;
import java.util.*;


import za.ac.wits.snake.DevelopmentAgent;

public class MyAgent extends DevelopmentAgent {

    ArrayList<Coordinate> myPreviousHeads = new ArrayList<>();

    public static void main(String[] args) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            String initString = br.readLine();
            String[] temp = initString.split(" ");
            int nSnakes = Integer.parseInt(temp[0]);

            int[] xMove = {1, 0, -1, 0};
            int[] yMove = {0, -1, 0, 1};


            int[] rowOffsets = {-1, 1, 0, 0};
            int[] colOffsets = {0, 0, -1, 1};



            int[] xMoveAll = {1, 1, 0, -1, -1, -1, 0, 1};
            int[] yMoveAll = {0, 1, 1, 1, 0, -1, -1, -1};



            while (true) {



                char[][] myMatrix = new char[50][50];
                int[][] floodFillBoard = new int[50][50];
                int[][] floodFillBoardCopy = new int[50][50];
                char[][] mySnakeBoard = new char[50][50];
                int[][] appleFillBoard = new int[50][50];



                for (int i = 0; i < 50; i++) {
                    for (int j = 0; j < 50; j++) {
                        floodFillBoard[i][j] = -1;
                        myMatrix[i][j] = '.';
                        mySnakeBoard[i][j] = '.';

                    }
                }

                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                ArrayList<Snake> Snakes = new ArrayList<>();
                // Process apple
                Coordinate appleCoordinate = new Coordinate(line, " ");
                int appleRow = appleCoordinate.row;
                int appleCol = appleCoordinate.col;
                myMatrix[appleRow][appleCol] = 'A';


                // Process obstacles
                int nObstacles = 3;
                for (int obstacle = 0; obstacle < nObstacles; obstacle++) {
                    String obs = br.readLine();
                    String[] obsCoords = obs.split(" ");
                    String[] obsStart = obsCoords[0].split(",");
                    String[] obsEnd = obsCoords[obsCoords.length - 1].split(",");

                    int obsStartX = Integer.parseInt(obsStart[0]);
                    int obsStartY = Integer.parseInt(obsStart[1]);
                    int obsEndX = Integer.parseInt(obsEnd[0]);
                    int obsEndY = Integer.parseInt(obsEnd[1]);

                    Drawing.drawObstacle(myMatrix, obsStartX, obsStartY, obsEndX, obsEndY);

                    Drawing.drawObstacleFloodFill(floodFillBoard, obsStartX, obsStartY, obsEndX, obsEndY);
                }


                Coordinate zombieHeadCoords;

                // Process zombies
                int nZombies = 3;
                for (int zombie = 0; zombie < nZombies; zombie++) {
                    String zom = br.readLine();
                    String[] singleZombie = zom.split("\\s");
                    String[] zombieHeadCoordinate = singleZombie[0].split(",");
                    int zombieHeadX = Integer.parseInt(zombieHeadCoordinate[1]);
                    int zombieHeadY = Integer.parseInt(zombieHeadCoordinate[0]);

                    zombieHeadCoords = new Coordinate(zombieHeadX, zombieHeadY);

                    for (int i = 0; i < 4; i++) {
                        int zombieHelmetRow = zombieHeadCoords.row + xMove[i];
                        int zombieHelmetCol = zombieHeadCoords.col + yMove[i];

                        if (zombieHelmetRow >= 0 && zombieHelmetRow < 50 && zombieHelmetCol >= 0 && zombieHelmetCol < 50 && myMatrix[zombieHelmetRow][zombieHelmetCol] != 'x') {
                            myMatrix[zombieHelmetRow][zombieHelmetCol] = 'k';

                        }
                    }


                    Drawing.drawSnake(zom, myMatrix, zombie, true);

                    Drawing.drawSnakeFloodFIll(zom, floodFillBoard, 11 + 32, true);
                    Snakes.add(new Snake(11, zombieHeadCoords, true));


                }

                // Collect all snakes

                int length = 0;
                int mySnakeNum = Integer.parseInt(br.readLine());



                Coordinate mySnakeHead = null;

                Snake mySnake = null;
                Coordinate mySnakeTail = null;

                boolean state;
                boolean mystate = false;

                for (int i = 0; i < nSnakes; i++) {
                    String snakeLine = br.readLine();
                    String[] singleSnake = snakeLine.split(" ");
                    state = singleSnake[0].equals("alive");


                    if (state) {
                        Coordinate snakeHead;
                        StringBuilder snakeToPrint = new StringBuilder();

                        if (i == mySnakeNum) {
                            mystate = true;
                            length = Integer.parseInt(singleSnake[1]);
                            String[] myHeadCoords = singleSnake[3].split(",");
                            int mySnakeHeadX = Integer.parseInt(myHeadCoords[1]);
                            int mySnakeHeadY = Integer.parseInt(myHeadCoords[0]);
                            mySnakeHead = new Coordinate(mySnakeHeadX, mySnakeHeadY);


                            String[] myTailCoords = singleSnake[singleSnake.length - 1].split(",");
                            int mySnakeTailX = Integer.parseInt(myTailCoords[1]);
                            int mySnakeTailY = Integer.parseInt(myTailCoords[0]);
                             mySnakeTail = new Coordinate(mySnakeTailX, mySnakeTailY);



                        } else {
                            String[] snakeHeadCoords = singleSnake[3].split(",");
                            int snakeHeadX = Integer.parseInt(snakeHeadCoords[1]);
                            int snakeHeadY = Integer.parseInt(snakeHeadCoords[0]);
                            snakeHead = new Coordinate(snakeHeadX, snakeHeadY);


                            for (int k = 0; k < 4; k++) {
                                int helmetRow = snakeHead.row + xMove[k];
                                int helmetCol = snakeHead.col + yMove[k];



                                if (helmetRow >= 0 && helmetRow < 50 && helmetCol >= 0 && helmetCol < 50 && myMatrix[helmetRow][helmetCol] != 'x') {
                                    myMatrix[helmetRow][helmetCol] = 'k';


                                }

                            }


                        }

                        for (int j = 3; j < singleSnake.length; j++) {
                            if (j + 1 == singleSnake.length) {
                                snakeToPrint.append(singleSnake[j]);
                            } else {
                                snakeToPrint.append(singleSnake[j]).append(" ");
                            }
                        }

                        String[] shc = singleSnake[3].split(",");
                        int shx = Integer.parseInt(shc[1]);
                        int shy = Integer.parseInt(shc[0]);
                        Coordinate snakeHeadNew = new Coordinate(shx, shy);


                        Drawing.drawSnake(snakeToPrint.toString(), myMatrix, i, false);
                        Drawing.drawSnakeFloodFIll(snakeToPrint.toString(), floodFillBoard, i + 99, false);


                        if (i != mySnakeNum) {
                            Snakes.add(new Snake(i, snakeHeadNew, false));
                        }

                        if (i == mySnakeNum) {
                            Drawing.drawSnake(snakeToPrint.toString(), mySnakeBoard, i, false);
                            mySnake = new Snake(mySnakeNum, mySnakeHead, false);
                            Snakes.add(mySnake);
                            myPreviousHeads.add(mySnakeHead);
                        }


                    }
                }


                boolean appleHasEnoughSpace = true;


                int surroundingObstacles = 0;

                for (int i = 0; i < 8; i++) {
                    int newRow = appleRow + xMoveAll[i];
                    int newCol = appleCol + yMoveAll[i];

                    if (newRow >= 0 && newRow < 50 && newCol >= 0 && newCol < 50) {
                        if (myMatrix[newRow][newCol] == 'x' || myMatrix[newRow][newCol] == 'k') {
                            surroundingObstacles++;
                        }
                    } else {
                        surroundingObstacles++;
                    }
                }

                if (surroundingObstacles >= 6) {
                    appleHasEnoughSpace = false;
                }





                // Calculate move


                int cell;


                VOR.resetBoard(floodFillBoard,floodFillBoardCopy);
                int currentSpace =  VOR.Voronoi(floodFillBoard, Snakes, mySnakeNum, 2);

                boolean isCloser = floodFillBoard[appleRow][appleCol] == mySnakeNum;

                VOR.resetBoard(floodFillBoardCopy,floodFillBoard);
                VOR.resetBoard(floodFillBoardCopy,appleFillBoard);

                Snake appleSnake = new Snake(69, appleCoordinate, false);
                Snakes.addFirst(appleSnake);

                int appleCount = VOR.Voronoi(appleFillBoard, Snakes, 69, 7);

                Snakes.removeFirst();
                VOR.resetBoard(floodFillBoardCopy , appleFillBoard);



                int move = 0;




                if (mystate) {

                    myMatrix[mySnakeHead.row][mySnakeHead.col] = 'H';
                    myMatrix[mySnakeTail.row][mySnakeTail.col] = 'T';

                    int tmpMove = 0;
                    boolean isLooped = false;



                        bfs tmpSolver = new bfs(myMatrix, mySnakeHead, mySnakeTail);
                        tmpMove = tmpSolver.getNextMove();

                        if(tmpMove == -1){
                            isLooped = true;
                        }



                    myMatrix[mySnakeHead.row][mySnakeHead.col] = 'x';
                    myMatrix[mySnakeTail.row][mySnakeTail.col] = 'x';




                    bfs solver = new bfs(myMatrix, mySnakeHead, appleCoordinate);
                    move = solver.getNextMove();


                    if (isCloser && move != -1 && !isLooped && appleHasEnoughSpace) {


                        System.out.println(move);



                    } else {


                        int bestmove = 0;
                        int largestArea = 0;
                        boolean hasMove = false;


                        for (int i = 0; i < 4; i++) {
                            move = i;

                            int newRow = mySnakeHead.row + rowOffsets[i];
                            int newCol = mySnakeHead.col + colOffsets[i];

                            if (newRow < 50 && newRow >= 0 && newCol < 50 && newCol >= 0) {

                                if (myMatrix[newRow][newCol] == 'x' || myMatrix[newRow][newCol] == 'k') {
                                    continue;
                                }

                                Coordinate originalHead = null;
                                Coordinate newHead = new Coordinate(newRow, newCol);

                                for (Snake snake : Snakes) {
                                    if (snake.id == mySnakeNum) {
                                        originalHead = snake.head;
                                        snake.head = newHead;
                                        break;
                                    }
                                }

                                cell = VOR.Voronoi(floodFillBoard, Snakes, mySnakeNum, 14);

                                if (floodFillBoard[newRow][newCol] == mySnakeNum) {
                                    if (cell > largestArea) {
                                        largestArea = cell;
                                        bestmove = move;
                                        hasMove = true;
                                    }
                                }

                                for (Snake snake : Snakes) {
                                    if (snake.id == mySnakeNum) {
                                        snake.head = originalHead;
                                        break;
                                    }
                                }

                                VOR.resetBoard(floodFillBoardCopy, floodFillBoard);
                            }
                        }

                        move = bestmove;
                        boolean lastHope = false;

                        if (!hasMove) {
                            for (int i = 0; i < 4; i++) {
                                int newRow = mySnakeHead.row + rowOffsets[i];
                                int newCol = mySnakeHead.col + colOffsets[i];

                                if (newRow < 50 && newRow >= 0 && newCol < 50 && newCol >= 0) {
                                    if (myMatrix[newRow][newCol] != 'x' && myMatrix[newRow][newCol] != 'k') {
                                        move = i;
                                        lastHope = true;
                                        break;
                                    }
                                }
                            }

                            if (!lastHope) {
                                Drawing.removeHelmets(Snakes, mySnakeNum, myMatrix);
                                bfs secondTry = new bfs(myMatrix, mySnakeHead, appleCoordinate);
                                move = secondTry.getNextMove();
                                if (move != -1) {
                                    hasMove = true;
                                }


                                if (!hasMove) {
                                    for (int i = 0; i < 4; i++) {
                                        int newRow = mySnakeHead.row + rowOffsets[i];
                                        int newCol = mySnakeHead.col + colOffsets[i];

                                        if (newRow < 50 && newRow >= 0 && newCol < 50 && newCol >= 0) {
                                            if (myMatrix[newRow][newCol] != 'x') {
                                                move = i;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }


                    }


                }



                System.out.println(move);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
