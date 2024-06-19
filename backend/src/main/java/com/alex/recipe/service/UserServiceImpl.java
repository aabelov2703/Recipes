package com.alex.recipe.service;

import com.alex.recipe.config.JwtProvider;
import com.alex.recipe.model.User;
import com.alex.recipe.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long id) throws Exception {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) return user.get();
        throw new Exception("User with id:" + id + " not found");
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromToken(jwt);
        if (email == null) throw new Exception("Provided JWT token is invalid");

        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()) throw new Exception("User with email: " + email + " not found");
        return user.get();
    }

}
