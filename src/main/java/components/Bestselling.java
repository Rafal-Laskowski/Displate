package components;

import com.github.metalloid.pagefactory.FindBy;
import com.github.metalloid.pagefactory.components.Component;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.JavaScript;
import com.github.metalloid.webdriver.utils.UtilsFactory;
import controls.Button;
import controls.SlickSlide;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

@FindBy(css = "div[class*='homepage-feed'][class*='bestselling']")
public class Bestselling extends Component {

  @FindBy(css = "div.slick-slide")
  private List<SlickSlide> slides;

  @FindBy(css = "div.slick-current")
  private SlickSlide currentSlide;

  @FindBy(css = "button[class='slick-arrow slick-next']")
  private Button nextButton;

  @Inject private JavaScript js;

  public Bestselling(WebDriver driver, By selector) {
    super(driver, selector);
    UtilsFactory.initUtilities(driver, this);
  }

  public Bestselling(WebDriver driver) {
    super(driver);
  }

  public SlickSlide selectByImageDescription(String description) {
    js.scrollToElement(currentSlide);

    Optional<SlickSlide> optionalDesiredSlide = lookForImageDescription(description);
    if (optionalDesiredSlide.isPresent()) {
      return optionalDesiredSlide.get();
    } else {
      int allSlides = slides.size();
      int visibleSlides = (int) slides.stream().filter(SlickSlide::isActive).count();

      for (int i = visibleSlides; i < allSlides; i++) {
        nextButton.click();
        optionalDesiredSlide = lookForImageDescription(description);
        if (optionalDesiredSlide.isPresent()) {
          return optionalDesiredSlide.get();
        }
      }
    }

    throw new NoSuchElementException(
        String.format(
            "Cannot find slide with image description: [%s] in Bestselling category!",
            description));
  }

  private Optional<SlickSlide> lookForImageDescription(String description) {
    return slides
        .stream()
        .filter(SlickSlide::isActive)
        .filter(slide -> slide.getImageDescription().get().equals(description))
        .findAny();
  }
}
