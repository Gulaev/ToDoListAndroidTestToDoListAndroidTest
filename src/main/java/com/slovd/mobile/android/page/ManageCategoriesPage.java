package com.slovd.mobile.android.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ManageCategoriesPage extends AbstractPage {

  @FindBy(xpath = "//android.widget.TextView[@text=\"Create New\"]")
  private ExtendedWebElement createNewButton;

  @FindBy(xpath = "//android.widget.EditText[@resource-id]")
  private ExtendedWebElement newCategoryNameInput;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/dialog_confirm")
  private ExtendedWebElement acceptNewCategoryButton;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/toolbar_back")
  private ExtendedWebElement beckToHomePageButton;

  @FindBy(xpath = "//android.view.ViewGroup[contains(@resource-id, 'categorymag_layout')]")
  private List<ExtendedWebElement> categories;


  public ManageCategoriesPage(WebDriver driver) {
    super(driver);
    setUiLoadedMarker(createNewButton);
  }

  public HomePage backToHomePage() {
    beckToHomePageButton.click();
    return new HomePage(getDriver());
  }

  public ManageCategoriesPage createNewCategory(String categoryName) {
    createNewButton.click();
    newCategoryNameInput.type(categoryName);
    acceptNewCategoryButton.click();
    return new ManageCategoriesPage(getDriver());
  }

  public int numberOfCategories() {
    return categories.size();
  }
}
