package com.example.pidevcocomarket.authentication;

import com.example.pidevcocomarket.configuration.JwtService;
import com.example.pidevcocomarket.entities.User;
import com.example.pidevcocomarket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.example.pidevcocomarket.entities.RoleType.*;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = null;
        if (request.getRole()==BUYER){
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(BUYER)
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==VENDOR){
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(VENDOR)
                    .address(request.getAddress())
                    .company(request.getCompany())
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==ADMIN){
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(ADMIN)
                    .address("Tunisia")
                    .company("CocoMarket")
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==MODERATOR){
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(MODERATOR)
                    .address("Tunisia")
                    .company("CocoMarket")
                    .build();
            repository.save(user);
        }
        else if (request.getRole()==PROVIDER){
            user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(PROVIDER)
                    .address(request.getAddress())
                    .company(request.getCompany())
                    .build();
            repository.save(user);
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user.get());
        System.out.print("Connected Successfully !!!  Welcome To COCOMARKET");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
