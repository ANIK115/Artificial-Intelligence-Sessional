package base;

import java.util.List;

public class Graph {
    private List<Vertex> vertices;

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    @Override
    public String toString() {
        String s = "";
        for(Vertex v: vertices)
        {
            s+="course: "+v.getNode().getCourseCode();
            for(Vertex u : v.getEdges())
            {
                s+= " -> "+u.getNode().getCourseCode();
            }
            s+= "\n";
        }
        return s;
    }
}
