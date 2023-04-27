package com.FPT.webbasic.service;

import com.FPT.webbasic.dto.AppUserDto;
import com.FPT.webbasic.dto.TypeOfTechDto;

import com.FPT.webbasic.entity.AppUser;
import com.FPT.webbasic.entity.TypeOfTechnology;
import com.FPT.webbasic.repository.TypeOfTechnologyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TypeOfTechServiceImpl {
    private TypeOfTechnologyRepository typeOfTechnologyRepository;
    private final ModelMapper modelMapper;

    public TypeOfTechDto createTypeOfTech(TypeOfTechnology req) throws Exception {
        TypeOfTechnology typeOfTechnology = new TypeOfTechnology();
        boolean isExists = typeOfTechnologyRepository.existsByNameOfType(req.getNameOfType());
        if (isExists) {
            throw new Exception("Technology already exists.");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Date dateCreated = calendar.getTime();
        typeOfTechnology.setCodeOfType(req.getCodeOfType());
        typeOfTechnology.setNameOfType(req.getNameOfType());
        typeOfTechnology.setDescOfType(req.getDescOfType());
        typeOfTechnology.setDateCreated(dateCreated);
        TypeOfTechnology savedTypeOfTech = typeOfTechnologyRepository.save(typeOfTechnology);
        return modelMapper.map(savedTypeOfTech, TypeOfTechDto.class);
    }

    public TypeOfTechDto updateTypeOfTech(TypeOfTechnology req) {
        TypeOfTechnology typeOfTechnology = typeOfTechnologyRepository.findById(req.getId()).get();
        if (typeOfTechnology != null) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            Date dateUpdated = calendar.getTime();
            typeOfTechnology.setCodeOfType(req.getCodeOfType());
            typeOfTechnology.setNameOfType(req.getNameOfType());
            typeOfTechnology.setDescOfType(req.getDescOfType());
            typeOfTechnology.setDateUpdated(dateUpdated);
            TypeOfTechnology savedTypeOfTech = typeOfTechnologyRepository.save(typeOfTechnology);
            return modelMapper.map(savedTypeOfTech, TypeOfTechDto.class);
        }
        {
            return null;
        }
    }

    public List<TypeOfTechDto> getAllTypeOfTech() {
        List<TypeOfTechnology> typeOfTech = typeOfTechnologyRepository.findAll();
        return typeOfTech.stream().map((user) -> modelMapper.map(user, TypeOfTechDto.class)).collect(Collectors.toList());
    }

    public TypeOfTechDto getTypeOfTechById(Long id) {
        Optional<TypeOfTechnology> optionalTypeOfTechnology = typeOfTechnologyRepository.findById(id);
        TypeOfTechnology typeOfTech = optionalTypeOfTechnology.get();
        return modelMapper.map(typeOfTech, TypeOfTechDto.class);
    }

    public String deleteTypeOfTech(Long id) {
        typeOfTechnologyRepository.deleteById(id);
        return "OK";
    }

}
