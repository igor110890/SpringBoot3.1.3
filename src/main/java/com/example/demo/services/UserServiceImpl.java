package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void add(User user) {
        if (getByName(user.getUsername()) == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public User get(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(long id, User user) {
            user.setPassword(get(id).getPassword());
            userRepository.save(user);
    }

    @Override
    public void updatePassword(long id, String newPassword) {
        User updatedUser = new User();
        updatedUser.setId(get(id).getId());
        updatedUser.setName(get(id).getName());
        updatedUser.setFirstName(get(id).getFirstName());
        updatedUser.setLastName(get(id).getLastName());
        updatedUser.setEmail(get(id).getEmail());
        updatedUser.setAge(get(id).getAge());
        updatedUser.setRoles(get(id).getRoles());
        updatedUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        userRepository.save(updatedUser);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(get(id));
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }
}
