package com.nmt.universitysb.service;

import com.nmt.universitysb.requests.MailRequest;

public interface MailService {
    void sendMailToStudent(MailRequest mailRequest);
}
