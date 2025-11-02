package com.microservices.emailservice.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailRecipientsConfig {

    @Value("${emails.list}")
    private String emailList;

    public List<String> getEmails() {
        return Arrays.asList(emailList.split(","));
    }
}
