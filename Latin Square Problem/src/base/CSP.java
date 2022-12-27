package base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CSP {
    public List<Variable> variableList;
    public LatinSquareConstraint constraint;
    public HashMap<Variable, Boolean> assignment;
    public String varOrderHeuristic;

    public CSP(List<Variable> variableList, String varOrderHeuristic, HashMap<Variable, Boolean> assignment) {
        this.variableList = variableList;
        this.constraint = new LatinSquareConstraint();
        this.assignment = assignment;
        this.varOrderHeuristic = varOrderHeuristic;
    }

}
