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
            for(int j=0; j<n; j++)
            {
                if(i == variable.row && j == variable.col)
                    continue;
                if(currentGrid[i][j] == value)
                    return false;
            }
        }
        return true;
    }
}
