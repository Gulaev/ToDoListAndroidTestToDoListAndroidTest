package com.slovd.mobile;

import com.slovd.mobile.android.HomePage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.mobile.IMobileUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MobileTesting extends AbstractTest implements IMobileUtils {

  @BeforeTest
  public void startAppTodoList() {
    startApp("todolist.scheduleplanner.dailyplanner.todo.reminders");
  }

  @Test
  public void verifyHomeScreenTest() {
    HomePage homePage = new HomePage(getDriver());
    homePage.openSelectedCategory("Work");
    homePage.addNewTask("New task");
  }



}
