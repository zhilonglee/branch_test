package com.example.demo.api;

import lombok.Data;

@Data
public class FakeResp<T> {
    private Integer code;
    private String month;
    private String day;
    private T data;

    public FakeResp() {
    }
}
