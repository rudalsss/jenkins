package com.example.oliveback.controller.user;

import com.example.oliveback.dto.user.UserSignupRequest;
import com.example.oliveback.dto.user.UserResponse;
import com.example.oliveback.service.user.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserSignupRequest request) {
        UserResponse response = usersService.signup(request);
        return ResponseEntity.ok(response);
    }
}
