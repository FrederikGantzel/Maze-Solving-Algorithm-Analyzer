package com.utility;

import java.util.Deque;

public class CombinedReturn {
    public Coordinates [] Path;
    public int itte;

    public CombinedReturn (Deque<Coordinates> Path, int itte){
        Coordinates [] returnPath = new Coordinates [Path.size()];
        int x=0;
        while (Path.size() != 0){
            returnPath[x]=Path.pollLast();
            x++;
        }

        this.Path = returnPath;
        this.itte = itte;
    }
}
