package vsu.cs.server.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import vsu.cs.server.repository.RoleRepository;
import vsu.cs.server.repository.UserRepository;

@Controller
@Api(description = "role controller")
public class RoleController {
    private RoleRepository roleRepository;
    private UserRepository repository;

    @Autowired
    public RoleController(RoleRepository roleRepository, UserRepository repository) {
        this.roleRepository = roleRepository;
        this.repository = repository;
    }

}
