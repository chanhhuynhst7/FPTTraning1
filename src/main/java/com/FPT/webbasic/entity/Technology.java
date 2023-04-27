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
@Table(name = "technologys")
public class Technology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name="typeOfTech_id")
    private Long idOfType;

    @Column(nullable = false)
    private String nameOfType;

    @Column(nullable = false)
    private String codeOfTech;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private String nameOfTech;

    @Column(nullable = false)
    private String customer;

    @Column(nullable = false)
    private String descOfTech;

    @Column(columnDefinition = "boolean default false")
    private boolean CongKhai;

    @Column(nullable = false,name="appUser_id")
    private Long appUserId;

    @Column(nullable = false)
    private String username;

    @Column
    private Date dateCreated;

    @Column
    private Date dateUpdated;
}

