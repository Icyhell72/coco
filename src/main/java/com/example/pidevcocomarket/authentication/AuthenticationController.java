package com.example.pidevcocomarket.authentication;

import com.example.pidevcocomarket.entities.User;
import com.example.pidevcocomarket.interfaces.IUserService;
import com.example.pidevcocomarket.repositories.UserRepository;
import com.example.pidevcocomarket.services.EmailServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.example.pidevcocomarket.entities.RoleType.BUYER;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService service;
    @Autowired
    private ForgotPasswordService pwdservice;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        /*emailServiceImpl.sendSimpleEmail(request.getEmail(),
                "Welcome To Cocomarket",
                "\n" +
                        "Bonjour cher client,\n" +
                        "\n" +
                        "Merci d’avoir rejoint Cocomarket.\n" +
                        "\n" +
                        "Nous aimerions vous confirmer que votre compte a été créé avec succès. Pour accéder à notre site, cliquez sur le lien ci-dessous http://localhost:8075/authentication/verify . \n "+
                        "\n" +
                        "Si vous rencontrez des difficultés pour vous connecter à votre compte, contactez-nous à cocomarket@gmail.com.\n" +
                        "\n" +
                        "Cordialement,\n" +
                        "Valhalla Team");*/
        return ResponseEntity.ok(service.register(request));

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        try {
            String newPassword = pwdservice.generateNewPassword();
            pwdservice.sendPasswordResetEmail(email, newPassword);

            User user = userRepository.findByEmail(email).get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);

            return ResponseEntity.ok("Password reset email sent");
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    /*@PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String email, @RequestParam String oldPassword, @RequestParam String newPassword) {
        User user = userRepository.findByEmail(email).get();

        if (!user.getPassword().equals(oldPassword)) {
            // Return error response if old password is incorrect
            return ResponseEntity.ok(" Incorrect");
        }
        else {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password updated successfully!");}
    }*/


    @PostMapping("/verify")
    public String verifyCode(@RequestParam("verificationCode") String verificationCode) {
        // check if verification code is valid
        if (verificationCode.equals("1234")) {
            // verification succeeded, redirect to success page
            return "redirect:/success";
        } else {
            // verification failed, show error message
            return "error";
        }
    }


}
