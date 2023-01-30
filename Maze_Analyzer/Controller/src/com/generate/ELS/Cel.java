package com.generate.ELS;

// a utility class to represent single cell

import java.util.*;

public class Cel{
    public boolean right,down;

    public List<Cel> set;

    public int x,y;

    Cel(int a,int b){

        x=a;

        y=b;

        right=false;

        down=true;

        set=null;

    }

    boolean isContainsInList(List<Cel> set) {  //utility function

        for(Cel i : set) {

            if(i== this)

                return true;

        }

        return false;

    }

    int findPos(List<Cel> set){

        Cel[] tmpArray = new Cel[set.size()];

        tmpArray = set.toArray(tmpArray);

        for(int i=0;i<tmpArray.length;i++)

            if(tmpArray[i]== this)

                return i;

        return -1;

    }
}
// class to implement the algorithm





