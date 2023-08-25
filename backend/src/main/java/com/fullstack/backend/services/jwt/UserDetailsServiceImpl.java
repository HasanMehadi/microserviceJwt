package com.fullstack.backend.services.jwt;


import com.fullstack.backend.entities.Roles;
import com.fullstack.backend.entities.User;
import com.fullstack.backend.repositories.RolesRepository;
import com.fullstack.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Write Logic to get the user from the DB
        User user = userRepository.findFirstByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        //List<Roles> rolesList= rolesRepository.findByProfileId(user.getProfile().getId());

        List<Roles> rolesList = new ArrayList<>();

        Roles role = new Roles();
        role.setId(1l);
        role.setName("ADMIN");
        role.setDescription("ADMIN");
        role.setProfile(null);
        rolesList.add(role);
        List<GrantedAuthority> grantedAuthorities =  rolesList.stream().map(roles -> new SimpleGrantedAuthority("ROLE_"+roles.getName())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
