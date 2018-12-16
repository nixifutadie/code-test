package com.atguigu.springboot.controller;

import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.mapper.CategoryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

//    @DeleteMapping
//    @PutMapping
//    @GetMapping

    @Autowired
    CategoryMapper categoryMapper;

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@Param("lastName") String lastName,
                        @Param("password") String password,
                        Map<String,Object> map, HttpSession session){

        Employee e  = categoryMapper.login(password,lastName);

        /*if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";*/
        if (e!=null){
            session.setAttribute("loginUser",lastName);
            return "redirect:/empxs";
        }else if ("admin".equals(lastName)&& "123456".equals(password)){
            session.setAttribute("loginUser",lastName);
            return "redirect:/emps";

        }else {
            map.put("msg","用户名密码错误");
            return  "login";
        }

    }




    @GetMapping("/userloginout")
    public String toAddPage(HttpSession session){
        session.invalidate();
        return "login";
    }
}
