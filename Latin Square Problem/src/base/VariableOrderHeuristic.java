package base;

import java.util.List;

public class VariableOrderHeuristic {
    public static Variable getNextVariable(List<Variable> variableList, String heuristic)
    {
        if(heuristic.equalsIgnoreCase("VAH1"))
        {
            return heuristicVAH1(variableList);
        }else if(heuristic.equalsIgnoreCase("VAH2"))
        {
            return heuristicVAH2(variableList);
        }else
        {
            return heuristicVAH5(variableList);
        }
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

    private static Variable heuristicVAH2(List<Variable> variableList) {
        return new Variable();
    }

    private static Variable heuristicVAH5(List<Variable> variableList) {
        return new Variable();
    }

}
