package com.dailycodebuffer.springboot.tutorial_1.service;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
import com.dailycodebuffer.springboot.tutorial_1.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboot.tutorial_1.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmetServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department not found");
        }
        return department.get();
    }

    @Override
    public int deleteDepartmentById(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent()) {
            departmentRepository.deleteById(departmentId);
            return 1;
        } else return 0;

    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department db = departmentRepository.findById(departmentId).get();

        if (Objects.nonNull(department.getDepartmentName())
                && !"".equalsIgnoreCase(department.getDepartmentName())) {
            db.setDepartmentName(department.getDepartmentName());
        }

        if (Objects.nonNull(department.getDepartmentAddress())
                && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
            db.setDepartmentAddress(department.getDepartmentAddress());
        }

        if (Objects.nonNull(department.getDepartmentCode())
                && !"".equalsIgnoreCase(department.getDepartmentCode())) {
            db.setDepartmentCode(department.getDepartmentCode());
        }

        return departmentRepository.save(db);
    }

    @Override
    public List<Department> fetchDepartmentByName(String departmentName) {
        return (List<Department>) departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }


}
