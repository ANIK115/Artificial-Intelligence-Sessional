package schedule.heuristics.perturbative;

import base.Vertex;
import base.data.Student;

import java.util.HashMap;
import java.util.List;

public interface Penalty {
    public double getPenalty(List<Student> students, HashMap<Integer, Vertex> map);
}
