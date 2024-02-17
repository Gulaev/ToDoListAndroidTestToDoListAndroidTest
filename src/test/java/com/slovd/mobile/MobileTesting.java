package com.slovd.mobile;

import com.slovd.mobile.android.page.CompletedTaskPage;
import com.slovd.mobile.android.page.HomePage;
import com.slovd.mobile.android.page.ManageCategoriesPage;
import com.slovd.mobile.android.page.TaskPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MobileTesting extends AbstractTest implements IMobileUtils {

  @BeforeMethod
  public void startAppTodoList() {
    startApp("todolist.scheduleplanner.dailyplanner.todo.reminders");
  }

  @Test(testName = "Test Case 1: Add New Task")
  public void verifyAddNewTaskTest() {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage = new HomePage(getDriver());
    Assert.assertTrue(homePage.isPageOpened(), "Home page should be open but it's not.");
    int numberOfTaskBeforeTest = homePage.getNumberOfTasks();
    homePage.addNewTask("New task");
    homePage.hideKeyboard();
    int currentNumberOfTask = homePage.getNumberOfTasks();
    softAssert.assertEquals(currentNumberOfTask, numberOfTaskBeforeTest + 1,
        "Expected number of tasks to increase by 1, but it didn't.");
    softAssert.assertAll();
  }

  @Test(testName = "Test Case 2: Add new To-Do to Different Category")
  public void verifyAddNewTaskToDifferentCategoryTest() {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage = new HomePage(getDriver());
    Assert.assertTrue(homePage.isPageOpened(), "Home page should be open but it's not.");
    homePage.openSelectedCategory("Work");
    int numberOfTasksBefore = homePage.getNumberOfTasks();
    homePage.addNewTask("New task");
    homePage.hideKeyboard();
    softAssert.assertEquals(numberOfTasksBefore +1, homePage.getNumberOfTasks(),
            "Expected number of tasks in 'Work' category to increase by 1, but it didn't.");
    softAssert.assertAll();
  }

  @Test(testName = "Test Case 3: Add new Category")
  public void verifyAddNewCategoryTest() {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage = new HomePage(getDriver());
    ManageCategoriesPage manageCategoriesPage =
        homePage.clickToMenuButtonAndChoiceOption("Manage Categories");
    int numberOfCategoryBefore = manageCategoriesPage.numberOfCategories();
     manageCategoriesPage = manageCategoriesPage.createNewCategory("Test Category");
    softAssert.assertEquals(numberOfCategoryBefore +1, manageCategoriesPage.numberOfCategories(),
        "Expected number of categories to increase by 1 after adding 'Test Category', but it didn't.");
    homePage = manageCategoriesPage.backToHomePage();
    Assert.assertTrue(homePage.isPageOpened());
    softAssert.assertAll();
  }


  @Test(testName = "Test Case 5: Done Task")
  public void verifyDoneTask() {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage = new HomePage(getDriver());
    CompletedTaskPage completedTaskPage = homePage.openCompletedTaskPage();
    Assert.assertTrue(completedTaskPage.isPageOpened(), "Completed page should be open but it's not.");
    int numberOfCompletedTaskBefore = completedTaskPage.numberOfTask();
    homePage = completedTaskPage.backToHomePage();
    Assert.assertTrue(homePage.isPageOpened(), "Home page should be open but it's not.");
    TaskPage taskPage = homePage.clickOnRandomTask();
    taskPage.clickToMenuButtonAndChoiceOption("Mark as Done");
    homePage = taskPage.backToHomePage();
    Assert.assertTrue(homePage.isPageOpened(), "Home page should be open but it's not.");
    completedTaskPage = homePage.openCompletedTaskPage();
    int currentNumberCompletedTask = completedTaskPage.numberOfTask();
    softAssert.assertEquals(numberOfCompletedTaskBefore +1, currentNumberCompletedTask,
        "Expected number of completed tasks to increase by 1 after marking a task as done, but it didn't.");
    homePage = completedTaskPage.backToHomePage();
    Assert.assertTrue(homePage.isPageOpened(), "Home page should be open but it's not.");
    softAssert.assertAll();

  }


  @Test(testName = "Test Case 4: Delete Task")
  public void verifyDeleteTaskTest() {
    SoftAssert softAssert = new SoftAssert();
    HomePage homePage = new HomePage(getDriver());
    int numberOfTasksBefore = homePage.getNumberOfTasks();
    homePage.deleteRandomTask();
    Assert.assertTrue(homePage.isPageOpened(),
        "Expected number of categories to increase by 1 after adding 'Test Category', but it didn't.");
    softAssert.assertEquals(numberOfTasksBefore -1, homePage.getNumberOfTasks(),
        "Expected number of tasks to decrease by 1 after deleting a task, but it didn't.");
    softAssert.assertAll();
  }

  @AfterMethod
  public void closeTheApplication() {
    getDevice().quitDriver();
  }
}
