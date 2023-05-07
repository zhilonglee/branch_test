package com.example.demo.service.impl;

import com.example.demo.service.MutilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("serviceThree")
public class ServiceThreeImpl implements MutilService {
    @Override
    public void execute() {
        log.info("serviceThree ---- execute");
    }
}
