package components;

import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.webdriver.utils.UtilsFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.CartView;

@FindBy(id = "select-country")
public class CountryDropdownComponent extends DropdownComponent<CartView.ShippingOptions> {

  @FindBy(className = "simplebar-scroll-content")
  private WebElement scrollBar;

  public CountryDropdownComponent(WebDriver driver, By selector) {
    super(driver, selector);
    UtilsFactory.initUtilities(driver, this);
  }

  public CountryDropdownComponent(WebDriver driver) {
    super(driver);
    UtilsFactory.initUtilities(driver, this);
  }

  public CartView.ShippingOptions getSelectedCountry() {
    return super.getSelected(CartView.ShippingOptions::parse);
  }

  public void select(CartView.ShippingOptions shippingOptions) {
    super.select(shippingOptions.name());
  }
}
