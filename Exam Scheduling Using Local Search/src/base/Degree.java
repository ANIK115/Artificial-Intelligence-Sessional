package base;

import base.data.Course;

import java.util.List;

public class Degree {
    public int getDegree(Vertex v)
    {
        List<Vertex> edges = v.getEdges();
        return edges.size();
    }
}
