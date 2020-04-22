package controls;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class AddToCartButton extends Button {

  public AddToCartButton(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
  }

  public AddToCartButton(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
  }

  public Double getPrice() {
    return Double.parseDouble(
        element().findElement(By.cssSelector("span[class*='item-price']")).getText());
  }
}
