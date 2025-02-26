package com.example.backend.service;

import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.EmployeeModel;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Create Employee
    public EmployeeModel createEmployee(EmployeeModel employee) {
        return employeeRepository.save(employee);
    }

    // Get All Employees
    public List<EmployeeModel> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Update Employee
    public EmployeeModel updateEmployee(String id, EmployeeModel updatedEmployee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setTitle(updatedEmployee.getTitle());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setPhone(updatedEmployee.getPhone());
            existingEmployee.setDepartment(updatedEmployee.getDepartment());
            existingEmployee.setGender(updatedEmployee.getGender());
            existingEmployee.setReportingManager(updatedEmployee.getReportingManager());
            existingEmployee.setPermanentAddress(updatedEmployee.getPermanentAddress());
            existingEmployee.setCurrentAddress(updatedEmployee.getCurrentAddress());
            existingEmployee.setIdProofs(updatedEmployee.getIdProofs());
            existingEmployee.setBankDetails(updatedEmployee.getBankDetails());
            existingEmployee.setSalaryDetails(updatedEmployee.getSalaryDetails());
            return employeeRepository.save(existingEmployee);
        }).orElseThrow(() -> new ResourceNotFoundException("Employee with ID " + id + " not found"));
    }
}
