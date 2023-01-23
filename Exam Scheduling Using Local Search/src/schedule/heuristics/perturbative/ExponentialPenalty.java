package schedule.heuristics.perturbative;

import base.Vertex;
import base.data.Student;

import java.util.HashMap;
import java.util.List;

public class ExponentialPenalty implements Penalty{
    @Override
    public double getPenalty(List<Student> students, HashMap<Integer, Vertex> map) {
        double penalty = 0;
        for(Student student : students)
        {
            for(int i=0; i< student.getCourses().size()-1; i++)
            {
                int courseCode1 = student.getCourses().get(i).getCourseCode();
                for(int j=i+1; j<student.getCourses().size(); j++)
                {
                    int courseCode2 = student.getCourses().get(j).getCourseCode();
                    int n = Math.abs(map.get(courseCode1).getDay()- map.get(courseCode2).getDay()-1);
                    if(n <=5)
                    {
                        penalty += Math.pow(2, (5-n));
                    }
                }
            }
        }
        return penalty / students.size();
    }
}
