package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class PingUtils {

    public static boolean ping(String ip, int timeOut)  {
        BufferedReader in = null;
        String pingCommand = null;
        
        Runtime r = Runtime.getRuntime();
        String osName = System.getProperty("os.name");
        System.out.println(osName);
        //-n:要发送的回显请求数   -w：每次请求的超时时间
        pingCommand = "ping " + ip  + " -t " + timeOut;
        String finalPingCommand = pingCommand;
        CompletableFuture<Boolean> future1 =
                CompletableFuture.supplyAsync(() -> executePing(in, finalPingCommand, r, osName));
        CompletableFuture<Boolean> future2 =
                CompletableFuture.supplyAsync(() -> executePing(in, finalPingCommand, r, osName));
        CompletableFuture<Boolean> future3 =
                CompletableFuture.supplyAsync(() -> executePing(in, finalPingCommand, r, osName));
        Boolean aBoolean = null;
        try {
            aBoolean = future1.get() && future2.get() && future3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        Boolean aBoolean = Boolean.TRUE;
//        for (int i = 0; i < 3; i++) {
//            aBoolean = executePing(in, finalPingCommand, r, osName) && aBoolean;
//        }
        return aBoolean;
    }

    private static boolean executePing(BufferedReader in, String pingCommand, Runtime r, String osName) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Process p = r.exec(pingCommand);
            System.out.println(System.currentTimeMillis() - currentTimeMillis);
            if(p == null) {
                return false;
            }
            // ping命令使用的是GBK编码
            in = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));
            int connectCount = 0;
            String line = null;
            while((line = in.readLine()) != null) {
                connectCount += getCheckResult(line, osName);
            }
            System.out.println(Thread.currentThread().getName() + " ---- :" + connectCount);

            //只要ping通一次就说明连接成功？
            return connectCount > 0 ;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("连接设备状态失败：" + e.getMessage());
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //若含有ttl=64字样,说明已经ping通,返回1,否則返回0.
    private static int getCheckResult(String line, String osName) {
        System.out.println(line);
        if(osName.contains("Windows")) {
            if(line.contains("TTL")) {
                return 1;
            }
        }else {
            if(line.contains("ttl")) {
                return 1;
            }
        }
        return 0;
    }
    
    
    public static void main(String[] args) {

        String ipAddr = "127.0.0.1";
        Integer waitTime = 3;
        if (args.length > 0) {
            ipAddr = args[0];
        }
        if (args.length > 1) {
            waitTime = Integer.valueOf(args[1]);
        }
        long currentTimeMillis = System.currentTimeMillis();
        ping(ipAddr, waitTime);
        System.out.println("main : " + (System.currentTimeMillis() - currentTimeMillis));

    }
    
}