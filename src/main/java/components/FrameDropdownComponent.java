package components;

import com.github.metalloid.pagefactory.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.ProductView;

@FindBy(css = "div[class*='frame-cart-select']")
public class FrameDropdownComponent extends DropdownComponent<ProductView.Frame> {

  public FrameDropdownComponent(WebDriver driver, By selector) {
    super(driver, selector);
  }

  public FrameDropdownComponent(WebDriver driver) {
    super(driver);
  }

  public ProductView.Frame getSelectedFrame() {
    return super.getSelected(ProductView.Frame::parse);
  }
}
