package com.example.prog4.repository;

import com.example.prog4.controller.mapper.EmployeeMapper;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.postgres1.EmployeeRepository;
import com.example.prog4.repository.postgres1.entity.Employee;
import com.example.prog4.repository.postgres2.CNAPSRepository;
import com.example.prog4.repository.postgres2.entity.CNAPSEmployee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Slf4j
@Repository
@AllArgsConstructor
public class RepositoryImpl implements com.example.prog4.repository.Repository {
    private EmployeeMapper mapper;
    private EmployeeRepository employeeRepository;
    private CNAPSRepository cnapsRepository;

    @Override
    public CNAPSEmployee findByEmployeeId(String id) {
        return cnapsRepository.findCNAPSEmployeeByEndToEndId(id);
    }

    @Override
    public Employee findById(String idEmployee) {
        return employeeRepository.findById(idEmployee).get();
    }

    @Override
    public void save(com.example.prog4.model.Employee toSave) {
        Employee saved = employeeRepository.save(mapper.toDomain(toSave));
        cnapsRepository.save(CNAPSEmployee.builder()
                        .endToEndId(saved.getId())
                        .address(toSave.getAddress())
                        .cin(toSave.getCin())
                        .cnaps(toSave.getCnaps())
                        .firstName(toSave.getFirstName())
                        .lastName(toSave.getLastName())
                        .birthDate(toSave.getBirthDate())
                        .childrenNumber(toSave.getChildrenNumber())
                        .personalEmail(toSave.getPersonalEmail())
                        .professionalEmail(toSave.getProfessionalEmail())
                        .entranceDate(toSave.getEntranceDate())
                        .departureDate(toSave.getDepartureDate())
                .build());
    }

    @Override
    public String getCnapsById(String idEmployee) {
        CNAPSEmployee cnapsEmployee = cnapsRepository.findCNAPSEmployeeByEndToEndId(idEmployee);
        return cnapsEmployee.getCnaps();
    }
}
