package no.asmadsen.exam.service;

import com.github.javafaker.Faker;
import no.asmadsen.exam.StubApplication;
import no.asmadsen.exam.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Faker faker = new Faker();

    @Test
    public void shouldCreateNewUser() {
        assertTrue(userService.createUser(faker.internet().emailAddress(),
                                          "Password",
                                          faker.name().username(),
                                          faker.name().firstName(),
                                          faker.name().lastName()));
    }


    @Test
    public void shouldNotCreateIfClashesWithExistingEmailOrAlias() {
        String email = faker.internet().emailAddress();
        String alias = faker.name().username();
        userService.createUser(email,
                               "Password",
                               alias,
                               faker.name().firstName(),
                               faker.name().lastName());

        // Make sure doesn't fail because of clashing alias when testing email
        String newAlias = faker.name().username();
        while (alias.equals(newAlias)) {
            newAlias = faker.name().username();
        }

        assertFalse(userService.createUser(email,
                               "Password",
                               newAlias,
                               faker.name().firstName(),
                               faker.name().lastName()));

        String newEmail = faker.internet().emailAddress();
        while (newEmail.equals(email)) {
            newEmail = faker.internet().emailAddress();
        }

        assertFalse(userService.createUser(newEmail,
                               "Password",
                               alias,
                               faker.name().firstName(),
                               faker.name().lastName()));
    }

    @Test
    public void shouldGetUserByEmailOrAlias() {
        String email = faker.internet().emailAddress();
        String alias = faker.name().username();
        userService.createUser(email,
                               "Password",
                               alias,
                               faker.name().firstName(),
                               faker.name().lastName());

        assertNotNull(userService.findByEmail(email));
        assertNotNull(userService.findByAlias(alias));
    }

    @Test
    public void shouldChangePassword() {
        String email = faker.internet().emailAddress();
        String alias = faker.name().username();
        userService.createUser(email,
                               "Password",
                               alias,
                               faker.name().firstName(),
                               faker.name().lastName());

        User user = userService.findByEmail(email);

        assertTrue(passwordEncoder.matches("Password", user.getPasswordHash()));

        userService.updatePassword(user.getId(), "NewPassword");

        user = userService.findByEmail(email);
        assertFalse(passwordEncoder.matches("Password", user.getPasswordHash()));
        assertTrue(passwordEncoder.matches("NewPassword", user.getPasswordHash()));
    }
}
