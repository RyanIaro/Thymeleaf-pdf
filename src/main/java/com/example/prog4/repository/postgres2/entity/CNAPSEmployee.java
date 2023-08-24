package com.example.prog4.repository.postgres2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "\"cnapsemployee\"")
public class CNAPSEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String cin;
    private String cnaps;
    @Column(name = "end_to_end_id")
    private String endToEndId;
    private String image;
    private String address;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "professional_email")
    private String professionalEmail;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "entrance_date")
    private LocalDate entranceDate;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "children_number")
    private Integer childrenNumber;
}