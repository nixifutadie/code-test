package com.atguigu.springboot.controller;

import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.entities.Employeebm;
import com.atguigu.springboot.mapper.EmployeexsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;


@Controller
public class EmployeexsController {

    @Autowired
    EmployeexsMapper employeexsMapper;



    @GetMapping("/empxs")
    public String toAddPage(Model model){
        return "empxs/list";
    }

    //显示学生个人信息
    @GetMapping("/empxsxx")
    public String toEditPage(Model model, HttpSession session){
        String sessionName = (String) session.getAttribute("loginUser");
        Employee employee = employeexsMapper.findgetxs(sessionName);
        model.addAttribute("emps",employee);
        return "empxs/listxsxx";
    }


    //来到学生添加页面
    @GetMapping("/empgr")
    public String toAddPage(){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "empxs/add";
    }

    //来到修改页面，查出当前学生，在页面回显
    @GetMapping("/empgr/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeexsMapper.get(id);
        model.addAttribute("emp",employee);
        return "empxs/add";
    }

    //学生修改；需要提交学生id；
    @PutMapping("/empgr")
    public String updateEmployee(Employee employee){
        System.out.println("修改的学生数据："+employee);
        employeexsMapper.update(employee);
        return "redirect:/empxsxx";
    }

    //学生删除
    @DeleteMapping("/empgr/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeexsMapper.delete(id);
        return "redirect:/empxsxx";
    }







    //显示学生报名信息
    @GetMapping("/empbmxx")
    public String toEditPagebm(Model model, HttpSession session){
        String sessionName = (String) session.getAttribute("loginUser");
        Employeebm employeebm = employeexsMapper.findgetbm(sessionName);
        model.addAttribute("emps",employeebm);
        return "empxs/listbmxx";
    }

    //来到学生添加页面
    @GetMapping("/empbmgr")
    public String toAddPagebm(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "empxs/addbm";
    }

    //来到修改页面，查出当前学生，在页面回显
    @GetMapping("/empbmgr/{bm_id}")
    public String toEditPagebm(@PathVariable("bm_id") Integer bm_id,Model model){
        Employeebm employeebm = employeexsMapper.getbm(bm_id);
        model.addAttribute("emp",employeebm);

        //页面要显示所有的部门列表
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);*/
        return "empxs/addbm";
    }

    //学生修改；需要提交学生id；
    @PutMapping("/empbmgr")
    public String updateEmployee(Employeebm employeebm){
        System.out.println("修改的员工数据："+employeebm);
        employeexsMapper.updatebm(employeebm);
        return "redirect:/empbmxx";
    }

    //学生删除
    @DeleteMapping("/empbmgr/{bm_id}")
    public String deleteEmployeebm(@PathVariable("bm_id") Integer bm_id){
        employeexsMapper.deletebmxx(bm_id);
        return "redirect:/empbmxx";
    }


}
