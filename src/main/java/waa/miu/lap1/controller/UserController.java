package waa.miu.lap1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.UserDto;
import waa.miu.lap1.entity.dto.output.PostNoAuthorDto;
import waa.miu.lap1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@RequestParam(required = false) String hasMoreThanOnePost, @RequestParam(defaultValue = "0") int hasMoreThanNPosts) {
        List<UserDto> users;
        if (hasMoreThanOnePost != null) {
            users = userService.getUsersHaveMoreThanOnePost();
        } else if (hasMoreThanNPosts != 0) {
            users = userService.getUsersHaveMoreThanNPost(hasMoreThanNPosts);
        } else {
            users = userService.findAll();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") int id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostNoAuthorDto>> getPosts(@PathVariable("id") int id) {
        List<PostNoAuthorDto> posts = userService.getPosts(id);
        return ResponseEntity.ok(posts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }
}
