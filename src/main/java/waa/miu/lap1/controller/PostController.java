package waa.miu.lap1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping // GET - localhost:8080/posts
    public ResponseEntity<List<PostDto>> getPosts(@RequestParam(required = false) String author, @RequestParam(required = false) String search) {
        List<PostDto> posts;
        if (author != null) {
            posts = postService.getPostsByAuthor(author);
        } else if (search != null) {
            posts = postService.getPostsBySearch(search);
        } else {
            posts = postService.getPosts();
        }
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}") // GET - localhost:8080/posts/{id}
    public ResponseEntity<Post> getPost(@PathVariable("id") long id) {
        Post post = postService.getPost(id);

        return ResponseEntity.ok(post);
    }

    @PostMapping // POST - localhost:8080/posts
    public void createPost(@RequestBody PostDto post) {
        postService.addPost(post);
    }

    @DeleteMapping("/{id}") // DELETE - localhost:8080/posts/{id}
    public void deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
    }

    @PutMapping("/{id}") // PUT - localhost:8080/posts/{id}
    public void updatePost(@PathVariable("id") long id, @RequestBody PostDto post) {
        postService.updatePost(id, post);
    }
}
