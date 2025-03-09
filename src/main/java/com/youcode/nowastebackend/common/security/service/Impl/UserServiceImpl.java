package com.youcode.nowastebackend.common.security.service.Impl;

import com.youcode.nowastebackend.common.security.config.Jwt.JwtUtils;
import com.youcode.nowastebackend.common.security.dto.password.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppRole;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.mapper.AppUserMapper;
import com.youcode.nowastebackend.common.security.repository.AppRoleRepository;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.common.security.service.UserService;
import com.youcode.nowastebackend.dto.response.PagedResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Validated
@Transactional
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AppUserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AppUserRepository appUserRepository;
    private final HaveIBeenPwnedService haveIBeenPwnedService;

    @Override
    public AppUserResponseDto save(AppUserRequestDto requestDto) {
        AppRole role = roleRepository.findByName(requestDto.role())
                .orElseThrow(() -> new RuntimeException("Role not found: " + requestDto.role()));

        return createNewUser(requestDto, role);


    }

    public AppUserResponseDto createNewUser(AppUserRequestDto requestDto, AppRole role) {
        log.info("Creating a new user with role: {}", role.getName());

        var user = AppUser.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .phone(requestDto.phone())
                .password(passwordEncoder.encode(requestDto.password()))
                .address(requestDto.address())
                .role(role)
                .build();

        var savedUser = userRepository.save(user);
        log.info("User created: {}", savedUser);

        Collection<SimpleGrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role.getName())
        );
        var token = jwtUtils.generateToken(requestDto.email(), authorities);

        return new AppUserResponseDto(
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                token);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                ));

        String token = jwtUtils.generateToken(
                loginRequestDto.email(),
                authentication.getAuthorities()
        );

        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        LoginResponseDto responseDto = new LoginResponseDto(
                authentication.getName(),
                role,
                token
        );

        return responseDto;
    }

    @Override
    public AppUserResponseDto update(Long aLong, AppUserRequestDto requestDto) {
        return null;
    }

    @Override
    public AppUserResponseDto findById(Long aLong) {
        return null;
    }

    @Override
    public PagedResponse<AppUserResponseDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public AppUserResponseDto getByUsername(String name) {
        return null;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {

    }

}
