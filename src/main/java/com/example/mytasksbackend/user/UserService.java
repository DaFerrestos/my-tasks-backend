package com.example.mytasksbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService implements UserServiceI{
    private UserConfig userConfig;
    private UserRepository userRepository;

    public UserService(UserConfig userConfig, UserRepository userRepository) {
        this.userConfig = userConfig;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(UserReq userReq) {
        if (userConfig.getMaxNumber() == userRepository.count()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível cadastrar mais usuários");
        }

        if (userRepository.existsByEmail(UserReq.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já cadastrado com esse e-mail");
        }

        return UserRepository.save(new User(userReq.getName(), userReq.getEmail(), userReq.getPassword()));
    }

}
