package com.generate.ELS;

import com.utility.Generator;

import java.io.IOException;
import java.util.*;

public class Ellers implements Generator {

    Random randomizer = new Random();

    int size;

    int[][] maze;

    Scanner in = new Scanner(System.in);



    /*  Step 2: Grouping ungrouped cells to form new sets    */


    Cel[] end(Cel[] row) {

        for(int i = 1; i < row.length; i++) {

            if(row[i].findPos(row[i-1].set) == -1) {

                row[i-1].right=false;

                row= new Ellers().merge(row,i);

            }

        }

        return row;

    }

    /*********************************************************/



    /*  Step 4: Creating down walls                          */

    boolean isNotDone(List<Cel> set){    //utility function

        boolean rslt=true;

        for(Cel x:set)

            rslt=rslt&&x.down;

        return rslt;

    }


    Cel[] merge(Cel[] row, int i) {  //utility function

        List<Cel> currentList = row[i-1].set;

        List<Cel> nextList = row[i].set;

        for(Cel j : nextList) {

            currentList.add(j);

            j.set=currentList;

        }

        return row;

    }


    /*public void export(int[] x) throws IOException {
        String uuid = UUID.randomUUID().toString();
        uuid=uuid+".csv";
        BufferedWriter outputWriter = null;

        String file = System.getProperty("user.dir");
        for (int x1=1; x1<20; x1++){
            file = new Ellers().removeLastChar(file);
        }

        String fileGen = file + "Mazes\\";

        outputWriter = new BufferedWriter(new FileWriter(fileGen + uuid));
        outputWriter.write(Arrays.toString(x));
        outputWriter.flush();
        outputWriter.close();

    }*/

    void printMaze(Cel[] row, int rowPos){

        rowPos=2*rowPos+1;

        for(int i=0;i<row.length;i++){

            if(row[i].right)

                maze[rowPos][2*i+2]=1;

            if(row[i].down)

                maze[rowPos+1][2*i+1]=1;

        }

    }

    Cel[] makeDown(Cel[] row){

        for(int i=0;i<row.length;i++){

            for(Cel x:row[i].set)x.down=true;

            while(isNotDone(row[i].set)){

                do{

                    row[i].set.get(randomizer.nextInt(row[i].set.size())).down=false;

                }while(randomizer.nextBoolean());

            }

        }

        return row;

    }

    Cel[] genNextRow(Cel[] pre){

        for(int i = 0; i < pre.length;i++ ) {

            pre[i].right=false;

            pre[i].x++;

            if(pre[i].down) {

                pre[i].set.remove(pre[i].findPos(pre[i].set));

                pre[i].set=null;

                pre[i].down=false;

            }

        }

        return pre;

    }

    /*********************************************************/



    /*  Step 3: Creating right walls                         */

    Cel[] makeRightWalls(Cel[] row) {

        for(int i = 1; i < row.length; i++) {

            if(row[i].isContainsInList(row[i-1].set)) {

                row[i-1].right=true;

                continue;

            }

            if(randomizer.nextBoolean())

                row[i-1].right=true;

            else

                row=merge(row,i);

        }

        return row;

    }

    Cel[] makeSet(Cel[] row) {

        for(int index = 0; index < row.length; ) {

            Cel cell = row[index++];

            if(cell.set == null) {

                List<Cel> list = new ArrayList<Cel>();

                list.add(cell);

                cell.set=list;

            }

        }

        return row;

    }


    /*********************************************************/



    /*  Driver function to execute the algorithm             */

    public int [][] driver(int size) throws IOException{

        maze = new int[2 * size + 1][2 * size + 1];

        Cel[] cur = new Cel[size];

        for (int i = 0; i < size; i++)

            cur[i] = new Cel(0, i);

        for (int i = 2; i <= 2 * size; i++)

            for (int j = 2; j <= 2 * size; j++)

                maze[i][j] = 0;

        for (int i = 0; i < size; i++) {

            cur = makeSet(cur);

            cur = makeRightWalls(cur);

            cur = makeDown(cur);

            if (i == size - 1)

                cur = end(cur);

            printMaze(cur, i);


            if (i != size - 1)

                cur = genNextRow(cur);

        }
        int[] x = new int[(2 * size + 1) * (2 * size + 1)];
        //Creating upper and left boundary

        for (int i = 0; i <= 2 * size; i++)

            maze[i][0] = maze[0][i] = maze[i][2 * size] = maze[2 * size][i] = 1;

        for (int i = 2; i <= 2 * size; i += 2)

            for (int j = 2; j <= 2 * size; j += 2)

                maze[i][j] = 1;

        //export(x);
        return maze;
    }

    private String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }
}