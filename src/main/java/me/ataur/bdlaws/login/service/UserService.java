package me.ataur.bdlaws.login.service;

/**
 * @author Amran
 */
import me.ataur.bdlaws.admin.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
