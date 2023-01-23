package schedule;

import base.Graph;
import base.Vertex;
import base.data.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FreeSlot {
    public int nextFreeSlot(Vertex v)
    {
        List<Vertex> edges = v.getEdges();
        List<Integer> days = new ArrayList<>();
        for(int i=0; i<edges.size(); i++)
        {
            Vertex course = edges.get(i);
            if(course.getDay() != -1)
                days.add(course.getDay());
        }
        Collections.sort(days);
        int day = 1;
        for(int i=0; i< days.size(); i++)
        {
            if(day == days.get(i))
                day++;
        }
        return day;
    }
}
