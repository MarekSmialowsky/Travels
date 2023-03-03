import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HomeWork {

    @Test
    public void sigbUpWithoutParams() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        // stream lepszym rozwiazaniem jezeli do wyboru 2 obiekty i tylko jeden widoczny
        //driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).get(1).click();
        driver.findElement(By.xpath("//button[@class ='signupbtn btn_full btn btn-action btn-block btn-lg']")).click();

        List<String> validationAlerts = driver.findElements(By.xpath("//div[(@class = 'alert alert-danger')]//p"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        System.out.println(validationAlerts.size());
        validationAlerts.forEach(System.out::println);

        Assert.assertEquals(validationAlerts.get(0),"The Email field is required.");
        Assert.assertEquals(validationAlerts.get(1),"The Password field is required.");
        Assert.assertEquals(validationAlerts.get(2),"The Password field is required.");
        Assert.assertEquals(validationAlerts.get(3),"The First name field is required.");
        Assert.assertEquals(validationAlerts.get(4),"The Last Name field is required.");



    }

    @Test
    public void sigbUpWithInvalidEmail() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        // stream lepszym rozwiazaniem jezeli do wyboru 2 obiekty i tylko jeden widoczny
        //driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).get(1).click();

        String  lastName = "Van Basten";
        /*int randomNumber = (int) (Math.random()* 1000);
        String email = "marco" + randomNumber +  "@vanbasten.com";*/
        driver.findElement(By.name("firstname")).sendKeys("Marco");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("444555666");
        driver.findElement(By.name("email")).sendKeys("vanbaten.pl");
        driver.findElement(By.name("password")).sendKeys("QWEasd!23");
        driver.findElement(By.name("confirmpassword")).sendKeys("QWEasd!23");
        driver.findElement(By.xpath("//button[@class ='signupbtn btn_full btn btn-action btn-block btn-lg']")).click();

        WebElement alertText = driver.findElement(By.xpath("//div[@class='alert alert-danger']//p"));
        System.out.println(alertText.getText());

        Assert.assertTrue(alertText.isDisplayed());
        Assert.assertEquals(alertText.getText(), "The Email field must contain a valid email address.");


    }

}
