package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.UserDTO;
import ear.trainer.eartrainerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto){
        UserDTO saved = userService.createUser(dto);
        return ResponseEntity.ok(saved);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id){
        UserDTO user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    //@PutMapping // for updating email or name or whatever?

    //@DeleteMapping // for Deleting your account?

}
