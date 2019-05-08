package no.asmadsen.exam.selenium.po;

import no.asmadsen.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class LayoutPO extends PageObject {
    public LayoutPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LayoutPO(PageObject other) {
        super(other);
    }

    public SignUpPO toSignUp() {
        clickAndWait("signUpBtn");

        SignUpPO po = new SignUpPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public LoginPO toLogin() {
        clickAndWait("loginBtn");

        LoginPO po = new LoginPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public ProfilePO toProfile() {
        clickAndWait("profileBtn");

        ProfilePO po = new ProfilePO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public IndexPO doLogout() {
        clickAndWait("logoutBtn");

        IndexPO po = new IndexPO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public boolean isLoggedIn() {
        return getDriver().findElements(By.id("logoutBtn")).size() > 0 &&
                getDriver().findElements(By.id("loginBtn")).size() == 0 &&
                getDriver().findElements(By.id("signUpBtn")).size() == 0;
    }
}
