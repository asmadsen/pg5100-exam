package no.asmadsen.exam.controller;

import no.asmadsen.exam.entity.User;
import no.asmadsen.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
@RequestScoped
public class UserController {
    @Autowired
    private UserService userService;

    private Optional<User> _getUser() {
        Object principal = SecurityContextHolder.getContext()
                                                .getAuthentication()
                                                .getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();

            return Optional.ofNullable(userService.findByEmail(email));
        }
        return Optional.empty();
    }

    public User getUser() {
        return _getUser().orElse(null);
    }

    public String getUserAlias() {
        return _getUser().map(User::getAlias).orElse(null);
    }

    public UUID getUserId() {
        return _getUser().map(User::getId).orElse(null);
    }
}
