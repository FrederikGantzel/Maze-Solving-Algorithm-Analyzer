# Maze-Solving-Algorithm-Analyzer
The program I wrote for my 4th semester project at Roskilde University "Evaluating the Efficiency of Maze-Solving Algorithms"

Link to the project paper: https://rucforsk.ruc.dk/ws/files/64897417/Algorithms_for_maze_solving.pdf

Authors: Frederik Gantzel, Poul Thrane, Max Thrane

## Installation
The project is made with IntelliJ IDEA, and thus it is easily run using this application. I'm sure there is some way to run it from the command line, but I have not been able to figure it out. Additionally, the project also uses JavaFX for the user interface. JavaFX comes bundled with Java 10 or below, but requires a seperate download and installation for Java versions above 10. I haven't been able to make the program work with a seperate installation of JavaFX, and thus I believe it is easiest to simply download and use a Java version below 10 (I used Java 8).

To run the program, first download the "Maze_Analyzer" folder. Open the "Controller" folder inside the Maze_Analyzer folder with IntelliJ IDEA. If you are already using Java 8, you should be able to press "run" to run the program right away. The GUI should look like this:

![image](https://user-images.githubusercontent.com/91853323/215628157-f0215b99-f667-4c44-a870-6cbcf2bb30eb.png)

If it doesn't work, maybe you have another version of Java installed, so I'll show you how to install and use Java 8 for this project. First, in the upper left corner of  go to __File >> Project_Structure >> Project Settings >> Project__ and then in the __SDK__ dropdown menu, choose "1.8" (which I think is short for Java 8, but I'm not sure, anyway, 1.8 worked for me). Also, in the __Language Level__ dropdown menu, choose "8 - Lambdas, type annotations etc."

![image](https://user-images.githubusercontent.com/91853323/215626343-13493e93-67d4-455c-8175-6fb9d7e6b384.png)

Next, go to __File >> Project_Structure >> Project Settings >> Modules__, an in the __Sources__ tab, set __Language Level__ to "8 - Lambdas, type annotations etc.". Then, flip over to the __Dependencies__ tab and set __Module SDK__ to "1.8".

![image](https://user-images.githubusercontent.com/91853323/215626671-d4a4c857-71f1-4821-8edc-b9eb7045f6a2.png)
![image](https://user-images.githubusercontent.com/91853323/215626764-1fe0d523-748c-47cb-87f2-ba4ad6393adb.png)

Lastly, go to __File >> Settings >> Build, Execution, Deployment >> Compiler >> Java Compiler__, and set __Project bytecode version__ to "8". Then, just below __Per-module bytecode version__, click the + sign, and add "Controller". Finally, set the __Target bytecode version__ of Controller to "8"

![image](https://user-images.githubusercontent.com/91853323/215627649-c3ac1d09-1a9a-4584-a8ff-916135973abd.png)

I fould this video to be helpful when setting all of this up: https://www.youtube.com/watch?v=yIMN0ief3b8&ab_channel=SagarS

## Usage
The program alazyses the efficiency of three different maze-solving algorithms, and gathers and outputs some data about the algorithms. The user can choose to save the data to a file, which should appear in the "Results" folder. The full description of the program can be found in the project paper.


