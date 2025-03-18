package com.youcode.nowastebackend.common.security.service.Impl;

import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AppUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String roleName = user.getRole().getName();

        String formattedRole = roleName.startsWith("ROLE_") ? roleName : "ROLE_" + roleName;

        Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(formattedRole)
        );

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
