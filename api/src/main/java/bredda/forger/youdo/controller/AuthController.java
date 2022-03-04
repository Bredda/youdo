package bredda.forger.youdo.controller;


import bredda.forger.youdo.exception.UserAlreadyExistsException;
import bredda.forger.youdo.models.User;
import bredda.forger.youdo.payload.request.SigninRequest;
import bredda.forger.youdo.payload.request.SignupRequest;
import bredda.forger.youdo.payload.response.JwtResponse;
import bredda.forger.youdo.payload.response.UserInfoResponse;
import bredda.forger.youdo.security.UserDetailsImpl;
import bredda.forger.youdo.security.jwt.JwtUtils;
import bredda.forger.youdo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name="Authentication", description = "Signing in/up users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserInfoResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        var user = userService.create(signUpRequest);

        return ResponseEntity.ok(new UserInfoResponse(user.getId(),
                user.getUsername()));
    }

    @GetMapping("me")
    public ResponseEntity<UserInfoResponse> getCurrentUser() {
        User me = userService.getUserFromContext();
        return ResponseEntity.ok(new UserInfoResponse(me.getId(), me.getUsername()));
    }
}
