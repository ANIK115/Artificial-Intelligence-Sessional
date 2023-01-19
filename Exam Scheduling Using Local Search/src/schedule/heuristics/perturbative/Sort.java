package schedule.heuristics.perturbative;

import base.Graph;
import base.Vertex;

import java.util.Collections;
import java.util.Comparator;

class DayComparator implements Comparator<Vertex> {

    @Override
    public int compare(Vertex o1, Vertex o2) {
        return o1.getDay()-o2.getDay();
    }
}
public class Sort {
    public void sortVerticesBasedOnDay(Graph graph)
    {
        Collections.sort(graph.getVertices(), new DayComparator());
    }
}
