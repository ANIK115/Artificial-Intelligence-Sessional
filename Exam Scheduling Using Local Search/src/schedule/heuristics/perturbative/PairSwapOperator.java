package schedule.heuristics.perturbative;

import base.Graph;
import base.Vertex;
import base.data.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PairSwapOperator{
    Graph graph;
    List<Student> students;
    HashMap<Integer, Vertex> map;
    List<DiffColorPair> pairs;
    Penalty penalty;
    public PairSwapOperator(Graph graph) {
        pairs = new ArrayList<>();
        this.graph = graph;
        students = new ArrayList<>();
        map = new HashMap<>();
    }

    public void setPenalty(Penalty penalty) {
        this.penalty = penalty;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setMap() {
        for(Vertex v : graph.getVertices())
        {
            int courseCode = v.getNode().getCourseCode();
            map.put(courseCode, v);
        }
    }

    public List<DiffColorPair> createDiffColorPairs(Graph graph) {
        List<Vertex> vertices = graph.getVertices();
        for(int i=0; i<vertices.size(); i++)
        {
            Vertex u = vertices.get(i);
            for(int j=0; j<vertices.size(); j++)
            {
                Vertex v = vertices.get(j);
                if(u.getNode().getCourseCode() != v.getNode().getCourseCode())
                {
                    if(!u.getEdges().contains(v) && u.getDay() != v.getDay())
                    {
                        DiffColorPair pair = new DiffColorPair(u,v);
                        pairs.add(pair);
                    }
                }
            }
        }
        return pairs;
    }
    boolean swapPair(Vertex u, Vertex v)
    {
        int day1 = u.getDay();
        int day2 = v.getDay();
        for(Vertex k : u.getEdges())
        {
            if(k.getDay() == day2)
            {
                return false;
            }
        }
        for(Vertex m : v.getEdges())
        {
            if(m.getDay() == day1)
            {
                return false;
            }
        }
        u.setDay(day2);
        v.setDay(day1);
        return true;
    }
    public double reducePenalty(int iterations)
    {
        double totalPenalty = penalty.getPenalty(students, map);
        System.out.println("Current penalty: "+totalPenalty);
        pairs = createDiffColorPairs(graph);
        Random random = new Random(3);
        for(int i=0; i<iterations; i++)
        {
            int index = random.nextInt(pairs.size());
            DiffColorPair pair = pairs.get(index);
            if(swapPair(pair.u, pair.v))
            {
                double newPenalty = penalty.getPenalty(students, map);
                if(newPenalty < totalPenalty)
                {
                    totalPenalty = newPenalty;
//                    System.out.println("Current penalty from pair swap operator "+totalPenalty);
                }else
                {
                    pair.u.setDay(pair.v.getDay());
                    pair.v.setDay(pair.u.getDay());
                }
            }
        }
        return totalPenalty;
    }
}
