package waa.miu.lap1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.miu.lap1.entity.dto.input.InputCommentDto;
import waa.miu.lap1.entity.dto.output.SimpleCommentDto;
import waa.miu.lap1.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping()
    public ResponseEntity<List<SimpleCommentDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimpleCommentDto> getCommentById(@PathVariable("id") int commentId) {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PostMapping
    public void createComment(@RequestBody InputCommentDto cmt) {
        commentService.save(cmt);
    }
}
