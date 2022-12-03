package com.company;

public class Puzzle {

    int [][] finalState;
    int size;

    public Puzzle(int size) {
        this.size = size;
        int k = size * size;
        int count = 1;
        finalState = new int[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                finalState[i][j] = count % k;
                count++;
                System.out.print(finalState[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int inversionCount(int[][]board)
    {
        int n = board.length * board.length;
        int[] arr = new int[n];
        int k = 0;
        //making it a 1D array
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                arr[k++] = board[i][j];
            }
        }
        //counting inversions
        int count = 0;
        for(int i=0; i<n-1; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                if(arr[i]!=0 && arr[j]!=0 && arr[i] > arr[j])
                    count++;
            }
        }
        return count;
    }

    //Finding position of blank tile from bottom
    public int findBlankPosition(int[][] board)
    {
        //starting from bottom right corner
        for(int i=board.length-1; i>=0; i--)
        {
            for(int j=board.length-1; j>=0; j--)
            {
                if(board[i][j] == 0)
                    return board.length-i;
            }
        }
        return -1;
    }

    public boolean isSolvable(int [][]board)
    {
        int inversion = inversionCount(board);
        if(board.length % 2 == 1)
        {
            return inversion%2 == 0;
        }else
        {
            int blankPosition = findBlankPosition(board);
            if(blankPosition % 2 == 1)
            {
                return inversion%2 == 0;
            }else
            {
                return inversion%2 == 1;
            }
        }
    }
}
