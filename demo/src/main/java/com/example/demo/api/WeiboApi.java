package com.example.demo.api;

import feign.Param;
import feign.RequestLine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WeiboApi {

  @RequestLine("GET /ajax/statuses/hot_band")
  WeiBoResp getHotSearches();

}