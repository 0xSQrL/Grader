package ws.fruitbowl.grader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;

public class Student {
    private int studentID;
    private String name;

    private LinkedList<Homework> homeworkFiles;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name.replace(' ', '_');
        homeworkFiles = new LinkedList<>();
    }

    public void addHomework(Homework homework){
        homeworkFiles.add(homework);
        homework.setStudent(this);
    }

    public void composeHomeworkFiles(){

        File folder = new File(homeworkFiles.getFirst().getHomeworkfile().getParentFile().toString() + "/" + studentID + '-' + name);
        folder.mkdir();
        for(Homework h: homeworkFiles) {
            Path target = Paths.get(folder.toString() + "/" + h.homeworkfile.getName().substring(h.homeworkfile.getName().lastIndexOf('-') + 2));
            try {
                Files.move(h.homeworkfile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

    public int getStudentID(){
        return studentID;
    }

    @Override
    public boolean equals(Object obj) {
        Student s = (Student) obj;
        if(s != null){
            return (s.studentID == this.studentID);
        }
        return false;
    }
}
