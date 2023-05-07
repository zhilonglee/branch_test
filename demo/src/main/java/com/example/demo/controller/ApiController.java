package com.example.demo.controller;

import com.example.demo.api.FakeApi;
import com.example.demo.api.WeiboApi;
import com.example.demo.service.MutilService;
import com.example.demo.utils.FeignFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    private Map<String, MutilService> myServiceMap;

    @RequestMapping("/weiBoTop")
    public ResponseEntity getWeiBoTop() {
        WeiboApi weiboApi = FeignFactoryUtil.getFeignClient(WeiboApi.class, "https://weibo.com");
        return ResponseEntity.ok(weiboApi.getHotSearches());
    }

    @RequestMapping("/historyToday")
    public ResponseEntity getWeatherAndIp() {
        FakeApi fakeApi = FeignFactoryUtil.getFeignClient(FakeApi.class, "https://query.asilu.com");
        return ResponseEntity.ok(fakeApi.getHistoryToday());
    }

    @RequestMapping("/service")
    public ResponseEntity getWeatherAndIp(String serviceName) {
        MutilService mutilService = myServiceMap.get(serviceName);
        mutilService.execute();
        return ResponseEntity.ok("666");
    }

}
