import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gmail {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "/Users/dheeru/Downloads/geckodriver");
		File file = new File("/Users/dheeru/Documents/workspace/Selenium_gmail/data.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		WebDriver driver = new FirefoxDriver();

		// Scanner s= new Scanner(System.in);
		driver.get("https://mail.google.com");
		// driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys(s.nextLine());
		driver.findElement(By.xpath(".//*[@id='identifierId']")).sendKeys(prop.getProperty("Email"));
		driver.findElement(By.id("identifierNext")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.xpath(".//*[@id='password']/div[1]/div/div[1]/input"))
				.sendKeys(prop.getProperty("password"));
		driver.findElement(By.id("passwordNext")).click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> email = driver.findElements(By.xpath(prop.getProperty("unread")));

		for (int i = 0; i < email.size(); i++) {
			if (email.get(i).isDisplayed() == true)
				email.get(i).click();
			if (i == 1)
				break;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("First Unread mail is Successfully opened");

		}

	}
}
