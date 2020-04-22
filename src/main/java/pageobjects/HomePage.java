package pageobjects;

import com.github.metalloid.core.Metalloid;
import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.pagefactory.components.FindComponent;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.Wait;
import components.Bestselling;
import controls.Button;
import java.time.Duration;

public class HomePage extends PageObject {

  @FindComponent private Bestselling bestsellers;

  @FindBy(css = "button[onclick*='acceptCookieAlert()']")
  private Button acceptCookieAlert;

  @Inject protected Wait wait;

  public HomePage open() {
    Metalloid.get(System.getProperty("url"));
    acceptCookieAlert.click();
    return this;
  }

  public boolean isOpened() {
    return Metalloid.getTitle().contains("Displate");
  }

  public Bestselling bestselling() {
    wait.withTimeout(Duration.ofSeconds(10)).until(driver -> bestsellers.isDisplayed());
    return bestsellers;
  }
}
