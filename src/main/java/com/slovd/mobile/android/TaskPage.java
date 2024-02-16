package com.slovd.mobile.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TaskPage extends AbstractPage {

  @FindBy(xpath = "//*[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_detail_more\"]")
  private ExtendedWebElement openMenuButton;

  @FindBy(xpath = "//android.widget.TextView[@resource-id]")
  private List<ExtendedWebElement> menuOptions;


  public TaskPage(WebDriver driver) {
    super(driver);
  }
}
