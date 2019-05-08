package no.asmadsen.exam.selenium;

import com.github.javafaker.Faker;
import no.asmadsen.exam.selenium.po.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SeleniumTestBase {

    protected abstract WebDriver getDriver();
    protected abstract String getServerHost();
    protected abstract int getServerPort();

    private Faker faker = new Faker();
    private IndexPO home;

    private static final AtomicInteger counter = new AtomicInteger(0);

    private String getUniqueId() {
        return faker.name().username() + counter.getAndIncrement();
    }

    private IndexPO createNewUser(String email, String password, String alias) {
        home.toStartingPage();

        SignUpPO signUpPO = home.toSignUp();

        IndexPO indexPO = signUpPO.createUser(email, password, alias);
        assertNotNull(indexPO);

        return indexPO;
    }

    @BeforeEach
    public void initTest() {
        getDriver().manage().deleteAllCookies();

        home = new IndexPO(getDriver(), getServerHost(), getServerPort());

        home.toStartingPage();

        assertTrue(home.isOnPage(), "Failed to start from Home Page");
    }

    @Test
    public void shouldFilterBasedOnGenres() {
        home.doFilterGenre(new String[]{"Action"});

        List<WebElement> movies = getDriver().findElements(By.className("movie-card"));
        for (WebElement movie : movies) {
            String genres = movie.findElement(By.className("all-genres")).getAttribute("innerText");
            assertTrue(genres.contains("Action"));
        }

        getDriver().findElement(By.id("genre-Action")).click();
        home.waitForPageToLoad();

        home.toStartingPage();
        home.doFilterGenre(new String[]{"Action", "Thriller"});

        movies = getDriver().findElements(By.className("movie-card"));
        for (WebElement movie : movies) {
            String genres = movie.findElement(By.className("all-genres")).getAttribute("innerText");
            assertTrue(genres.contains("Action"));
            assertTrue(genres.contains("Thriller"));
        }
    }

    @Test
    public void shouldCreateAndLogoutAndBackInUser() {
        assertFalse(home.isLoggedIn());

        String alias = getUniqueId();
        String email = faker.internet().safeEmailAddress(alias);
        String password = faker.internet().password();
        home = createNewUser(email, password, alias);

        assertTrue(home.isLoggedIn());
        assertTrue(getDriver().getPageSource().contains("Welcome " + alias));

        home.doLogout();

        assertFalse(home.isLoggedIn());
        assertFalse(getDriver().getPageSource().contains("Welcome " + alias));

        LoginPO loginPO = home.toLogin();
        assertNull(loginPO.login(email, password + "WRONG"));

        IndexPO indexPO = loginPO.login(email, password);
        assertTrue(indexPO.isOnPage());
    }

    @Test
    public void shouldFailWhenTyingToRegisterWithSameEmailOrAlias() {
        String alias = getUniqueId();
        String email = faker.internet().safeEmailAddress(alias);
        String password = faker.internet().password();
        home = createNewUser(email, password, alias);
        home.doLogout();

        SignUpPO signUpPO = home.toSignUp();

        String newAlias = getUniqueId();
        assertNull(signUpPO.createUser(email, password, newAlias));

        String newEmail = faker.internet().safeEmailAddress(newAlias);
        assertNull(signUpPO.createUser(newEmail, password, alias));
    }

    @Test
    public void shouldRateMovie() {
        String alias = getUniqueId();
        String email = faker.internet().safeEmailAddress(alias);
        String password = faker.internet().password();
        createNewUser(email, password, alias);

        MoviePO moviePO = home.showMovieDetails();
        moviePO.clickAndWait("rateMovieBtn");
        moviePO.doRateMovie(2, "____MY___FIRST___COMMENT___");
        assertTrue(moviePO.getDriver().getPageSource().contains("____MY___FIRST___COMMENT___"));

        getDriver().findElement(By.className("changeRatingBtn")).click();
        moviePO.waitForPageToLoad();
        moviePO.doRateMovie(5, "____MY___SECOND___COMMENT___");
        assertFalse(moviePO.getDriver().getPageSource().contains("____MY___FIRST___COMMENT___"));
        assertTrue(moviePO.getDriver().getPageSource().contains("____MY___SECOND___COMMENT___"));
    }

    @Test
    public void shouldShowMessageIfNoMovieFound() {
        getDriver().get(getServerHost() + ":" + getServerPort() + "/movie.xhtml");
        MoviePO moviePO = new MoviePO(home);
        assertTrue(moviePO.isOnPage());

        assertTrue(getDriver().getPageSource().contains("Could not find any movie with that id"));

        getDriver().get(getServerHost() + ":" + getServerPort() + "/movie.xhtml?id=" + UUID.randomUUID().toString());
        assertTrue(moviePO.isOnPage());

        assertTrue(getDriver().getPageSource().contains("Could not find any movie with that id"));
    }

    @Test
    public void shouldChangePassword() {
        String alias = getUniqueId();
        String email = faker.internet().safeEmailAddress(alias);
        String password = "Password";
        home = createNewUser(email, password, alias);

        ProfilePO profilePO = home.toProfile();
        assertTrue(profilePO.isOnPage());

        profilePO.clickAndWait("changePassword");
        profilePO.changePassword("Wrong", "New");
        assertTrue(profilePO.isChangingPassword());

        profilePO.changePassword(password, "New", "Mismatch");
        assertTrue(profilePO.isChangingPassword());

        profilePO.changePassword(password, "New");
        assertFalse(profilePO.isChangingPassword());
    }
}
