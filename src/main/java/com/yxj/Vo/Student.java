package com.yxj.Vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:yuxj
 * @descriptio
 * @create:2018/9/30 下午8:22
 */
public class Student {

    private String name;



    private String number;

    private String className;

    private String school;

    private int age;



    public Student(){

    }


    public Student(String name, String number, String className, String school,int age) {
        this.name = name;
        this.number = number;
        this.className = className;
        this.school = school;
        this.age = age;
    }

    public static List<Student> getStudentList(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("a","1111","A班","文理",10));
        studentList.add(new Student("b","2222","A班","文理",11));
        studentList.add(new Student("c","3333","A班","文理",23));
        studentList.add(new Student("d","4444","A班","文理",10));
        studentList.add(new Student("e","5555","A班","文理",11));
        studentList.add(new Student("f","6666","B班","文理",10));
        studentList.add(new Student("g","7777","B班","文理",9));
        studentList.add(new Student("h","8888","B班","文理",13));
        studentList.add(new Student("i","9999","B班","文理",11));

        return studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
