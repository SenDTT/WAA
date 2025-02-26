package waa.miu.lap1.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String title;
    String content;

    @ManyToOne
    User author;
}
