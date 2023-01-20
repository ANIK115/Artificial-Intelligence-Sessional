package schedule;

import base.Graph;
import base.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SolutionLog {
    public Graph graph;
    public void setGraph(Graph graph)
    {
        this.graph = graph;
    }
    public void printSolution(File file, File outputFile) throws IOException {
        FileWriter writer = new FileWriter(outputFile);
        HashMap<Integer, Vertex> courseMap = new HashMap<>();
        for(Vertex u : graph.getVertices())
        {
            courseMap.put(u.getNode().getCourseCode(), u);
        }

        Scanner scanner = new Scanner(file);
        int i=1;
        boolean flag = false;
        while(scanner.hasNext())
        {
            String s = scanner.nextLine();
            String[] courses = s.split(" ");
            writer.write("Student: "+(i++)+"\n");
            HashMap<Integer, Integer> map = new HashMap<>();
            for(String c : courses)
            {
                int code = Integer.parseInt(c)-1;
                writer.write("Course: "+(code+1)+", exam day: "+courseMap.get(code).getDay()+"----");
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
            writer.write("\n");
            writer.flush();
            if(flag)
                break;
        }
    }
}
