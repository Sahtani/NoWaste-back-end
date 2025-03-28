package com.youcode.nowastebackend;

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
import com.youcode.nowastebackend.common.security.service.Impl.UserDetailsServiceImpl;
import com.youcode.nowastebackend.dto.response.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private AppRoleRepository roleRepository;

    @Mock
    private AppUserMapper userMapper;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserDetailsServiceImpl userService;

    private AppUser testUser;
    private AppRole testRole;
    private AppUserRequestDto testUserRequestDto;
    private AppUserResponseDto testUserResponseDto;
    private LoginRequestDto testLoginRequestDto;
    private UpdateUserRequestDto testUpdateUserRequestDto;

    @BeforeEach
    void setUp() {
        testRole = new AppRole();
        testRole.setId(1L);
        testRole.setName("DONOR");

        testUser = new AppUser();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("encodedPassword");
        testUser.setPhone("1234567890");
        testUser.setAddress("Test Address");
        testUser.setRole(testRole);
        testUser.setLastLogin(LocalDateTime.now());

        testUserRequestDto = new AppUserRequestDto(
                "Test User",
                "test@example.com",
                "password",
                "Test Address",
                "1234567890",
                "ROLE_DONOR"
        );

        testUserResponseDto = new AppUserResponseDto(
                1L,
                "Test User",
                "test@example.com",
                "encodedPassword",
                "1234567890",
                "Test Address",
                LocalDateTime.now(),
                null,
                "DONOR",
                "testToken"
        );

        testLoginRequestDto = new LoginRequestDto(
                "test@example.com",
                "password"
        );

        testUpdateUserRequestDto = new UpdateUserRequestDto(
                "Updated User",
                "updated@example.com",
                "Updated Address",
                "0987654321",
                "Updated Bio"
        );
    }

    @Test
    void save_shouldCreateNewUser_whenValidInput() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(testRole));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(AppUser.class))).thenReturn(testUser);
        when(jwtUtils.generateToken(anyMap(), anyString(), anyCollection())).thenReturn("testToken");

        AppUserResponseDto result = userService.save(testUserRequestDto);

        assertNotNull(result);
        assertEquals(testUser.getId(), result.id());
        assertEquals(testUser.getName(), result.name());
        assertEquals(testUser.getEmail(), result.email());
        assertEquals("testToken", result.token());

        verify(userRepository).findByEmail(testUserRequestDto.email());
        verify(roleRepository).findByName(testUserRequestDto.role());
        verify(passwordEncoder).encode(testUserRequestDto.password());
        verify(userRepository).save(any(AppUser.class));
        verify(jwtUtils).generateToken(anyMap(), eq(testUser.getEmail()), anyCollection());
    }

    @Test
    void save_shouldThrowException_whenUserAlreadyExists() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));

        assertThrows(RuntimeException.class, () -> userService.save(testUserRequestDto));
        verify(userRepository).findByEmail(testUserRequestDto.email());
        verify(roleRepository, never()).findByName(anyString());
    }

    @Test
    void save_shouldThrowException_whenRoleNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.save(testUserRequestDto));
        verify(userRepository).findByEmail(testUserRequestDto.email());
        verify(roleRepository).findByName(testUserRequestDto.role());
    }

    @Test
    void login_shouldThrowException_whenUserNotFound() {

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.login(testLoginRequestDto));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(testLoginRequestDto.email());
    }

    @Test
    void update_shouldUpdateUser_whenValidInput() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any(AppUser.class))).thenReturn(testUser);
        when(userMapper.toDto(any(AppUser.class))).thenReturn(testUserResponseDto);

        AppUserResponseDto result = userService.update(1L, testUpdateUserRequestDto);

        assertNotNull(result);
        assertEquals(testUserResponseDto.id(), result.id());
        assertEquals(testUserResponseDto.name(), result.name());

        verify(userRepository).findById(1L);
        verify(userRepository).save(testUser);
        verify(userMapper).toDto(testUser);
    }

    @Test
    void update_shouldThrowException_whenUserNotFound() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.update(1L, testUpdateUserRequestDto));
        verify(userRepository).findById(1L);
    }

    @Test
    void findById_shouldReturnUser_whenUserExists() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(any(AppUser.class))).thenReturn(testUserResponseDto);

        AppUserResponseDto result = userService.findById(1L);

        assertNotNull(result);
        assertEquals(testUserResponseDto.id(), result.id());
        assertEquals(testUserResponseDto.name(), result.name());

        verify(userRepository).findById(1L);
        verify(userMapper).toDto(testUser);
    }

    @Test
    void findById_shouldThrowException_whenUserNotFound() {

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findById(1L));
        verify(userRepository).findById(1L);
    }

    @Test
    void findAll_shouldReturnPagedResponse() {

        Pageable pageable = PageRequest.of(0, 10);
        List<AppUser> users = Collections.singletonList(testUser);
        Page<AppUser> page = new PageImpl<>(users, pageable, users.size());

        when(userRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(userMapper.toDto(any(AppUser.class))).thenReturn(testUserResponseDto);


        PagedResponse<AppUserResponseDto> result = userService.findAll(pageable);


        assertNotNull(result);
        assertEquals(1, result.content().size());
        assertEquals(testUserResponseDto.id(), result.content().get(0).id());
        assertEquals(0, result.pageNumber());
        assertEquals(10, result.pageSize());
        assertEquals(1, result.totalElements());
        assertEquals(1, result.totalPages());
        assertTrue(result.isLast());

        verify(userRepository).findAll(pageable);
        verify(userMapper).toDto(testUser);
    }

    @Test
    void deleteById_shouldDeleteUser_whenUserExists() {

        when(userRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(userRepository).deleteById(anyLong());


        userService.deleteById(1L);


        verify(userRepository).existsById(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteById_shouldThrowException_whenUserNotFound() {

        when(userRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.deleteById(1L));
        verify(userRepository).existsById(1L);
        verify(userRepository, never()).deleteById(anyLong());
    }

    @Test
    void getByUsername_shouldReturnUser_whenUserExists() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(any(AppUser.class))).thenReturn(testUserResponseDto);


        AppUserResponseDto result = userService.getByUsername("test@example.com");


        assertNotNull(result);
        assertEquals(testUserResponseDto.id(), result.id());
        assertEquals(testUserResponseDto.email(), result.email());

        verify(userRepository).findByEmail("test@example.com");
        verify(userMapper).toDto(testUser);
    }

    @Test
    void getByUsername_shouldThrowException_whenUserNotFound() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.getByUsername("test@example.com"));
        verify(userRepository).findByEmail("test@example.com");
    }

    @Test
    void changePassword_shouldThrowException_whenUserNotFound() {

        ChangePasswordDto changePasswordDto = new ChangePasswordDto("test@example.com", "oldPassword", "newPassword");
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.changePassword(changePasswordDto));
        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void changePassword_shouldThrowException_whenInvalidOldPassword() {

        ChangePasswordDto changePasswordDto = new ChangePasswordDto("test@example.com", "oldPassword", "newPassword");

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(testUser));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(RuntimeException.class, () -> userService.changePassword(changePasswordDto));
        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("oldPassword", testUser.getPassword());
        verify(passwordEncoder, never()).encode(anyString());
    }
}