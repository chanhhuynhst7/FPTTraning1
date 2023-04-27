package com.FPT.webbasic.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    private long id;
    private String username;
    private String password;
    private String userRole;
    private Date dateCreated;
    private Date dateUpdated;
    private Date dateDeleted;
}
