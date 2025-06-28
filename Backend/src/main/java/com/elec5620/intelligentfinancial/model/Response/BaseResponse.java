package com.elec5620.intelligentfinancial.model.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BaseResponse {
    private String message;
    private String responseCode;

    public BaseResponse(String message, String responseCode) {
        this.message = message;
        this.responseCode = responseCode;
    }

}
