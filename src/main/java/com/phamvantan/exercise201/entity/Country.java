package com.phamvantan.exercise201.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "id")
    private Integer id; // INT NOT NULL

    @Column(name = "iso", nullable = false, length = 2)
    private String iso; // CHAR(2) NOT NULL

    @Column(name = "name", nullable = false, length = 80)
    private String name; // VARCHAR(80) NOT NULL

    @Column(name = "upper_name", nullable = false, length = 80)
    private String upperName; // VARCHAR(80) NOT NULL

    @Column(name = "iso3", length = 3)
    private String iso3; // CHAR(3) DEFAULT NULL

    @Column(name = "num_code")
    private Short numCode; // SMALLINT DEFAULT NULL

    @Column(name = "phone_code", nullable = false)
    private Integer phoneCode; // INT NOT NULL
}