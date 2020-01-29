package com.company;

import javax.swing.*;
import java.util.ArrayList;

public class StudentLists {
    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s){
        students.add(s);
    }//ADD STUDENT OBJECT TO ARRAY

    public Student searchstudentInfobyID(int studentnumber) {//SEARCH STUDENT BY ID
        for(Student s:students){
            if(s.getStudentId() == studentnumber){
                return s;
            }
        }
        return null;
    }

    public void deleteStudentInfobyID(int studentnumber){//DELETE STUDENT BY ID
        for(Student s:students){
            if(s.getStudentId() == studentnumber){
                students.remove(s);
                JOptionPane.showMessageDialog(null, "ENTRY WITH STUDENT ID" + s.getStudentId() + " HAS REMOVED FROM THE LIST");
                return;
            }
        }
    }
    public String[] printStudentInfo(){//PRINT STUDENT INFO
        String[] sg = new String[arrayLength()];
        int i = 1;
        for (Student s:students){
            sg[i-1] = String.valueOf(s);
            i++;
        }
        return sg;
    }

    public int arrayLength(){//RETURN LENGTH OF STUDENT OBJECT ARRAY AS INTEGER
        int length = 0;
        for (Student s:students){
            length++;
        }
        return length;
    }
}
