package base;

import java.util.List;

public class VariableOrderHeuristic {
    public static Variable getNextVariable(List<Variable> variableList, String heuristic, int[][]grid)
    {
        if(heuristic.equalsIgnoreCase("VAH1"))
        {
            return heuristicVAH1(variableList);
        }else if(heuristic.equalsIgnoreCase("VAH2"))
        {
            return heuristicVAH2(variableList, grid);
        }else if(heuristic.equalsIgnoreCase("VAH3"))
        {
            return heuristicVAH3(variableList, grid);
        }
        else
        {
            return heuristicVAH5(variableList);
        }
    }

    private static Variable heuristicVAH3(List<Variable> variableList, int[][] grid) {
        int min = Integer.MAX_VALUE;
        Variable smallestDomainVariable = null;
        for (Variable v: variableList)
        {
            if(v.domain.size() < min)
            {
                smallestDomainVariable = v;
                min = v.domain.size();
            }else if(v.domain.size() == min)
            {
                if(countDegree(v, grid) > countDegree(smallestDomainVariable, grid))
                {
                    smallestDomainVariable = v;
                    min = v.domain.size();
                }
            }
        }
        return smallestDomainVariable;
    }

    private static int countDegree(Variable v, int[][]grid)
    {
        int degree = 0;
        for(int i=0; i<grid.length; i++)
        {
            if(grid[v.row][i] == 0)
            {
                degree++;
            }

        }
        for(int i=0; i<grid.length; i++)
        {
            if(grid[i][v.col] == 0)
            {
                degree++;
            }
        }
        degree -= 2;
        return degree;
    }

    //heuristicVAH1 is choosing the variable with the smallest domain
    private static Variable heuristicVAH1(List<Variable> variableList) {
        int min = Integer.MAX_VALUE;
        Variable smallestDomainVariable = null;
        for (Variable v: variableList)
        {
            if(v.domain.size() < min)
            {
                smallestDomainVariable = v;
                min = v.domain.size();
            }
        }
        return smallestDomainVariable;
    }

    private static Variable heuristicVAH2(List<Variable> variableList, int[][] grid) {

        int maxDeg = Integer.MIN_VALUE;
        Variable maxDegVariable = null;
        for(Variable v : variableList)
        {
            int degree = 0;
            for(int i=0; i<grid.length; i++)
            {
                if(grid[v.row][i] == 0)
                {
                    degree++;
                }

            }
            for(int i=0; i<grid.length; i++)
            {
                if(grid[i][v.col] == 0)
                {
                    degree++;
                }
            }
            degree -= 2;
            if(degree > maxDeg)
            {
                maxDeg = degree;
                maxDegVariable = v;
            }
        }
        return maxDegVariable;
    }

    private static Variable heuristicVAH5(List<Variable> variableList) {
        return new Variable();
    }

}
