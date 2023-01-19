package schedule.heuristics.perturbative;

import base.Graph;
import base.Vertex;
import base.data.Student;

import java.util.*;

public class KempeChain {
    List<List<DiffColorPair>> pairs;
    Graph graph;
    List<Student> students;
    HashMap<Integer, Vertex> map;

    public KempeChain(Graph graph) {
        pairs = new ArrayList<>();
        this.graph = graph;
        students = new ArrayList<>();
        map = new HashMap<>();
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

    public List<List<DiffColorPair>> createDiffColorPairs()
    {
        int n = graph.getVertices().size();
        Sort sort = new Sort();
        sort.sortVerticesBasedOnDay(graph);
        int outerCount = 0;
        for(int i=0; i<n-1; i++)
        {
            pairs.add(new ArrayList<>());
            for(int j=i+1; j<n; j++)
            {
                if(graph.getVertices().get(i).getDay() != graph.getVertices().get(j).getDay())
                {
                    Vertex u = graph.getVertices().get(i);
                    Vertex v = graph.getVertices().get(j);
                    pairs.get(outerCount).add(new DiffColorPair(u,v));
                }
            }
            outerCount++;
        }
        return pairs;
    }

    public List<Vertex> getKempeChain (List<Vertex> vertices, Vertex u, int day2)
    {
        List<Vertex> list = new ArrayList<>();
        HashMap<Integer, Boolean> track = new HashMap<>();
        int courseCode = u.getNode().getCourseCode();
        track.put(courseCode, true);
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(u);
        list.add(u);
        while(!queue.isEmpty())
        {
            Vertex v = queue.poll();
            for(Vertex k : v.getEdges())
            {
                courseCode = k.getNode().getCourseCode();
                if(!track.containsKey(courseCode))
                {
                    if(day2 == k.getDay())
                    {
                        queue.add(k);
                        track.put(courseCode, true);
                        list.add(k);
                    }

                }
            }
        }
        return list;
    }

    public void colorSwap(int day1, int day2, List<Vertex> kempechain)
    {
        for(Vertex v : kempechain)
        {
            if(v.getDay() == day1)
                v.setDay(day2);
            else
                v.setDay(day1);
        }
    }
    public void revertColorSwap(int day1, int day2, List<Vertex> kempechain)
    {
        colorSwap(day1, day2, kempechain);
    }

    public double keepReducingPenalty(int iterations)
    {
        pairs = createDiffColorPairs();
        Random random = new Random(3);
        Penalty penalty = new Penalty();
        double totalPenalty = penalty.getPenalty(students, map);
        for(int i=0; i<iterations; i++)
        {
            int ind1 = i%pairs.size();
            if(pairs.get(ind1).size() == 0)
                continue;
            int ind2 = random.nextInt(pairs.get(ind1).size());
            DiffColorPair pair = pairs.get(ind1).get(ind2);
            List<Vertex> kempechain = getKempeChain(graph.getVertices(), pair.u, pair.v.getDay());
            colorSwap(pair.u.getDay(), pair.v.getDay(), kempechain);
            double newPenalty = penalty.getPenalty(students, map);

            if(newPenalty < totalPenalty)
            {
                System.out.println("Penalty: "+newPenalty);
                totalPenalty = newPenalty;
            }

            else
                revertColorSwap(pair.u.getDay(), pair.v.getDay(), kempechain);

        }
        return totalPenalty;
    }
}
