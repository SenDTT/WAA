package waa.miu.lap1.service;

import waa.miu.lap1.entity.dto.PostDto;
import waa.miu.lap1.entity.dto.UserDto;
import waa.miu.lap1.entity.dto.output.PostNoAuthorDto;

import java.util.List;

public interface UserService {
    public UserDto findById(int id);
    public List<UserDto> findAll();
    public void save(UserDto userDto);
    public List<PostNoAuthorDto> getPosts(int id);
    public List<UserDto> getUsersHaveMoreThanOnePost();
    public List<UserDto> getUsersHaveMoreThanNPost(int n);
    public void delete(int id);
}
