import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageObjects.MainPage;
import pageObjects.OrderPage;
import pageObjects.RentPage;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SamokatOrderingTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String stateMetro;
    private final String telephoneNumber;
    private final String date;
    private final String comment;

    public SamokatOrderingTest(String name, String surname, String address, String stateMetro, String telephoneNumber,
                        String date, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stateMetro = stateMetro;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {"Костя", "Фамилия", "Адрес", "Лубянка", "79991111111", "28.05.2023", "comments one"},
                {"Маша", "Фамилия", "Адрес", "Лубянка", "79992222222", "28.05.2023", "comments two"},
                {"Дима", "Фамилия", "Адрес", "Лубянка", "79993333333", "28.05.2023", "comments three"},
                {"Кирилл", "Фамилия", "Адрес", "Лубянка", "79991111111", "28.05.2023", "comments one"},
                {"Анна", "Фамилия", "Адрес", "Лубянка", "79992222222", "28.05.2023", "comments two"},
                {"Юля", "Фамилия", "Адрес", "Лубянка", "79993333333", "28.05.2023", "comments three"},
        };
    }
    // Тест заказа через кнопку в хэдере
    @Test
    public void samokatOrderingByHeaderOrderButton() {
        new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .clickHeaderOrderButton();

        new OrderPage(driver)
                .sendClientFirstName(name)
                .sendClientLastName(surname)
                .sendDeliveryAddress(address)
                .selectMetroStation(stateMetro)
                .sendDeliveryClientPhoneNumber(telephoneNumber)
                .clickNextButton();

        boolean isDisplayed = new RentPage(driver)
                .sendRentalDate(date)
                .setRentalTime()
                .clickCheckBoxColourGreyDespair()
                .sendComment(comment)
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();
        assertTrue("Окно заказа отсутствует: ", isDisplayed);
    }
// Тест заказа через кнопку в середине страницы
    @Test
    public void samokatOrderingByMiddleOrderButton() {
        new MainPage(driver)
                .openSite()
                .clickCookieButton()
                .scrollToDownOrderButton()
                .clickMiddleOrderButton();

        new OrderPage(driver)
                .sendClientFirstName(name)
                .sendClientLastName(surname)
                .sendDeliveryAddress(address)
                .selectMetroStation(stateMetro)
                .sendDeliveryClientPhoneNumber(telephoneNumber)
                .clickNextButton();

        boolean isDisplayed = new RentPage(driver)
                .sendRentalDate(date)
                .setRentalTime()
                .clickCheckBoxColourGreyDespair()
                .sendComment(comment)
                .clickOrderButton()
                .clickOrderButtonYes()
                .isModalOrderWindowDisplayed();
        assertTrue("Окно заказа отсутствует: ", isDisplayed);
    }


}
