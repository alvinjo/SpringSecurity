package com.alvinsthebest.security.service;

import com.alvinsthebest.security.repo.UserRepo;
import com.alvinsthebest.security.repo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getInfo(Long id) {
        return userRepo.findById(id).orElse(new User()).getInfo();
    }

    public List<User> all() {
        return userRepo.findAll();
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}
