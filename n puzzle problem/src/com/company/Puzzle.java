package com.company;

import java.util.*;

public class Puzzle {

    int [][] finalState;
    int size;
    HashMap<Integer, Tile>finalMap;
    PriorityQueue<Node> priorityQueue;  //to use in dijkstra
    HashSet<int[][]> uniqueStates;
    String currentHeuristic;    //if it is manhattan distance or hamming distance
    int expanded = 0;
    int explored = 0;
    Node goalNode;
    Node initialNode;

    public Puzzle(int size) {
        priorityQueue = new PriorityQueue<>(new NodeComparator());
        uniqueStates = new HashSet<>();
        finalMap = new HashMap<>();
        this.size = size;
        int k = size * size;
        int count = 1;
        finalState = new int[size][size];
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                finalState[i][j] = count % k;
                Tile tile = new Tile(i,j);
                finalMap.put(count%k, tile);
                count++;
            }
        }
    }

    public void setInitialNode(int[][]board)
    {
        initialNode = new Node(board, 0);
        initialNode.parent = null;
        initialNode.move = "Initial State";
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                if(board[i][j] == 0)
                {
                    initialNode.blankTile = new Tile(i,j);
                    break;
                }
            }
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

    public void generateNodes(Node node)
    {
        int x = node.blankTile.x;
        int y = node.blankTile.y;
        //there can be maximum 4 nodes generated
        if(node.blankTile.x < node.board.length-1)  //we can move a tile left
        {
            int[][]leftBoard = node.board.clone();
            leftBoard[x][y] = leftBoard[x+1][y];
            Node leftNode = new Node(leftBoard, node.g+1);
            leftNode.move = "Left";
            if(!uniqueStates.contains(leftBoard))
            {
                uniqueStates.add(leftBoard);
                leftNode.parent = node;
                leftNode.blankTile = new Tile(x+1, y);
                if(currentHeuristic.equalsIgnoreCase("Hamming"))
                {
                    leftNode.calculateHammingDistance(leftBoard);
                }
                if(currentHeuristic.equalsIgnoreCase("Manhattan"))
                {
                    leftNode.calculateManhattanDistance(finalMap);
                }
                leftNode.updateF();
                priorityQueue.add(leftNode);
                expanded++;
            }
        }else if(node.blankTile.x > 0)  //Right Move
        {
            int[][]rightBoard = node.board.clone();
            rightBoard[x][y] = rightBoard[x-1][y];
            Node rightNode = new Node(rightBoard, node.g+1);
            rightNode.move = "Right";
            if(!uniqueStates.contains(rightBoard))
            {
                uniqueStates.add(rightBoard);
                rightNode.parent = node;
                rightNode.blankTile = new Tile(x-1, y);
                if(currentHeuristic.equalsIgnoreCase("Hamming"))
                {
                    rightNode.calculateHammingDistance(rightBoard);
                }
                if(currentHeuristic.equalsIgnoreCase("Manhattan"))
                {
                    rightNode.calculateManhattanDistance(finalMap);
                }
                rightNode.updateF();
                priorityQueue.add(rightNode);
                expanded++;
            }
        }else if(node.blankTile.y > 0)  //Up Move
        {
            int[][]upBoard = node.board.clone();
            upBoard[x][y] = upBoard[x][y-1];
            Node upNode = new Node(upBoard, node.g+1);
            upNode.move = "Up";
            if(!uniqueStates.contains(upBoard))
            {
                uniqueStates.add(upBoard);
                upNode.parent = node;
                upNode.blankTile = new Tile(x, y-1);
                if(currentHeuristic.equalsIgnoreCase("Hamming"))
                {
                    upNode.calculateHammingDistance(upBoard);
                }
                if(currentHeuristic.equalsIgnoreCase("Manhattan"))
                {
                    upNode.calculateManhattanDistance(finalMap);
                }
                upNode.updateF();
                priorityQueue.add(upNode);
                expanded++;
            }
        }else if(node.blankTile.y < node.board.length-1)  //we can move a tile left
        {
            int[][]downBoard = node.board.clone();
            downBoard[x][y] =downBoard[x][y+1];
            Node downNode = new Node(downBoard, node.g+1);
            downNode.move = "Down";
            if(!uniqueStates.contains(downBoard))
            {
                uniqueStates.add(downBoard);
                downNode.parent = node;
                downNode.blankTile = new Tile(x, y+1);
                if(currentHeuristic.equalsIgnoreCase("Hamming"))
                {
                    downNode.calculateHammingDistance(downBoard);
                }
                if(currentHeuristic.equalsIgnoreCase("Manhattan"))
                {
                    downNode.calculateManhattanDistance(finalMap);
                }
                downNode.updateF();
                priorityQueue.add(downNode);
                expanded++;
            }
        }
    }

    public boolean dijkstra()
    {
        if(currentHeuristic.equalsIgnoreCase("Hamming"))
        {
            initialNode.calculateHammingDistance(finalState);
            initialNode.updateF();
        }
        if(currentHeuristic.equalsIgnoreCase("Manhattan"))
        {
            initialNode.calculateManhattanDistance(finalMap);
            initialNode.updateF();
        }
        priorityQueue.add(initialNode);
        uniqueStates.add(initialNode.board);
        while(!priorityQueue.isEmpty())
        {
            Node currentNode = priorityQueue.poll();
            explored++;
            if(isGoalReached(currentNode.board))
            {
                return true;
            }
            generateNodes(currentNode);
        }
        return false;
    }
    
    public boolean isGoalReached(int[][]board)
    {
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board.length; j++)
            {
                if(board[i][j] != finalState[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void printSolution()
    {
        Stack<Node> solution = new Stack<>();
        Node current = goalNode;
        while(current != null)
        {
            solution.push(current);
            current = current.parent;
        }
        System.out.println("---------------Solution----------------");
        while (!solution.isEmpty())
        {
            System.out.println(solution.peek().move);
            solution.peek().printState();
            solution.pop();
        }

        System.out.println("Total expanded nodes: "+expanded);
        System.out.println("Total explored nodes: "+explored);
    }

}
