package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.UserDTO;
import ear.trainer.eartrainerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/users/$id")
    public UserDTO getUser(UUID id){
    // TODO
    }
}
