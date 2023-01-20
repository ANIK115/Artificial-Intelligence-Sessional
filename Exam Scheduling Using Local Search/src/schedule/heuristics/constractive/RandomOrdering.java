package schedule.heuristics.constractive;

import base.Graph;
import base.Vertex;

import java.util.Collections;
import java.util.Random;

public class RandomOrdering implements ConstructiveHeuristic{
    int count = -1;
    @Override
    public Vertex getNextUncoloredVertex(Graph graph) {

        if(count == -1)
        {
            Collections.shuffle(graph.getVertices(), new Random(3));
        }
        count++;
        if(count < graph.getVertices().size())
        {
            return graph.getVertices().get(count);
        }
        return null;
    }
}
