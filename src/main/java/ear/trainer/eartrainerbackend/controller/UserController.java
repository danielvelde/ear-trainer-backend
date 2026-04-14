package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto){
        UserDto saved = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // TODO -- POST login function

    // TODO -- POST logout function


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

}
