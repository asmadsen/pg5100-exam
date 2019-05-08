package no.asmadsen.exam.service;

import no.asmadsen.exam.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createUser(String email, String password, String alias, String firstName, String lastName) {
        email = email.toLowerCase();
        String hashedPassword = passwordEncoder.encode(password);

        if (findByEmail(email) != null) return false;
        if (findByAlias(alias) != null) return false;

        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hashedPassword);
        user.setAlias(alias);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Collections.singleton("USER"));
        user.setEnabled(true);

        em.persist(user);

        return true;
    }

    public User findByAlias(String alias) {
        try {
            return em.createNamedQuery(User.GET_BY_ALIAS, User.class)
                     .setParameter("alias", alias)
                     .getSingleResult();
        } catch (Exception ignored) {
            return null;
        }
    }

    public User findByEmail(String email) {
        try {
            return em.createNamedQuery(User.GET_BY_EMAIL, User.class)
                    .setParameter("email", email.toLowerCase())
                    .getSingleResult();
        } catch (Exception ignored) {
            return null;
        }
    }

    public void updatePassword(UUID userId, String password) {
        User user = em.find(User.class, userId);
        user.setPasswordHash(passwordEncoder.encode(password));
    }
}
