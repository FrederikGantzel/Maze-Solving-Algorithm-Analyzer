package com.company;

import java.io.IOException;
import com.generate.DFSGen.*;
import com.generate.ELS.*;
import com.generate.PRIMS.*;
import com.solve.ASTAR.*;
import com.solve.DFSSolve.*;
import com.solve.DIJK.*;

import java.lang.*;
import java.util.Arrays;
import java.util.stream.*;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;
import com.utility.*;

public class Model {


    public static Data DijkSolve (int smallest, int largest, int repetitions) throws IOException {
        long startTime;
        int [] DijkItteEl = new int [largest+1 - smallest];
        long [] DijkTimeEl = new long [largest+1 - smallest];

        int [] DijkItteDFS = new int [largest+1 - smallest];
        long [] DijkTimeDFS = new long [largest+1 - smallest];

        int [] DijkIttePrims = new int [largest+1 - smallest];
        long [] DijkTimePrims = new long [largest+1 - smallest];

        for (int x=smallest; x<largest+1; x++){

            for (int y=0; y<repetitions; y++){

                int[][] ellersMaze = new Ellers().driver(x);
                ellersMaze [1][1] = -1;
                ellersMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Dijkstra DijkEl1 = new Dijkstra();
                DijkEl1.itte = 0;
                DijkItteEl [x-smallest] += DijkEl1.driver(ellersMaze).itte;
                DijkTimeEl [x-smallest] += (System.nanoTime() - startTime);
                //Coordinates [] DijkSolEl = DijkEl1.dijkstraSolve(ellersMaze).Path;

                //MazeSaver save = new MazeSaver(ellersMaze,DijkSolEl,mazePath,solPath);


                int[][] DFSMaze = new DepthFirstGen().driver(x);
                DFSMaze [1][1] = -1;
                DFSMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Dijkstra DijkDFS = new Dijkstra();
                DijkDFS.itte = 0;
                DijkItteDFS [x-smallest] += DijkDFS.driver(DFSMaze).itte;
                DijkTimeDFS [x-smallest] += (System.nanoTime() - startTime);


                int[][] PrimsMaze = new Prims().driver(x);
                PrimsMaze [1][1] = -1;
                PrimsMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Dijkstra DijkPrims = new Dijkstra();
                DijkPrims.itte = 0;
                DijkIttePrims [x-smallest] += DijkPrims.driver(PrimsMaze).itte;
                DijkTimePrims [x-smallest] += (System.nanoTime() - startTime);



            }

        }

        int [] DijkItteElAVG = new int [largest+1 - smallest];
        long [] DijkTimeElAVG = new long [largest+1 - smallest];
        int [] DijkItteDFSAVG = new int [largest+1 - smallest];
        long [] DijkTimeDFSAVG = new long [largest+1 - smallest];
        int [] DijkIttePrimsAVG = new int [largest+1 - smallest];
        long [] DijkTimePrimsAVG = new long [largest+1 - smallest];
        System.out.println();

        average(DijkItteElAVG, DijkTimeElAVG, DijkItteDFSAVG, DijkTimeDFSAVG, DijkIttePrimsAVG, DijkTimePrimsAVG, DijkItteEl, DijkTimeEl, DijkItteDFS, DijkTimeDFS, DijkIttePrims, DijkTimePrims, repetitions, smallest);

        Data data = new Data();
        data.data(smallest, largest, DijkItteElAVG, DijkTimeElAVG, DijkItteDFSAVG,
                DijkTimeDFSAVG, DijkIttePrimsAVG, DijkTimePrimsAVG);
        return data;

    }

    public static Data AStarSolve (int smallest, int largest, int repetitions) throws IOException{
        long startTime;
        int [] aStarItteEl = new int [largest+1 - smallest];
        long [] aStarTimeEl = new long [largest+1 - smallest];

        int [] aStarItteDFS = new int [largest+1 - smallest];
        long [] aStarTimeDFS = new long [largest+1 - smallest];

        int [] aStarIttePrims = new int [largest+1 - smallest];
        long [] aStarTimePrims = new long [largest+1 - smallest];

        for (int x=smallest; x<largest+1; x++){

            for (int y=0; y<repetitions; y++){

                int[][] ellersMaze = new Ellers().driver(x);
                ellersMaze [1][1] = -1;
                ellersMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Astar aStarEl = new Astar();
                aStarEl.itte = 0;
                aStarItteEl [x-smallest] += aStarEl.driver(ellersMaze).itte;
                aStarTimeEl [x-smallest] += (System.nanoTime() - startTime);

                int[][] DFSMaze = new DepthFirstGen().driver(x);
                DFSMaze [1][1] = -1;
                DFSMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Astar aStarDFS = new Astar();
                aStarDFS.itte = 0;
                aStarItteDFS [x-smallest] += aStarDFS.driver(DFSMaze).itte;
                aStarTimeDFS [x-smallest] += (System.nanoTime() - startTime);


                int[][] PrimsMaze = new Prims().driver(x);
                PrimsMaze [1][1] = -1;
                PrimsMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                Astar aStarPrims = new Astar();
                aStarPrims.itte = 0;
                aStarIttePrims [x-smallest] += aStarPrims.driver(PrimsMaze).itte;
                aStarTimePrims [x-smallest] += (System.nanoTime() - startTime);



            }

        }

        int [] aStarItteElAVG = new int [largest+1 - smallest];
        long [] aStarTimeElAVG = new long [largest+1 - smallest];
        int [] aStarItteDFSAVG = new int [largest+1 - smallest];
        long [] aStarTimeDFSAVG = new long [largest+1 - smallest];
        int [] aStarIttePrimsAVG = new int [largest+1 - smallest];
        long [] aStarTimePrimsAVG = new long [largest+1 - smallest];
        System.out.println();

        average(aStarItteElAVG, aStarTimeElAVG, aStarItteDFSAVG, aStarTimeDFSAVG, aStarIttePrimsAVG, aStarTimePrimsAVG, aStarItteEl, aStarTimeEl, aStarItteDFS, aStarTimeDFS, aStarIttePrims, aStarTimePrims, repetitions, smallest);

        Data data = new Data();
        data.data(smallest, largest, aStarItteElAVG, aStarTimeElAVG, aStarItteDFSAVG,
                aStarTimeDFSAVG, aStarIttePrimsAVG, aStarTimePrimsAVG);
        return data;
    }


    public static Data DepthSolve (int smallest, int largest, int repetitions) throws IOException{
        long startTime;
        int [] depthItteEl = new int [largest+1 - smallest];
        long [] depthTimeEl = new long [largest+1 - smallest];

        int [] depthItteDFS = new int [largest+1 - smallest];
        long [] depthTimeDFS = new long [largest+1 - smallest];

        int [] depthIttePrims = new int [largest+1 - smallest];
        long [] depthTimePrims = new long [largest+1 - smallest];

        for (int x=smallest; x<largest+1; x++){

            for (int y=0; y<repetitions; y++){

                int[][] ellersMaze1 = new Ellers().driver(x);
                ellersMaze1 [1][1] = -1;
                ellersMaze1 [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                DepthFirst DFSEl = new DepthFirst(ellersMaze1);
                DFSEl.itte = 0;
                depthItteEl [x-smallest] += DFSEl.driver(DepthFirst.maze).itte;
                depthTimeEl [x-smallest] += (System.nanoTime() - startTime);

                int[][] DFSMaze = new DepthFirstGen().driver(x);
                DFSMaze [1][1] = -1;
                DFSMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                DepthFirst DFSDFS = new DepthFirst(DFSMaze);
                DFSDFS.itte = 0;
                depthItteDFS [x-smallest] += DFSDFS.driver(DepthFirst.maze).itte;
                depthTimeDFS [x-smallest] += (System.nanoTime() - startTime);

                int[][] PrimsMaze = new Prims().driver(x);
                PrimsMaze [1][1] = -1;
                PrimsMaze [2*x-1][2*x-1] = -2;
                startTime = System.nanoTime();
                DepthFirst DFSPrims = new DepthFirst(PrimsMaze);
                DFSPrims.itte = 0;
                depthIttePrims [x-smallest] += DFSPrims.driver(DepthFirst.maze).itte;
                depthTimePrims [x-smallest] += (System.nanoTime() - startTime);


            }

        }

        int [] depthItteElAVG = new int [largest+1 - smallest];
        long [] depthTimeElAVG = new long [largest+1 - smallest];
        int [] depthItteDFSAVG = new int [largest+1 - smallest];
        long [] depthTimeDFSAVG = new long [largest+1 - smallest];
        int [] depthIttePrimsAVG = new int [largest+1 - smallest];
        long [] depthTimePrimsAVG = new long [largest+1 - smallest];
        System.out.println();

        average(depthItteElAVG, depthTimeElAVG, depthItteDFSAVG, depthTimeDFSAVG, depthIttePrimsAVG, depthTimePrimsAVG, depthItteEl, depthTimeEl, depthItteDFS, depthTimeDFS, depthIttePrims, depthTimePrims, repetitions, smallest);

        Data data = new Data();
        data.data(smallest, largest, depthItteElAVG, depthTimeElAVG, depthItteDFSAVG,
                depthTimeDFSAVG, depthIttePrimsAVG, depthTimePrimsAVG);
        return data;
    }

    public static void average (int[] ItteElAVG, long[] TimeElAVG, int[] ItteDFSAVG, long[] TimeDFSAVG, int[] IttePrimsAVG, long[] TimePrimsAVG, int [] ItteEl, long [] TimeEl, int[] ItteDFS, long[] TimeDFS, int[] IttePrims, long[] TimePrims, int repetitions, int smallest) {
        for (int z=0; z<ItteEl.length; z++) {
            ItteElAVG [z] = (ItteEl[z]/repetitions)-1;
            TimeElAVG [z] = (TimeEl[z]/repetitions)-1;
            ItteDFSAVG [z] = (ItteDFS[z]/repetitions)-1;
            TimeDFSAVG [z] = (TimeDFS[z]/repetitions)-1;
            IttePrimsAVG [z] = (IttePrims[z]/repetitions)-1;
            TimePrimsAVG [z] =(TimePrims[z]/repetitions)-1;

            System.out.println("(Ellers) Maze size: " + Integer.toString(smallest+z) + " Avg itterations: " + Integer.toString(ItteElAVG[z]) + " Acg time: " + Long.toString(TimeElAVG[z]));
            System.out.println("(DFSGen) Maze size: " + Integer.toString(smallest+z) + " Avg itterations: " + Integer.toString(ItteDFSAVG[z]) + " Acg time: " + Long.toString(TimeDFSAVG[z]));
            System.out.println("(Prims) Maze size: " + Integer.toString(smallest+z) + " Avg itterations: " + Integer.toString(IttePrimsAVG[z]) + " Acg time: " + Long.toString(TimePrimsAVG[z]));
        }
    }

    public static AlgoScores Score(Data dik, Data ast, Data dep, long itteWeight, long timeWeight) {
        int maxxit = 0;
        long maxxtim = 0;
        double[] dikNormItteDFS = new double[dik.itteDFS.length];
        double[] dikNormTimeDFS = new double[dik.itteDFS.length];
        double[] dikNormItteEl = new double[dik.itteDFS.length];
        double[] dikNormTimeEl= new double[dik.itteDFS.length];
        double[] dikNormIttePrims = new double[dik.itteDFS.length];
        double[] dikNormTimePrims = new double[dik.itteDFS.length];
        double[] astNormItteDFS = new double[dik.itteDFS.length];
        double[] astNormTimeDFS = new double[dik.itteDFS.length];
        double[] astNormItteEl = new double[dik.itteDFS.length];
        double[] astNormTimeEl= new double[dik.itteDFS.length];
        double[] astNormIttePrims = new double[dik.itteDFS.length];
        double[] astNormTimePrims = new double[dik.itteDFS.length];
        double[] depNormItteDFS = new double[dik.itteDFS.length];
        double[] depNormTimeDFS = new double[dik.itteDFS.length];
        double[] depNormItteEl = new double[dik.itteDFS.length];
        double[] depNormTimeEl= new double[dik.itteDFS.length];
        double[] depNormIttePrims = new double[dik.itteDFS.length];
        double[] depNormTimePrims = new double[dik.itteDFS.length];

        double[] totalNormItteDik = new double[dik.itteDFS.length];
        double[] totalNormTimeDik = new double[dik.itteDFS.length];
        double[] totalNormItteAst = new double[dik.itteDFS.length];
        double[] totalNormTimeAst = new double[dik.itteDFS.length];
        double[] totalNormItteDep = new double[dik.itteDFS.length];
        double[] totalNormTimeDep = new double[dik.itteDFS.length];

        IntSummaryStatistics dikSumItteDFS = Arrays.stream(dik.itteDFS).summaryStatistics();
        LongSummaryStatistics dikSumTimeDFS = Arrays.stream(dik.timeDFS).summaryStatistics();
        IntSummaryStatistics dikSumItteEl = Arrays.stream(dik.itteEl).summaryStatistics();
        LongSummaryStatistics dikSumTimeEl = Arrays.stream(dik.timeEl).summaryStatistics();
        IntSummaryStatistics dikSumIttePrims = Arrays.stream(dik.ittePrims).summaryStatistics();
        LongSummaryStatistics dikSumTimePrims = Arrays.stream(dik.timePrims).summaryStatistics();
        IntSummaryStatistics astSumItteDFS = Arrays.stream(ast.itteDFS).summaryStatistics();
        LongSummaryStatistics astSumTimeDFS = Arrays.stream(ast.timeDFS).summaryStatistics();
        IntSummaryStatistics astSumItteEl = Arrays.stream(ast.itteEl).summaryStatistics();
        LongSummaryStatistics astSumTimeEl = Arrays.stream(ast.timeEl).summaryStatistics();
        IntSummaryStatistics astSumIttePrims = Arrays.stream(ast.ittePrims).summaryStatistics();
        LongSummaryStatistics astSumTimePrims = Arrays.stream(ast.timePrims).summaryStatistics();
        IntSummaryStatistics depSumItteDFS = Arrays.stream(dep.itteDFS).summaryStatistics();
        LongSummaryStatistics depSumTimeDFS = Arrays.stream(dep.timeDFS).summaryStatistics();
        IntSummaryStatistics depSumItteEl = Arrays.stream(dep.itteEl).summaryStatistics();
        LongSummaryStatistics depSumTimeEl = Arrays.stream(dep.timeEl).summaryStatistics();
        IntSummaryStatistics depSumIttePrims = Arrays.stream(dep.ittePrims).summaryStatistics();
        LongSummaryStatistics depSumTimePrims = Arrays.stream(dep.timePrims).summaryStatistics();

        int[] Maxittes = new int[9];
        long[] Maxtime = new long[9];
        Maxittes[0] = dikSumItteDFS.getMax();
        Maxittes[1] = dikSumItteEl.getMax();
        Maxittes[2] = dikSumIttePrims.getMax();
        Maxittes[3] = astSumItteDFS.getMax();
        Maxittes[4] = astSumItteEl.getMax();
        Maxittes[5] = astSumIttePrims.getMax();
        Maxittes[6] = depSumItteDFS.getMax();
        Maxittes[7] = depSumItteEl.getMax();
        Maxittes[8] = depSumIttePrims.getMax();
        Maxtime[0] = dikSumTimeDFS.getMax();
        Maxtime[1] = dikSumTimeEl.getMax();
        Maxtime[2] = dikSumTimePrims.getMax();
        Maxtime[3] = astSumTimeDFS.getMax();
        Maxtime[4] = astSumTimeEl.getMax();
        Maxtime[5] = astSumTimePrims.getMax();
        Maxtime[6] = depSumTimeDFS.getMax();
        Maxtime[7] = depSumTimeEl.getMax();
        Maxtime[8] = depSumTimePrims.getMax();

        for (int i=0; i<Maxittes.length; i++) {
            if (Maxittes[i] > maxxit) {
                maxxit = Maxittes[i];
            }
            if (Maxtime[i] > maxxtim) {
                maxxtim = Maxtime[i];
            }
        }

        for (int i=0; i<dik.itteDFS.length; i++) {
            dikNormItteDFS[i] = 1-(((double) dik.itteDFS[i])/maxxit);
            dikNormTimeDFS[i] = 1-(((double) dik.timeDFS[i])/maxxtim);
            dikNormItteEl[i] = 1-(((double) dik.itteEl[i])/maxxit);
            dikNormTimeEl[i] = 1-(((double) dik.timeEl[i])/maxxtim);
            dikNormIttePrims[i] = 1-(((double) dik.ittePrims[i])/maxxit);
            dikNormTimePrims[i] = 1-(((double) dik.timePrims[i])/maxxtim);
            astNormItteDFS[i] = 1-(((double) ast.itteDFS[i])/maxxit);
            astNormTimeDFS[i] = 1-(((double) ast.timeDFS[i])/maxxtim);
            astNormItteEl[i] = 1-(((double) ast.itteEl[i])/maxxit);
            astNormTimeEl[i] = 1-(((double) ast.timeEl[i])/maxxtim);
            astNormIttePrims[i] = 1-(((double) ast.ittePrims[i])/maxxit);
            astNormTimePrims[i] = 1-(((double) ast.timePrims[i])/maxxtim);
            depNormItteDFS[i] = 1-(((double) dep.itteDFS[i])/maxxit);
            depNormTimeDFS[i] = 1-(((double) dep.timeDFS[i])/maxxtim);
            depNormItteEl[i] = 1-(((double) dep.itteEl[i])/maxxit);
            depNormTimeEl[i] = 1-(((double) dep.timeEl[i])/maxxtim);
            depNormIttePrims[i] = 1-(((double) dep.ittePrims[i])/maxxit);
            depNormTimePrims[i] = 1-(((double) dep.timePrims[i])/maxxtim);
        }

        for (int i=0; i<dik.itteDFS.length; i++) {
            totalNormItteDik[i] = (dikNormItteDFS[i]+dikNormItteEl[i]+dikNormIttePrims[i])/3;
            totalNormTimeDik[i] = (dikNormTimeDFS[i]+dikNormTimeEl[i]+dikNormTimePrims[i])/3;
            totalNormItteAst[i] = (astNormItteDFS[i]+astNormItteEl[i]+astNormIttePrims[i])/3;
            totalNormTimeAst[i] = (astNormTimeDFS[i]+astNormTimeEl[i]+astNormTimePrims[i])/3;
            totalNormItteDep[i] = (depNormItteDFS[i]+depNormItteEl[i]+depNormIttePrims[i])/3;
            totalNormTimeDep[i] = (depNormTimeDFS[i]+depNormTimeEl[i]+depNormTimePrims[i])/3;
        }

        double finalDikItteScore = (DoubleStream.of(totalNormItteDik).sum())/totalNormItteDik.length;
        double finalDikTimeScore = (DoubleStream.of(totalNormTimeDik).sum())/totalNormTimeDik.length;
        double finalAstItteScore = (DoubleStream.of(totalNormItteAst).sum())/totalNormItteAst.length;
        double finalAstTimeScore = (DoubleStream.of(totalNormTimeAst).sum())/totalNormTimeAst.length;
        double finalDepItteScore = (DoubleStream.of(totalNormItteDep).sum())/totalNormItteDep.length;
        double finalDepTimeScore = (DoubleStream.of(totalNormTimeDep).sum())/totalNormTimeDep.length;

        AlgoScores finalScore = new AlgoScores((finalDikItteScore*itteWeight)+(finalDikTimeScore*timeWeight), (finalAstItteScore*itteWeight)+(finalAstTimeScore*timeWeight), (finalDepItteScore*itteWeight)+(finalDepTimeScore*timeWeight));
        return finalScore;
    }

}