package controls;

import com.github.metalloid.pagefactory.controls.Control;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import pageobjects.ProductView;

public class SlickSlide extends Control {

  public SlickSlide(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
  }

  public SlickSlide(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
  }

  public ProductView openDetails() {
    this.click();
    return new ProductView();
  }

  public boolean isActive() {
    return !Boolean.parseBoolean(this.element().getAttribute("aria-hidden"));
  }

  public Optional<String> getImageDescription() {
    if (isActive()) {
      return Optional.ofNullable(this.element().findElement(By.tagName("img")).getAttribute("alt"));
    } else {
      return Optional.empty();
    }
  }
}
