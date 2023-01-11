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

//    public boolean isInDomain(Variable variable, int value)
//    {
//        for(int i=0; i<variable.domain.size(); i++)
//        {
//            if(variable.domain.get(i) == value)
//                return true;
//        }
//        return false;
//    }

    public int countVariablesLosingADomainValue(int val)
    {
        int count = 0;
        for(Variable v : variableList)
        {
            if(v.domain.contains(val))
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
    public int row;
    public int col;

    public List<Integer> domain;

    public Variable() {
    }

    public Variable(int row, int col, int value) {
        this.row = row;
        this.col = col;
        domain = new ArrayList<>();
    }

    //same value cannot occur in a row or in a column
    public void createDomain(int n, int[][] grid)
    {
        boolean[] mark = new boolean[n];
        for(int i=0; i<n; i++)
            mark[i] = false;

        for(int i=0; i<n; i++)
        {
            if(grid[row][i] != 0)
            {
                mark[grid[row][i]-1] = true;
            }
        }
        for(int i=0; i<n; i++)
        {
            if(grid[i][col] !=0)
            {
                mark[grid[i][col]-1] = true;
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

    //This is for Least Constraint Value
    public void sortDomain(List<Variable> variableList)
    {
        Collections.sort(domain, new ValueOrderHeuristic(variableList));
    }

    public void showDomain()
    {
        System.out.println("Variable description: ");
        System.out.println("row: "+row+", col: "+col);
        System.out.println("Domain: ");
        for(int v: domain)
            System.out.print(v+" ");
        System.out.println();
    }

    @Override
    public String toString() {
        return "Variable{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
