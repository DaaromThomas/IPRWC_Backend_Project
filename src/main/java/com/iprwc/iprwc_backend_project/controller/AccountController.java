package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.Account;
import com.iprwc.iprwc_backend_project.model.RoleType;
import com.iprwc.iprwc_backend_project.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public ResponseEntity<Account> getAccount(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String enteredPassword = credentials.get("password");

        Optional<Account> optionalAccount = accountService.findByName(username);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            String storedSalt = account.getSalt();
            String hashedEnteredPassword = hashPassword(enteredPassword, storedSalt);

            if (hashedEnteredPassword.equals(account.getPassword())) {
                return ResponseEntity.status(HttpStatus.OK).body(account);
            } else {
                System.out.println("Mag niet");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        String salt = generateRandomSalt();
        String hashedPassword = hashPassword(password, salt);

        Account account = new Account();
        account.setName(username);
        account.setPassword(hashedPassword);
        account.setSalt(salt);
        account.setRole(RoleType.Customer);

        this.accountService.save(account);

        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    private String generateRandomSalt() {
        // Generate a random 16-character salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String password, String salt) {
        String saltedPassword = password + salt;

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a base64-encoded string
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception, e.g., log it or throw a custom exception
            throw new RuntimeException("Error hashing password.", e);
        }
    }
}
