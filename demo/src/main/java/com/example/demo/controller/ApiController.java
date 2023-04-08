package com.example.demo.controller;

import com.example.demo.api.FakeApi;
import com.example.demo.api.WeiboApi;
import com.example.demo.utils.FeignFactoryUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {


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

}
