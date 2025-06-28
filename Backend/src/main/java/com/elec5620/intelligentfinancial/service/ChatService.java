package com.elec5620.intelligentfinancial.service;

import com.elec5620.intelligentfinancial.dto.*;
import com.elec5620.intelligentfinancial.model.*;
import com.elec5620.intelligentfinancial.repository.*;
import com.elec5620.intelligentfinancial.utils.OpenAIAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public ChatDTO chatWithOpenAi(Long customerId, String message) {
        ChatDTO dto = new ChatDTO();
        dto.setCustomerId(customerId);
        dto.setMessage(message);
        String response = OpenAIAPI.chat(message);
        dto.setResponse(response);


        return dto;
    }

    public List<ChatDTO> getChatHistoryByCustomerId(Long customerId) {
        List<Chat> chatHistory = chatRepository.findByCustomerId(customerId);
        return chatHistory.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private ChatDTO toDTO(Chat chat) {
        ChatDTO dto = new ChatDTO();
        dto.setId(chat.getId());
        dto.setCustomerId(chat.getCustomerId());
        dto.setMessage(chat.getMessage());
        dto.setResponse(chat.getResponse());
        dto.setTimestamp(chat.getTimestamp());
        return dto;
    }
}
