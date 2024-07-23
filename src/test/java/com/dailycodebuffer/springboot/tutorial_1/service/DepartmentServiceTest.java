package com.dailycodebuffer.springboot.tutorial_1.service;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
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

import static org.junit.jupiter.api.Assertions.*;
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

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(Arrays.asList(department));
    }
    @Test
    @Disabled
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        List<Department> found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.get(0).getDepartmentName());
    }
}