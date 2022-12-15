package com.moneim.api.Endpoint;

import com.moneim.api.entities.Journal;
import com.moneim.api.entities.RefreshToken;
import com.moneim.api.payload.*;
import com.moneim.api.repositories.JournalRepository;
import com.moneim.api.repositories.UserRepository;
import com.moneim.api.security.jwt.JwtUtils;
import com.moneim.api.security.services.RefreshTokenService;
import com.moneim.api.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthentificationController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    JournalRepository journalReposiory;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        Journal journalSignIn = journalReposiory.save(new Journal(
                userDetails.getId(),
                (userRepository.findById(userDetails.getId()).get().getNom()) + " " + userRepository.findById(userDetails.getId()).get().getPrenom(),
                "LogIn",
                "Logged in app",
                new Date(),
                "User sign in"));
        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signout/{idUser}")
    public ResponseEntity<?> logoutUser(@PathVariable String idUser) {

        refreshTokenService.deleteByUserId(idUser);

        Journal journalSignOut = journalReposiory.save(new Journal(
                idUser,
                (userRepository.findById(idUser).get().getNom()) + " " + userRepository.findById(idUser).get().getPrenom(),
                "LogOut",
                "Logged out from app",
                new Date(),
                "User Log out"));
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshTheToken( @RequestBody RefreshTokenRequest request) {
//        String  requestRefreshToken = request.getRefreshToken();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(  (RefreshToken::getIdUser) )
//
//                .map (  user -> {
//                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
//                    return ResponseEntity.ok(new RefreshTokenResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }
}
