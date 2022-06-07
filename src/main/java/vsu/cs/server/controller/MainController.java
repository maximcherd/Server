package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.cs.server.config.WebSecurityConfig;
import vsu.cs.server.model.User;
import vsu.cs.server.service.PictureService;
import vsu.cs.server.service.RoleService;
import vsu.cs.server.service.UserService;

import java.security.Principal;


@Controller
@Api(description = "picture controller")
@RequestMapping("/home")
public class MainController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/main")
    public String mainGet(
            Principal principal,
            Model model) {
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        return "home/main";
    }

    @GetMapping("/about")
    public String aboutGet(
            Principal principal,
            Model model) {
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        return "home/main";
    }
}
