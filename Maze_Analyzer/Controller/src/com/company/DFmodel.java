package com.company;

import java.io.IOException;
import java.util.ArrayList;

import com.generate.PRIMS.*;
import com.solve.DFSSolve.*;

public class DFmodel {

    public static ArrayList<Integer> manysolves (int size, int repetitions) throws IOException {
        int[][] PrimsMaze = new Prims().driver(size);
        PrimsMaze [1][1] = -1;
        PrimsMaze [2*size-1][2*size-1] = -2;
        ArrayList<Integer> results = new ArrayList<Integer>();
        for (int x=0; x<repetitions; x++) {
            long startTime = System.nanoTime();
            DepthFirst DFSP = new DepthFirst(PrimsMaze);
            DFSP.itte=0;
            results.add(DFSP.driver(DepthFirst.maze).itte);
        }
        return results;
    }
}
