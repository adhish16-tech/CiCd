package Framework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import Framework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Page Factory
    @FindBy(css = ".action__submit")
    private WebElement submit;

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-results button:nth-of-type(1)")
    private WebElement selectCountryResult;

    public void selectCountry(String countryName) {
        waitForWebElementToAppear(countryInput);
        countryInput.sendKeys(countryName);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(selectCountryResult)).click();
        System.out.println("🌏 Selecting country: " + countryName);
    }

    public ConfirmationPage submitOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForOverlayToDisappear(); // ✅ Ensure any spinners are gone

        try {
            wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            System.out.println("⚠️ Element intercepted. Retrying click with JS.");
            scrollIntoViewAndClick(submit);
        }

        System.out.println("✅ Submitting order...");
        return new ConfirmationPage(driver);
    }
}
//package Framework.pageobjects;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//
//import Framework.AbstractComponents.AbstractComponent;
//
//public class CheckoutPage  extends AbstractComponent{
//
//	WebDriver driver;
//	public CheckoutPage(WebDriver driver) {
//		super(driver);
//		this.driver=driver;
//		PageFactory.initElements(driver, this);
//		// TODO Auto-generated constructor stub
//	}
//	@FindBy(css="[placeholder='Select Country']")
//	WebElement country;
//	
//	
//	@FindBy(xpath="//a[contains(@class,'action__submit') and contains(text(),'Place Order')]")
//	WebElement submit;
//	
//	
//	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
//	WebElement selectCountry;
//	By results=By.cssSelector(".ta-results");
//
//	public void selectCountry(String countryName) {
//
//		Actions action= new Actions(driver);
//	action.sendKeys(country,countryName).build().perform();
//	waitForElementToAppear(By.cssSelector(".ta-results")); // waits for country dropdown suggestions
//	selectCountry.click();
//	}
//	
//	public ConfirmationPage submitOrder() {
//		submit.click();
//		return new ConfirmationPage(driver);
//	}
//}
//
//	
//	
