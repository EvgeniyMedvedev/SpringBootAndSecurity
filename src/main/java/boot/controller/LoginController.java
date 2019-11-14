package boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import boot.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private UserService service;

    @Autowired
    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            //model.addAttribute("user", request.getParameter("name"));
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/user")
    public String getIndexPage(Authentication authentication, ModelMap model) {
        String name = service.getByLogin(authentication.getName()).getName();
        model.addAttribute("user", name);
        return "welcome";
    }

    @GetMapping("/")
    public String getLoginPage(Authentication authentication, Model model,HttpServletRequest request) {
        if (authentication != null) {
            model.addAttribute("user", request.getParameter("name"));
            return "redirect: /welcome";
        }
        return "login";
    }

}
