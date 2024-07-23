package com.dailycodebuffer.springboot.tutorial_1.repository;

import com.dailycodebuffer.springboot.tutorial_1.entity.Department;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    public void whenFindById_thenReturnDepartmen(){
        Department department1 =
                Department.builder()
                        .departmentName("Mechnical Engineering")
                        .departmentCode("ME-01")
                        .departmentAddress("Solapur")
                        .build();
        entityManager.persist(department1);
        Department department = departmentRepository.findById(1L).get();
        assertEquals("Mechnical Engineering", department.getDepartmentName());
    }
}
