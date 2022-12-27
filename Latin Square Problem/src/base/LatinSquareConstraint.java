package base;

public class LatinSquareConstraint implements Constraint{
    int[][] currentGrid;

    public LatinSquareConstraint() {

    }

    public void setCurrentGrid(int[][] currentGrid) {
        this.currentGrid = currentGrid;
    }

    @Override
    public boolean isConstraintSatisfied(Variable variable, int value) {
        int n = currentGrid[0].length;

        for(int i=0; i<n; i++)
        {
            if(i == variable.col)
                continue;
            if(currentGrid[variable.row][i] == value)
                return false;
        }

        for(int i=0; i<n; i++)
        {
            if(i == variable.row)
                continue;
            if(currentGrid[i][variable.col] == value)
                return false;
        }
        return true;
    }
}
