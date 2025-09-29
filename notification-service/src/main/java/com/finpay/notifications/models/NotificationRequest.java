package com.finpay.notifications.models;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationRequest {
    private String userId;
    private String message;
    private String channel;
}

