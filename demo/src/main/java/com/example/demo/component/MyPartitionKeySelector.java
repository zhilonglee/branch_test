package com.example.demo.component;

import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.stereotype.Component;

/**
 * @author fanxiaoyu
 */
@Component
public class MyPartitionKeySelector implements PartitionSelectorStrategy {
    @Override
    public int selectPartition(Object key, int partitionCount) {
        return Integer.parseInt(key.toString()) % partitionCount;
    }
}
