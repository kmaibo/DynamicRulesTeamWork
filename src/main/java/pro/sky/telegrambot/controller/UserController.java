package pro.sky.telegrambot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Users;
import pro.sky.telegrambot.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") long id, @RequestBody Users user) {
        if (userService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.update(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
