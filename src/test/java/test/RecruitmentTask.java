package test;

import static org.junit.Assert.assertEquals;

import com.github.metalloid.webdriver.WebDriverPool;
import com.github.metalloid.webdriver.utils.Inject;
import com.github.metalloid.webdriver.utils.UtilsFactory;
import com.github.metalloid.webdriver.utils.Wait;
import components.SizeDropdownComponent;
import org.junit.Assert;
import org.junit.Test;
import pageobjects.CartView;
import pageobjects.HomePage;
import pageobjects.ProductView;
import utils.MathUtils;

public class RecruitmentTask {
  private static final Double US_SHIPPING_COST = 14.29d;

  @Inject private Wait wait;

  public RecruitmentTask() {
    UtilsFactory.initUtilities(WebDriverPool.get(), this);
  }

  @Test
  public void test() {
    HomePage homePage = new HomePage().open();
    Assert.assertTrue(homePage.isOpened());

    ProductView product =
        homePage
            .bestselling()
            .selectByImageDescription("The ultimate prince warrior ")
            .openDetails()
            .selectFinishAndFrame(ProductView.Finish.Gloss, ProductView.Frame.BLACK_WOOD);

    String displateUrl = product.getDisplateUrl();
    Double price = product.getPrice();

    CartView cart = product.addToCart().proceedToCart();

    assertEquals(displateUrl, cart.getDisplateUrl());
    assertEquals(price, cart.getPrice());
    assertEquals(price, cart.getCartTotal());
    assertEquals(1, cart.getQuantity());
    assertEquals(SizeDropdownComponent.Size.M, cart.getSize());
    assertEquals(ProductView.Finish.Gloss, cart.getFinish());
    assertEquals(ProductView.Frame.BLACK_WOOD, cart.getFrame());

    cart.shipTo(CartView.ShippingOptions.US);
    price = cart.getPrice();
    assertEquals(US_SHIPPING_COST, cart.getShippingCost());
    assertEquals(cart.getOrderTotalCost(), MathUtils.round(price + cart.getShippingCost(), 2));

    cart.enterDiscountCode("APR15");
    assertEquals((Integer) 15, cart.getAppliedDiscountAmount());

    Double expectedPriceAfterDiscount = price * ((100.0d - cart.getAppliedDiscountAmount()) / 100);
    assertEquals(expectedPriceAfterDiscount, cart.getCartTotal());
    assertEquals(expectedPriceAfterDiscount, cart.getPrice());
    assertEquals(US_SHIPPING_COST, cart.getShippingCost());
    assertEquals(
        cart.getOrderTotalCost(), MathUtils.round(cart.getCartTotal() + cart.getShippingCost(), 2));
  }
}
