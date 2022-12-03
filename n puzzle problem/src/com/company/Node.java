package com.company;

import java.util.Comparator;
import java.util.HashMap;

class Tile
{
    int x;
    int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


class NodeComparator implements Comparator<Node>
{
    @Override
    public int compare(Node o1, Node o2) {
        return o1.f - o2.f;
    }
}

public class Node {
    int[][] board;
    Node parent;
    int g;  //distance from current node to root node
    int h;  //number of misplaced tiles by comparing the current state and goal state
    int f;  //f = g + h
    String move;    //To store the moves: Up,Down,Right,Left
    Tile blankTile;    //to keep the position of the blank tile

    public Node(int[][] board, int g) {
        this.board = board;
        this.g = g;
    }

    public void printState()
    {
        System.out.println("Move: "+move);
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public void calculateHammingDistance(int[][]finalState)
    {
        h = 0;
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                if(board[i][j] != 0 && board[i][j] != finalState[i][j])
                {
                    h++;
                }
            }
        }
    }

    public void updateF()
    {
        f = g + h;
    }

    public void calculateManhattanDistance(HashMap<Integer, Tile> finalMap)
    {
        h = 0;
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                int t = board[i][j];
                Tile tile = finalMap.get(t);
                h += Math.abs(i-tile.x) + Math.abs(j-tile.y);
            }
        }
    }
}
