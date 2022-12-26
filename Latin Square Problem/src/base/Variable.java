package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class ValueOrderHeuristic implements Comparator<Integer>
{
    List<Variable> variableList;
    int value;

    public ValueOrderHeuristic(List<Variable> variableList) {
        this.variableList = variableList;
    }

    public boolean isInDomain(Variable variable, int value)
    {
        for(int i=0; i<variable.domain.size(); i++)
        {
            if(variable.domain.get(i) == value)
                return true;
        }
        return false;
    }

    public int countVariablesLosingADomainValue(int val)
    {
        int count = 0;
        for(Variable v : variableList)
        {
            if(isInDomain(v, val))
                count++;
        }
        return count;
    }
    @Override
    public int compare(Integer o1, Integer o2) {
        return countVariablesLosingADomainValue(o1) - countVariablesLosingADomainValue(o2);
    }
}

public class Variable {
    //row and col denotes the position of the variable
    int row;
    int col;
    int value;
    List<Integer> domain;

    public Variable() {
    }

    public Variable(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    //same value cannot occur in a row or in a column
    public void createDomain(int n, int[][] grid)
    {
        domain = new ArrayList<>();
        boolean[] mark = new boolean[n];
        for(int i=0; i<n; i++)
            mark[i] = false;

        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(grid[i][j] != 0)
                {
                    mark[grid[i][j]-1] = true;
                }
            }
        }

        for(int i=1; i<=n; i++)
        {
            if(!mark[i-1])
            {
                domain.add(i);
            }
        }
    }

    public void sortDomain(int val, List<Variable> variableList)
    {
        Collections.sort(domain, new ValueOrderHeuristic(variableList));
    }
}
