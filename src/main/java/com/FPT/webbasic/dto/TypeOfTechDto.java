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
public class TypeOfTechDto {

    private Long id;

    private String codeOfType;

    private String nameOfType;

    private String descOfType;

    private Date dateCreated;

    private Date dateUpdated;
}
