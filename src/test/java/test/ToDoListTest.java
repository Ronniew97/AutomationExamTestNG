package test;

import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import page.ToDoListPage;
import util.BrowserFactory;

public class ToDoListTest {

	WebDriver driver;
	ToDoListPage todolist;
	BrowserFactory browser;

	@Test(priority = 1)
	public void userShouldBeAbleToAddACategory() {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.removeJunitCategory();
		todolist.enterCategory("Junit");
		todolist.selectColor("Burnt Orange");
		todolist.clickAddCategory();
	}

	@Test(priority = 2)
	public void userShouldntBeAbleToAddADuplicateCategory() {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.enterCategory("Junit");
		todolist.selectColor("Burnt Orange");
		todolist.clickAddCategory();
		todolist.validateTheCategoryCanNotBeDuplicated();
	}

	@Test(priority = 3)
	public void allMonthsInAYearShouldBeDisplayedInDropDownBox() {
		driver = BrowserFactory.init();
		todolist = PageFactory.initElements(driver, ToDoListPage.class);
		todolist.validateMonthsAreCorrect();
	}

	@AfterMethod
	public void closeBrowser() {
		browser = new BrowserFactory();
		try {
		browser.closeBrowser();
		} catch(NoSuchSessionException n) {
			System.out.println();
		}
	}
	
}
