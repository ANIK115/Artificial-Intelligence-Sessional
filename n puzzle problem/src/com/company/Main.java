package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code here
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);
        String st = scan.nextLine();
        int n = Integer.parseInt(st);
        System.out.println("n: "+n);
        int[][]board = new int[n][n];


        for(int i=0; i<n; i++)
        {
            st = scan.nextLine();
            String[] s = st.split(" ");
            for(int j=0; j<n; j++)
            {
                if(s[j].equalsIgnoreCase("*"))
                {
                    board[i][j] = 0;
                }else
                {
                    board[i][j] = Integer.parseInt(s[j]);
                }
            }

        }

        System.out.println("=======Solving using Manhattan heuristic==========");
        Puzzle puzzle = new Puzzle(board.length);
        puzzle.currentHeuristic = "Manhattan";
        puzzle.setInitialNode(board);
        puzzle.initialNode.calculateManhattanDistance(puzzle.finalMap);
        puzzle.solvePuzzle(board);

        System.out.println("=========Solving using Hamming heuristic=========");
        Puzzle p = new Puzzle(board.length);
        p.currentHeuristic = "Hamming";
        p.setInitialNode(board);
        p.initialNode.calculateManhattanDistance(puzzle.finalMap);
        p.solvePuzzle(board);
    }
}
