package com.example.mytasksbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private static List<User> users = new ArrayList<>();

        @Value("${user.maxNumber}")
        private int userMaxNumber;

        @Autowired
        private Environment environment;

        @Autowired
        private UserConfig userConfig;

    @GetMapping
    public ResponseEntity<List<User>> index(){
         return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserReq userReq){

        System.out.println(userMaxNumber);
        System.out.println(environment.getProperty("user.maxNumber"));
        System.out.println(userConfig.getMaxNumber());

        User user = new User(userReq.getName(), userReq.getEmail(), userReq.getPassword());
        users.add(user);
        return ResponseEntity.ok(user);
    }

}
