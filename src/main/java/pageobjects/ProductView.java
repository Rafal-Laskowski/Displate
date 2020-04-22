package pageobjects;

import com.github.metalloid.core.Metalloid;
import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.Wait;
import com.github.metalloid.webdriver.utils.conditions.ControlConditions;
import controls.AddToCartButton;
import controls.Button;
import controls.RadioButton;

public class ProductView extends PageObject {

  public enum Finish {
    Matte,
    Gloss;

    public static Finish parse(String value) {
      switch (value) {
        case "0":
          return Matte;
        case "1":
        case "2":
          return Gloss;
        default:
          throw new RuntimeException(String.format("Value: [%s] is not implemented", value));
      }
    }
  }

  public enum Frame {
    NONE,
    BLACK_WOOD,
    WHITE_WOOD,
    GRAPHITE,
    NATURAL_WOOD;

    public static Frame parse(String value) {
      switch (value) {
        default:
          throw new RuntimeException("not implemented");
        case "512":
          return BLACK_WOOD;
      }
    }
  }

  @FindBy(textEquals = "Select finish & add frame")
  private Button selectFinishAndFrameButton;

  @FindBy(xpath = "//*[contains(.,'Gloss')]/preceding-sibling::input[@name='displate-finish']")
  private RadioButton glossInput;

  @FindBy(
      xpath =
          "//*[contains(.,'Gloss')]/preceding-sibling::input[@name='displate-finish']/following-sibling::span[@class='input-radio__checkmark']")
  private RadioButton glossCheckmark;

  @FindBy(
      xpath =
          "//div[contains(.,'Black wood pattern')][contains(@class, 'tooltip')]/div[@class='tooltip__icon']//img")
  private Button blackWoodPattern;

  @FindBy(id = "add-to-cart")
  private AddToCartButton addToCartButton;

  @Inject private Wait wait;

  public ProductView selectFinishAndFrame(Finish finish, Frame frame) {
    wait.until(ControlConditions.controlToBeClickable(selectFinishAndFrameButton));
    selectFinishAndFrameButton.click();

    if (finish.equals(Finish.Gloss)) {
      glossCheckmark.check();
    }

    switch (frame) {
      default:
        throw new RuntimeException("not implemented");
      case BLACK_WOOD:
        blackWoodPattern.click();
        break;
    }

    return this;
  }

  public Double getPrice() {
    return addToCartButton.getPrice();
  }

  public ShoppingOption addToCart() {
    addToCartButton.click();
    return new ShoppingOption();
  }

  public String getDisplateUrl() {
    return Metalloid.getCurrentUrl();
  }
}
