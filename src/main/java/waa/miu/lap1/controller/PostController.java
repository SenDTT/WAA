package waa.miu.lap1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.lap1.entity.dto.CommentDto;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.input.InputPostDto;
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

    @GetMapping("/filter")
    public ResponseEntity<Page<PostDto>> searchPosts(@RequestParam(required = false) String author, @RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(postService.searchPost(author, title, PageRequest.of(page, size)));
    }

    @GetMapping("/{id}") // GET - localhost:8080/posts/{id}
    public ResponseEntity<PostDto> getPost(@PathVariable("id") int id) {
        PostDto post = postService.getPost(id);

        return ResponseEntity.ok(post);
    }

    @PostMapping // POST - localhost:8080/posts
    public void createPost(@RequestBody InputPostDto post) {
        postService.addPost(post);
    }

    @DeleteMapping("/{id}") // DELETE - localhost:8080/posts/{id}
    public void deletePost(@PathVariable("id") int id) {
        postService.deletePost(id);
    }

    @PutMapping("/{id}") // PUT - localhost:8080/posts/{id}
    public void updatePost(@PathVariable("id") int id, @RequestBody PostDto post) {
        postService.updatePost(id, post);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("id") int id) {
        List<CommentDto> comments = postService.getCommentsByPost(id);
        return ResponseEntity.ok(comments);
    }
}
