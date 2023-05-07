package com.example.demo.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;

public class SearchIndex {
    public static void main(String[] args) throws IOException {
        ConnectElasticsearch.connect(client -> {

            // 查询索引 - 请求对象
            GetIndexRequest request = new GetIndexRequest("user2");
            // 发送请求，获取响应
            GetIndexResponse response = client.indices().get(request,
                    RequestOptions.DEFAULT);

            System.out.println("aliases:" + response.getAliases());
            System.out.println("mappings:" + response.getMappings());
            System.out.println("settings:" + response.getSettings());
        });
    }
}