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
        return totalDays;
    }
}
