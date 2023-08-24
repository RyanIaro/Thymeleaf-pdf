package com.example.prog4.service;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.RepositoryImpl;
import com.example.prog4.repository.postgres1.dao.EmployeeManagerDao;
import com.example.prog4.repository.postgres1.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private RepositoryImpl repositoryImpl;
    private EmployeeManagerDao employeeManagerDao;


    @Transactional
    public Employee getOne(String id) {
        return repositoryImpl.findById(id);
    }

    @Transactional
    public List<Employee> getAll(EmployeeFilter filter) {
        Sort sort = Sort.by(filter.getOrderDirection(), filter.getOrderBy().toString());
        Pageable pageable = PageRequest.of(filter.getIntPage() - 1, filter.getIntPerPage(), sort);
        return employeeManagerDao.findByCriteria(
                filter.getLastName(),
                filter.getFirstName(),
                filter.getCountryCode(),
                filter.getSex(),
                filter.getPosition(),
                filter.getEntrance(),
                filter.getDeparture(),
                pageable
        );
    }

    public void saveOne(com.example.prog4.model.Employee employee) {
        repositoryImpl.save(employee);
    }

    public String getEmployeeCnaps(String idEmployee) {
        return repositoryImpl.getCnapsById(idEmployee);
    }
}
