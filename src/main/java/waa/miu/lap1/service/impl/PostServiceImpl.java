package waa.miu.lap1.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.repository.PostRepo;
import waa.miu.lap1.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo repo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PostDto> getPosts() {
        List<Post> posts = repo.getPosts();
        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addPost(PostDto post) {
        repo.addPost(modelMapper.map(post, Post.class));
    }

    @Override
    public void updatePost(long id, PostDto post) {
        repo.updatePost(id, modelMapper.map(post, Post.class));
    }

    @Override
    public void deletePost(long id) {
        repo.deletePost(id);
    }

    @Override
    public Post getPost(long id) {
        return repo.getPost(id);
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        List<Post> posts = repo.getPostsByAuthor(author);

        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsBySearch(String search) {
        List<Post> posts = repo.getPostsBySearch(search);

        return posts.stream().map(p -> modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
    }
}
