package com.example.prog4.repository;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.postgres1.EmployeeRepository;
import com.example.prog4.repository.postgres1.dao.EmployeeManagerDao;
import com.example.prog4.repository.postgres1.entity.Employee;
import com.example.prog4.repository.postgres2.CNAPSRepository;
import com.example.prog4.repository.postgres2.entity.CNAPSEmployee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RepostiroyImpl implements com.example.prog4.repository.Repository {
    private EmployeeRepository employeeRepository;
    private CNAPSRepository cnapsRepository;

    @Override
    public Employee findById(String idEmployee) {
        return employeeRepository.findById(idEmployee).orElseThrow(() -> new NotFoundException("Employee not found"));
    }

    @Override
    public void save(Employee toSave) {
        employeeRepository.save(toSave);
        cnapsRepository.save(CNAPSEmployee.builder()
                        .address(toSave.getAddress())
                        .cin(toSave.getCin())
                        .cnaps(toSave.getCnaps())
                        .firstName(toSave.getFirstName())
                        .lastName(toSave.getLastName())
                        .birthDate(toSave.getBirthdate())
                        .childrenNumber(toSave.getChildrenNumber())
                        .personalEmail(toSave.getPersonalEmail())
                        .professionalEmail(toSave.getProfessionalEmail())
                        .entranceDate(toSave.getEntranceDate())
                        .departureDate(toSave.getDepartureDate())
                .build());
    }
}
