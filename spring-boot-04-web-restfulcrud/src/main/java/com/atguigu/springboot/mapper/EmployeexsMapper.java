package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.entities.Employeebm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface EmployeexsMapper {

    @Select("select * from employee where lastName= #{lastName} ")
    public Employee findgetxs(String lastName);

    @Update("UPDATE employee SET password=#{password},lastName=#{lastName},email=#{email},gender=#{gender},department=#{department},birth=#{birth} WHERE id=#{id} ")
    public int update(Employee employee);

    @Select("select * from employee where id= #{id} ")
    public Employee get(int id);

    @Delete(" delete from employee where id= #{id} ")
    public void delete(int id);






    @Select("select * from employeebm where lastName= #{lastName} ")
    public Employeebm findgetbm(String lastName);

    @Update("UPDATE employeebm SET lastName=#{lastName},departmentyx=#{departmentyx},discipline=#{discipline},department=#{department} WHERE lastName=#{lastName} ")
    public int updatebm(Employeebm employeebm);

    @Select("select * from employeebm where bm_id= #{bm_id} ")
    public Employeebm getbm(int bm_id);

    @Delete(" delete from employeebm where bm_id= #{bm_id} ")
    public void deletebmxx(int id);
}
