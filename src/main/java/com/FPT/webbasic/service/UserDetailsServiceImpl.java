package com.FPT.webbasic.service;

import com.FPT.webbasic.dto.AppUserDto;
import com.FPT.webbasic.entity.AppUser;
import com.FPT.webbasic.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find user by username
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

        var authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(appUser.getUserRole()));

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    public AppUserDto createUser(AppUser req) throws Exception {

        AppUser appUser = new AppUser();

        // Check whether username exists or not
        boolean isExists = appUserRepository.existsByUsername(req.getUsername());

        if (isExists) {
            throw new Exception("User already exists.");
        }
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        Date dateCreated = calendar.getTime();
        appUser.setUsername(req.getUsername());
        appUser.setPassword(passwordEncoder.encode(req.getPassword()));
        appUser.setUserRole(req.getUserRole());
        appUser.setDateCreated(dateCreated);
        AppUser savedUser = appUserRepository.save(appUser);
        return modelMapper.map(savedUser, AppUserDto.class);
    }


    public AppUserDto updateUser(AppUser req) {
        AppUser appUser = appUserRepository.findById(req.getId()).get();
        if (appUser != null) {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
            Date dateUpdated = calendar.getTime();
            appUser.setUsername(req.getUsername());
            appUser.setPassword(passwordEncoder.encode(req.getPassword()));
            appUser.setUserRole(req.getUserRole());
            appUser.setDateUpdated(dateUpdated);
            AppUser savedUser = appUserRepository.save(appUser);
            return modelMapper.map(savedUser, AppUserDto.class);
        }
        {
            return null;
        }
    }


    public List<AppUserDto> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, AppUserDto.class)).collect(Collectors.toList());
    }

    public AppUserDto getUserById(Long id) {
        Optional<AppUser> optionalUser = appUserRepository.findById(id);
        AppUser user = optionalUser.get();
        return modelMapper.map(user, AppUserDto.class);

    }

    public String deleteUser(Long id) {
        appUserRepository.deleteById(id);
        return "OK";
    }


    public AppUserDto getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(currentUserName);
        AppUser appUser = appUserOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(appUser.getId());
        appUserDto.setUsername(currentUserName);
        return appUserDto;
    }
}
