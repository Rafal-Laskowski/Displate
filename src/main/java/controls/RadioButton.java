package controls;

import com.github.metalloid.pagefactory.controls.Control;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.JavaScript;
import com.github.metalloid.webdriver.utils.UtilsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class RadioButton extends Control {

  @Inject private JavaScript js;

  public RadioButton(WebDriver driver, SearchContext searchContext, By locator) {
    super(driver, searchContext, locator);
    UtilsFactory.initUtilities(driver, this);
  }

  public RadioButton(WebDriver driver, SearchContext searchContext, By locator, Integer index) {
    super(driver, searchContext, locator, index);
    UtilsFactory.initUtilities(driver, this);
  }

  public void check() {
    if (isChecked() == null || !isChecked()) {
      element().click();
    }
  }

  public Boolean isChecked() {
    return js.isChecked(element());
  }
}
