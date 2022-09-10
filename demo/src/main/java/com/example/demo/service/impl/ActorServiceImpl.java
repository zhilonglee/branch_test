package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Actor;
import com.example.demo.mapper.ActorMapper;
import com.example.demo.service.IActorService;
import org.springframework.stereotype.Service;


@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements IActorService {
}
