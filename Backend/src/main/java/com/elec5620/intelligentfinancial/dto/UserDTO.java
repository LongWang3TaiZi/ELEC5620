package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class  UserDTO {

    private Long id;
    private String email;
    private String username;
    private String role;  // "customer" or "bank" or "admin"
    private CustomerDTO customer;
    private BankDTO bank;
    private AdminDTO admin;

    public UserDTO(User userEntity) {
        this.id = userEntity.getId();
        this.email = userEntity.getEmail();
        this.username = userEntity.getUsername();
        this.role = userEntity.getRole();

        if (userEntity.getCustomer() != null) {
            this.customer = new CustomerDTO(userEntity.getCustomer());
        }

        if (userEntity.getBank() != null) {
            this.bank = new BankDTO(userEntity.getBank());
        }

        if (userEntity.getAdmin() != null) {
            this.admin = new AdminDTO(userEntity.getAdmin());
        }
    }
}
