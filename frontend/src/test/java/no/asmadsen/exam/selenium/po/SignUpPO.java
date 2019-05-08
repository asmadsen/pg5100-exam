package no.asmadsen.exam.selenium.po;

import com.github.javafaker.Faker;
import no.asmadsen.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

public class SignUpPO extends LayoutPO {
    private Faker faker = new Faker();

    public SignUpPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public SignUpPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Sign up");
    }

    public IndexPO createUser(String email, String password, String alias) {
        setText("email", email);
        setText("password", password);
        setText("confirmPassword", password);
        setText("alias", alias);
        setText("firstName", faker.name().firstName());
        setText("lastName", faker.name().lastName());
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if (po.isOnPage()) {
            return po;
        }
        return null;
    }
}
