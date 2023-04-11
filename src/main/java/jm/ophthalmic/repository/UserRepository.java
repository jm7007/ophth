package jm.ophthalmic.repository;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.User;

public interface UserRepository {
    User save(User user);
    Optional<User> findbyId(Long id);
    Optional<User> findbyAccount(String account);
    Optional<User> findbyEmail(String email);
    List<User> findAll();
    Optional<User> delete(Long id);
    Optional<User> modifyUser(Long id, User newUser);
}
