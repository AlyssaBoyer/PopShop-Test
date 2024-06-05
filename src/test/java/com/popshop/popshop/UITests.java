package com.popshop.popshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UITests {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeEach
	public void setUp() {
		String geckoDriverPath = System.getenv("GECKODRIVER_PATH");
		if (geckoDriverPath == null || geckoDriverPath.isEmpty()) {
			throw new RuntimeException("GECKODRIVER_PATH environment variable not set");
		}
		System.setProperty("webdriver.gecko.driver", geckoDriverPath);

		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--headless"); // Run in headless mode
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		driver = new FirefoxDriver(options);
		driver.get("http://localhost:8080");
		Duration duration = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, duration);
	}

	@Test
	public void testHomePageTitle() {
		assertEquals("Pop Shop", driver.getTitle());
	}

	@Test
	public void testAddFigure() {
		driver.get("http://localhost:8080/add");

		WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
		WebElement themeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("theme")));
		WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")));

		nameInput.sendKeys("Hulk");
		themeInput.sendKeys("Marvel");
		submitButton.click();

		// Attendre un peu pour permettre la redirection
		try {
			Thread.sleep(2000);  // Attendre 2 secondes
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Vérifier que nous sommes bien redirigés vers la page d'accueil
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.equals("http://localhost:8080") || currentUrl.equals("http://localhost:8080/"));

		// Attendre que la nouvelle figure apparaisse dans la liste des figures
		List<WebElement> figureNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card']/h3")));
		List<WebElement> figureThemes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card']/p")));

		List<String> names = figureNames.stream().map(WebElement::getText).toList();
		List<String> themes = figureThemes.stream().map(WebElement::getText).toList();

		assertTrue(names.contains("Hulk"));
		assertTrue(themes.contains("Marvel"));
	}

	@AfterEach
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}