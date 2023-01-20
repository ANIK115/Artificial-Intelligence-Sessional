package base;

import schedule.Scheduler;
import schedule.SolutionLog;
import schedule.heuristics.constractive.*;
import schedule.heuristics.perturbative.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {

    public static void main(String[] args) throws IOException, InterruptedException {
        String studentFile = "Toronto/car-s-91.stu";
        String courseFile = "Toronto/car-s-91.crs";
        Model model = new Model(courseFile, studentFile);
        ConstructiveHeuristic conHeuristic = new LargestDegreeHeuristic();
        Scheduler scheduler = new Scheduler();
        scheduler.setGraph(model);
        scheduler.setConHeuristic(conHeuristic);
        System.out.println("Total days: "+scheduler.schedule());
        Penalty penalty = new LinearPenalty();
        HashMap<Integer, Vertex> map = new HashMap<>();
        for(Vertex v: scheduler.graph.getVertices())
        {
            map.put(v.getNode().getCourseCode(), v);
        }
        System.out.println("Penalty: "+penalty.getPenalty(model.students,map ));
        KempeChain kempeChain = new KempeChain(scheduler.graph);
        kempeChain.setMap();
        kempeChain.setStudents(model.students);

        double finalPenalty = kempeChain.keepReducingPenalty(5000);

        System.out.println("Final Penalty: "+finalPenalty);

        SolutionLog solution = new SolutionLog();
        solution.setGraph(scheduler.graph);
        solution.printSolution(new File(studentFile));
    }
}
