package com.example.prog4.repository.postgres2;

import com.example.prog4.repository.postgres2.entity.CNAPSEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CNAPSRepository extends JpaRepository<CNAPSEmployee, String> {
    CNAPSEmployee findCNAPSEmployeeByEndToEndId(String id);
}
