package waa.miu.lap1.service;

import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.dto.PostDto;

import java.util.List;

public interface PostService {
    public List<PostDto> getPosts();
    public void addPost(PostDto post);
    public void updatePost(long id, PostDto post);
    public void deletePost(long id);
    public Post getPost(long id);
    public List<PostDto> getPostsByAuthor(String author);
    public List<PostDto> getPostsBySearch(String search);
}
