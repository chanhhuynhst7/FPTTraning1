package com.FPT.webbasic.controller;

import com.FPT.webbasic.dto.AppUserDto;
import com.FPT.webbasic.entity.AppUser;
import com.FPT.webbasic.security.JwtProvider;
import com.FPT.webbasic.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class AppUserController {
    private UserDetailsServiceImpl userDetailsService;
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/all")
    public ResponseEntity<List<AppUserDto>> getAllUsers(){
        List<AppUserDto> users = userDetailsService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/find/{id}")
    public ResponseEntity<AppUserDto> getUserById(@PathVariable("id") Long id){
        AppUserDto user = userDetailsService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    public ResponseEntity<AppUserDto> createUser(@RequestBody AppUser req) throws Exception {
        AppUserDto createUser = userDetailsService.createUser(req);
        return new ResponseEntity<>(createUser,HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @PutMapping("/update/{id}")
    public ResponseEntity<AppUserDto> updateUser(@PathVariable("id") Long id,@RequestBody AppUser req)throws Exception{
        req.setId(id);
        AppUserDto updatedUser = userDetailsService.updateUser(req);
        return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id)throws Exception{
        userDetailsService.deleteUser(id);
        return new ResponseEntity<>("Delete successfully",HttpStatus.OK);
    }



    @GetMapping("/current")
    public ResponseEntity<AppUserDto> getCurrentUser() {
        AppUserDto userDto = userDetailsService.getCurrent();
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }










}
