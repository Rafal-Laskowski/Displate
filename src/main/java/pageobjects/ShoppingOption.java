package pageobjects;

import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.Wait;
import com.github.metalloid.webdriver.utils.conditions.ControlConditions;
import controls.Button;

public class ShoppingOption extends PageObject {

  @FindBy(id = "proceedToCart")
  private Button proceedToCartButton;

  @Inject private Wait wait;

  public CartView proceedToCart() {
    wait.until(ControlConditions.isDisplayed(proceedToCartButton));
    wait.until(ControlConditions.controlToBeClickable(proceedToCartButton));
    proceedToCartButton.click();
    return new CartView();
  }
}
