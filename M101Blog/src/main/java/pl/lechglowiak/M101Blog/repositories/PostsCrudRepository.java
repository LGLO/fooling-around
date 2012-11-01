package pl.lechglowiak.M101Blog.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import pl.lechglowiak.M101Blog.entities.Post;

public interface PostsCrudRepository extends CrudRepository<Post, Integer> {
    public Collection<Post> findByAuthor(String author);
}
