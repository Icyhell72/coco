package com.example.pidevcocomarket.authentication;

import com.example.pidevcocomarket.entities.User;
import com.example.pidevcocomarket.interfaces.IUserService;
import com.example.pidevcocomarket.repositories.UserRepository;
import com.example.pidevcocomarket.utils.ForgotPasswordDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private JavaMailSender javaMailSender;

    public void resetPassword(ForgotPasswordDto forgotPasswordDto) {
        User user = userRepository.findByEmail(forgotPasswordDto.getEmail()).get();
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        // generate new password
        String newPassword = generateNewPassword();

        // update user password
        userService.updatePassword(user.getId(), newPassword);

        // send password reset email
        sendPasswordResetEmail(user.getEmail(), newPassword);
    }

    String generateNewPassword() {
        // generate new password
        //random password that consists of 10 alphanumeric characters
        String newPassword = RandomStringUtils.randomAlphanumeric(10);
        return newPassword;
    }

    void sendPasswordResetEmail(String email, String newPassword) {
        // send password reset email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Password Reset");
        mail.setText("Your new password is: " + newPassword);
        javaMailSender.send(mail);
    }
}
