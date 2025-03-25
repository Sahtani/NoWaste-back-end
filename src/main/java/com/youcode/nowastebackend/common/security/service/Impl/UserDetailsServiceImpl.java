package com.youcode.nowastebackend.common.security.service.Impl;

import com.youcode.nowastebackend.common.exception.ResourceNotFoundException;
import com.youcode.nowastebackend.common.security.config.Jwt.JwtUtils;
import com.youcode.nowastebackend.common.security.dto.password.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.request.UpdateUserRequestDto;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final AppUserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AppUserResponseDto save(AppUserRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.email()).isPresent()) {
            throw new RuntimeException("User with email " + requestDto.email() + " already exists");
        }

        AppRole role = roleRepository.findByName(requestDto.role())
                .orElseThrow(() -> new RuntimeException("Role not found: " + requestDto.role()));

        return createNewUser(requestDto, role);
    }

    @Override
    public AppUserResponseDto update(Long aLong, AppUserRequestDto requestDto) {
        return null;
    }

    @Override
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

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", savedUser.getId());

        var token = jwtUtils.generateToken(claims, savedUser.getEmail(), authorities);

        return new AppUserResponseDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getPhone(),
                savedUser.getAddress(),
                savedUser.getLastLogin(),
                savedUser.getRole().getName(),
                token);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                ));

        AppUser user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequestDto.email()));

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());

        String token = jwtUtils.generateToken(
                claims,
                user.getEmail(),
                authentication.getAuthorities()
        );

        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return new LoginResponseDto(
                user.getName(),
                user.getEmail(),
                role,
                token,
                user.getId()
        );
    }


    public AppUserResponseDto update(Long id, UpdateUserRequestDto requestDto) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (requestDto.name() != null) {
            user.setName(requestDto.name());
        }
        if (requestDto.email() != null) {
            user.setEmail(requestDto.email());
        }
        if (requestDto.phone() != null) {
            user.setPhone(requestDto.phone());
        }
        if (requestDto.address() != null) {
            user.setAddress(requestDto.address());
        }
        AppUser updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public AppUserResponseDto findById(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public PagedResponse<AppUserResponseDto> findAll(Pageable pageable) {
        Page<AppUser> usersPage = userRepository.findAll(pageable);

        var users = usersPage.getContent().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                users,
                usersPage.getNumber(),
                usersPage.getSize(),
                usersPage.getTotalElements(),
                usersPage.getTotalPages(),
                usersPage.isLast()
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public AppUserResponseDto getByUsername(String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        return userMapper.toDto(user);
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {
        AppUser user = userRepository.findByEmail(changePasswordDto.email())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + changePasswordDto.email()));

        if (!passwordEncoder.matches(changePasswordDto.oldPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDto.newPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void verifyAccount(String token) {
       /* VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new BadRequestException("Invalid verification token"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Verification token has expired");
        }

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        // Delete used token
        tokenRepository.delete(verificationToken);*/
    }

    @Transactional
    public void sendPasswordResetEmail(String email) {
       /* AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        String token = UUID.randomUUID().toString();
        VerificationToken resetToken = new VerificationToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        resetToken.setTokenType("PASSWORD_RESET");
        tokenRepository.save(resetToken);

        String subject = "NoWaste Password Reset";
        String content = "Please click on the link to reset your password: " +
                "http://your-app-url/reset-password?token=" + token;
        emailService.sendEmail(user.getEmail(), subject, content);*/
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {/*
        VerificationToken resetToken = tokenRepository.findByTokenAndTokenType(token, "PASSWORD_RESET")
                .orElseThrow(() -> new BadRequestException("Invalid password reset token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Password reset token has expired");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        // Delete used token
        tokenRepository.delete(resetToken);*/
    }

}
