package com.configurations.demo.controller;

import com.configurations.demo.entity.Student;
import com.configurations.demo.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/configurations")
public class ConfigController {

    //Calling Custom Application Properties
    @Value("${teacher.name}")
    private String teacherName;

    @Value("${teacher.qualification}")
    private String teacherQualification;

    @Value("${teacher.location}")
    private String teacherLocation;

    @GetMapping("/teacher")
    public String sayHello() {
        return teacherName + " : " + teacherQualification + " : " + teacherLocation;
    }

    //Configuring Spring Boot - Log Configurations
    private final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @GetMapping("/logs")
    public String log(){
        System.out.println("getmapping /logs is called!!");
        //different log levels - trace[every detail],warn[harmful details],info[normal],error,...
        //logger.warn("log level: WARN"); //this sting "log level: WARN" will be visible in console below like sysout
        //others are -
        logger.info("log level: INFO");
        //logger.error("log level: ERROR");

        String logFilePath = "C:/Users/ACNusrHoneyP/Desktop/logs/configurations.log";
        try {
            return Files.lines(Paths.get(logFilePath)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            logger.error("Failed to read log file", e);
            return "Error reading log file: " + e.getMessage();
        }
    }


    @GetMapping("/students")
    public List<Student> getStudents(){

        List<Student> allStudents = new ArrayList<>();

        allStudents.add(new Student("honey","paliwal"));
        allStudents.add(new Student("himanshu","purohit"));
        allStudents.add(new Student("abhinav","paliwal"));

        System.out.println("All Students are : " + allStudents);

        return allStudents;
    }


    // define @PostConstruct to load the student data ... only once!
    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Poornima", "Patel"));
        theStudents.add(new Student("Mario", "Rossi"));
        theStudents.add(new Student("Mary", "Smith"));
        System.out.println("Students Created by @PostConstruct - loadData()");
    }

    // define endpoint for "/students" - return a list of students
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        System.out.println("JUST RETURNING THE STUDENTS BY - @GetMapping(\"/getAllStudents\")");
        return theStudents;
    }

    //PATH VARIABLE
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // just index into the array list ... lets keep it simple

        Student studentFound = theStudents.get(studentId);

        System.out.println("Student with id : " + studentId + " is " + studentFound.getFirstName() + " " + studentFound.getLastName());

        return studentFound;
    }

    //E X C E P T I O N      H A N D L I N G
    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/getStudents/{studentId}")
    public Student getStudents(@PathVariable int studentId) {


        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }



}
