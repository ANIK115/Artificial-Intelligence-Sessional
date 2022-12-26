package backtrack;

import base.CSP;
import base.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BacktrackSolver {
    CSP csp;
    int[][] solution;

    public BacktrackSolver() {
    }

    public void createCSP(int n, int[][]grid, String varOrderHeuristic)
    {
        List<Variable> variableList = new ArrayList<>();
        HashMap<Variable, Boolean> assignment = new HashMap<>();
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(grid[i][j] == 0)
                {
                    Variable variable = new Variable(i, j, 0);
                    variable.createDomain(n, grid);
                    variableList.add(variable);
                    assignment.put(variable, false);
                }
            }
        }
        csp = new CSP(variableList, varOrderHeuristic, assignment);
        solution = grid;
    }

    public int[][] solveLatinSquare(int[][]grid)
    {
        

        return null;    //failure
    }
}
