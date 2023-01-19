package base;

import base.data.Course;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private Course node;
    private List<Vertex> edges;
    private int day;

    public Vertex() {
    }

    public Vertex(Course node) {
        this.node = node;
        this.edges = new ArrayList<>();
    }

    public Course getNode() {
        return node;
    }

    public void setNode(Course node) {
        this.node = node;
    }

    public List<Vertex> getEdges() {
        return edges;
    }

    public void setEdges(List<Vertex> edges) {
        this.edges = edges;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
