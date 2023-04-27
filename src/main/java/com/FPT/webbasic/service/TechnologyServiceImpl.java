package com.FPT.webbasic.service;

import com.FPT.webbasic.dto.AppUserDto;
import com.FPT.webbasic.dto.TechDto;
import com.FPT.webbasic.dto.TypeOfTechDto;
import com.FPT.webbasic.entity.AppUser;
import com.FPT.webbasic.entity.Technology;
import com.FPT.webbasic.entity.TypeOfTechnology;
import com.FPT.webbasic.repository.AppUserRepository;
import com.FPT.webbasic.repository.TechnologyRepository;
import com.FPT.webbasic.repository.TypeOfTechnologyRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TechnologyServiceImpl {
    private TechnologyRepository technologyRepository;
    private TypeOfTechnologyRepository typeOfTechnologyRepository;
    private AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public TechDto createTech(Technology req) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(currentUserName);
        AppUser appUser = appUserOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(appUser.getId());
        appUserDto.setUsername(currentUserName);

        Optional<TypeOfTechnology> typeOfTechnologyOptional = typeOfTechnologyRepository.findByNameOfType(req.getNameOfType());
        TypeOfTechnology typeOfTechnology = typeOfTechnologyOptional.orElseThrow(() -> new Exception("Type of Tech not found"));

        TypeOfTechDto typeOfTechDto = new TypeOfTechDto();
        typeOfTechDto.setId(typeOfTechnology.getId());
        typeOfTechDto.setNameOfType(typeOfTechnology.getNameOfType());

        Technology technology = new Technology();
        boolean isExists = technologyRepository.existsByNameOfTech(req.getNameOfTech());
        if (isExists) {
            throw new Exception("Technology already exists.");
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Date dateCreated = calendar.getTime();
        technology.setNameOfTech(req.getNameOfTech());
        technology.setCodeOfTech(req.getCodeOfTech());
        technology.setDescOfTech(req.getDescOfTech());
        technology.setProduct(req.getProduct());
        technology.setCustomer(req.getCustomer());
        technology.setDateCreated(dateCreated);
        technology.setIdOfType(typeOfTechDto.getId());
        technology.setNameOfType(typeOfTechDto.getNameOfType());
        technology.setAppUserId(appUserDto.getId());
        technology.setUsername(appUserDto.getUsername());
        Technology savedTech = technologyRepository.save(technology);
        return modelMapper.map(savedTech, TechDto.class);
    }

    public TechDto updateTech(Technology req) throws Exception {
        Technology technology = technologyRepository.findById(req.getId()).get();
        if (technology != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            Optional<AppUser> appUserOptional = appUserRepository.findByUsername(currentUserName);
            AppUser appUser = appUserOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
            AppUserDto appUserDto = new AppUserDto();
            appUserDto.setId(appUser.getId());
            appUserDto.setUsername(currentUserName);

            Optional<TypeOfTechnology> typeOfTechnologyOptional = typeOfTechnologyRepository.findByNameOfType(req.getNameOfType());
            TypeOfTechnology typeOfTechnology = typeOfTechnologyOptional.orElseThrow(() -> new Exception("Type of Tech not found"));

            TypeOfTechDto typeOfTechDto = new TypeOfTechDto();
            typeOfTechDto.setId(typeOfTechnology.getId());
            typeOfTechDto.setNameOfType(typeOfTechnology.getNameOfType());

            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            Date dateUpdated = calendar.getTime();
            technology.setNameOfTech(req.getNameOfTech());
            technology.setCodeOfTech(req.getCodeOfTech());
            technology.setDescOfTech(req.getDescOfTech());
            technology.setProduct(req.getProduct());
            technology.setCustomer(req.getCustomer());
            technology.setDateCreated(dateUpdated);
            technology.setIdOfType(typeOfTechDto.getId());
            technology.setNameOfType(typeOfTechDto.getNameOfType());
            technology.setAppUserId(appUserDto.getId());
            technology.setUsername(appUserDto.getUsername());
            Technology savedTech = technologyRepository.save(technology);
            return modelMapper.map(savedTech, TechDto.class);
        }
        return null;
    }

    public TechDto congKhai(Long id) throws Exception {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        if(optionalTechnology.isPresent()) {
            Technology technology = optionalTechnology.get();
            if (!technology.isCongKhai()) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                Date dateUpdated = calendar.getTime();
                technology.setDateUpdated(dateUpdated);
                technology.setCongKhai(true);
                technologyRepository.save(technology);
            }
            return modelMapper.map(technology, TechDto.class);
        } else {
            throw new Exception("Technology not found");
        }
    }
    public TechDto riengtu(Long id) throws Exception {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        if(optionalTechnology.isPresent()) {
            Technology technology = optionalTechnology.get();
            if (technology.isCongKhai() == true) {
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                Date dateUpdated = calendar.getTime();
                technology.setDateUpdated(dateUpdated);
                technology.setCongKhai(false);
                technologyRepository.save(technology);
            }
            return modelMapper.map(technology, TechDto.class);
        } else {
            throw new Exception("Technology not found");
        }
    }

    public List<TechDto> getAllTech() {
        List<Technology> technologies = technologyRepository.findAll();
        return technologies.stream().map((tech) -> modelMapper.map(tech, TechDto.class)).collect(Collectors.toList());
    }

    public TechDto getTechById(Long id) {
        Optional<Technology> optionalTechnology = technologyRepository.findById(id);
        Technology technology = optionalTechnology.get();
        return modelMapper.map(technology, TechDto.class);
    }

    public String deleteTech(Long id) {
        technologyRepository.deleteById(id);
        return "OK";
    }

    public Page<TechDto> getTechPagination(Integer pageNumber,Integer pageSize){
        Sort sort = Sort.by(Sort.Direction.ASC,"dateCreated");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Technology> technologyPage = technologyRepository.findAll(pageable);
        List<Technology> technologyList = technologyPage.getContent();
        List<TechDto> techDtoList = new ArrayList<>();
        for (Technology technology : technologyList) {
            TechDto techDto = new TechDto();
            techDto.setCodeOfTech(technology.getCodeOfTech());
            techDto.setNameOfTech(technology.getNameOfTech());
            // set the properties of the techDto object using the properties of the technology object
            techDtoList.add(techDto);
        }

        return new PageImpl<>(techDtoList, pageable, technologyPage.getTotalElements());

    }


}
