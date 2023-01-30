// A Java program for Dijkstra's single source shortest path algorithm.
// The program is for adjacency matrix representation of the graph
package com.solve.ASTAR;

import java.util.ArrayList;
import java.lang.*;
import java.lang.Math;
import java.util.Deque;
import java.util.LinkedList;
import com.utility.*;

public class Astar implements Solver {
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree
    public int itte;

    int minDistance(Dist dist[], Boolean sptSet[], int V) {
        // Initialize min value
        int min = Integer.MAX_VALUE;
        int min_index = -1;
        double width = Math.sqrt(V);
        Coordinates end = new Coordinates((int) Math.round(width-1), (int) Math.round(width-1));

        for (int v = 0; v < V; v++)

            if (sptSet[v] == false && dist[v].x <= min)
            {
                int y = (int) Math.round(v % width);
                int x = (int) Math.round((v - y)/width);
                Coordinates point = new Coordinates(x, y);
                int toEnd = ((end.x - point.x) + (end.y - point.y));
                min = dist[v].x + toEnd;
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance array
    Deque<Coordinates> Solution(ArrayList<Integer> path, int V) {
        double width = Math.sqrt(V);
        Deque<Coordinates> solutionList = new LinkedList<Coordinates>();
        for (int i=0; i < path.size(); i++) {
            int y = (int) Math.round(path.get(i) % width);
            int x = (int) Math.round((path.get(i) - y)/width);
            Coordinates point = new Coordinates(x, y);
            solutionList.addFirst(point);
        }
        solutionList.addFirst(new Coordinates((int) Math.round(width-1), (int) Math.round(width-1)));
        return solutionList;
    }

    // Funtion that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix
    // representation
    CombinedReturn astarGraph(int graph[][], int V) {
        Dist distarray[] = new Dist[V]; // The output array. dist[i] will hold
        // the shortest distance from src to i
        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            distarray[i] = new Dist(Integer.MAX_VALUE, new ArrayList<Integer>());
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        distarray[0].x = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V-1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(distarray, sptSet, V);

            if (u == V-1) {
                break;
            }
            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < V; v++) {

                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && distarray[u].x != Integer.MAX_VALUE && distarray[u].x + graph[u][v] < distarray[v].x) {
                    distarray[v].x = distarray[u].x + graph[u][v];
                    for (int a=0; a < distarray[u].path.size(); a++) {
                        distarray[v].path.add(distarray[u].path.get(a));
                    }
                    distarray[v].path.add(u);
                    itte++;
                }
            }
        }

        // print the constructed distance array
        Deque<Coordinates> solutionList = Solution(distarray[V-1].path, V);
        CombinedReturn combine = new CombinedReturn(solutionList, itte);
        return combine;
    }

    // Driver method
    public CombinedReturn driver (int[][] matrix) {
        int shortlength = (matrix.length-1)/2;
        int V = shortlength*shortlength;
        int graph[][] = new int[V][V];
        // Let us create the example graph discussed above
        for (int i=0; i < shortlength; i++) {
            for (int j = 0; j < shortlength; j++) {
                try {
                    //Check right
                    if (matrix[(i * 2) + 1][(j * 2) + 2] == 0) {
                        graph[(i * shortlength) + j][(i * shortlength) + j + 1] = 1;
                    }
                    else {
                        graph[(i * shortlength) + j][(i * shortlength) + j + 1] = 0;
                    }
                }
                catch (Exception e) {}
                try {
                    //Check left
                    if (matrix[(i * 2) + 1][(j * 2)] == 0) {
                        graph[(i * shortlength) + j][(i * shortlength) + j - 1] = 1;
                    }
                    else {
                        graph[(i * shortlength) + j][(i * shortlength) + j - 1] = 0;
                    }
                }
                catch (Exception e) {}
                try {
                    //Check up
                    if (matrix[(i * 2) + 2][(j * 2) + 1] == 0) {
                        graph[(i * shortlength) + j][(i * shortlength) + j + shortlength] = 1;
                    }
                    else {
                        graph[(i * shortlength) + j][(i * shortlength) + j + shortlength] = 0;
                    }
                }
                catch (Exception e) {}
                try {
                    //Check down
                    if (matrix[(i * 2)][(j * 2) + 1] == 0) {
                        graph[(i * shortlength) + j][(i * shortlength) + j - shortlength] = 1;
                    }
                    else {
                        graph[(i * shortlength) + j][(i * shortlength) + j - shortlength] = 0;
                    }
                }
                catch (Exception e) {}
            }
        }

        /*for (int i=0; i<graph.length; i++) {
            for (int j=0; j<graph.length; j++) {
                System.out.print(graph[i][j]);
            }
            System.out.println();
        } */
        Astar t = new Astar();
        CombinedReturn solution = t.astarGraph(graph, V);
        return solution;
    }
}
//This code is contributed by Aakash Hasija
