package vsu.cs.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vsu.cs.server.model.User;
import vsu.cs.server.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(User value) {
        this.userRepository.save(value);
    }

    public void remove(User value) {
        this.userRepository.delete(value);
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }
}
