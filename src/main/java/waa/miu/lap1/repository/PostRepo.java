package waa.miu.lap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.repository.custom.PostCustomRepo;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer>, PostCustomRepo {
    @Query("select p from Post p Join p.author u where u.name = :author")
    public List<Post> getPostsByAuthor(@Param("author") String author);

    @Query("select p from Post p Join p.author u where u.name like :search")
    public List<Post> getPostsBySearch(@Param("search") String search);
}
