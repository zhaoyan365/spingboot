## 项目名称：springboot入门 ##
## 描述: 基于springboot快速搭建ssm框架项目 ##
## 任务: ##
	1. 通过http://start.spring.io官网实现ssm框架项目搭建(方式一)
	2. 通过ideal实现ssm框架项目的快速搭建(方式二)
	3. 完成对用户的增、删、改、查
## 实现: ##
	1  通过http://start.spring.io官网实现ssm框架项目搭建

		- 将下载下来的压缩包解压，导入ideal或eclipse中
	- ![](https://github.com/zhaoyan365/springboot/blob/master/method1.png)

 	2. 通过ideal实现ssm框架项目的快速搭建
	 	
		file--->new--->project--->Spring Initializr---next--->Group填写com.zhaoyan,Artificat填写ssm--->next--->web中勾选web--->SQL中勾选jdb、mysql、mybatis---next

  	- ![](https://github.com/zhaoyan365/springboot/blob/master/method2.png)
  	- ![](https://github.com/zhaoyan365/springboot/blob/master/structure.png)
  	
	


**domain层**

		package com.zhaoyan.ssm.domain;
		@Getter
		@Setter
		@ToString
		@NoArgsConstructor
		@AllArgsConstructor
		public class Student {
			private int id;
			private String username;
			private String password;
			public Student(String username, String password) {
				this.username = username;
				this.password = password;
			}
		}


**dao层**

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

**service层**

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

**serviceImpl层**
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

**controller层**
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


**SsmApplication**
    package com.zhaoyan.ssm;
    
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.transaction.annotation.EnableTransactionManagement;
    
    @EnableTransactionManagement
    @SpringBootApplication
    public class SsmApplication {
    
	    public static void main(String[] args) {
	  	  SpringApplication.run(SsmApplication.class, args);
	    }
    
    }


**application.properties层**
    
    server.port=80
    
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    spring.datasource.url=jdbc:mysql:///student?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=root
    
    #mybatis映射(sql语句中的参数值与实体类对应)
    mybatis.type-aliases-package=com.zhaoyan.ssm.domain
    
    #配置日志
    logging.level.com.zhaoyan.ssm.dao=debug
    logging.path=/
    logging.file=stu.log


**user.sql脚本**
    
    Create Table
    
    CREATE TABLE `user` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `username` varchar(10) NOT NULL,
      `password` varchar(10) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `id` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

## 测试 ##
	
1. 启动SsmApplication
2. 地址栏：
3. 
		- localhost/add?username=wangwu&password=000 
 		- localhost/del?id=1
 		- localhost/upd?username=zhaoliu&password=000&id=2
 		- localhost/get?id=2
 		- localhost/lis
			
