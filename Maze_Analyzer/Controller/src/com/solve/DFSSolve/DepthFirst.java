package com.solve.DFSSolve;

import com.utility.CombinedReturn;
import com.utility.Coordinates;
import com.utility.Solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class DepthFirst implements Solver {
    public static int itte;
    public static int[][] maze;
    public static Coordinates lastPosition;
    public static Deque<Coordinates> Path = new ArrayDeque();
    public static Coordinates FirstPos;
    public static int[][][] maze3d;

    public DepthFirst(int[][] var1) {
        maze = var1;
        FirstPos = new Coordinates(1, 1);
        int var2 = var1[0].length;
        maze3d = new int[var2][var2][5];
        lastPosition = new Coordinates(0, 0);
        Path.addFirst(FirstPos);
    }

    public CombinedReturn driver(int[][] maze) {
        if (Path.isEmpty()){
            System.out.println();
            System.out.println("No solution found");
            CombinedReturn a = new CombinedReturn(Path,999999999);
            return a;
        }
        else {
            if (maze[(Path.getFirst()).x][(Path.getFirst()).y] == -2) {
                CombinedReturn a = new CombinedReturn(Path, itte);
                return a;
            } else {
                itte++;
                ArrayList var4 = new ArrayList();
                Coordinates currentPosition = Path.getFirst();
                int var6 = 0;
                if (maze3d[currentPosition.x][currentPosition.y][0] != 1) {
                    checkDir(maze, currentPosition, lastPosition, maze3d);
                    driver(maze);
                } else {
                    int var7;
                    for (var7 = 1; var7 < 5; var7++) {
                        if (maze3d[currentPosition.x][currentPosition.y][var7] == 1) {
                            var4.add(var7);
                        }
                    }

                    for (var7 = 1; var7 < 5; ++var7) {
                        var6 += maze3d[currentPosition.x][currentPosition.y][var7];
                    }

                    if (var6 == 0) {
                        try {
                            Path.removeFirst();
                            lastPosition = currentPosition;
                            currentPosition = (Coordinates) Path.getFirst();
                            driver(maze);
                        } catch (java.util.NoSuchElementException E) {

                            //Coordinates noSolution = new Coordinates(1, 1);
                            //Path.addFirst(noSolution);
                            //System.out.println();
                            //System.out.println("No solution found");
                            //CombinedReturn a = new CombinedReturn(Path, 999999999);
                            //return a;
                        }
                    } else {
                        Collections.shuffle(var4);
                        maze3d[currentPosition.x][currentPosition.y][(Integer) var4.get(0)] = 0;
                        Coordinates var8;
                        if ((Integer) var4.get(0) == 1) {
                            var8 = new Coordinates(currentPosition.x - 2, currentPosition.y);
                            Path.addFirst(var8);
                            driver(maze);
                        }

                        if ((Integer) var4.get(0) == 2) {
                            var8 = new Coordinates(currentPosition.x, currentPosition.y - 2);
                            Path.addFirst(var8);
                            driver(maze);
                        }

                        if ((Integer) var4.get(0) == 3) {
                            var8 = new Coordinates(currentPosition.x + 2, currentPosition.y);
                            Path.addFirst(var8);
                            driver(maze);
                        }

                        if ((Integer) var4.get(0) == 4) {
                            var8 = new Coordinates(currentPosition.x, currentPosition.y + 2);
                            Path.addFirst(var8);
                            driver(maze);
                        }
                    }
                }

            }
        }
        CombinedReturn a = new CombinedReturn(Path, itte);
        return a;
    }

    public static void checkDir(int[][] maze, Coordinates currentPosition, Coordinates lastPosition, int[][][] maze3d) {
        maze3d[currentPosition.x][currentPosition.y][0] = 1;
        if (maze[currentPosition.x - 1][currentPosition.y] == 0 & currentPosition.x - 2 != lastPosition.x) {
            maze3d[currentPosition.x][currentPosition.y][1] = 1;
        }

        if (maze[currentPosition.x][currentPosition.y - 1] == 0 & currentPosition.y - 2 != lastPosition.y) {
            maze3d[currentPosition.x][currentPosition.y][2] = 1;
        }

        if (maze[currentPosition.x + 1][currentPosition.y] == 0 & currentPosition.x + 2 != lastPosition.x) {
            maze3d[currentPosition.x][currentPosition.y][3] = 1;
        }

        if (maze[currentPosition.x][currentPosition.y + 1] == 0 & currentPosition.y + 2 != lastPosition.y) {
            maze3d[currentPosition.x][currentPosition.y][4] = 1;
        }

    }

}