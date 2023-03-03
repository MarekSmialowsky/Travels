import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;




public class SignUpTest extends BaseTest{


    @Test
    public void signUpTest() {
        driver.findElements(By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        // stream lepszym rozwiazaniem jezeli do wyboru 2 obiekty i tylko jeden widoczny
        //driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).stream().filter(WebElement::isDisplayed).findFirst().ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()= '  Sign Up' ]")).get(1).click();

        String  lastName = "Van Basten";
        int randomNumber = (int) (Math.random()* 1000);
        String email = "marco" + randomNumber +  "@vanbasten.com";
        driver.findElement(By.name("firstname")).sendKeys("Marco");
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("444555666");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("QWEasd!23");
        driver.findElement(By.name("confirmpassword")).sendKeys("QWEasd!23");
        driver.findElement(By.xpath("//button[@class ='signupbtn btn_full btn btn-action btn-block btn-lg']")).click();
        WebElement heading = driver.findElement(By.xpath("//h3[@class = 'RTL']"));
        System.out.println(heading.getText());

        Assert.assertTrue(heading.getText().contains(lastName));
        Assert.assertEquals(heading.getText(), "Hi, Marco Van Basten");
    }
}
