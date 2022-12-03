package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] board = {
                {0, 1, 3},
                {4, 2, 5},
                {7, 8, 6}
        };

        Puzzle puzzle = new Puzzle(board.length);
        System.out.println("Solvable : "+puzzle.isSolvable(board));
    }
}
