package waa.miu.lap1.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import waa.miu.lap1.entity.Post;


public interface PostCustomRepo {
    Page<Post> searchPosts(String author, String title, Pageable pageable);
}
