package com.example.amcef.posts.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequest {
    private String title;
    private String body;
}
