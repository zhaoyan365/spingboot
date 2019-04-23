package com.zhaoyan.ssm.dao;

import com.zhaoyan.ssm.domain.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface StudentDao {
    //增
    @Insert("INSERT INTO user(username,password) values(#{username},#{password})")
    void add(String username,String password);
    //删
    @Delete("DELETE FROM user WHERE id=#{id}")
    void del(int id);
    //改
    @Update("UPDATE user SET username=#{username} WHERE id=#{id}")
    void upd(Student stu);

    //根据id查询
    @Select("SELECT * FROM user WHERE id=#{id}")
    Student get(int id);

    //查询所有
    @Select("SELECT * FROM user")
    List<Student> list();




}
