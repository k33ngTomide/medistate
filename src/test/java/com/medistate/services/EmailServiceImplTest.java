package com.medistate.services;

import com.medistate.dtos.request.SendMailRequest;
import com.medistate.dtos.request.Recipient;
import com.medistate.dtos.request.Sender;
import com.medistate.dtos.response.SendMailResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class EmailServiceImplTest {
    @Autowired
    private EmailServices mailService;



    @Test
    public void testSendMail(){
        SendMailRequest mailRequest = buildMailRequest();
        SendMailResponse response = mailService.sendMail(mailRequest);
        assertNotNull(response);
        assertEquals(201, response.getStatusCode());
    }

    private static SendMailRequest buildMailRequest() {
        //1. Create mail sending request
        SendMailRequest mailRequest = new SendMailRequest();
        //2. Create Sender
        Sender sender = new Sender("acebook", "ejioforkelvin@gmail.com");
        //3. Create Recipient Collection
        List<Recipient> recipients = List.of(
                new Recipient("Ziggy", "kelvin475@yahoo.com")
        );
        mailRequest.setSubject("testing 1,2,3...");
        mailRequest.setHtmlContent("<p>Hello Semicolon</p>");
        mailRequest.setSender(sender);
        mailRequest.setRecipients(recipients);
        return mailRequest;
    }
}