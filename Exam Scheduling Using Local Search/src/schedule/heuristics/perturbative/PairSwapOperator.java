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

    public boolean isThereEdge(Vertex a, List<Vertex> edges)
    {
        for(Vertex u : edges)
        {
            if(u.getNode().getCourseCode() == a.getNode().getCourseCode())
            {
                return true;
            }
        }
        return false;
    }

    public List<DiffColorPair> createDiffColorPairs(Graph graph) {
        List<Vertex> vertices = graph.getVertices();
        for(int i=0; i<vertices.size(); i++)
        {
            Vertex u = vertices.get(i);
            for(int j=i+1; j<vertices.size(); j++)
            {
                Vertex v = vertices.get(j);
                if(u.getDay() != v.getDay())
                {
                    DiffColorPair p = new DiffColorPair(u,v);
                    pairs.add(p);
                }
            }
        }
        return pairs;
    }
    boolean swapPair(Vertex u, Vertex v)
    {
        boolean flag = true;
        for(Vertex k : u.getEdges())
        {
            if(k.getDay() == v.getDay())
            {
                flag = false;
            }
        }
        for(Vertex m : v.getEdges())
        {
            if(m.getDay() == u.getDay())
            {
                flag = false;
            }
        }
        if(flag)
        {
            int day = u.getDay();
            u.setDay(v.getDay());
            v.setDay(day);
        }

        return flag;
    }
    public double reducePenalty(int iterations)
    {
        double totalPenalty = penalty.getPenalty(students, map);
        System.out.println("Current penalty: "+totalPenalty);
        pairs = createDiffColorPairs(graph);
        Random random = new Random(10);
        for(int i=0; i<iterations; i++)
        {
            int index = random.nextInt(pairs.size());
            DiffColorPair pair = pairs.get(index);
            Vertex u = pair.u;
            Vertex v = pair.v;
            if(swapPair(u,v))
            {
                double newPenalty = penalty.getPenalty(students, map);

                if(newPenalty < totalPenalty)
                {
                    totalPenalty = newPenalty;
                }else
                {
                    int dayu = u.getDay();
                    int dayv = v.getDay();
                    u.setDay(dayv);
                    v.setDay(dayu);
                }
            }
        }
        return totalPenalty;
    }
}
