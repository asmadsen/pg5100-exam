package no.asmadsen.exam.selenium.po;

import no.asmadsen.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MoviePO extends LayoutPO {
    public MoviePO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public MoviePO(PageObject other) {
        super(other);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().findElements(By.id("moviePage")).size() > 0;
    }

    public void doRateMovie(int rating, String review) {
        setText("review", review);
        driver.findElement(By.id("score:" + (rating - 1))).click();
        clickAndWait("submit");
    }
}
