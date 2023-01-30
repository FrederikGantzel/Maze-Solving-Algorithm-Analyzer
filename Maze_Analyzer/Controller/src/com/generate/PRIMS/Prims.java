package com.generate.PRIMS;

import java.util.*;
import com.utility.Coordinates;
import com.utility.Generator;

public class Prims implements Generator {

    public int[][] driver (int step) {
        int size = (2*step)+1;
        int bigmatrix[][] = new int[size][size];
        int Primmatrix[][] = new int[step][step];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                bigmatrix[x][y] = 1;
            }
        }

        for (int x = 0; x < step; x++) {
            for (int y = 0; y < step; y++) {
                Primmatrix[x][y] = 1;
            }
        }

        int Finalmatrix[][] = Primgen(Primmatrix, bigmatrix);

        return Finalmatrix;
    }

    public static int[][] Primgen (int[][] Primmatrix, int[][] bigmatrix) {

        Random rand = new Random();
        ArrayList<Coordinates> frontier = new ArrayList<Coordinates>();
        ArrayList<Coordinates> potential = new ArrayList<Coordinates>();
        Primmatrix[0][0]=0;

        while (finnishcheck(Primmatrix, 0) == false){

            for (int x = 0; x < Primmatrix.length; x++) {
                for (int y = 0; y < Primmatrix[0].length; y++) {
                    if (Primmatrix [x][y] == 0) {
                        try {
                            if (Primmatrix[x - 1][y] == 1) {
                                Primmatrix[x - 1][y] = 2;
                                frontier.add(new Coordinates(x - 1, y));
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {}
                        try {
                            if (Primmatrix[x + 1][y] == 1) {
                                Primmatrix[x + 1][y] = 2;
                                frontier.add(new Coordinates(x + 1, y));
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {}
                        try {
                            if (Primmatrix[x][y - 1] == 1) {
                                Primmatrix[x][y - 1] = 2;
                                frontier.add(new Coordinates(x, y - 1));
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {}
                        try {
                            if (Primmatrix[x][y + 1] == 1) {
                                Primmatrix[x][y + 1] = 2;
                                frontier.add(new Coordinates(x, y + 1));
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e) {}
                    }
                }
            }

            int n = rand.nextInt(frontier.size());
            Coordinates current = frontier.get(n);
            frontier.remove(n);
            try {
                if (Primmatrix[current.x - 1][current.y] == 0) {
                    potential.add(new Coordinates(current.x - 1, current.y));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (Primmatrix[current.x + 1][current.y] == 0) {
                    potential.add(new Coordinates(current.x + 1, current.y));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (Primmatrix[current.x][current.y - 1] == 0) {
                    potential.add(new Coordinates(current.x, current.y - 1));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}
            try {
                if (Primmatrix[current.x][current.y + 1] == 0) {
                    potential.add(new Coordinates(current.x, current.y + 1));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}

            int m = rand.nextInt(potential.size());
            Coordinates pathtocurrent = potential.get(m);
            potential.clear();
            Primmatrix[current.x][current.y] = 0;
            Coordinates bigcurrent = convert(current);

            if (current.x-1 == pathtocurrent.x) {
                bigmatrix[bigcurrent.x][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x-1][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x-2][bigcurrent.y]=0;
            }
            if (current.x+1 == pathtocurrent.x) {
                bigmatrix[bigcurrent.x][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x+1][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x+2][bigcurrent.y]=0;
            }
            if (current.y-1 == pathtocurrent.y) {
                bigmatrix[bigcurrent.x][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x][bigcurrent.y-1]=0;
                bigmatrix[bigcurrent.x][bigcurrent.y-2]=0;
            }
            if (current.y+1 == pathtocurrent.y) {
                bigmatrix[bigcurrent.x][bigcurrent.y]=0;
                bigmatrix[bigcurrent.x][bigcurrent.y+1]=0;
                bigmatrix[bigcurrent.x][bigcurrent.y+2]=0;
            }

        }
        return bigmatrix;
    }

    public static boolean finnishcheck (final int[][] Primmatrix, final int key) {
        int any = 0;
        for (int x = 0; x < Primmatrix.length; x++) {
            for (int y = 0; y < Primmatrix[0].length; y++) {
                if (Primmatrix [x][y] == key) {
                    any = any+1;
                }
            }
        }
        if (any < (Primmatrix.length * Primmatrix[0].length)) {
            return false;
        }
        else {
            return true;
        }
    }

    public static Coordinates convert (Coordinates input) {
        Coordinates output = new Coordinates((input.x*2)+1, (input.y*2)+1);
        return output;
    }
}
