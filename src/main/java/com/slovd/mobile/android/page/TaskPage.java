package com.slovd.mobile.android.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends AbstractPage {

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_detail_more")
  private ExtendedWebElement openMenuButton;

  @FindBy(xpath = "//android.widget.TextView[@resource-id]")
  private List<ExtendedWebElement> menuOptions;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/toolbar_back")
  private ExtendedWebElement backToHomePage;

  public TaskPage(WebDriver driver) {
    super(driver);
    setUiLoadedMarker(openMenuButton);
  }

  public void clickToMenuButtonAndChoiceOption(String option) {
    openMenuButton.click();
    menuOptions.forEach(o-> {
      if(o.getText().equals(option))
        o.click();
    });
  }

  public HomePage backToHomePage() {
    backToHomePage.click();
    return new HomePage(getDriver());
  }
}
