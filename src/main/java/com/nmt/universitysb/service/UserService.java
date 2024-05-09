package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.AccountDto;
import com.nmt.universitysb.dto.UserDto;
import com.nmt.universitysb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService{
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(int id);
    UserDto findByUsername(String username);
    User save(User f);
    boolean deleteUser(int id);
    User getUserByUsername(String username);
    Page<User> findAllByUsernameContaining(String keyword, Pageable pageable);
    User addUser(AccountDto accountDto, MultipartFile avatar);
    boolean isValidSchoolEmail(String email);
    boolean changePassword(String oldPassword, String newPassword);
}
