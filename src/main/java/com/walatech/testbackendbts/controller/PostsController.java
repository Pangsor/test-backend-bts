package com.walatech.testbackendbts.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostsController {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createPost(){
        return "Successfully Create Post";
    }

    @GetMapping
    public String getPosts(){
        return "Successfully Get Posts";
    }
}
