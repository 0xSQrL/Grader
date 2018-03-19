package ws.fruitbowl.grader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Homework {
    File homeworkfile;
    Assignment assignment;
    Student s;

    int[] characterOccurances  = new int[256];

    public Homework(File fileLocation){
        homeworkfile = fileLocation;
        readCharacters();
    }

    public File getHomeworkfile() {
        return homeworkfile;
    }

    public void setHomeworkfile(File homeworkfile) {
        this.homeworkfile = homeworkfile;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public Student getStudent() {
        return s;
    }

    public void setStudent(Student s){
        this.s = s;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public int difference(Homework h){
        int diff = 0;
        for (int i = 0; i < characterOccurances.length; i++) {
            diff += Math.abs(h.characterOccurances[i] - this.characterOccurances[i]);
        }
        return diff;
    }

    private void readCharacters(){
        try {

            Scanner s = new Scanner(homeworkfile);
            while(s.hasNextLine()){
                String line = s.nextLine() + '\n';
                for (int i = 0; i < line.length(); i++) {
                    characterOccurances[line.charAt(i)] ++;
                }
            }
            s.close();

        }catch (FileNotFoundException fnfe){

        }
    }
}
