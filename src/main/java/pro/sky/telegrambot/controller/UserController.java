package pro.sky.telegrambot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.model.primary.Users;
import pro.sky.telegrambot.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "User management API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Find user by ID")
    @GetMapping("/{id}")
    public Users getUserById(@Parameter(description = "User ID") @PathVariable("id") long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Create user")
    @PostMapping
    public ResponseEntity<Users> createUser(@Parameter(description = "User") @RequestBody Users user) {
        userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(summary = "Update user by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@Parameter(description = "User ID") @PathVariable("id") long id,
                                            @Parameter(description = "User") @RequestBody Users user) {
        if (userService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        userService.update(id, user);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@Parameter(description = "User ID") @PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
