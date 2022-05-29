package com.example.amcef.posts.web;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.posts.data.Post;
import com.example.amcef.posts.logic.IPostService;
import com.example.amcef.posts.web.bodies.PostRequest;
import com.example.amcef.posts.web.bodies.PostUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService service;


    @GetMapping
    public List<Post> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public Post getPost(@PathVariable Integer id) throws NotFoundException {
        return service.getById(id);
    }

    @GetMapping(value = "/users/{userId}")
    public Post getPostByUser(@PathVariable Integer userId) throws NotFoundException {
        return service.getPostByUserId(userId);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable("id") Integer id) throws NotFoundException {
        service.delete(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostRequest request) throws NotFoundException {
        return service.create(request);
    }

    @PutMapping(path="/{id}")
    public Post update(@RequestBody PostUpdateRequest request, @PathVariable Integer id) throws NotFoundException {
        return service.update(id, request);
    }

}
