package ws.fruitbowl.grader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    static ArrayList<String> allowedImports;

    public static void main(String[] args){
        File homeworkDir = new File("D:\\Downloads\\Lab 5 Download Dec 5, 2017 1009 PM");
        File[] files = homeworkDir.listFiles();

        allowedImports = new ArrayList<>();
        allowedImports.add("java.util.Scanner");
        ArrayList<Student> students = new ArrayList<>(31);
        ArrayList<Assignment> assignments = new ArrayList<>(10);

        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().endsWith(".java")) {
                int studentID = Integer.parseInt(files[i].getName().substring(0, 6));
                String assignmentName = files[i].getName().substring(files[i].getName().lastIndexOf('-') + 2).toUpperCase();
                boolean studentFound  = false;
                boolean assignmentFound  = false;
                Homework homework = new Homework(files[i]);

                for (int j = 0; j < students.size(); j++) {
                    if(students.get(j).getStudentID() == studentID)
                    {
                        students.get(j).addHomework(homework);
                        studentFound = true;
                        break;
                    }
                }
                for (int j = 0; j < assignments.size(); j++) {
                    if(assignments.get(j).getAssignmentName().equals(assignmentName))
                    {
                        assignments.get(j).addHomework(homework);
                        assignmentFound = true;
                        break;
                    }
                }
                if(!studentFound){
                    String studentName = files[i].getName().substring(17, 44);
                    studentName = studentName.substring(0, studentName.indexOf('-') - 1);
                    System.out.println("Student Added: " + studentName  + " - " + studentID);
                    students.add(new Student(studentID, studentName));
                    students.get(students.size() - 1).addHomework(homework);
                }
                if(!assignmentFound){
                    System.out.println("Assignment Added: " + assignmentName);
                    assignments.add(new Assignment(assignmentName));
                    assignments.get(assignments.size() - 1).addHomework(homework);
                }
                //System.out.println(Arrays.toString(homework.characterOccurances));
            }
        }
        System.out.println("Found " + students.size() + " students.");

        for(Assignment a: assignments){
            System.out.println(a.assignmentName + ": ");
            //a.checkCheating();
        }

        for (Student s: students)
           s.composeHomeworkFiles();

    }
}
