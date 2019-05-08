package no.asmadsen.exam.selenium.po;

import no.asmadsen.exam.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class IndexPO extends LayoutPO {
    public IndexPO(WebDriver driver, String host, int port) {
        super(driver, host, port);
    }

    public IndexPO(PageObject other) {
        super(other);
    }

    public void toStartingPage() {
        getDriver().get(host + ":" + port);
    }

    @Override
    public boolean isOnPage() {
        return getDriver().getTitle().contains("Home");
    }

    public MoviePO showMovieDetails() {
        WebElement element = getDriver().findElements(By.className("show-details-btn")).get(0);
        element.click();
        waitForPageToLoad();

        MoviePO po = new MoviePO(this);
        assertTrue(po.isOnPage());

        return po;
    }

    public void doFilterGenre(String[] genres) {
        for (String genre : genres) {
            clickAndWait("genre-" + genre);
        }
    }
}
