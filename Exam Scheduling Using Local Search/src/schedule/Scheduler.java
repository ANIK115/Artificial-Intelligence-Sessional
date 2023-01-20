package schedule;

import base.Graph;
import base.Model;
import base.Vertex;
import schedule.heuristics.constractive.ConstructiveHeuristic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Scheduler {
    public Graph graph;
    public ConstructiveHeuristic conHeuristic;

    public void setGraph(Model model) throws IOException {
        this.graph = model.modelGraph();
    }

    public void setConHeuristic(ConstructiveHeuristic heuristic)
    {
        this.conHeuristic = heuristic;
    }
    public int schedule() throws FileNotFoundException {
        int totalDays = 0;
        while(true)
        {
            Vertex v = conHeuristic.getNextUncoloredVertex(graph);
            if(v == null)
                break;
            int day = new FreeSlot().nextFreeSlot(v);
            v.setDay(day);
            if(totalDays < day)
                totalDays = day;

        }
        System.out.println(graph.getVertices().size());
        HashMap<Integer, Vertex> courseMap = new HashMap<>();
        for(Vertex u : graph.getVertices())
        {
            courseMap.put(u.getNode().getCourseCode(), u);
        }
        Scanner scanner = new Scanner(new File("Toronto/yor-f-83.stu"));
        int i=1;
        boolean flag = false;
        while(scanner.hasNext())
        {
            String s = scanner.nextLine();
            String[] courses = s.split(" ");
            System.out.println("Student: "+(i++));
            HashMap<Integer, Integer> map = new HashMap<>();
            for(String c : courses)
            {
                int code = Integer.parseInt(c)-1;
                System.out.print("Course: "+(code+1)+", exam day: "+courseMap.get(code).getDay()+"----");
                if(map.containsKey(courseMap.get(code).getDay()))
                {
                    System.out.println("Error in your solve!");
                    flag = true;
                    break;
                }else
                {
                    map.put(courseMap.get(code).getDay(), code);
                }
            }
            System.out.println();
            if(flag)
                break;
        }
        return totalDays;
    }
}
