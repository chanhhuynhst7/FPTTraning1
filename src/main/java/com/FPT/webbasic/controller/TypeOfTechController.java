package com.FPT.webbasic.controller;


import com.FPT.webbasic.dto.TypeOfTechDto;

import com.FPT.webbasic.entity.TypeOfTechnology;
import com.FPT.webbasic.service.TypeOfTechServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/typeof-tech")
public class TypeOfTechController {
    private TypeOfTechServiceImpl typeOfTechService;

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/create")
    public ResponseEntity<TypeOfTechDto> createTypeOfTech(@RequestBody TypeOfTechnology req) throws Exception {
        TypeOfTechDto typeOfTech = typeOfTechService.createTypeOfTech(req);
        return new ResponseEntity<>(typeOfTech,HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/all")
    public ResponseEntity<List<TypeOfTechDto>> getAllTypeOfTech(){
        List<TypeOfTechDto> typeOfTech = typeOfTechService.getAllTypeOfTech();
        return new ResponseEntity<>(typeOfTech, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/find/{id}")
    public ResponseEntity<TypeOfTechDto> getTypeOfTechById(@PathVariable("id") Long id){
        TypeOfTechDto typeOfTech = typeOfTechService.getTypeOfTechById(id);
        return new ResponseEntity<>(typeOfTech, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<TypeOfTechDto> updateTypeOfTech(@PathVariable("id") Long id,@RequestBody TypeOfTechnology req)throws Exception{
        req.setId(id);
        TypeOfTechDto typeOfTech = typeOfTechService.updateTypeOfTech(req);
        return new ResponseEntity<>(typeOfTech, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTypeOfTech(@PathVariable("id") Long id)throws Exception{
        typeOfTechService.deleteTypeOfTech(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }
}
