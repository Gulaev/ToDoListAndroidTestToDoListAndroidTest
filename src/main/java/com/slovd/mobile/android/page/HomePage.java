package com.slovd.mobile.android.page;

import com.zebrunner.carina.utils.mobile.IMobileUtils;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage  implements IMobileUtils {

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/iv_task_add")
  private ExtendedWebElement addNewTaskButton;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_input")
  private ExtendedWebElement inputNewTaskName;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_create_btn")
  private ExtendedWebElement applyNewTaskButton;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/category_text")
  private List<ExtendedWebElement> categoriesChoiceButtons;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/tag_management")
  private ExtendedWebElement openMenuButton;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/popup_tv")
  private List<ExtendedWebElement> menuOptions;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/task_contains_bg1")
  private List<ExtendedWebElement> tasks;

  @FindBy(xpath = "//android.widget.LinearLayout[@resource-id]//android.widget.TextView[@text=\"Delete\"]")
  private ExtendedWebElement deleteTask;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/dialog_confirm")
  private ExtendedWebElement acceptDeleteButton;

  @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView")
  private ExtendedWebElement keyboardComponent;

  @FindBy(id = "todolist.scheduleplanner.dailyplanner.todo.reminders:id/all_completed")
  private ExtendedWebElement allCompletedTaskButton;


  public HomePage(WebDriver driver) {
    super(driver);
    setUiLoadedMarker(addNewTaskButton);
  }

  public CompletedTaskPage openCompletedTaskPage() {
    allCompletedTaskButton.click();
    return new CompletedTaskPage(getDriver());
  }

  public void hideKeyboard() {
    swipeInContainer(keyboardComponent, Direction.DOWN, 100);
  }

  public int getNumberOfTasks() {
    return tasks.size();
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

  public void deleteRandomTask() {
    int randomIndex = (int) Math.floor(Math.random() * tasks.size());
    ExtendedWebElement randomTask = tasks.get(randomIndex);
    swipeInContainer(randomTask,Direction.LEFT, 100);
    deleteTask.click();
    waitUntil(ExpectedConditions.elementToBeClickable(acceptDeleteButton), 10);
    acceptDeleteButton.click();
  }

  public ManageCategoriesPage clickToMenuButtonAndChoiceOption(String option) {
    openMenuButton.click();
    waitUntil(value -> !menuOptions.isEmpty(), 100);
    menuOptions.stream()
        .filter(o -> o.getText().equals(option))
        .findFirst()
        .ifPresent(ExtendedWebElement::click);
    return new ManageCategoriesPage(getDriver());
  }

}
