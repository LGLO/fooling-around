package pl.lechglowiak.M101Blog.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.lechglowiak.M101Blog.entities.User;

public interface UsersCrudRepository extends CrudRepository<User, String> {

}
