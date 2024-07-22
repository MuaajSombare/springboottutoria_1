package com.dailycodebuffer.springboot.tutorial_1.service;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
import com.dailycodebuffer.springboot.tutorial_1.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartments();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public int deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public List<Department> fetchDepartmentByName(String departmentName);
}
