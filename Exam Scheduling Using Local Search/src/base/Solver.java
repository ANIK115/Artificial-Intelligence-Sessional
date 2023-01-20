package base;

import schedule.Scheduler;
import schedule.heuristics.constractive.*;
import schedule.heuristics.perturbative.ExponentialPenalty;
import schedule.heuristics.perturbative.KempeChain;
import schedule.heuristics.perturbative.LinearPenalty;
import schedule.heuristics.perturbative.Penalty;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {

    public static void main(String[] args) throws IOException {
        String studentFile = "Toronto/yor-f-83.stu";
        String courseFile = "Toronto/yor-f-83.crs";
        Model model = new Model(courseFile, studentFile);
        ConstructiveHeuristic conHeuristic = new RandomOrdering();
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
        double finalPenalty = kempeChain.keepReducingPenalty(2000);
        System.out.println("Finally: "+finalPenalty);
    }
}
