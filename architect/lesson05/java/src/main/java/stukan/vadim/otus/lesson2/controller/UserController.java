package stukan.vadim.otus.lesson2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;
import stukan.vadim.otus.lesson2.domain.User;
import stukan.vadim.otus.lesson2.exception.UserCreateException;
import stukan.vadim.otus.lesson2.exception.UserNotFoundedException;
import stukan.vadim.otus.lesson2.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User getUser(@PathVariable UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundedException(id.toString()));
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        User finalUser = user;

        if(user.getUsername() == null || user.getUsername().equals(""))
            throw new UserCreateException("User name can not be null");

        try {
            user = userRepository.save(user);
        }catch (Exception e){
            throw new UserCreateException("Can not create user: " + finalUser.getUsername());
        }

        return userRepository.findById(user.getId())
                .orElseThrow(() -> new UserCreateException("Can not create user: " + finalUser.getUsername()));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new UserNotFoundedException(id.toString());
        }
    }

    @PutMapping("/{id}")
    public Optional<User> changeUser(@RequestBody User user, @PathVariable UUID id){
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundedException(id.toString()));

        if(user.getUsername() == null || user.getUsername().equals(""))
            throw new UserCreateException("User name can not be null");

        user.setId(id);
        user = userRepository.save(user);

        return userRepository.findById(user.getId());
    }

}
