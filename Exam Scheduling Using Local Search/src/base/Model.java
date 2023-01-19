package base;

import base.data.Course;
import base.data.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.LinkPermission;
import java.util.*;

public class Model {
    String courseFilePath ;
    String studentFilePath ;
    public List<Student> students;

    public Model(String courseFilePath, String studentFilePath) {
        this.courseFilePath = courseFilePath;
        this.studentFilePath = studentFilePath;
        students = new ArrayList<>();
    }

    public Graph modelGraph() throws IOException {
        HashMap<Integer, Course> courseMap = new HashMap<Integer, Course>();
        List<Vertex> vertices = new ArrayList<>();
        Scanner scan = new Scanner(new File(courseFilePath));
        while(scan.hasNext())
        {
            String s = scan.nextLine();
            String[] input = s.split(" ");
            Course node = new Course((Integer.parseInt(input[0]))-1, Integer.parseInt(input[1]));
            courseMap.put(node.getCourseCode(), node);
            Vertex v = new Vertex(node);
            v.setDay(-1);
            vertices.add(v);
        }

        int totalCourses = vertices.size();

        int[][] trackEdge = new int[totalCourses][totalCourses];
        scan = new Scanner(new File(studentFilePath));
        int id =0;
        while(scan.hasNext())
        {
            String s = scan.nextLine();
            String[] input = s.split(" ");
            int[] courseOfAStudent = new int[input.length];
            List<Course> courses = new ArrayList<>();
            for(int i=0; i<input.length; i++)
            {
                int courseCode = Integer.parseInt(input[i])-1;
                courseOfAStudent[i] = courseCode;
                courses.add(courseMap.get(courseCode));
            }
            Student student = new Student(id, courses);
            students.add(student);
            for(int i=0; i<input.length-1; i++)
            {
                for(int j=i+1; j<input.length; j++)
                {
                    trackEdge[courseOfAStudent[i]][courseOfAStudent[j]] = 1;
                    trackEdge[courseOfAStudent[j]][courseOfAStudent[i]] = 1;
                }
            }
        }



        for(int i=0; i<totalCourses; i++)
        {
            for(int j=0; j<totalCourses; j++)
            {
                if(trackEdge[i][j] == 1)
                {
                    vertices.get(i).getEdges().add(vertices.get(j));
                }
            }
        }
        System.out.println(vertices.size());
        File file = new File("output.txt");
        FileWriter writer = new FileWriter(file);
        for(Vertex v: vertices)
        {
            writer.write("Course: "+(v.getNode().getCourseCode()+1) +", degree: "+v.getEdges().size()+"\n");
        }
        return new Graph(vertices);
    }
}
