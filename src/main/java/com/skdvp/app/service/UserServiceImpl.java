package com.skdvp.app.service;

import com.skdvp.app.model.Role;
import com.skdvp.app.model.User;
import com.skdvp.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

}
