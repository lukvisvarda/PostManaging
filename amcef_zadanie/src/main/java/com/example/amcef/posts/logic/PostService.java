package com.example.amcef.posts.logic;

import com.example.amcef.exception.NotFoundException;
import com.example.amcef.posts.data.Post;
import com.example.amcef.posts.data.PostRepository;
import com.example.amcef.posts.web.bodies.PostRequest;
import com.example.amcef.posts.web.bodies.PostUpdateRequest;
import com.example.amcef.users.data.User;
import com.example.amcef.users.logic.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PostRepository repository;

    @Autowired
    private IUserService userService;

    @Override
    public List<Post> getAll() {
        return this.repository.findAll();
    }

    public Post[] getAllFromApi() {
        String url = "https://jsonplaceholder.typicode.com/posts/";
        ResponseEntity<Post[]> response = restTemplate.exchange(url, HttpMethod.GET, null, Post[].class);
        return response.getBody();
    }

    @Override
    public Post create(PostRequest request) throws NotFoundException {
        Post[] allposts = getAllFromApi();
        Post p = new Post(request);
        User[] allUsers = this.userService.getAll();
        for (User user : allUsers) {
            if (request.getUserId().equals(user.getId())) { // zistujem ci existuje user s danym ID
                if(this.repository.findAll().isEmpty()){ // priradenie id ak este nebol ziadny vytvoreny
                    p.setId(allposts[allposts.length - 1].getId() + 1);
                } else {
                    p.setId(repository.findAll().get(repository.findAll().size() - 1).getId() + 1); // priradenie id potom
                }
                return this.repository.save(p);
            }
        }
        throw new NotFoundException();
    }

    @Override
    public Post getById(Integer id) throws NotFoundException {
        Post p = this.repository.findPostById(id);
        if (p == null) {
            String url = "https://jsonplaceholder.typicode.com/posts/" + id;
            try {
                ResponseEntity<Post> response = restTemplate.exchange(url, HttpMethod.GET, null, Post.class );
//                this.repository.save(Objects.requireNonNull(response.getBody()));
                return response.getBody();
            } catch (HttpClientErrorException e) {
                throw new NotFoundException();
            }
        }
        return p;
    }

    @Override
    public Post update(Integer id, PostUpdateRequest request) throws NotFoundException {
        Post p = this.repository.findPostById(id);
        if(p == null) {
            throw new NotFoundException();
        }
        if(request.getBody() != null) {
            p.setBody(request.getBody());
        }
        if(request.getTitle() != null) {
            p.setTitle(request.getTitle());
        }
        return this.repository.save(p);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        this.repository.delete(getById(id));
    }

    @Override
    public Post getPostByUserId(Integer userId) throws NotFoundException {
        List<Post> posts = this.repository.findAll();
        for (Post post : posts) {
            if (post.getUserId().equals(userId)) {
                return post;
            }
        }
        throw new NotFoundException();
    }
}
