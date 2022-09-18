package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.cs.server.config.WebSecurityConfig;
import vsu.cs.server.model.Role;
import vsu.cs.server.model.User;
import vsu.cs.server.repository.RoleRepository;
import vsu.cs.server.repository.UserRepository;
import vsu.cs.server.service.PictureService;
import vsu.cs.server.service.RoleService;
import vsu.cs.server.service.UserService;

import java.security.Principal;

@Controller
@Api(description = "user controller")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/profile")
    public String profileGet(
            Principal principal,
            Model model) {
        if (principal != null) {
            User currUser = userService.getByLogin(principal.getName());
            model.addAttribute("currUser", currUser);
            model.addAttribute("pictures", pictureService.getByUser(currUser.getId()));
        }
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        return "user/profile";
    }
}

