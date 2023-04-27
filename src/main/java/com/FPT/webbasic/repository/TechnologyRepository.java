package com.FPT.webbasic.repository;

import com.FPT.webbasic.entity.AppUser;
import com.FPT.webbasic.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TechnologyRepository extends JpaRepository<Technology,Long> {

    boolean existsByNameOfTech (String nameOfTech);

}
