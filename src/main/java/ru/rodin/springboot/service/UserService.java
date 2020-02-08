package ru.rodin.springboot.service;

import ru.rodin.springboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> allUsers();
    boolean addUser(User user, String role);
    void updateUser(User user);
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
    User loadUserByUsername(String username);
    void setRoleByName(User user, String role);
}