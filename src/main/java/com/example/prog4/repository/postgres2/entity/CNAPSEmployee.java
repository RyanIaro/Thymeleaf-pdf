package com.example.prog4.repository.postgres2.entity;

import com.example.prog4.repository.postgres1.entity.Phone;
import com.example.prog4.repository.postgres1.entity.Position;
import com.example.prog4.repository.postgres1.entity.enums.Csp;
import com.example.prog4.repository.postgres1.entity.enums.Sex;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CNAPSEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String cin;
    private String cnaps;
    private String image;
    private String address;
    private String lastName;
    private String firstName;
    private String personalEmail;
    private String professionalEmail;
    private String registrationNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    private LocalDate entranceDate;
    private LocalDate departureDate;
    @Column(name = "children_number")
    private Integer childrenNumber;
}
