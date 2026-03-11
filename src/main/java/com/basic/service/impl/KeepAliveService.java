package com.basic.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 * 保活
 *
 * @author vains
 */
@Slf4j
@Component
@EnableScheduling
public class KeepAliveService {

    private final RestClient restClient;

    public KeepAliveService() {
        this.restClient = RestClient.builder().baseUrl("https://basic-platform.onrender.com").build();
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void keepAlive() {
        restClient.get()
                .uri("/test01/test01")
                .retrieve()
                .body(String.class);
//        log.info("KeepAlive body: {}", body);
    }

}
