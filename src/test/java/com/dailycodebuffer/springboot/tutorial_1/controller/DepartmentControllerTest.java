package com.dailycodebuffer.springboot.tutorial_1.controller;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
import com.dailycodebuffer.springboot.tutorial_1.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {

    }

    @Test
    void saveDepartments() throws Exception {
        department = Department.builder()
                .departmentName("CS")
                .departmentCode("cs-05")
                .departmentAddress("kolhapur")
                .departmentId(1L)
                .build();

        Department inputDepartment = Department.builder()
                .departmentName("CS")
                .departmentCode("cs-05")
                .departmentAddress("kolhapur")
                .build();
        Mockito.when(departmentService.saveDepartment(inputDepartment)).thenReturn(department);
        mockMvc.perform(post("/departments") // Add the correct URL path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"departmentName\": \"CS\",\n" +
                                "    \"departmentAddress\": \"kolhapur\",\n" +
                                "    \"departmentCode\": \"cs-05\"\n" +
                                "}"))
                .andExpect(status().isOk()); // Fix the method chaining and add correct imports
    }

    @Test
    void fetchDepartmentById() throws Exception {

        department = Department.builder()
                .departmentName("CS")
                .departmentCode("cs-05")
                .departmentAddress("kolhapur")
                .departmentId(1L)
                .build();

        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()))
                .andExpect(jsonPath("$.departmentCode").value(department.getDepartmentCode()))
                .andExpect(jsonPath("$.departmentAddress").value(department.getDepartmentAddress()));
    }
}
