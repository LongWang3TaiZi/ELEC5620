package com.elec5620.intelligentfinancial.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private Long id;
    private String username;
    private String role;  // "customer" or "bank"

    public LoginDTO(Long id, String username, String role) {
        this.id=id;
        this.username=username;
        this.role=role;
    }
}
