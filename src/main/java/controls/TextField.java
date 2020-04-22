package controls;

import com.github.metalloid.pagefactory.controls.Control;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class TextField extends Control {

  public TextField(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
  }

  public TextField(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
  }

  public void sendKeys(String string) {
    element().sendKeys(string);
  }
}
