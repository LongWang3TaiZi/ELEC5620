package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.Response.UserResponse;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import com.elec5620.intelligentfinancial.utils.ResponseCode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Business logic for User management.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository,
                       BankRepository bankRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.bankRepository = bankRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * This method allows users to register a user account by email and password.
     *
     * @param email     the email of the user
     * @param username  User-defined user name
     * @param password  User-defined password
     * @return          UserDTO
     */
    @Transactional
    public UserResponse registerUser(String role, String email, String username, String password) {
        // Check if the user email already exists.
       if (userRepository.findUserByEmail(email) != null) { // When the user account already exists.
            // The system will return an error message.
            return new UserResponse((User) null, ResponseCode.F0.getMessage(),
                    ResponseCode.F0.getResponseCode());
        }

        if (userRepository.findByUsername(username) != null) { // When the userName already exists.
            // The system will return an error message.
            return new UserResponse((User) null, ResponseCode.F4.getMessage(),
                    ResponseCode.F4.getResponseCode());
        }

        // When the user account does not exist,
        // a new account is created based on the user's email address, username, and password.
        User user = new User(role, email, username, password);

        if (Objects.equals(role, "customer")) {
            // Create a new Customer object and associate it with the User
            Customer customer = new Customer(username);
            user.setCustomer(customer);
            customerRepository.save(customer);

        } else if (Objects.equals(role, "bank")) {
            // Create a new Bank object and associate it with the User
            Bank bank = new Bank(username);
            user.setBank(bank);
            bankRepository.save(bank);
        } else if (Objects.equals(role, "admin")) {
            // Create a new Admin object and associate it with the User
            Admin admin = new Admin(username);
            user.setAdmin(admin);
            adminRepository.save(admin);
        } else {
            // The system will return an error message.
            return new UserResponse(user, ResponseCode.F3.getMessage(),
                    ResponseCode.F3.getResponseCode());
        }

        // Save the newly created user object to the corresponding database.
        userRepository.save(user);

        // The system will return a successful account creation message.
        return new UserResponse(user, ResponseCode.A0.getMessage(), ResponseCode.A0.getResponseCode());
    }


    @Transactional
    public UserResponse loginUser(String email, String password) {
        // Finds the user object in the corresponding database based on the email.
        User user = userRepository.findUserByEmail(email);
        if (user == null) { // If the email is not registered.
            // The system returns an error message.
            return new UserResponse((User) null, ResponseCode.F1.getMessage(),
                    ResponseCode.F1.getResponseCode());
        }
        if (!user.getPassword().equals(password)) { // When the password is incorrect.
            // The system returns an error message.
            return new UserResponse((User) null, ResponseCode.F2.getMessage(),
                    ResponseCode.F2.getResponseCode());
        }

        // The email and password are correct, the user logs in successfully,
        // and a UserDTO object is created.
        UserDTO userDTO = new UserDTO(user);
        // The system will return a successful user login message.
        return new UserResponse(userDTO, ResponseCode.A1.getMessage(), ResponseCode.A1.getResponseCode());
    }

}
