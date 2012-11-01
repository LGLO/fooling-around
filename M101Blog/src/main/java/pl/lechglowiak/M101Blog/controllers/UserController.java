package pl.lechglowiak.M101Blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.lechglowiak.M101Blog.entities.User;

@Controller
public class UserController {
    @RequestMapping(value = "/author/{name}", method = RequestMethod.GET)
    public String showUser(@PathVariable("name") User user, ModelMap model){
        if(user==null){
            model.addAttribute("name", "<unknown_user>");
            model.addAttribute("hits",-1);
        }else{
            model.addAttribute("name", user.getName());
            model.addAttribute("hits", user.getHits());
        }
        return "hello";
    }
}
