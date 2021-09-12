package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findById(Long id);
    public void save(User user);
    public void delete(Long id);
    public List<Role> roles();
    public Role roleForId(Long id);
    public Role findByRole(String role);
}
