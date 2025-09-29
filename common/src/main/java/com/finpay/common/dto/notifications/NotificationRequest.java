package com.finpay.common.dto.notifications;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequest {
    private String userId;
    private String message;
    private String channel; // e.g., EMAIL, SMS, PUSH
}

