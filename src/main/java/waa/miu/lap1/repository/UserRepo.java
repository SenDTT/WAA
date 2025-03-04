package waa.miu.lap1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import waa.miu.lap1.entity.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Integer> {
    List<User> findAll();

//    User findById(int id);

    User findByEmail(String email);

    void deleteById(int id);

    @Query("select u, count(p.id) from User u Join u.posts p group by u.id having count(p.id) > 1 ")
    public List<User> getUsersHaveMoreThanOnePost();

    @Query("select u, count(p.id) from User u Join u.posts p group by u.id having count(p.id) > :num ")
    public List<User> getUsersHaveMoreThanNPost(int num);
}
