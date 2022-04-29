package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.cs.server.repository.PictureRepository;
import vsu.cs.server.repository.UserRepository;

@RestController
@RequestMapping("/controller")
@Api(description = "Picture controller")
public class PictureController {
    private PictureRepository pictureRepository;
    private UserRepository userRepository;

    @Autowired
    public PictureController(PictureRepository pictureRepository, UserRepository userRepository) {
        this.pictureRepository = pictureRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/controller")
    @ApiOperation("Test function")
    void test() {
        //test
    }
}
