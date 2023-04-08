package com.example.demo.api;

import feign.RequestLine;

public interface FakeApi {

    @RequestLine("GET /today/list/")
    FakeResp getHistoryToday();
}
