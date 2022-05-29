package com.example.amcef.posts.data;

import com.example.amcef.posts.web.bodies.PostRequest;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

    public Post(PostRequest request) {
        this.id = 101;
        this.userId = request.getUserId();
        this.title = request.getTitle();
        this.body = request.getBody();
    }

}
