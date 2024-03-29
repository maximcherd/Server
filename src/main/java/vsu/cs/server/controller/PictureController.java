package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vsu.cs.server.config.WebSecurityConfig;
import vsu.cs.server.model.Picture;
import vsu.cs.server.model.User;
import vsu.cs.server.service.PictureService;
import vsu.cs.server.service.RoleService;
import vsu.cs.server.service.UserService;
import vsu.cs.server.utils.FileUploadUtil;
import vsu.cs.server.utils.image_processing.BasicImageProcessingUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Controller
@Api(description = "picture controller")
@RequestMapping("/picture")
public class PictureController {
    private static final String C_ERROR_PICTURE_SIZE = "размер изображения слишком большой";

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
        String fileName = creationDate.getTime() + "." + "png";
        Picture picture = new Picture(creator, fileName, creationDate, isPublic);
        pictureService.add(picture);
        String uploadDir = "user-photos/" + creator.getId().toString();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "picture/add";
    }

    @GetMapping("/info/{id}")
    public String infoGet(
            @PathVariable("id") Long id,
            Principal principal,
            Model model) {
        if (principal != null) {
            User currUser = userService.getByLogin(principal.getName());
            model.addAttribute("currUser", currUser);
        }
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        model.addAttribute("picture", pictureService.getById(id));
        model.addAttribute("pictures", pictureService.getByPicture(id));
        return "picture/info";
    }

    @PostMapping("/info/{id}")
    public String infoPost(
            @PathVariable("id") Long id,
            @RequestParam String action,
            Principal principal,
            Model model) {
        if (principal != null) {
            User currUser = userService.getByLogin(principal.getName());
            model.addAttribute("currUser", currUser);
        }
        model.addAttribute("isUser", WebSecurityConfig.isUser());
        model.addAttribute("isAdmin", WebSecurityConfig.isAdmin());
        model.addAttribute("picture", pictureService.getById(id));
        model.addAttribute("pictures", pictureService.getByPicture(id));
        if (action.equals("delete")) {
            pictureService.remove(pictureService.getById(id));
        }
        return "redirect:/";
    }

    @GetMapping("/change/{id}")
    public String changeGet(
            @PathVariable("id") Long id,
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
        model.addAttribute("picture", pictureService.getById(id));
        model.addAttribute("buffer", pictureService.getById(id));
        BasicImageProcessingUtil util = new BasicImageProcessingUtil();
        String path = "user-photos/" + creator.getId() + "/";
        String fileName = "buffer.png";
        File outputfile = new File(path + fileName);
        BufferedImage image = util.getImage(path + pictureService.getById(id).getUrl());
        ImageIO.write(image, "png", outputfile);
        return "picture/change";
    }

    @PostMapping("/change/{id}")
    public String changePost(
            @PathVariable("id") Long id,
            @RequestParam Integer coefficient,
            @RequestParam String action,
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
        model.addAttribute("picture", pictureService.getById(id));

        BasicImageProcessingUtil util = new BasicImageProcessingUtil();
        String path = "user-photos/" + creator.getId() + "/";
        String fileName = "buffer.png";
        BufferedImage image = util.getImage(path + fileName);
        if (action.equals("save")) {
            Date date = new Date();
            Picture picture = new Picture(creator, date.getTime() + ".png", new Date(), true, pictureService.getById(id));
            pictureService.add(picture);
            File outputfile = new File(path + date.getTime() + ".png");
            ImageIO.write(image, "png", outputfile);
            return "redirect:/picture/info/" + picture.getId();
        }
        File outputfile = new File(path + fileName);
        image = util.editImage(image, coefficient);
        ImageIO.write(image, "png", outputfile);
        Picture buffer = new Picture(creator, fileName, new Date(), true);
        model.addAttribute("buffer", buffer);
        return "picture/change";
    }
}
