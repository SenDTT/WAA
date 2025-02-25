package waa.miu.lap1.repository;

import waa.miu.lap1.entity.Post;

import java.util.List;

public interface PostRepo {
    public List<Post> getPosts();
    public Post getPost(long id);
    public void addPost(Post post);
    public void updatePost(long id, Post post);
    public void deletePost(long id);
    public List<Post> getPostsByAuthor(String author);
    public List<Post> getPostsBySearch(String search);
}
