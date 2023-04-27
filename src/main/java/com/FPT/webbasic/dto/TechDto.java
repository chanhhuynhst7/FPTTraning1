package com.FPT.webbasic.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TechDto {

    private Long id;

    private Long idOfType;

    private String nameOfType;

    private String codeOfTech;

    private String product;

    private String nameOfTech;

    private String customer;

    private String descOfTech;

    private boolean CongKhai;

    private Long appUserId;

    private String username;

    private Date dateCreated;

    private Date dateUpdated;
}
