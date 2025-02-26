package waa.miu.lap1.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.User;
import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.UserDto;
import waa.miu.lap1.entity.dto.output.PostNoAuthorDto;
import waa.miu.lap1.repository.UserRepo;
import waa.miu.lap1.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto findById(int id) {
        User u = userRepo.findById(id);

        return modelMapper.map(u, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepo.findAll();

        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void save(UserDto userDto) {
        userRepo.save(modelMapper.map(userDto, User.class));
    }

    @Override
    public List<PostNoAuthorDto> getPosts(int id) {
        User user = userRepo.findById(id);
        List<Post> posts = user.getPosts();

        return posts.stream().map(post -> modelMapper.map(post, PostNoAuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersHaveMoreThanOnePost() {
        List<User> users = userRepo.getUsersHaveMoreThanOnePost();
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }
}
