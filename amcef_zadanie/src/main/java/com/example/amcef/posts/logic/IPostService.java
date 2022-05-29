package com.example.amcef.posts.logic;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.posts.data.Post;
import com.example.amcef.posts.web.bodies.PostRequest;
import com.example.amcef.posts.web.bodies.PostUpdateRequest;

import java.util.List;

public interface IPostService {
    List<Post> getAll();

    Post create(PostRequest request) throws NotFoundException;

    Post getById(Integer id) throws NotFoundException;

    Post update(Integer id, PostUpdateRequest request) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    Post getPostByUserId(Integer userId) throws NotFoundException;
}
