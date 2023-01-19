package schedule.heuristics.constractive;

import base.Degree;
import base.Graph;
import base.Vertex;


public class LargestDegreeHeuristic implements ConstructiveHeuristic{
    int count = 0;
    @Override
    public Vertex getNextUncoloredVertex(Graph graph) {
        int largest = -1;
        int courseCode = -1;
        Degree degree = new Degree();
        for(Vertex v : graph.getVertices())
        {
            if(v.getDay() == -1)
            {
                int deg = degree.getDegree(v);
                if(deg > largest)
                {
                    largest = deg;
                    courseCode = v.getNode().getCourseCode();
                }
            }
        }
        count++;
        if(largest == -1)
            return null;
        return graph.getVertices().get(courseCode);
    }
}
