package com.FPT.webbasic.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "typeoftechnologys")
public class TypeOfTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codeOfType;

    @Column(nullable = false)
    private String nameOfType;

    @Column
    private String descOfType;

    @Column
    private Date dateCreated;

    @Column
    private Date dateUpdated;

}
