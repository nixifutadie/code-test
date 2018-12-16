package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entities.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from employee ")
    public List<Employee> findAll();

    @Insert(" INSERT INTO employee (lastName,password,email,gender,department,birth) VALUES(#{lastName},#{password},#{email},#{gender},#{department},#{birth}) ")
    public int save(Employee employee);

    @Delete(" delete from employee where id= #{id} ")
    public void delete(int id);

    @Update("UPDATE employee SET password=#{password},lastName=#{lastName},email=#{email},gender=#{gender},department=#{department},birth=#{birth} WHERE id=#{id} ")
    public int update(Employee employee);

    @Select("select * from employee where id= #{id} ")
    public Employee get(int id);

    @Select("select * from employee where password= #{password} and lastName=#{lastName}")
    public Employee login(@Param("password") String passsowrd,@Param("lastName") String lastName);
}
