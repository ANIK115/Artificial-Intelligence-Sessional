package schedule.heuristics.perturbative;

import base.Graph;
import base.Vertex;
import base.data.Course;
import base.data.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KempeChain {
    List<DiffColorPair> pairs;
    Graph graph;
    List<Student> students;
    HashMap<Integer, Vertex> map;
    Penalty penalty;
    FileWriter writer;

    public KempeChain(Graph graph) {
        pairs = new ArrayList<>();
        this.graph = graph;
        students = new ArrayList<>();
        map = new HashMap<>();
    }

    public void setWriter(FileWriter writer) {
        this.writer = writer;
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

    public List<DiffColorPair> createDiffColorPairs()
    {
        int n = graph.getVertices().size();
        int outerCount = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(i==j)
                    continue;
                if(graph.getVertices().get(i).getDay() != graph.getVertices().get(j).getDay())
                {
                    Vertex u = graph.getVertices().get(i);
                    Vertex v = graph.getVertices().get(j);
                    pairs.add(new DiffColorPair(u,v));
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
        int day1 = u.getDay();
        while(!queue.isEmpty())
        {
            Vertex v = queue.poll();
            for(Vertex k : v.getEdges())
            {
                courseCode = k.getNode().getCourseCode();
                if(!track.containsKey(courseCode))
                {
                    if(day2 == k.getDay() || day1 == k.getDay())
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
        double prevPenalty = penalty.getPenalty(students, map);
        for(Vertex v : kempechain)
        {
            //System.out.println("Course code: "+v.getNode().getCourseCode()+1 +", day: "+v.getDay());
            if(v.getDay() == day1)
                v.setDay(day2);
            else if(v.getDay() == day2)
                v.setDay(day1);
        }
        double newPenalty = penalty.getPenalty(students, map);
        if(newPenalty > prevPenalty)
        {
            for(Vertex v : kempechain)
            {
                //System.out.println("Course code: "+v.getNode().getCourseCode()+1 +", day: "+v.getDay());
                if(v.getDay() == day1)
                    v.setDay(day2);
                else if(v.getDay() == day2)
                    v.setDay(day1);
            }
        }

    }
    public void revertColorSwap(int day1, int day2, List<Vertex> kempechain)
    {
        colorSwap(day1, day2, kempechain);
    }

    public double keepReducingPenalty(int iterations) throws IOException {
        pairs = createDiffColorPairs();
        Random random = new Random(3);
        double totalPenalty = penalty.getPenalty(students, map);
        writer.write("Penalty before kempe chain: "+totalPenalty+"\n");
        writer.flush();
        for(int i=0; i<iterations; i++)
        {
            int ind = random.nextInt(pairs.size());
            DiffColorPair pair = pairs.get(ind);
            List<Vertex> kempechain = getKempeChain(graph.getVertices(), pair.u, pair.v.getDay());
            colorSwap(pair.u.getDay(), pair.v.getDay(), kempechain);
        }
        totalPenalty = penalty.getPenalty(students, map);
        writer.write("Penalty after kempe chain: "+totalPenalty+"\n");
        writer.flush();


        PairSwapOperator pso = new PairSwapOperator(this.graph);
        pso.setPenalty(this.penalty);
        pso.setMap();
        pso.setStudents(this.students);
        double penaltyAfterPairSwap = pso.reducePenalty(10000);
        writer.write("Penalty after running Pair swap operator: "+penaltyAfterPairSwap+"\n");
        writer.flush();
        return penaltyAfterPairSwap;
    }

}
