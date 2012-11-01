package pl.lechglowiak.M101Blog.controllers;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lechglowiak.M101Blog.entities.Post;
import pl.lechglowiak.M101Blog.repositories.PostsCrudRepository;

@Controller
public class BlogController {

    private PostsCrudRepository postsCrudRepository;

    @RequestMapping(value = "/blogs/{name}", method = RequestMethod.GET)
    public String printPosts(@PathVariable String name, Principal principal, Model model) {
        Collection<Post> posts = postsCrudRepository.findByAuthor(name);
        model.addAttribute("allPosts", posts);
        model.addAttribute("name", name);
        return "blog";
    }

    @Autowired
    public void setPostsCrudRepository(PostsCrudRepository postsCrudRepository) {
        this.postsCrudRepository = postsCrudRepository;
    }
}
