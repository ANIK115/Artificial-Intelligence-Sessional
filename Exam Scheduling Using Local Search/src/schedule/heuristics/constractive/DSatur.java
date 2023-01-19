package schedule.heuristics.constractive;

import base.Degree;
import base.Graph;
import base.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//degree of saturation
//number of different colors used by the neighbor
class DegreeOfSaturation implements Comparator<Vertex> {

    public int degreeOfSaturation(Vertex v)
    {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(Vertex u : v.getEdges())
        {
            int day = u.getDay();
            //if day is -1, it is unscheduled
            if(day == -1)
                continue;

            int courseCode = u.getNode().getCourseCode();
            if(!map.containsKey(day))
            {
                map.put(day, courseCode);
            }
        }
        return map.size();
    }

    @Override
    public int compare(Vertex v1, Vertex v2) {
        int diff = degreeOfSaturation(v1) - degreeOfSaturation(v2);
        if(diff == 0)
        {
            Degree degree = new Degree();
            return degree.getDegree(v1)- degree.getDegree(v2);
        }
        return diff;
    }
}
public class DSatur implements ConstructiveHeuristic {

    @Override
    public Vertex getNextUncoloredVertex(Graph graph) {
        Collections.sort(graph.getVertices(), new DegreeOfSaturation());
        for(int i=graph.getVertices().size()-1; i>=0; i--)
        {
            if(graph.getVertices().get(i).getDay() == -1)
                return graph.getVertices().get(i);
        }
        return null;
    }
}
