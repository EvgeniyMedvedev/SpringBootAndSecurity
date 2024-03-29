package boot.controller;

import boot.model.User;
import boot.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final UserService service;

    public LoginController(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            return "redirect: /user";
        }
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        return "login";
    }

    @GetMapping("/user")
    public String getIndexPage(Authentication authentication, ModelMap model) {
        User user = service.getByLogin(authentication.getName());
        String name = user.getName();
        String role = user.getRoles().iterator().next().getAuthority();
        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("isAdmin", true);
        }

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
