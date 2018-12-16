package com.atguigu.springboot.controller;

import com.atguigu.springboot.Tools.Exportexcel;
import com.atguigu.springboot.dao.DepartmentDao;
import com.atguigu.springboot.dao.EmployeeDao;
import com.atguigu.springboot.entities.Department;
import com.atguigu.springboot.entities.Employee;
import com.atguigu.springboot.entities.Employeebm;
import com.atguigu.springboot.mapper.CategoryMapper;
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
import java.util.Collection;
import java.util.List;

@Controller
public class EmployeeController {
    /*Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;*/
    @Autowired
    CategoryMapper categoryMapper;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String  list(Model model){
        List<Employee> employees = categoryMapper.findAll();

        //放在请求域中
        model.addAttribute("emps",employees);
        // thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }

    //来到学生添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "emp/add";
    }

    //学生添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        //来到学生列表页面

        System.out.println("保存的学生信息："+employee);
        //保存学生
        categoryMapper.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/emps";
    }

    //来到修改页面，查出当前学生，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = categoryMapper.get(id);
        model.addAttribute("emp",employee);

        //页面要显示所有的部门列表
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面(add是一个修改添加二合一的页面);*/
        return "emp/add";
    }

    //学生修改；需要提交学生id；
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的学生数据："+employee);
        categoryMapper.update(employee);
        return "redirect:/emps";
    }

    //学生删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        categoryMapper.delete(id);
        return "redirect:/emps";
    }











    //来到学生添加页面
    @GetMapping("/userRegistered")
    public String toAddPage1(Model model){
        //来到添加页面,查出所有的部门，在页面显示
        /*Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);*/
        return "add";
    }

    //学生添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求请求参数的名字和javaBean入参的对象里面的属性名是一样的
    @PostMapping("/userRegistered")
    public String addEmp1(Employee employee){
        //来到学生列表页面

        System.out.println("保存的学生信息："+employee);
        //保存学生
        categoryMapper.save(employee);
        // redirect: 表示重定向到一个地址  /代表当前项目路径
        // forward: 表示转发到一个地址
        return "redirect:/userRegistered1";
    }




    //创建Excel
    @RequestMapping("/createExcel1")
    public String createExcel(HttpServletResponse response) throws IOException {

        List<Employee> employees = categoryMapper.findAll();
        String columnName[] = {"学号","密码","姓名","邮箱","性别","班级","生日"};

        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("基本信息表");
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
        cell.setCellValue("学生基本信息一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));

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
        for (int i = 0; i < 7; i++)
        {
            HSSFCell cell1 = row2.createCell(i);
            cell1.setCellValue(columnName[i]);
            cell1.setCellStyle(style);
        }

        //在sheet里创建第三行
        for(int i =2;i<employees.size()+2;i++) {
            HSSFRow row3 = sheet.createRow(i);
            row3.createCell(0).setCellValue(employees.get(i-2).getId());
            row3.createCell(1).setCellValue(employees.get(i-2).getPassword());
            row3.createCell(2).setCellValue(employees.get(i-2).getLastName());
            row3.createCell(3).setCellValue(employees.get(i-2).getEmail());
            row3.createCell(4).setCellValue(employees.get(i-2).getGender());
            row3.createCell(5).setCellValue(employees.get(i-2).getDepartment());
            row3.createCell(6).setCellValue(employees.get(i-2).getBirth());

        }



//输出Excel文件
        OutputStream output=response.getOutputStream();
        Exportexcel exportexcel = new Exportexcel();
        response.reset();
        response.setHeader("Content-disposition", "attachment; filename="+exportexcel.toUtf8String("学生基本信息表.xls"));
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
        return null;
    }

}
