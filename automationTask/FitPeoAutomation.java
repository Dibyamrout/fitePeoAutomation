package fitPeo.automationTask;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FitPeoAutomation {

    private static final String DRIVER_PATH = "C:\\Users\\dibya\\Selenium-workspace\\automationTask\\Driver\\chromedriver.exe";
    private static final String BASE_URL = "https://www.fitpeo.com/";
    private static final String EXPECTED_REIMBURSEMENT = "$110700";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        try {
            driver.get(BASE_URL);
            navigateToRevenueCalculator(driver, wait);
            adjustSlider(driver, wait);
            updateTextField(driver);
            validateSliderValue(driver, "560");
            againAdjustSlider(driver, wait);
            selectCPTCodes(driver, wait);
            validateReimbursement(driver, wait);
            System.out.println("All test cases passed successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void navigateToRevenueCalculator(WebDriver driver, WebDriverWait wait) {
        WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/revenue-calculator']")));
        revenueCalculatorLink.click();
        System.out.println("Navigated to Revenue Calculator page.");
    }

    private static void adjustSlider(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement sliderLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Medicare Eligible Patients']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sliderLabel);

        WebElement slider = driver.findElement(By.xpath("//input[@data-index='0']"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(93, 0).release().perform();

        for (int i = 0; i < 3; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        System.out.println("Slider adjusted to 820.");
    }

    private static void againAdjustSlider(WebDriver driver, WebDriverWait wait) throws InterruptedException {
        WebElement sliderLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[text()='Medicare Eligible Patients']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", sliderLabel);

        WebElement slider = driver.findElement(By.xpath("//input[@data-index='0']"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider).moveByOffset(39, 0).release().perform();

        for (int i = 0; i < 3; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
        System.out.println("Slider adjusted again from 560 to 820.");
    }
    private static void updateTextField(WebDriver driver) {
        WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
        textField.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", textField);
        textField.sendKeys("560");
        System.out.println("Updated text field to 560.");
    }

    private static void validateSliderValue(WebDriver driver, String expectedValue) {
        WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
        String actualValue = textField.getAttribute("value");
        if (!actualValue.equals(expectedValue)) {
            throw new AssertionError("Slider value is incorrect! Expected: " + expectedValue + ", Actual: " + actualValue);
        }
        System.out.println("Slider value validated successfully: " + actualValue);
    }

    private static void selectCPTCodes(WebDriver driver, WebDriverWait wait) {
        String[] cptLabels = {"57", "19.19", "63", "15"};
        for (String cpt : cptLabels) {
            try {
                WebElement cptElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='" + cpt + "']")));
                
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cptElement);

                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");

                try {
                    cptElement.click();
                } catch (ElementClickInterceptedException e) {
                    System.out.println("Click intercepted. Using JavaScript click for: " + cpt);
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cptElement);
                }
                
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        }
        System.out.println("Selected All given CPT checkboxes");
    }


    private static void validateReimbursement(WebDriver driver, WebDriverWait wait) {
        WebElement reimbursementElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-1bl0tdj'])[4]")));
        String actualReimbursement = reimbursementElement.getText();
        if (!actualReimbursement.equals(EXPECTED_REIMBURSEMENT)) {
            throw new AssertionError("Reimbursement value is incorrect! Expected: " + EXPECTED_REIMBURSEMENT +
                    ", Actual: " + actualReimbursement);
        }
        System.out.println("Reimbursement validated successfully: " + actualReimbursement);
    }
}
