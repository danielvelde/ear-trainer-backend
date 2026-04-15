package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.LoginRequestDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import ear.trainer.eartrainerbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto dto){
        return ResponseEntity.status(201).body(authService.register(dto));
    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto dto){
//        // TODO
//    }


}
