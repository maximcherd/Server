package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vsu.cs.server.repository.RoleRepository;
import vsu.cs.server.repository.UserRepository;

@Controller
@Api(description = "user controller")
public class UserController {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }
}
