package base;

import base.data.Course;

import java.util.List;

public class Degree {
    public int getDegree(Vertex v)
    {
        List<Vertex> edges = v.getEdges();
        return edges.size();
    }
    public int getUncoloredDegree(Vertex v)
    {
        int count = 0;
        for(Vertex u : v.getEdges())
        {
            if(u.getDay() == -1)
                count++;
        }
        return count;
    }
}
