package com.company.springbootcrudrestful.controller;


import com.company.springbootcrudrestful.dao.EmployeeDAO;
import com.company.springbootcrudrestful.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainRESTController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @RequestMapping("/")
    @ResponseBody
    public String welcome(){
        return "Welcome to RestTemplate Example.";
    }

    //url
    // http://localhost:8080/SomeContextPath/employees
    // http://localhost:8080/SomeContextPath/employees.xml
    // http://localhost:8080/SomeContextPath/employees.json
    @RequestMapping(value = "/employees",
                    produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE}, method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmployees(){
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    // http://localhost:8080/SomeContextPath/employee/{empNo}.xml
    // http://localhost:8080/SomeContextPath/employee/{empNo}.json
    @RequestMapping(value = "/employee/{empNo}",
                    produces = {MediaType.APPLICATION_JSON_VALUE,
                            MediaType.APPLICATION_XML_VALUE},
                    method = RequestMethod.GET)
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo){
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @RequestMapping(value = "/employee",
                    produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
                    method = RequestMethod.POST)
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp){
        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json

    @RequestMapping(value = "/employee",
                    produces = {MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE},
                    method = RequestMethod.PUT)
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp){
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}

    @RequestMapping(value = "/employee/{empNo}",
                    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
                    method = RequestMethod.DELETE)

    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo){
        System.out.println("(Service Side) Deleting employee: " + empNo);
        employeeDAO.deleteEmployee(empNo);
    }

}
