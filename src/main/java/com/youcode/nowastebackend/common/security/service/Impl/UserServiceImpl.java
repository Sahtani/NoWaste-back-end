package com.youcode.nowastebackend.common.security.service.Impl;

import com.youcode.nowastebackend.common.security.config.Jwt.JwtUtils;
import com.youcode.nowastebackend.common.security.dto.password.ChangePasswordDto;
import com.youcode.nowastebackend.common.security.dto.request.LoginRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.LoginResponseDto;
import com.youcode.nowastebackend.common.security.entity.AppUser;
import com.youcode.nowastebackend.common.security.service.UserService;
import com.youcode.nowastebackend.common.service.AbstractService;
import com.youcode.nowastebackend.common.security.dto.request.AppUserRequestDto;
import com.youcode.nowastebackend.common.security.dto.response.AppUserResponseDto;
import com.youcode.nowastebackend.common.security.mapper.UserMapper;
import com.youcode.nowastebackend.common.security.repository.AppRoleRepository;
import com.youcode.nowastebackend.common.security.repository.AppUserRepository;
import com.youcode.nowastebackend.dto.response.PagedResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@Transactional
@AllArgsConstructor
public class UserServiceImpl  implements UserService {

    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public AppUserResponseDto getByUsername(String name) {
        return null;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto) {

    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                ));
                String token = jwtUtils.generateToken(loginRequestDto.email());
                LoginResponseDto responseDto = new LoginResponseDto(
                        token,
                        authentication.getName(),
                        authentication.getAuthorities().iterator().next().getAuthority()

                );
                return responseDto;
    }

    @Override
    public AppUserResponseDto save(AppUserRequestDto requestDto) {
        return null;
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
}
