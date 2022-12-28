package base;

import backtrack.BacktrackSolver;
import forwardcheck.ForwardChecking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //input to this problem will be a 2D grid with some of the values filled up
        //0 denotes empty cell
        //The 2D grid is n*n
        File file = new File("d-10-06.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        int grid[][] = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                grid[i][j] = scanner.nextInt();
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                System.out.print(grid[i][j]+"  ");
            }
            System.out.println();
        }
        BacktrackSolver bsolver = new BacktrackSolver();
        ForwardChecking fcsolver = new ForwardChecking();
//        fcsolver.createCSP(n, grid, "VAH5");
//        fcsolver.solve();

        bsolver.createCSP(n, grid, "VAH5");
        bsolver.solve();
    }
}
