package waa.miu.lap1.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.Comment;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.User;
import waa.miu.lap1.entity.dto.CommentDto;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.input.InputPostDto;
import waa.miu.lap1.repository.CommentRepo;
import waa.miu.lap1.repository.PostRepo;
import waa.miu.lap1.repository.UserRepo;
import waa.miu.lap1.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addPost(InputPostDto post) {
        User u = userRepo.findById(post.getAuthor_id()).orElse(null);
        Post p = modelMapper.map(post, Post.class);
        p.setAuthor(u);
        entityManager.persist(p);
    }

    @Override
    @Transactional
    public void updatePost(int id, PostDto post) {
        Post p = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        p.setTitle(post.getTitle());
        //entityManager.flush(); // already manage new title in persistent context
        //entityManager.detach(p); // close persistent context
        p.setContent(post.getContent()); // no manage change content
        //postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void deletePost(int id) {
        entityManager.remove(userRepo.findById(id));
    }

    @Override
    public PostDto getPost(int id) {
        Post p = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return modelMapper.map(p, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        List<Post> posts = postRepo.getPostsByAuthor(author);

        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsBySearch(String search) {
        List<Post> posts = postRepo.getPostsBySearch(search);

        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> searchPost(String author, String title, Pageable pageable) {
        Page<Post> posts = postRepo.searchPosts(author, title, pageable);
        return posts.map(p -> modelMapper.map(p, PostDto.class));
    }

    @Override
    public List<CommentDto> getCommentsByPost(int postId) {
        List<Comment> comments = commentRepo.getCommentsByPostId(postId);
        return comments.stream().map(c -> modelMapper.map(c, CommentDto.class)).collect(Collectors.toList());
    }
}
