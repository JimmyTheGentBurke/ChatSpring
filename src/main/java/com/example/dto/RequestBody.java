package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestBody {
    String searchByNickName;
    String chatName;
    String recipientName;
    String UserName;
    Long chatIdForAddUser;
    Long userIdCreateSearch;
    Long chatId;
    String textMessage;
}
