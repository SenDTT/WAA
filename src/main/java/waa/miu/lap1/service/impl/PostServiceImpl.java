package waa.miu.lap1.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.User;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.input.InputPostDto;
import waa.miu.lap1.repository.PostRepo;
import waa.miu.lap1.repository.UserRepo;
import waa.miu.lap1.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addPost(InputPostDto post) {
        User u = userRepo.findById(post.getAuthor_id());
        Post p = modelMapper.map(post, Post.class);
        p.setAuthor(u);
        postRepo.save(p);
    }

    @Override
    public void updatePost(int id, PostDto post) {
        Post p = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        postRepo.save(modelMapper.map(p, Post.class));
    }

    @Override
    public void deletePost(int id) {
        postRepo.deleteById(id);
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
}
