package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private HashMap<String,Student> studentMap;
    private HashMap<String,Teacher> teacherMap;
    private HashMap<String, List<String>> studentTeacherMap;

    public StudentRepository(){
        this.studentMap = new HashMap<>();
        this.teacherMap = new HashMap<>();
        this.studentTeacherMap = new HashMap<>();
    }
    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student,String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> studentsList = new ArrayList<>();
            if(studentTeacherMap.containsKey(teacher)){
                studentsList = studentTeacherMap.get(teacher);
            }
            studentsList.add(student);

            studentTeacherMap.put(teacher,studentsList);
        }
    }

    public Student getStudentByName(String name){
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherMap.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        List<String> studentList = new ArrayList<>();
        if(!studentTeacherMap.containsKey(teacher)){
            return studentList;
        }

        return studentTeacherMap.get(teacher);
    }

    public List<String> getAllStudents(){
        List<String> allStudents = new ArrayList<>();
        for(String student : studentMap.keySet()){
            allStudents.add(student);
        }

        return allStudents;
    }

    public void deleteTeacherByName(String teacher){
        if(studentTeacherMap.containsKey(teacher)){
            List<String> students = studentTeacherMap.get(teacher);

            for(String student : students){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }

            studentTeacherMap.remove(teacher);
        }

        if(teacherMap.containsKey(teacher)){
            teacherMap.remove(teacher);
        }
    }

    public void deleteAllTeachers(){

        teacherMap.clear();

        List<String> students = new ArrayList<>();

        for(String teacher : studentTeacherMap.keySet()){
            for(String student : studentTeacherMap.get(teacher)){
                students.add(student);
            }
        }

        studentTeacherMap.clear();

        for(String student : students){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }
    }
}
