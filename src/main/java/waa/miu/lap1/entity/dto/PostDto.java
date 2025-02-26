package waa.miu.lap1.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostDto {
    long id;
    String title;
    String content;
    UserDto author;

//    public PostDto(int i, String s, String s1, UserDto userDto) {
//        id = i;
//        title = s;
//        content = s1;
//        userPostDto = new UserPostDto(userDto.id, userDto.name);
//    }
//
//
//    static class UserPostDto {
//        long author_id;
//        String author_name;
//
//        public UserPostDto(long author_id, String author_name) {
//            this.author_id = author_id;
//            this.author_name = author_name;
//        }
//    }

}
