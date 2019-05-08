package no.asmadsen.exam.controller;

import no.asmadsen.exam.entity.User;
import no.asmadsen.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NamedQuery;

@Named
@RequestScoped
public class UpdatePasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean changePassword;

    private String previousPassword;

    private String password;

    private String confirmPassword;

    public boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext()
                                                  .getAuthentication()
                                                  .getPrincipal();
    }

    public String changePassword() {
        User user = userService.findByEmail(getUserDetails().getUsername());

        if (!passwordEncoder.matches(previousPassword, user.getPasswordHash()) || !password.equals(confirmPassword)) {
            return "/profile.jsf?faces-redirect=true&includeViewParams=true&error=true";
        }

        userService.updatePassword(user.getId(), password);

        return "/profile.jsf?faces-redirect=true";
    }
}
