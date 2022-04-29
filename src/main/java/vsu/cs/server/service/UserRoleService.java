package vsu.cs.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.cs.server.model.User;
import vsu.cs.server.model.UserRole;
import vsu.cs.server.repository.UserRepository;
import vsu.cs.server.repository.UserRoleRepository;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void add(UserRole value) {
        this.userRoleRepository.save(value);
    }

    public void remove(UserRole value) {
        this.userRoleRepository.delete(value);
    }

    public List<UserRole> getAll() {
        return this.userRoleRepository.findAll();
    }
}
