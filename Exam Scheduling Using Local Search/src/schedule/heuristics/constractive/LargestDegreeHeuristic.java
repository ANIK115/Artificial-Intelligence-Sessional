package schedule.heuristics.constractive;

import base.Degree;
import base.Graph;
import base.Vertex;

import java.util.Collections;
import java.util.Comparator;

class DegreeComparator implements Comparator<Vertex> {
    Degree degree = new Degree();
    @Override
    public int compare(Vertex o1, Vertex o2) {
        return degree.getDegree(o2) - degree.getDegree(o1);
    }
}
public class LargestDegreeHeuristic implements ConstructiveHeuristic{
    int count = -1;
    @Override
    public Vertex getNextUncoloredVertex(Graph graph) {
        if(count == -1)
        {
            Collections.sort(graph.getVertices(), new DegreeComparator());
        }
        count++;
        if(count < graph.getVertices().size())
        {
            return graph.getVertices().get(count);
        }
        return null;
    }
}
