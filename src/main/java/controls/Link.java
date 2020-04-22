package controls;

import com.github.metalloid.pagefactory.controls.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class Link extends Control {

  public Link(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
  }

  public Link(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
  }

  public String getUrl() {
    return element().getAttribute("href");
  }
}
