package waa.miu.lap1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import waa.miu.lap1.entity.dto.CommentDto;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.input.InputPostDto;

import java.util.List;

public interface PostService {
    public List<PostDto> getPosts();
    public void addPost(InputPostDto post);
    public void updatePost(int id, PostDto post);
    public void deletePost(int id);
    public PostDto getPost(int id);
    public List<PostDto> getPostsByAuthor(String author);
    public List<PostDto> getPostsBySearch(String search);
    public Page<PostDto> searchPost(String author, String title, Pageable pageable);
    public List<CommentDto> getCommentsByPost(int postId);
}
