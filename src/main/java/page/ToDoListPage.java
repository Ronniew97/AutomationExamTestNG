package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.Test;

import util.BasePage;

public class ToDoListPage extends BasePage {

	WebDriver driver;

	public ToDoListPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(), 'Junit')]")
	WebElement JUNIT_CATEGORY;
	@FindBy(how = How.LINK_TEXT, using = "Yes")
	WebElement YES_BUTTON;
	@FindBy(how = How.NAME, using = "categorydata")
	WebElement CATEGORY_TEXTBOX;
	@FindBy(how = How.NAME, using = "colour")
	WebElement COLOR_DROPDOWN;
	@FindBy(how = How.XPATH, using = "//input[@value='Add category']")
	WebElement ADD_CATEGORY;
	@FindBy(how = How.NAME, using = "due_month")
	WebElement MONTH_DROPDOWN;
	@FindBy(how = How.XPATH, using = "//body[contains(text(), 'The category you want to add already exists: ')]")
	WebElement EXISTING_CATEGORY;
	@FindBy(how = How.XPATH, using = "//option[contains(text(), 'Jan')]")
	WebElement VALIDATE_MONTHS;

	public void removeJunitCategory() {
		JUNIT_CATEGORY.click();
		YES_BUTTON.click();
	}

	public void enterCategory(String category) {
		CATEGORY_TEXTBOX.sendKeys(category);
	}

	public void selectColor(String color) {
		dropDown(COLOR_DROPDOWN, color);
	}

	public void clickAddCategory() {
		ADD_CATEGORY.click();
	}

	public void validateTheCategoryCanNotBeDuplicated() {
		System.out.println("@Test(priority=2) status: ");
		System.out.println(EXISTING_CATEGORY.getText());
	}

	public void validateMonthsAreCorrect() {
		String before_xpath = "//*[@id=\"extra\"]/select[3]/option[";
		String after_xpath = "]";
		
		//Array list to save the months of a year 
		List<String> monthsOfAYear = new ArrayList<String>();
		
		monthsOfAYear.add("Jan");
		monthsOfAYear.add("Feb");
		monthsOfAYear.add("Mar");
		monthsOfAYear.add("Apr");
		monthsOfAYear.add("May");
		monthsOfAYear.add("Jun");
		monthsOfAYear.add("Jul");
		monthsOfAYear.add("Aug");
		monthsOfAYear.add("Sep");
		monthsOfAYear.add("Oct");
		monthsOfAYear.add("Nov");
		monthsOfAYear.add("Dec");
		
		//Array list to save the actual months from the dropDown list
		List<String> actualListOfMonths = new ArrayList<String>();
		
		for (int i = 2; i <= 13; i++) {
			String month = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			actualListOfMonths.add(month);
		}
		
		//compare the actual months to the expected list of months
		if(actualListOfMonths.equals(monthsOfAYear)) {
			System.out.println("@Test(priority=3) status: ");
			System.out.println("All the months of a year are displayed");
		}
		else {
			System.out.println("@Test(priority=3) status: ");
			System.out.println("All the months of a year are not displayed");
		}
	}
}
