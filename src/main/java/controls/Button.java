package controls;

import com.github.metalloid.pagefactory.controls.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class Button extends Control {

  public Button(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
  }

  public Button(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
  }
}
