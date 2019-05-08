package no.asmadsen.exam.selenium.po;

import no.asmadsen.exam.selenium.PageObject;
import org.openqa.selenium.WebDriver;

public class LoginPO extends LayoutPO {
    public LoginPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public LoginPO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Login");
    }

    public IndexPO login(String email, String password) {
        setText("username", email);
        setText("password", password);
        clickAndWait("submit");

        IndexPO po = new IndexPO(this);
        if (po.isOnPage()) {
            return po;
        }
        return null;
    }
}
