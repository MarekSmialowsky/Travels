import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;
import pl.seleniumdemo.pages.HotelSearchPage;




public class HotelSearchTest extends BaseTest {

    @Test
    public void serachHotelTest() {

        HotelSearchPage hotelSearchPage= new HotelSearchPage(driver);

        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("10/03/2023", "15/10/2023");
        hotelSearchPage.setTravelers();
        hotelSearchPage.performSearch();

        List<String> hotelNames = driver.findElements(By.xpath("//h4[contains(@class,'list_title')]//b"))
                .stream()
                .map(el -> el.getAttribute("textContent"))
                .collect(Collectors.toList());

        System.out.println(hotelNames.size());

        hotelNames.forEach(el -> System.out.println(el));
        // obie metody robią to samo
        //hotelNames.forEach(System.out::println);

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2));
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3));

    }
}
