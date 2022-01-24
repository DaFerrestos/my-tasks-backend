package com.example.mytasksbackend.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

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
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(UserReq userReq) {
        if (userConfig.getMaxNumber() == userRepository.count()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível cadastrar mais usuários");
        }

        if(userRepository.existsByEmail(userReq.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "usuário já cadastrado com esse e-mail");
        }

        return userRepository.save(new User(userReq.getName(), userReq.getEmail(), userReq.getPassword()));
    }

}
