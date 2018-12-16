package com.atguigu.springboot.controller;


import com.atguigu.springboot.Tools.Exportexcel;
import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.entities.Employeebm;
import com.atguigu.springboot.mapper.EmployeebmMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class EmployeebmController {

    @Autowired
    EmployeebmMapper employeebmMapper;

    @GetMapping("/empsbm")
    public String  list(Model model){
        List<Employeebm> employeebms = employeebmMapper.findAll();

        //放在请求域中
        model.addAttribute("emps",employeebms);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "empbm/list";
    }

    //学生报名信息删除
    @DeleteMapping("/empbm/{bm_id}")
    public String deleteEmployee(@PathVariable("bm_id") Integer bm_id){
        employeebmMapper.delete(bm_id);
        return "redirect:/empsbm";
    }

    //来到学生添加页面
    @GetMapping("/empbm")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "empbm/add";
    }

    //学生添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/empbm")
    public String addEmp(Employeebm employeebm){
        //来到学生列表页面

        System.out.println("保存的学生信息："+employeebm);
        //保存学生
        employeebmMapper.save(employeebm);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/empsbm";
    }

    //来到修改页面，查出当前学生，在页面回显
    @GetMapping("/empbm/{bm_id}")
    public String toEditPage(@PathVariable("bm_id") Integer bm_id,Model model){
        Employeebm employeebm = employeebmMapper.get(bm_id);
        model.addAttribute("emp",employeebm);

        //页面要显示所有的部门列表
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);*/
        return "empbm/add";
    }

    //学生修改；需要提交学生id；
    @PutMapping("/empbm")
    public String updateEmployee(Employeebm employeebm){
        System.out.println("修改的员工数据："+employeebm);
        employeebmMapper.update(employeebm);
        return "redirect:/empsbm";
    }











    //来到学生添加页面
    @GetMapping("/userRegistered1")
    public String toAddPage1(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "add1";
    }

    //学生添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/userRegistered1")
    public String addEmp1(Employeebm employeebm){
        //来到学生列表页面

        System.out.println("保存的学生信息："+employeebm);
        //保存学生
        employeebmMapper.save(employeebm);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "login";
    }







    //创建Excel
    @RequestMapping("/createExcel")
    public String createExcel(HttpServletResponse response) throws IOException {

        List<Employeebm> employeebms = employeebmMapper.findAll();
        String columnName[] = {"学号","姓名","院系","专业","班级"};

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("报名信息表");
        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);

        row1.setHeightInPoints(50);// 设备标题的高度
        // 第三步创建标题的单元格样式style2以及字体样式headerFont1
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont headerFont1 = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont1.setFontName("黑体"); // 设置字体类型
        headerFont1.setFontHeightInPoints((short) 15); // 设置字体大小
        style2.setFont(headerFont1); // 为标题样式设置字体样式

        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        cell.setCellStyle(style2); // 设置标题样式
        //设置单元格内容
        cell.setCellValue("学生报名信息一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        row2.setHeightInPoints(37);// 设置表头高度


        // 第四步，创建表头单元格样式 以及表头的字体样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);// 设置自动换行
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 创建一个居中格式

        style.setBottomBorderColor(HSSFColor.BLACK.index);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
        headerFont.setFontName("黑体"); // 设置字体类型
        headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
        style.setFont(headerFont); // 为标题样式设置字体样式
        //创建单元格并设置单元格内容
        for (int i = 0; i < 5; i++)
        {
            HSSFCell cell1 = row2.createCell(i);
            cell1.setCellValue(columnName[i]);
            cell1.setCellStyle(style);
        }

        //在sheet里创建第三行
        for(int i =2;i<employeebms.size()+2;i++) {
            HSSFRow row3 = sheet.createRow(i);
                row3.createCell(0).setCellValue(employeebms.get(i-2).getBm_id());
                row3.createCell(1).setCellValue(employeebms.get(i-2).getLastName());
                row3.createCell(2).setCellValue(employeebms.get(i-2).getDepartmentyx());
                row3.createCell(3).setCellValue(employeebms.get(i-2).getDiscipline());
                row3.createCell(4).setCellValue(employeebms.get(i-2).getDepartment());

        }



//输出Excel文件
        OutputStream output=response.getOutputStream();
        Exportexcel exportexcel = new Exportexcel();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+exportexcel.toUtf8String("学生报名信息表.xls"));
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return null;
    }
}
