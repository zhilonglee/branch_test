package com.example.demo.service.impl;

import com.example.demo.service.MutilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("serviceOne")
public class ServiceOneImpl implements MutilService {
    @Override
    public void execute() {
        log.info("serviceone ---- execute");
    }
}
