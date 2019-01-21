package com.yxj.lambda;

import com.yxj.Vo.Student;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author:yuxj
 * @descriptio
 * @create:2018/9/30 下午8:36
 */
public class Collector {

    public static void main(String[] args) {
        List<Student> studentList = Student.getStudentList();

        //将学生的班级名拼接

        String className = studentList.stream().map(Student::getClassName)
                .distinct().collect(Collectors.joining(","));
        System.out.println(className);


        //计算学生年龄总数
        int totalAge = studentList.stream().collect(Collectors.reducing(0,Student::getAge,(i,j) -> i+j));
        System.out.println(totalAge);

        //计算最大年龄的学生
        Optional<Student> maxAge = studentList.stream().collect(Collectors.maxBy(Comparator.comparingInt(Student::getAge)));
        System.out.println(maxAge.get().getAge());

        //根据班级分组
        Map<String,List<Student>> stringListMap = studentList.stream().collect(Collectors.groupingBy(Student::getClassName));

        System.out.println(stringListMap.keySet());





    }


}
