package com.slovd.mobile.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ManageCategoriesPage extends AbstractPage {

  @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id]/android.view.ViewGroup")
  private List<ExtendedWebElement> categories;



  public ManageCategoriesPage(WebDriver driver) {
    super(driver);
  }
}
