package com.zhaoyan.ssm.service;

import com.zhaoyan.ssm.domain.Student;

import java.util.List;

public interface StudentService {
    void add(String username,String password);
    void del(int id);
    void upd(Student stu);
    Student get(int id);
    List<Student> list();
}
