package waa.miu.lap1.repository.impl;

import org.springframework.stereotype.Repository;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.repository.PostRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepoImpl implements PostRepo {
    private static List<Post> data;
    private static long postId = 6;
    static {
        data = new ArrayList<>();
        Post p1 = new Post(1, "Post 1", "Content 1", "author1");
        Post p2 = new Post(2, "Post 2", "Content 2", "author2");
        Post p3 = new Post(3, "Post 3", "Content 3", "author1");
        Post p4 = new Post(4, "Post 4", "Content 4", "author2");
        Post p5 = new Post(5, "Post 5", "Content 5", "author1");

        data.add(p1);
        data.add(p2);
        data.add(p3);
        data.add(p4);
        data.add(p5);
    }

    @Override
    public List<Post> getPosts() {
        return data;
    }

    @Override
    public Post getPost(long id) {
        return data.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void addPost(Post post) {
        post.setId(postId++);
        data.add(post);
    }

    @Override
    public void updatePost(long id, Post post) {
        Post p = getPost(id);
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setAuthor(post.getAuthor());
    }

    @Override
    public void deletePost(long id) {
        Post p = getPost(id);
        data.remove(p);
    }

    @Override
    public List<Post> getPostsByAuthor(String author) {
        return data.stream().filter(p -> p.getAuthor().equals(author)).collect(Collectors.toList());
    }

    @Override
    public List<Post> getPostsBySearch(String search) {
        return data.stream().filter(post -> post.getAuthor().contains(search)).collect(Collectors.toList());
    }

}
