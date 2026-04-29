package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable UUID id){
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

}
