package waa.miu.lap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import waa.miu.lap1.entity.Comment;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c join c.post p where p.id = :id")
    public List<Comment> getCommentsByPostId(@Param("id") int id);
}
