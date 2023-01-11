package base;

import backtrack.BacktrackSolver;
import forwardcheck.ForwardChecking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void Driver(Scanner scanner, String solverName, String heuristic, File output) throws IOException {
        int n = scanner.nextInt();
        int grid[][] = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                grid[i][j] = scanner.nextInt();
            }
        }
        if(solverName.equalsIgnoreCase("Backtrack"))
        {
            BacktrackSolver bsolver = new BacktrackSolver();
            bsolver.createCSP(n, grid, heuristic);
            bsolver.solve(output);
        }else if(solverName.equalsIgnoreCase("Forward Check"))
        {

            ForwardChecking fcsolver = new ForwardChecking();
            fcsolver.createCSP(n, grid, heuristic);
            fcsolver.solve(output);
        }
    }

    public static void main(String[] args) throws IOException {
        //input to this problem will be a 2D grid with some of the values filled up
        //0 denotes empty cell
        //The 2D grid is n*n
        List<String> filenames = new ArrayList<>();
//        filenames.add("d-10-01.txt");
//        filenames.add("d-10-06.txt");
//        filenames.add("d-10-07.txt");
//        filenames.add("d-10-08.txt");
        filenames.add("d-10-09.txt");
        filenames.add("d-15-01.txt");

        for (String fileName: filenames)
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            Driver(scanner, "Backtrack", "VAH2", new File("outputs/output BT+VAH2 "+fileName));
        }





    }
}
