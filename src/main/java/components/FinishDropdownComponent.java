package components;

import com.github.metalloid.pagefactory.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobjects.ProductView;

@FindBy(css = "div[class*='finish-cart-select']")
public class FinishDropdownComponent extends DropdownComponent<ProductView.Finish> {

  public FinishDropdownComponent(WebDriver driver, By selector) {
    super(driver, selector);
  }

  public FinishDropdownComponent(WebDriver driver) {
    super(driver);
  }

  public ProductView.Finish getSelectedFinish() {
    return super.getSelected(ProductView.Finish::parse);
  }
}
