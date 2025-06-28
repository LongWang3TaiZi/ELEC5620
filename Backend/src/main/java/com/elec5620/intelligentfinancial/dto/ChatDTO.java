package com.elec5620.intelligentfinancial.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class ChatDTO {
    private Long id;
    private Long customerId;
    private String message;
    private String response;
    private LocalDateTime timestamp;
}
