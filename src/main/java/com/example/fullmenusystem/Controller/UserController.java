package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Api.ApiResponse;
import com.example.fullmenusystem.Controller.dto.DTOUser;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @GetMapping("/register")
    public ResponseEntity register(@RequestBody  DTOUser user) {
        authService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Register successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@AuthenticationPrincipal User user, @RequestBody @Valid User newUser) {
        authService.updated(user.getUsername(), newUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@AuthenticationPrincipal User user) {
        authService.deleted(user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("deleted successfully"));
    }

    @GetMapping("/info")
    public ResponseEntity info(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getInfo(user.getUsername()));
    }
}
