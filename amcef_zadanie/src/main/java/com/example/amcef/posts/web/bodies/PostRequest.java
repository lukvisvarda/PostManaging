package com.example.amcef.posts.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Integer userId;
    private String title;
    private String body;
}
