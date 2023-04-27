package com.FPT.webbasic.repository;

import com.FPT.webbasic.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    boolean existsByUsername(String username);

    Optional<AppUser> findByUsername(String username);



}
