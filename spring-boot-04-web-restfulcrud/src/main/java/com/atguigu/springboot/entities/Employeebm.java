package com.atguigu.springboot.entities;

public class Employeebm {

    private  Integer bm_id;

    private String lastName;

    private String departmentyx;

    private String discipline;

    private String department;

    public Integer getBm_id() {
        return bm_id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartmentyx() {
        return departmentyx;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getDepartment() {
        return department;
    }

    public void setBm_id(Integer bm_id) {
        this.bm_id = bm_id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentyx(String departmentyx) {
        this.departmentyx = departmentyx;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employeebm(Integer bm_id,String lastName, String departmentyx, String discipline, String department) {
        this.bm_id = bm_id;
        this.lastName = lastName;
        this.departmentyx = departmentyx;
        this.discipline = discipline;
        this.department = department;
    }

    public Employeebm() {
    }

    @Override
    public String toString() {
        return "Employeebm{" +
                "bm_id=" + bm_id +
                ", lastName='" + lastName + '\'' +
                ", departmentyx='" + departmentyx + '\'' +
                ", discipline='" + discipline + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
