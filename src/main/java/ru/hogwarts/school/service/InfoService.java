package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Value("${server.port}")
    private Long sp;

    public Long getSp() {
        return sp;
    }
}
