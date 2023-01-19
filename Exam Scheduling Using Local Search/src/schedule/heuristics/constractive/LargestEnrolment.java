package schedule.heuristics.constractive;

import base.Graph;
import base.Vertex;

import java.util.Collections;
import java.util.Comparator;

class EnrolmentComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o2.getNode().getTotalStudents() - o1.getNode().getTotalStudents();
    }
}
public class LargestEnrolment implements ConstructiveHeuristic{
    @Override
    public Vertex getNextUncoloredVertex(Graph graph) {
        Collections.sort(graph.getVertices(), new EnrolmentComparator());
        for(Vertex v : graph.getVertices())
        {
            if(v.getDay() == -1)
                return v;
        }
        return null;
    }
}
