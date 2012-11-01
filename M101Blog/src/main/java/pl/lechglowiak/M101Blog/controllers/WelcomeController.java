package pl.lechglowiak.M101Blog.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lechglowiak.M101Blog.entities.User;
import pl.lechglowiak.M101Blog.repositories.UsersCrudRepository;

@Controller
public class WelcomeController {

    private UsersCrudRepository userCrudRepository;

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, Principal principal) {
        String name = principal.getName();
        model.addAttribute("name", name);
        User user = userCrudRepository.findOne(name);
        if (user == null) {
            user = new User(name, new char[] {});
        }
        user.setHits(user.getHits() + 1);
        userCrudRepository.save(user);
        model.addAttribute("hits", user.getHits());
        return "hello";
    }

    @Autowired
    public void setUserCrudRepository(UsersCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }
}
