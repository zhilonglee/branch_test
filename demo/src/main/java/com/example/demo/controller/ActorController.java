package com.example.demo.controller;


import com.example.demo.entity.Actor;
import com.example.demo.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhilong
 * @since 2022-09-09
 */
@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private IActorService actorService;

    @RequestMapping("/save")
    public ResponseEntity save(@RequestParam(defaultValue = "cai")String lastName, @RequestParam(defaultValue = "chao") String firstName) {
        Actor actor = new Actor();
        return ResponseEntity.ok(actorService.save(actor.setFirstName(firstName).setLastName(lastName)));
    }
}
