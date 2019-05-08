package no.asmadsen.exam.service;


import com.github.javafaker.Faker;
import no.asmadsen.exam.StubApplication;
import no.asmadsen.exam.entity.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RatingServiceTest extends ServiceTestBase {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    private Faker faker = new Faker();

    private UUID createMovie() {
        return movieService.createMovie(faker.book().title(), "http://example.com/image.jpg", "Plot", "1994-10-14", 154,
                                        Collections.singleton("Drama"),
                                        "Quentin Tarantino");
    }

    private User createUser() {
        userService.createUser("post@example.com", "Password", "admin", "John", "Doe");
        return userService.findByEmail("post@example.com");
    }

}
