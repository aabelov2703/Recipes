package com.alex.recipe.service;

import com.alex.recipe.model.User;

public interface UserService {

    public User findUserById(Long id) throws Exception;

    public User findUserByJwt(String jwt) throws Exception;

}
