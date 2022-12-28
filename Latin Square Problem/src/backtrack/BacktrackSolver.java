package backtrack;

import base.CSP;
import base.Variable;
import base.VariableOrderHeuristic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BacktrackSolver {
    CSP csp;
    int[][] solution;
    int totalNodes;
    int backtracks;

    public BacktrackSolver() {
        totalNodes = 0;
        backtracks = 0;
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
                }
            }
        }
        csp = new CSP(variableList, varOrderHeuristic, assignment);
        solution = grid;
    }


    public void updateDomain(List<Variable>variableList, Variable var, int value, List<Variable> updatedVariables)
    {
        for(Variable v : variableList)
        {
            if(v.row == var.row || v.col == var.col)
            {
                if(v.domain.contains(value))
                {
                    v.domain.remove((Object)value);
                    updatedVariables.add(v);
                }
            }
        }
    }

    public void addValueToDomain(List<Variable> variableList,int value, List<Variable> updatedVariables)
    {
        for(Variable v : variableList)
        {
            if(updatedVariables.contains(v))
            {
                v.domain.add(value);
            }
        }
    }

    public boolean solveLatinSquare(CSP csp)
    {
        totalNodes++;
        //base condition: if the unassigned variable list is empty, then we've reached to a solution
        if(csp.variableList.size() == 0)
        {
            return true;
        }
        Variable var = VariableOrderHeuristic.getNextVariable(csp.variableList, csp.varOrderHeuristic, solution);
//        System.out.println("Variable got: "+var);
        var.sortDomain(csp.variableList);

        for(int value : var.domain)
        {
//            System.out.println("value: "+value+", for variable: "+var);
            csp.constraint.setCurrentGrid(this.solution);
            if(csp.constraint.isConstraintSatisfied(var, value))
            {
//                System.out.println("Constraint satisfied");
                solution[var.row][var.col] = value;
                csp.variableList.remove(var);
                List<Variable> updatedVariables = new ArrayList<>();
                updateDomain(csp.variableList,var, value, updatedVariables);

                if(solveLatinSquare(csp))
                {
                    return true;
                }else
                {
                    addValueToDomain(csp.variableList, value, updatedVariables);
                    csp.variableList.add(var);
                    solution[var.row][var.col] = 0;
                }
            }
        }

        backtracks++;
        return false;    //failure
    }

    public void solve()
    {
        long start = System.currentTimeMillis();
        solveLatinSquare(this.csp);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;

            System.out.println("Printing solution for Backtracking and "+csp.varOrderHeuristic+": ");
            for(int i=0; i<solution.length; i++)
            {
                for(int j=0; j<solution.length; j++)
                {
                    System.out.print(solution[i][j]+"  ");
                }
                System.out.println();
            }
            System.out.println("Total Nodes: "+totalNodes);
            System.out.println("Total Backtracks: "+backtracks);
            System.out.println("Total time: "+elapsedTime+" ms");


    }

}
