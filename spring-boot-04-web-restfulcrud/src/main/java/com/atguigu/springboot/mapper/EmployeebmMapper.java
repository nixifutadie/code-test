package com.atguigu.springboot.mapper;

import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.entities.Employeebm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeebmMapper {

    @Select("select * from employeebm ")
    public List<Employeebm> findAll();

    @Delete(" delete from employeebm where bm_id= #{bm_id} ")
    public void delete(int bm_id);

    @Insert(" INSERT INTO employeebm (bm_id,lastName,departmentyx,discipline,department) VALUES(#{bm_id},#{lastName},#{departmentyx},#{discipline},#{department}) ")
    public int save(Employeebm employeebm);

    @Update("UPDATE employeebm SET bm_id=#{bm_id},lastName=#{lastName},departmentyx=#{departmentyx},discipline=#{discipline},department=#{department} WHERE bm_id=#{bm_id} ")
    public int update(Employeebm employeebm);

    @Select("select * from employeebm where bm_id= #{bm_id} ")
    public Employeebm get(int bm_id);
}
