package com.zhaoyan.ssm.service;

import com.zhaoyan.ssm.domain.Student;
import com.zhaoyan.ssm.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class StudentServiceImpl implements  StudentService {
    @Autowired
    private StudentDao dao;

    @Override
    public void add(String username, String password) {
        dao.add(username,password);
    }

    @Override
    public void del(int id) {
        dao.del(id);
    }

    @Override
    public void upd(Student stu) {
        dao.upd(stu);
    }

    @Override
    public Student get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Student> list() {
        return dao.list();
    }
}
