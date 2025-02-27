package waa.miu.lap1.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import waa.miu.lap1.entity.Post;
import waa.miu.lap1.entity.User;
import waa.miu.lap1.repository.custom.PostCustomRepo;

import java.util.ArrayList;
import java.util.List;

public class PostRepoImpl implements PostCustomRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Post> searchPosts(String author, String title, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = criteriaBuilder.createQuery(Post.class);
        Root<Post> postRoot = criteriaQuery.from(Post.class);
        criteriaQuery.select(postRoot);
        Join<Post, User> userJoin = postRoot.join("author");
        List<Predicate> predicates = new ArrayList<>();

        if (author != null && !author.isEmpty()) {
            predicates.add(criteriaBuilder.equal(userJoin.get("name"), author));
        }

        if (title != null && !title.isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(postRoot.get("title")), "%" + title.toLowerCase() + "%"));
        }

        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        List<Post> posts = em.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count Query for Total Records
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Post> countRoot = countQuery.from(Post.class);
        Join<Post, User> countUserJoin = countRoot.join("author", JoinType.LEFT);
        List<Predicate> countPredicates = new ArrayList<>();

        if (author != null && !author.isEmpty()) {
            countPredicates.add(criteriaBuilder.equal(countUserJoin.get("name"), author));
        }

        if (title != null && !title.isEmpty()) {
            countPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(countRoot.get("title")), "%" + title.toLowerCase() + "%"));
        }

        countQuery.select(criteriaBuilder.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));
        Long totalCount = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(posts, pageable, totalCount);
    }
}
