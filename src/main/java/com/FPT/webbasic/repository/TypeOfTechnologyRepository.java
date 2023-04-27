package com.FPT.webbasic.repository;

import com.FPT.webbasic.entity.Technology;
import com.FPT.webbasic.entity.TypeOfTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface TypeOfTechnologyRepository extends JpaRepository<TypeOfTechnology,Long> {

    boolean existsByNameOfType (String nameOfType);

    Optional<TypeOfTechnology> findByNameOfType(String nameOfType);
}
