package vsu.cs.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vsu.cs.server.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
