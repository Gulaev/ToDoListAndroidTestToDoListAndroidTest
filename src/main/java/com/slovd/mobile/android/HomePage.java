package com.slovd.mobile.android;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {


  @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add\"]")
  private ExtendedWebElement addNewTaskButton;

  @FindBy(xpath = "//android.widget.EditText[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_input\"]")
  private ExtendedWebElement inputNewTaskName;

  @FindBy(xpath = "//android.widget.ImageView[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_btn\"]")
  private ExtendedWebElement applyNewTaskButton;

  @FindBy(xpath = "//android.widget.TextView[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/category_text\"]")
  private List<ExtendedWebElement> categoriesChoiceButtons;

  @FindBy(xpath = "//*[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/tag_management\"]")
  private ExtendedWebElement openMenuButton;

  @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout")
  private List<ExtendedWebElement> menuOption;

  @FindBy(xpath = "(//android.view.View[@resource-id=\"todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_contains_bg1\"])")
  private List<ExtendedWebElement> tasks;


  public HomePage(WebDriver driver) {
    super(driver);
  }

  public TaskPage clickOnRandomTask() {
    int randomIndex = (int) Math.floor(Math.random() * tasks.size());
    tasks.get(randomIndex).click();
    return new TaskPage(getDriver());
  }

  public void addNewTask(String taskName) {
    addNewTaskButton.click();
    inputNewTaskName.type(taskName);
    applyNewTaskButton.click();
  }

  public void openSelectedCategory(String categoryName) {
    for (ExtendedWebElement button : categoriesChoiceButtons) {
      String buttonText = button.getText();
      if (buttonText.equals(categoryName)) {
        button.click();
        return;
      }
    }
    throw new IllegalArgumentException("Category button not found: " + categoryName);
  }


}
