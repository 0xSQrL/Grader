package ws.fruitbowl.grader;

import java.io.File;
import java.util.ArrayList;

public class Assignment {
    String assignmentName;
    ArrayList<Homework> homeworks;

    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
        homeworks = new ArrayList<>();
    }

    public void addHomework(Homework homeworkfile){
        homeworks.add(homeworkfile);
        homeworkfile.setAssignment(this);
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void checkCheating(){
        int[] values = new int[homeworks.size() * homeworks.size()];
        int index = 0;
        long total = 0;
        for(Homework h: homeworks){
            for(Homework j: homeworks){
                if(!h.s.equals(j.s)){
                    values[index] = h.difference(j);
                    total += values[index];
                    index++;
                    //System.out.println(h.s.getStudentID() + " - " + j.s.getStudentID() + " : "  +h.difference(j));
                }
            }
        }
        double totalMeans = 0;
        double average = total / index;
        for (int i = 0; i < index; i++) {
            totalMeans += Math.pow(average - values[i],2);
        }
        double stdev = Math.sqrt(totalMeans/index);
        System.out.println("Average: " + average + " Standard Deviation: " + stdev);
        for(Homework h: homeworks){
            for(Homework j: homeworks){
                if(!h.s.equals(j.s)) {
                    int diff = Math.abs(h.difference(j));
                    if (diff < (average - stdev) / 2)
                        System.out.println(h.s.getStudentID() + " - " + j.s.getStudentID() + " : " + diff);
                }
            }
        }
    }
}
