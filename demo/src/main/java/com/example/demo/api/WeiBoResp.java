package com.example.demo.api;

import lombok.Data;

@Data
public class WeiBoResp<T> {
    private long ok;
    private long httpCode;
    private T data;

    public WeiBoResp() {
    }
}