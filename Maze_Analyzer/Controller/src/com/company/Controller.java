package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.*;
import java.io.*;
import java.util.ArrayList;

import java.io.IOException;

import com.utility.*;

public class Controller {
    private DataInputs Inputs = new DataInputs(0, 0, 0, 50, 50);

    @FXML
    Button SetData = new Button();
    @FXML
    Button RunProgram = new Button();
    @FXML
    CheckBox SaveToFileCheck = new CheckBox();
    @FXML
    CheckBox ShortestPathOnly = new CheckBox();
    @FXML
    Button DFSEval = new Button();
    @FXML
    ChoiceBox GeneratorPick = new ChoiceBox();
    @FXML
    ChoiceBox SolverPick = new ChoiceBox();
    @FXML
    Slider sliderWeight = new Slider();
    @FXML
    Text itteWeightText = new Text();
    @FXML
    Text timeWeightText = new Text();
    @FXML
    TextField Smallest = new TextField();
    @FXML
    TextField Largest = new TextField();
    @FXML
    TextField Repetitions = new TextField();
    @FXML
    TextArea Displayer = new TextArea();

    @FXML
    public void buttonPressSetData(ActionEvent event) {
        int minMaze = 0;
        int maxMaze = 0;
        int repMaze = 0;
        String message = "";
        try {
            minMaze = Integer.parseInt(Smallest.getText());
            maxMaze = Integer.parseInt(Largest.getText());
            repMaze = Integer.parseInt(Repetitions.getText());
        }
        catch (NumberFormatException e){
            message = "You seem to have inputted a non integer character.\nPlease correct this.\n";
            Displayer.setText(message);
        }

        if (minMaze > 4 && minMaze <= maxMaze) {
            Inputs.SmallestMaze = minMaze;
        }
        else {
            message = message + "Minimum maze size is not within acceptable limits.\nPlease correct this.\n";
            Displayer.setText(message);
        }

        if (maxMaze < 31 && maxMaze >= minMaze && maxMaze > 4) {
            Inputs.LargestMaze = maxMaze;
        }
        else {
            message = message + "Maximum maze size is not within acceptable limits.\nPlease correct this.\n";
            Displayer.setText(message);
        }

        if (repMaze < 1001 && repMaze > 0) {
            Inputs.repetitionsMaze = repMaze;
        }
        else {
            message = message + "The number of repetitions is not within acceptable limits.\nPlease correct this.\n";
            Displayer.setText(message);
        }
        if (minMaze > 4 && maxMaze < 31 && minMaze <= maxMaze && repMaze < 1001 && repMaze > 0) {
            message = "Specifications recieved. Ready to run program.";
            Displayer.setText(message);
        }

    }

    @FXML
    public void buttonPressRunProgram(ActionEvent event) {
        String message;

        if (Inputs.SmallestMaze > 4 && Inputs.LargestMaze < 31 && Inputs.SmallestMaze <= Inputs.LargestMaze && Inputs.repetitionsMaze < 1001 && Inputs.repetitionsMaze > 0) {
            try {
                Data dataDijk = Model.DijkSolve(Inputs.SmallestMaze, Inputs.LargestMaze, Inputs.repetitionsMaze);
                Data dataAstar = Model.AStarSolve(Inputs.SmallestMaze, Inputs.LargestMaze, Inputs.repetitionsMaze);
                Data dataDepth = Model.DepthSolve(Inputs.SmallestMaze, Inputs.LargestMaze, Inputs.repetitionsMaze);

                AlgoScores Allscores = Model.Score(dataDijk, dataAstar, dataDepth, Inputs.itteWeight, Inputs.timeWeight);

                if(ShortestPathOnly.isSelected() ==true) {
                    Allscores.Depfirst = 0.0;
                }

                message = DataReader(dataDijk, dataAstar, dataDepth, round(Allscores.Dijkstra, 2), round(Allscores.Astar, 2), round(Allscores.Depfirst, 2));
                Displayer.setText(message);

                if (SaveToFileCheck.isSelected() == true) {
                    String files = System.getProperty("user.dir");
                    for (int i = 0; i < 10; i++) {
                        files = files.substring(0, files.length() - 1);
                    }

                    String DijkDFGItteFile = files + "Results\\Results_Dijk_DFG_Itte.csv";
                    String DijkDFGTimeFile = files + "Results\\Results_Dijk_DFG_Time.csv";
                    String DijkElItteFile = files + "Results\\Results_Dijk_El_Itte.csv";
                    String DijkElTimeFile = files + "Results\\Results_Dijk_El_Time.csv";
                    String DijkPrimsItteFile = files + "Results\\Results_Dijk_Prims_Itte.csv";
                    String DijkPrimsTimeFile = files + "Results\\Results_Dijk_Prims_Time.csv";
                    String AstarDFGItteFile = files + "Results\\Results_Astar_DFG_Itte.csv";
                    String AstarDFGTimeFile = files + "Results\\Results_Astar_DFG_Time.csv";
                    String AstarElItteFile = files + "Results\\Results_Astar_El_Itte.csv";
                    String AstarElTimeFile = files + "Results\\Results_Astar_El_Time.csv";
                    String AstarPrimsItteFile = files + "Results\\Results_Astar_Prims_Itte.csv";
                    String AstarPrimsTimeFile = files + "Results\\Results_Astar_Prims_Time.csv";
                    String DFSDFGItteFile = files + "Results\\Results_DFS_DFG_Itte.csv";
                    String DFSDFGTimeFile = files + "Results\\Results_DFS_DFG_Time.csv";
                    String DFSElItteFile = files + "Results\\Results_DFS_El_Itte.csv";
                    String DFSElTimeFile = files + "Results\\Results_DFS_El_Time.csv";
                    String DFSPrimsItteFile = files + "Results\\Results_DFS_Prims_Itte.csv";
                    String DFSPrimsTimeFile = files + "Results\\Results_DFS_Prims_Time.csv";

                    toFileSaver(DijkDFGItteFile, DijkDFGTimeFile, DijkElItteFile, DijkElTimeFile, DijkPrimsItteFile, DijkPrimsTimeFile, dataDijk);
                    toFileSaver(AstarDFGItteFile, AstarDFGTimeFile, AstarElItteFile, AstarElTimeFile, AstarPrimsItteFile, AstarPrimsTimeFile, dataAstar);
                    toFileSaver(DFSDFGItteFile, DFSDFGTimeFile, DFSElItteFile, DFSElTimeFile, DFSPrimsItteFile, DFSPrimsTimeFile, dataDepth);

                    System.out.println("Data saved");
                }

            } catch (IOException E) {
                message = "There was an error running the program";
                Displayer.setText(message);
            }
        }
        else {
            message = "The variables doesn't seem to be within the acceptable limits.\nPlease correct this";
            Displayer.setText(message);
        }
    }

    @FXML
    public void buttonPressDFSEval(ActionEvent event) throws IOException{
        String file = System.getProperty("user.dir");
        for (int i=0; i<10; i++) {
            file = file.substring(0, file.length() - 1);
        }
        file = file + "Results\\Results_Size1.csv";

        if (Inputs.SmallestMaze > 4 && Inputs.LargestMaze < 31  && Inputs.SmallestMaze <= Inputs.LargestMaze && Inputs.repetitionsMaze > 0 && Inputs.repetitionsMaze < 1001) {
            for (int a=Inputs.SmallestMaze; a<Inputs.LargestMaze+1; a++) {

                ArrayList<Integer> Boii = DFmodel.manysolves(a, Inputs.repetitionsMaze);

                if (a<11) {
                    for (int b = 0; b < 5; b++) {
                        file = file.substring(0, file.length() - 1);
                    }
                }
                else {
                    for (int b = 0; b < 6; b++) {
                        file = file.substring(0, file.length() - 1);
                    }
                }
                file = file + Integer.toString(a) + ".csv";

                try (FileWriter fw = new FileWriter(file, true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    for (int x = 0; x < Boii.size(); x++) {
                        out.print(Long.toString(Boii.get(x)) + ",");
                    }
                } catch (IOException e) {
                    Displayer.setText("Failed");
                }
            }

            Displayer.setText("Done");

        }
        else {
            Displayer.setText("Failed");
        }
    }

    @FXML
    public void sliderWeightChange () {
        Inputs.timeWeight = Math.round(sliderWeight.getValue());
        timeWeightText.setText(Long.toString(Inputs.timeWeight));

        Inputs.itteWeight = Math.round(100-sliderWeight.getValue());
        itteWeightText.setText(Long.toString(Inputs.itteWeight));
    }

    public String DataReader (Data dataDijk, Data dataAstar, Data dataDepth, double scoreDijk, double scoreAstar, double scoreDepth) {
        String dijkMin = Integer.toString(dataDijk.smallest);
        String dijkMax = Integer.toString(dataDijk.largest);
        String AstarMin = Integer.toString(dataAstar.smallest);
        String AstarMax = Integer.toString(dataAstar.largest);
        String DepthMin = Integer.toString(dataDepth.smallest);
        String DepthMax = Integer.toString(dataDepth.largest);
        int arrayLength = Inputs.LargestMaze + 1 - Inputs.SmallestMaze;

        String dijkDFSitte = "";
        String dijkDFStime = "";
        String dijkElitte = "";
        String dijkEltime = "";
        String dijkPrimitte = "";
        String dijkPrimtime = "";

        String AstarDFSitte = "";
        String AstarDFStime = "";
        String AstarElitte = "";
        String AstarEltime = "";
        String AstarPrimitte = "";
        String AstarPrimtime = "";

        String DepthDFSitte = "";
        String DepthDFStime = "";
        String DepthElitte = "";
        String DepthEltime = "";
        String DepthPrimitte = "";
        String DepthPrimtime = "";

        for (int i=0; i<arrayLength; i++) {
            dijkDFSitte = dijkDFSitte + Integer.toString(dataDijk.itteDFS[i]) + ", ";
            dijkDFStime = dijkDFStime + Long.toString((dataDijk.timeDFS[i])/1000) + " (µs), ";
            dijkElitte = dijkElitte + Integer.toString(dataDijk.itteEl[i]) + ", ";
            dijkEltime = dijkEltime + Long.toString((dataDijk.timeEl[i])/1000) + " (µs), ";
            dijkPrimitte = dijkPrimitte + Integer.toString(dataDijk.ittePrims[i]) + ", ";
            dijkPrimtime = dijkPrimtime + Long.toString((dataDijk.timePrims[i])/1000) + " (µs), ";

            AstarDFSitte = AstarDFSitte + Integer.toString(dataAstar.itteDFS[i]) + ", ";
            AstarDFStime = AstarDFStime + Long.toString((dataAstar.timeDFS[i])/1000) + " (µs), ";
            AstarElitte = AstarElitte + Integer.toString(dataAstar.itteEl[i]) + ", ";
            AstarEltime = AstarEltime + Long.toString((dataAstar.timeEl[i])/1000) + " (µs), ";
            AstarPrimitte = AstarPrimitte + Integer.toString(dataAstar.ittePrims[i]) + ", ";
            AstarPrimtime = AstarPrimtime + Long.toString((dataAstar.timePrims[i])/1000) + " (µs), ";

            DepthDFSitte = DepthDFSitte + Integer.toString(dataDepth.itteDFS[i]) + ", ";
            DepthDFStime = DepthDFStime + Long.toString((dataDepth.timeDFS[i])/1000) + " (µs), ";
            DepthElitte = DepthElitte + Integer.toString(dataDepth.itteEl[i]) + ", ";
            DepthEltime = DepthEltime + Long.toString((dataDepth.timeEl[i])/1000) + " (µs), ";
            DepthPrimitte = DepthPrimitte + Integer.toString(dataDepth.ittePrims[i]) + ", ";
            DepthPrimtime = DepthPrimtime + Long.toString((dataDepth.timePrims[i])/1000) + " (µs), ";
        }

        String dataString = "Data for Dijkstras: \n" +
                "Smallest maze size solved: " + dijkMin + "\n" +
                "Largest maze size solved: " + dijkMax + "\n" +
                "Iterations used to solve Depth-First generated mazes: " + dijkDFSitte + "\n" +
                "Time used to solve Depth-First generated mazes: " + dijkDFStime + "\n" +
                "Iterations used to solve Ellers generated mazes: " + dijkElitte + "\n" +
                "Time used to solve Ellers generated mazes: " + dijkEltime + "\n" +
                "Iterations used to solve Prims generated mazes: " + dijkPrimitte + "\n" +
                "Time used to solve Prims generated mazes: " + dijkPrimtime + "\n" +
                "Score for Dijkstra's: " + Double.toString(scoreDijk) + " \n" +
                "\nData for A*: \n" +
                "Smallest maze size solved: " + AstarMin + "\n" +
                "Largest maze size solved: " + AstarMax + "\n" +
                "Iterations used to solve Depth-First generated mazes: " + AstarDFSitte + "\n" +
                "Time used to solve Depth-First generated mazes: " + AstarDFStime + "\n" +
                "Iterations used to solve Ellers generated mazes: " + AstarElitte + "\n" +
                "Time used to solve Ellers generated mazes: " + AstarEltime + "\n" +
                "Iterations used to solve Prims generated mazes: " + AstarPrimitte + "\n" +
                "Time used to solve Prims generated mazes: " + AstarPrimtime + "\n" +
                "Score for A*: " + Double.toString(scoreAstar) + " \n" +
                "\nData for Depth-First-Solve: \n" +
                "Smallest maze size solved: " + DepthMin + "\n" +
                "Largest maze size solved: " + DepthMax + "\n" +
                "Iterations used to solve Depth-First generated mazes: " + DepthDFSitte + "\n" +
                "Time used to solve Depth-First generated mazes: " + DepthDFStime + "\n" +
                "Iterations used to solve Ellers generated mazes: " + DepthElitte + "\n" +
                "Time used to solve Ellers generated mazes: " + DepthEltime + "\n" +
                "Iterations used to solve Prims generated mazes: " + DepthPrimitte + "\n" +
                "Time used to solve Prims generated mazes: " + DepthPrimtime + "\n" +
                "Score for Depth-First-Solve: " + Double.toString(scoreDepth) + " \n";
        if (scoreDijk > scoreDepth && scoreDijk > scoreAstar) {
            dataString = dataString + "\n With the current weights, the best suited algorithm is Dijkstras, with a score of: " + Double.toString(scoreDijk);
        }
        if (scoreDepth > scoreDijk && scoreDepth > scoreAstar) {
            dataString = dataString + "\n With the current weights, the best suited algorithm is Depth-First-Solve, with a score of: " + Double.toString(scoreDepth);
        }
        if (scoreAstar > scoreDijk && scoreAstar > scoreDepth) {
            dataString = dataString + "\n With the current weights, the best suited algorithm is A*, with a score of: " + Double.toString(scoreAstar);
        }

        return dataString;
    }

    public void toFileSaver (String DFGItteFile, String DFGTimeFile, String ElItteFile, String ElTimeFile, String PrimsItteFile, String PrimsTimeFile, Data data){

        try (FileWriter fw = new FileWriter(DFGItteFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Integer.toString(data.itteDFS[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "DFGItte");
        }

        try (FileWriter fw = new FileWriter(DFGTimeFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Long.toString(data.timeDFS[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "DFGTime");
        }

        try (FileWriter fw = new FileWriter(ElItteFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Integer.toString(data.itteEl[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "ElItte");
        }

        try (FileWriter fw = new FileWriter(ElTimeFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Long.toString(data.timeEl[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "ElTime");
        }

        try (FileWriter fw = new FileWriter(PrimsItteFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Integer.toString(data.ittePrims[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "PrimsItte");
        }

        try (FileWriter fw = new FileWriter(PrimsTimeFile, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (int x = 0; x < Inputs.LargestMaze - Inputs.SmallestMaze + 1; x++) {
                out.print(Long.toString(data.timePrims[x]) + ",");
            }
        } catch (IOException e) {
            for (int b = 0; b < 11; b++) {
                DFGItteFile = DFGItteFile.substring(0, DFGItteFile.length() - 1);
            }
            Displayer.setText("Failed at " + DFGItteFile + "PrimsTime");
        }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}