package com.dailycodebuffer.springboot.tutorial_1.service;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
import com.dailycodebuffer.springboot.tutorial_1.error.DepartmentNotFoundException;
import com.dailycodebuffer.springboot.tutorial_1.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Ahmedabad")
                        .departmentCode("ITC-001")
                        .departmentId(1L)
                        .build();

        when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(Arrays.asList(department));
    }
    @Test
//    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        List<Department> found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.get(0).getDepartmentName());
    }
    @Test
    public void whenSaveDepartmen_thenShouldReturnDepartment(){
        Department department1 =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Ahmedabad")
                        .departmentCode("ITC-001")
                        .build();

        Department department2 =
                Department.builder()
                        .departmentId(1l)
                        .departmentName("IT")
                        .departmentAddress("Ahmedabad")
                        .departmentCode("ITC-001")
                        .build();
        when(departmentRepository.save(department1)).thenReturn(department2);
        Department found = departmentService.saveDepartment(department1);

        assertEquals(department2,found);
    }

    @Test
    public void getAllDepartments(){
        Department department1 =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Ahmedabad")
                        .departmentCode("ITC-001")
                        .build();

        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department1));
        List<Department> found = departmentService.fetchDepartments();
        assertEquals(department1,found.get(0));
    }

    @Test
    public void fetchDepartmentById() throws DepartmentNotFoundException {
        Department department1 =
                Department.builder()
                        .departmentName("IT")
                        .departmentAddress("Ahmedabad")
                        .departmentCode("ITC-001")
                        .build();

        when(departmentRepository.findById(1l)).thenReturn(Optional.ofNullable(department1));
        Department found = departmentService.fetchDepartmentById(1l);
        assertEquals(department1,found);

    }
    @Test
    public void fetchDepartmentById_throwsException_whenDepartmentNotFound() throws DepartmentNotFoundException {
        when(departmentRepository.findById(1l)).thenReturn(Optional.empty());
        try
        {
            departmentService.fetchDepartmentById(1l);
        }
        catch (DepartmentNotFoundException d){

        }
    }



}