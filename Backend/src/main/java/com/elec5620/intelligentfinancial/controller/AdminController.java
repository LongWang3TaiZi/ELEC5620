package com.elec5620.intelligentfinancial.controller;

import com.elec5620.intelligentfinancial.dto.UserDTO;
import com.elec5620.intelligentfinancial.model.Response.UserResponse;
import com.elec5620.intelligentfinancial.model.User;
import com.elec5620.intelligentfinancial.service.AdminService;
import com.elec5620.intelligentfinancial.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:3001")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) { this.adminService = adminService; }

    // get all users' detail
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUserDetail() {
        List<UserDTO> users = adminService.getAllUserDetail();
        if (users == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(users);
    }

    // get the user detail by userId
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserInfoById(
            @PathVariable Long userId) {
        UserDTO user = adminService.findUserById(userId);
        if (user == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(user);
    }

    // edit user information
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> EditUserInfo(
            @PathVariable Long userId, @RequestBody EditUserInfos request) {
        UserDTO user = adminService.EditUserInfo(userId, request.username, request.role);
        if (user == null) ResponseEntity.ok(new UserResponse((User) null,
                ResponseCode.F1.getMessage(), ResponseCode.F1.getResponseCode()));

        return ResponseEntity.ok(new UserResponse(user,
                ResponseCode.A2.getMessage(), ResponseCode.A2.getResponseCode()));
    }

    public static class EditUserInfos {
        public String username;
        public String role;
    }

    // delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponse> DeleteUser(@PathVariable Long userId) {
        UserDTO user = adminService.DeleteUser(userId);
        if (user == null) ResponseEntity.ok(new UserResponse((User) null,
                ResponseCode.F1.getMessage(), ResponseCode.F1.getResponseCode()));

        return ResponseEntity.ok(new UserResponse(user,
                ResponseCode.A3.getMessage(), ResponseCode.A3.getResponseCode()));
    }

}
