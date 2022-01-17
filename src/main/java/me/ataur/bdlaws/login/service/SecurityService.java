package me.ataur.bdlaws.login.service;

/**
 * @author Amran
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
