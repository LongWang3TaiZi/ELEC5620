package com.elec5620.intelligentfinancial.model.Response;

import com.elec5620.intelligentfinancial.dto.UserDTO;
import com.elec5620.intelligentfinancial.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponse extends BaseResponse{
    private UserDTO user;

    public UserResponse(User user, String message, String responseCode) {
        super(message, responseCode);
        if (user == null) {
            user = new User();
        }
        this.user = new UserDTO(user);
    }

    public UserResponse(UserDTO user, String message, String responseCode) {
        super(message, responseCode);
        this.user = user;
    }

    public UserResponse() {

    }
}
