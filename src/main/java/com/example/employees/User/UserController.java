package com.example.employees.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserEntity user) {
        String result = userService.registerUser(user);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> getUserDetails(
            @PathVariable String username,
            @RequestHeader("Authorization") String tokenHeader
    ) {
        try {
            if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(new LoginResponse(401, "Missing or invalid Authorization header"));
            }

            String token = tokenHeader.substring(7); // Remove "Bearer " prefix
            String usernameFromToken = jwtTokenUtil.extractUsername(token);

            if (!usernameFromToken.equals(username)) {
                return ResponseEntity.status(403).body(new LoginResponse(403, "Forbidden - Username mismatch"));
            }

            UserEntity user = userService.getUserByUsername(username);
            return ResponseEntity.ok(new LoginResponse(user));

        } catch (Exception e) {
            return ResponseEntity.status(401).body(new LoginResponse(401, "Unauthorized: " + e.getMessage()));
        }
    }

}
