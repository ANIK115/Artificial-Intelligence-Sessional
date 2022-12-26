package base;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CSP {
    List<Variable> variableList;
    LatinSquareConstraint constraint;
    HashMap<Variable, Boolean> assignment;
    String varOrderHeuristic;
    CSP parent;


    public CSP(List<Variable> variableList, String varOrderHeuristic, HashMap<Variable, Boolean> assignment) {
        this.variableList = variableList;
        this.constraint = new LatinSquareConstraint();
        this.assignment = assignment;
        this.varOrderHeuristic = varOrderHeuristic;
    }

}
