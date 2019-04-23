package com.zhaoyan.ssm.controller;

import com.zhaoyan.ssm.domain.Student;
import com.zhaoyan.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//@RestController可以将对象转换成json
@RestController
public class StudentController {
    @Autowired
    private StudentService service;

    @RequestMapping("/add")
    public String add(String username,String password){
        service.add(username,password);
        return "add success";
    }

    @RequestMapping("/del")
    public String del(int id){
        service.del(id);
        return "del success";
    }
    @RequestMapping("/upd")
    public String upd(Student stu){
        //dead code
        Student student = new Student("张三","123");
        service.upd(student);
        return  "upd success";
    }

    @RequestMapping("/get")
    public Student get(int id){
        return service.get(id);
    }

    @RequestMapping("/lis")
    public List<Student> list(){
        return  service.list();
    }











}
