package com.example.prog4.repository;

import com.example.prog4.repository.postgres1.entity.Employee;
import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.postgres2.entity.CNAPSEmployee;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository {
    CNAPSEmployee findByEmployeeId(String id);
    Employee findById(String idEmployee);

    void save(com.example.prog4.model.Employee toSave);

    String getCnapsById(String idEmployee);
}
