package com.FPT.webbasic.controller;

import com.FPT.webbasic.dto.TechDto;

import com.FPT.webbasic.dto.TypeOfTechDto;
import com.FPT.webbasic.entity.Technology;

import com.FPT.webbasic.entity.TypeOfTechnology;
import com.FPT.webbasic.service.TechnologyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/tech")
public class TechController {
    private TechnologyServiceImpl technologyService;


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PostMapping("/create")
    public ResponseEntity<TechDto> createTech(@RequestBody Technology req) throws Exception {
        TechDto techDto = technologyService.createTech(req);
        return new ResponseEntity<>(techDto, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/all")
    public ResponseEntity<List<TechDto>> getAllTech(){
        List<TechDto> tech = technologyService.getAllTech();
        return new ResponseEntity<>(tech, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/find/{id}")
    public ResponseEntity<TechDto> getTechById(@PathVariable("id") Long id){
        TechDto tech = technologyService.getTechById(id);
        return new ResponseEntity<>(tech, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<TechDto> updateTech(@PathVariable("id") Long id,@RequestBody Technology req)throws Exception{
        req.setId(id);
        TechDto tech = technologyService.updateTech(req);
        return new ResponseEntity<>(tech, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTypeOfTech(@PathVariable("id") Long id)throws Exception{
        technologyService.deleteTech(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping("/set-congkhai/{id}")
    public ResponseEntity<TechDto> congKhai(@PathVariable("id") Long id)throws Exception{
        TechDto tech = technologyService.congKhai(id);
        return new ResponseEntity<>(tech,HttpStatus.CREATED);
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping("/set-riengtu/{id}")
    public ResponseEntity<TechDto> riengtu(@PathVariable("id") Long id)throws Exception{
        TechDto tech = technologyService.riengtu(id);
        return new ResponseEntity<>(tech,HttpStatus.CREATED);
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/tech-page/{pageNumber}/{pageSize}")
    public ResponseEntity<Page<TechDto>> getTechPagination(@PathVariable Integer pageNumber , @PathVariable Integer pageSize){
      return new ResponseEntity<>(technologyService.getTechPagination(pageNumber,pageSize),HttpStatus.OK);
    }
}
