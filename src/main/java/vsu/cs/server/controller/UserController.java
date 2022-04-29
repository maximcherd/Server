package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vsu.cs.server.repository.UserRepository;
import vsu.cs.server.repository.UserRoleRepository;

@RestController
@RequestMapping("/controller")
@Api(description = "User controller")
public class UserController {
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRoleRepository userRoleRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/controller")
    @ApiOperation("Test function")
    void test() {

    }
}
