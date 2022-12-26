package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        //input to this problem will be a 2D grid with some of the values filled up
        //0 denotes empty cell
        //The 2D grid is n*n
        File file = new File("input.txt");
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

    }
}
