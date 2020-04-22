package components;

import com.github.metalloid.pagefactory.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@FindBy(css = "div[class*='format-cart-select']")
public class SizeDropdownComponent extends DropdownComponent<SizeDropdownComponent.Size> {

  public enum Size {
    M,
    L,
    XL;

    public static Size parse(String value) {
      switch (value) {
        case "1001":
          return M;
        case "1002":
          return L;
        case "1007":
          return XL;
        default:
          throw new RuntimeException("not implemented");
      }
    }
  }

  public SizeDropdownComponent(WebDriver driver, By selector) {
    super(driver, selector);
  }

  public SizeDropdownComponent(WebDriver driver) {
    super(driver);
  }

  public Size getSelectedSize() {
    return super.getSelected(Size::parse);
  }
}
