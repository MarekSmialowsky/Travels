import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.TimeUnit;


public class HotelSearch {

    @Test
    public void serachHotel() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");
        driver.findElement(By.xpath("//span[text() = 'Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        driver.findElement(By.xpath("//span[@class='select2-match'and text()='Dubai']")).click();
        //Input
        driver.findElement(By.name("checkin")).sendKeys("22/03/2023");
        driver.findElement(By.name("checkout")).sendKeys("25/03/2023");

        //kalendarz
        /*driver.findElement(By.name("checkout")).click();
        driver.findElements(By.xpath("//td[@class='day ' and text()='25'] "))
                .stream()
                .filter(el -> el.isDisplayed())
                .findFirst()
                .ifPresent(el -> el.click());*/
        driver.findElement(By.id("travellersInput")).click();
        //click
        /*driver.findElement(By.id("adultPlusBtn")).click();
        driver.findElement(By.id("childPlusBtn")).click();*/

        //input
        driver.findElement(By.id("adultInput")).clear();
        driver.findElement(By.id("adultInput")).sendKeys("3");
        driver.findElement(By.id("childInput")).clear();
        driver.findElement(By.id("childInput")).sendKeys("1");
        driver.findElement(By.xpath("//button[text()= ' Search']")).click();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        // System.out.println(hotelNames.size());

        //hotelNames.forEach(el -> System.out.println(el));
        // obie metody robią to samo
        //hotelNames.forEach(System.out::println);

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }
}
