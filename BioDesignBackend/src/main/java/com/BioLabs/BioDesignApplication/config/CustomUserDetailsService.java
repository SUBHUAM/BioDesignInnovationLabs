package com.BioLabs.BioDesignApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("admin")) {
            return new User("admin", passwordEncoder().encode("admin123"),
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
        } else if (username.equals("doctor")) {
            return new User("doctor", passwordEncoder().encode("doctor123"),
                    AuthorityUtils.createAuthorityList("ROLE_DOCTOR"));
        } else if (username.equals("nurse")) {
            return new User("nurse", passwordEncoder().encode("nurse123"),
                    AuthorityUtils.createAuthorityList("ROLE_NURSE"));
        }
        throw new UsernameNotFoundException("User not found");
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

