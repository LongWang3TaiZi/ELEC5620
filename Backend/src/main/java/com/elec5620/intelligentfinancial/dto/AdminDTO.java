package com.elec5620.intelligentfinancial.dto;

import com.elec5620.intelligentfinancial.model.Admin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO {

    private Long id;
    private String adminName;

    public AdminDTO(Admin adminEntity) {
        this.id = adminEntity.getId();
        this.adminName = adminEntity.getAdminName();
    }
}
