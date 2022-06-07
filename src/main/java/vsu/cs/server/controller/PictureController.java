package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vsu.cs.server.repository.PictureRepository;
import vsu.cs.server.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/picture")
@Api(description = "picture controller")
public class PictureController {
    private PictureRepository pictureRepository;
    private UserRepository userRepository;

    @Autowired
    public PictureController(PictureRepository pictureRepository, UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    @ApiOperation("Test function")
    public String test(
//            Principal principal,
            Model model
    ) {
        return "picture/test";
    }
}
