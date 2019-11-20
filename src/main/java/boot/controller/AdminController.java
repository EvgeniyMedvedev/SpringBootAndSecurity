package boot.controller;

import boot.model.User;
import boot.repository.UserRepository;
import boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    @Autowired
    private UserService repository;

    @GetMapping(value = "/admin")
    public String getAll(Model model){
        Iterable<User> all = repository.getAll();
        model.addAttribute("usersList",all);
        return "users";
    }

    @PostMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") int id, User user) {
        User get = repository.getById(id);
        get.setPassword(user.getPassword());
        get.setLogin(user.getLogin());
        get.setName(user.getName());
        repository.add(get);

        return "redirect:/admin";
    }

    @PostMapping(value = "/add")
    public String addUser(@ModelAttribute("user")User user){
        repository.add(user);

        return "redirect:/admin";
    }

    @GetMapping(value="/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        repository.delete(id);

        return "redirect:/admin";
    }

}
