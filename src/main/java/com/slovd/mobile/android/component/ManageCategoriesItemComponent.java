package com.slovd.mobile.android.component;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ManageCategoriesItemComponent extends AbstractPage {

  @FindBy(xpath = "(//android.widget.ImageView[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/more\"])")
  private ExtendedWebElement categoryOpenMenuButton;



  public ManageCategoriesItemComponent(WebDriver driver) {
    super(driver);
  }
}
