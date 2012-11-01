package pl.lechglowiak.M101Blog.integrationTests.repositories;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import pl.lechglowiak.M101Blog.entities.User;
import pl.lechglowiak.M101Blog.integrationTests.BaseSpringIntegrationTest;
import pl.lechglowiak.M101Blog.repositories.UsersCrudRepository;

public class UserCrudRepositoryTests extends BaseSpringIntegrationTest{

    @Autowired
    private UsersCrudRepository repo;

    @Test
    public void savedUserCanBeFindByName() {
        repo.save(new User("savedUserCanBeFindByName", new char[] { 'p', 'a', 's', 's' }));
        User user = repo.findOne("savedUserCanBeFindByName");
        assertThat(user, notNullValue(User.class));
    }

    @Test
    public void updateUserPassword() {
        repo.save(new User("updateUserPassword", new char[] { 'p', 'a', 's', 's' }));
        User user = repo.findOne("savedUserCanBeFindByName");
        user.setPassword(new char[] {'n', 'e', 'w'});
        repo.save(user);
        User u2 = repo.findOne("savedUserCanBeFindByName");
        assertThat(String.valueOf(u2.getPassword()), equalTo("new"));
    }

    @Test
    public void cantFindDeletedUser() {
        User user = repo.save(new User("cantFindDeletedUser", new char[] {}));
        repo.delete(user);
        User u2 = repo.findOne("cantFindDeletedUser");
        assertThat(u2, nullValue(User.class));
    }
}
