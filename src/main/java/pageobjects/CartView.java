package pageobjects;

import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.pagefactory.components.FindComponent;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.JavaScript;
import com.github.metalloid.webdriver.utils.Wait;
import com.github.metalloid.webdriver.utils.conditions.ControlConditions;
import com.github.metalloid.webdriver.utils.conditions.ExpectedCondition;
import com.github.metalloid.webdriver.utils.conditions.Visibility;
import components.CountryDropdownComponent;
import components.FinishDropdownComponent;
import components.FrameDropdownComponent;
import components.SizeDropdownComponent;
import controls.Button;
import controls.Link;
import controls.TextField;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartView extends PageObject {

  public enum ShippingOptions {
    US;

    public static ShippingOptions parse(String value) {
      switch (value) {
        case "US":
          return US;
        default:
          throw new RuntimeException("not implemented");
      }
    }
  }

  @ExpectedCondition(timeout = 10, condition = Visibility.class)
  @FindBy(css = "a[class*='image-in-cart']")
  private Link imageInCart;

  @FindBy(css = "div[class='desk-cart-subtotal'] span[class*='text--extra-big']")
  private WebElement subtotalPrice;

  @FindBy(css = "div[class*='quantity-cart-select']")
  private WebElement quantity;

  @FindComponent private SizeDropdownComponent sizeDropdown;

  @FindComponent private FinishDropdownComponent finishDropdown;

  @FindComponent private FrameDropdownComponent frameDropdown;

  @FindComponent private CountryDropdownComponent countryDropdown;

  @FindBy(textContains = "I have a discount code")
  private Button discountCodeButton;

  @FindBy(id = "discount-code")
  private TextField discountCodeTextField;

  @FindBy(id = "discount-apply")
  private Button applyDiscountCodeButton;

  @FindBy(xpath = "//div[contains(text(), 'Discount')]//strong")
  private WebElement appliedDiscountElement;

  @FindBy(id = "cart-total")
  private WebElement cartTotal;

  @FindBy(id = "shipment-amount")
  private WebElement shippingCostElement;

  @FindBy(css = "#order-total strong")
  private WebElement orderTotalElement;

  @Inject private JavaScript js;

  @Inject private Wait wait;

  public String getDisplateUrl() {
    return imageInCart.getUrl();
  }

  public Double getPrice() {
    return Double.parseDouble(subtotalPrice.getText().substring(1));
  }

  public int getQuantity() {
    return Integer.parseInt(quantity.getAttribute("value"));
  }

  public SizeDropdownComponent.Size getSize() {
    return sizeDropdown.getSelectedSize();
  }

  public ProductView.Finish getFinish() {
    return finishDropdown.getSelectedFinish();
  }

  public ProductView.Frame getFrame() {
    return frameDropdown.getSelectedFrame();
  }

  public CartView shipTo(ShippingOptions shippingOptions) {
    String currencyBeforeChange = getCurrency();

    countryDropdown.select(shippingOptions);
    wait.until(d -> countryDropdown.getSelectedCountry().equals(ShippingOptions.US));
    wait.withoutException(true).until(d -> !currencyBeforeChange.equals(getCurrency()));

    return new CartView();
  }

  public CartView enterDiscountCode(String discountCode) {
    js.scrollUp();
    wait.withoutException(true).until(ControlConditions.stalenessOf(discountCodeButton));
    discountCodeButton.click();
    discountCodeTextField.sendKeys(discountCode);
    applyDiscountCodeButton.click();
    return this;
  }

  public Integer getAppliedDiscountAmount() {
    wait.until(ExpectedConditions.visibilityOf(appliedDiscountElement));
    String discount = appliedDiscountElement.getText();
    return Integer.parseInt(discount.substring(0, discount.length() - 2));
  }

  public Double getCartTotal() {
    return Double.parseDouble(cartTotal.getText().trim().substring(1));
  }

  public String getCurrency() {
    return subtotalPrice.getText().substring(0, 2);
  }

  public Double getShippingCost() {
    return Double.parseDouble(shippingCostElement.getText().trim().substring(1));
  }

  public Double getOrderTotalCost() {
    String total = orderTotalElement.getText();
    return Double.parseDouble(total.split("\\s")[0]);
  }
}
