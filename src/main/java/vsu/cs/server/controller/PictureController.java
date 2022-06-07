package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import vsu.cs.server.config.WebSecurityConfig;
import vsu.cs.server.model.Picture;
import vsu.cs.server.model.User;
import vsu.cs.server.service.PictureService;
import vsu.cs.server.service.RoleService;
import vsu.cs.server.service.UserService;
import vsu.cs.server.utils.FileUploadUtil;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Controller
@Api(description = "picture controller")
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/add")
    public String addGet(
            Principal principal,
            Model model) {
        if (principal != null) {
            User currUser = userService.getByLogin(principal.getName());
            model.addAttribute("currUser", currUser);
        }
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        return "picture/add";
    }

    @PostMapping("/add")
    public String addPost(
            @RequestParam("image") MultipartFile multipartFile,
            @RequestParam String name,
            @RequestParam Boolean isPublic,
            Principal principal,
            Model model) throws IOException {
        User creator = null;
        if (principal != null) {
            User currUser = userService.getByLogin(principal.getName());
            model.addAttribute("currUser", currUser);
            creator = currUser;
        }
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        Date creationDate = new Date();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        Picture picture = new Picture(creator, name, fileName, creationDate, isPublic);
        String uploadDir = "user-photos/" + creator.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "picture/add";
    }
}
