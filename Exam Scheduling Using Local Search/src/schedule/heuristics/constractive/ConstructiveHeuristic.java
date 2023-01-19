package schedule.heuristics.constractive;

import base.Graph;
import base.Vertex;

public interface ConstructiveHeuristic {
    public Vertex getNextUncoloredVertex(Graph graph);
}
