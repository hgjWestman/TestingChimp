package se.hgj.chimptest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class MyStepDefs {


    private WebDriver driver;
    private HourGlass hourGlass;

    // ange webbadress


    @Before
    public void setUp() {
        // Ställ in så den använder rätt http klient för att komma ut på nätet
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @Given("I open {string} and navigates to the website")
    public void iOpenAndNavigatesToTheWebsite(String browser) {
        // ange webbadress
        String URL = "https://login.mailchimp.com/signup/";
        // Välj webbläsare från exemplen i scenariot
        if (browser.equalsIgnoreCase("firefox")) {
            System.out.println(" Executing on FireFox");
            System.setProperty("webdriver.chrome.driver", "c:\\webdrivers\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {

            System.out.println("Executing on CHROME");
            System.setProperty("webdriver.chrome.driver", "c:\\webdrivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("edge")) {
            System.out.println("Executing on Edge");
            System.setProperty("webdriver.edge.driver", "c:\\webdrivers\\msedgedriver.exe");
            driver = new EdgeDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } else {
            throw new IllegalArgumentException("The Browser Type is Undefined");
        }
                hourGlass = new HourGlass();
                HourGlass.setDriver(driver);

    }

    @And("I input {string} as my email address")
    public void IInputAsMyEmailAddress(String email) {
        // hitta och ange epost
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"marketing_newsletter\"]")));
        WebElement mailBox = driver.findElement(By.id("email"));
        if (!Objects.equals(TestHelper.uniqify(email), "-")) mailBox.sendKeys(TestHelper.uniqify(email));
    }

    @And("then choose {string} as my username")
    public void thenChooseAsMyUsername(String username) {
        // hitta och ange användarnamn
        WebElement userBox = driver.findElement(By.id("new_username"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_username\"]")));
        // Fuling för att få den att skiva in mejladressen som användarnamn och sedan kunna rensa det
        userBox.sendKeys(" ");
        userBox.clear();
        if (!Objects.equals(TestHelper.uniqify(username), "-")) userBox.sendKeys(TestHelper.uniqify(username));
     //   userBox.clear();
    }

    @And("input {string} as my password.")
    public void inputAsMyPassword(String password) {
        // hitta och ange lösenord
        WebElement userBox = driver.findElement(By.id("new_password"));
        userBox.clear();
        if (!Objects.equals(TestHelper.uniqify(password), "-")) userBox.sendKeys(TestHelper.uniqify(password));


    }

    @When("I am pressing Sign up I cross my fingers")
    public void iAmPressingSignUpICrossMyFingers() {
        // Lite olika saker för att försöka få den att ta Submit riktigt
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("create-account-enabled")), "Sign up"));
        // Klicka på submit...jag vet att det är tårta på tårta, men tyckte att den inte alltid reagerade
        driver.findElement(By.id("create-account-enabled")).click();
        driver.findElement(By.id("create-account-enabled")).submit();

    }

    @Then("hope its a success or at least show the correct {string}.")
    public void hopeItsASuccessOrAtLeastShowTheCorrect(String error) {
    // Vänta till det antingen är en lyckad registrering eller felmeddelanden
    hourGlass.posted();

    // utgå från att det gått åt fanders
    boolean success = false;
    // Hämta aktuell URL
    String current_url = driver.getCurrentUrl();

    if (current_url.contains("/success")) {
        // Har registreringen lyckats, märk den som lyckad
        success = true;
        }
    else {
        // Om den inte var lyckad, skriv ut de felmeddelanden som sidan returnerar
        List<WebElement> elements = driver.findElements(By.cssSelector(".invalid-error"));
        for (WebElement element : elements) {
            System.out.println("Error:" + element.getText());

            if (error.equals(element.getText())) success = true;

        }
    }

    // Klara testet om "sant"
    assertTrue(success);
    }
    @After
    public void tearDown() {
        // Stäng webbläsaren
        driver.quit();
    }



}
