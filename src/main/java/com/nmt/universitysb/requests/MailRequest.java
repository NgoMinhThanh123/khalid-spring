package com.nmt.universitysb.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private String subject;
    private String body;
    private String date;
    private String from;
    private String recipients;
}