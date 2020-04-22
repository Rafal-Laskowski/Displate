package components;

import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.pagefactory.components.Component;
import com.github.metalloid.webdriver.WebDriverPool;
import com.github.metalloid.webdriver.utils.JavaScript;
import controls.DropdownOption;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class DropdownComponent<T> extends Component {

  @FindBy(css = "div[class='input-select__item']")
  private List<DropdownOption> options;

  public DropdownComponent(WebDriver driver, By selector) {
    super(driver, selector);
  }

  public DropdownComponent(WebDriver driver) {
    super(driver);
  }

  public T getSelected(Function<String, T> parseFunction) {
    return parseFunction.apply(getSelectedValue());
  }

  public void select(String value) {
    openDropdown();
    Optional<DropdownOption> optionOptional =
        options.stream().filter(option -> option.getValue().equals(value)).findFirst();
    if (optionOptional.isPresent()) {
      DropdownOption desiredOption = optionOptional.get();
      JavaScript js = new JavaScript(WebDriverPool.get());
      js.scrollToElement(desiredOption);

      desiredOption.click();

    } else {
      throw new NoSuchElementException(
          String.format("Dropdown doesn't have option with value: [%s]", value));
    }
  }

  public void openDropdown() {
    element().click();
  }

  private String getSelectedValue() {
    return element().getAttribute("value");
  }
}
