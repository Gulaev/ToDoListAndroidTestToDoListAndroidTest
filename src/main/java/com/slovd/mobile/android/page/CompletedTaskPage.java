package com.slovd.mobile.android.page;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CompletedTaskPage extends AbstractPage {

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_contains")
  private List<ExtendedWebElement> tasks;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/toolbar_back")
  private ExtendedWebElement beckToHomePage;

  public CompletedTaskPage(WebDriver driver) {
    super(driver);
    setUiLoadedMarker(beckToHomePage);
  }

  public int numberOfTask() {
    return tasks.size();
  }

  public HomePage backToHomePage() {
    beckToHomePage.click();
    return new HomePage(getDriver());
  }
}
