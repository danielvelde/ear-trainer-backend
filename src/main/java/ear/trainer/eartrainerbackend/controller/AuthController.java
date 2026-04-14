package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.UserDTO;
import ear.trainer.eartrainerbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO dto){
        UserDTO saved = authService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // TODO -- POST login function

    // TODO -- POST logout function


    @GetMapping("/me/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable UUID id){
        UserDTO user = authService.getUser(id);
        return ResponseEntity.ok(user);
    }

    //@PutMapping // for updating email or name or whatever?
    // -- No, will probably use a seperate UserController/UserService class for this

    //@DeleteMapping // for Deleting your account? -- IDK maybe

}
