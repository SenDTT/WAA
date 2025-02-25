package waa.miu.lap1.entity.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import waa.miu.lap1.entity.Post;

@Data
@Getter
@Setter
public class PostDto {
    long id;
    String title;
    String content;
    String author;
    private PostDto() {}
    public PostDto(int i, String s, String s1, String s2) {
        id = i;
        title = s;
        content = s1;
        author = s2;
    }
}
