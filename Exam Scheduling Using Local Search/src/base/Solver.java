package base;

import schedule.Scheduler;
import schedule.SolutionLog;
import schedule.heuristics.constractive.*;
import schedule.heuristics.perturbative.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Solver {

    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> stuFiles = new ArrayList<>();
        List<String> crsFiles = new ArrayList<>();
        FileWriter writer = new FileWriter(new File("data output.txt"), true);
        stuFiles.add("Toronto/car-f-92.stu");
        stuFiles.add("Toronto/car-s-91.stu");
        stuFiles.add("Toronto/kfu-s-93.stu");
        stuFiles.add("Toronto/tre-s-92.stu");
        stuFiles.add("Toronto/yor-f-83.stu");

        crsFiles.add("Toronto/car-f-92.crs");
        crsFiles.add("Toronto/car-s-91.crs");
        crsFiles.add("Toronto/kfu-s-93.crs");
        crsFiles.add("Toronto/tre-s-92.crs");
        crsFiles.add("Toronto/yor-f-83.crs");


        for(int i=0; i<crsFiles.size(); i++)
        {
            String courseFile = crsFiles.get(i);
            String studentFile = stuFiles.get(i);
            writer.write("Solution for file "+studentFile+" using Random Ordering and Linear Penalty\n");
            writer.flush();
            Model model = new Model(courseFile, studentFile);
            ConstructiveHeuristic conHeuristic = new RandomOrdering();
            Scheduler scheduler = new Scheduler();
            scheduler.setGraph(model);
            scheduler.setConHeuristic(conHeuristic);

            writer.write("Total days: "+scheduler.schedule()+"\n");
            writer.flush();

            Penalty penalty = new LinearPenalty();
            HashMap<Integer, Vertex> map = new HashMap<>();
            for(Vertex v: scheduler.graph.getVertices())
            {
                map.put(v.getNode().getCourseCode(), v);
            }

            KempeChain kempeChain = new KempeChain(scheduler.graph);
            kempeChain.setMap();
            kempeChain.setStudents(model.students);
            kempeChain.setPenalty(penalty);
            kempeChain.setWriter(writer);

            writer.write("Penalty: "+penalty.getPenalty(model.students,map)+"\n");
            writer.flush();

            double finalPenalty = kempeChain.keepReducingPenalty(5000);

            writer.write("Final Penalty: "+finalPenalty+"\n");
            writer.write("------------------------------------------\n\n");
            writer.flush();

            SolutionLog solution = new SolutionLog();
            solution.setGraph(scheduler.graph);
            solution.printSolution(new File(studentFile), new File(studentFile.replace(".stu", "-output.txt")));

        }

    }
}
