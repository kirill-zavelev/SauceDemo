import com.saucedemo.page.LoginPage;
import com.saucedemo.page.ProductsPage;
import org.assertj.core.api.Assertions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void checkSuccessfulLogin() {
        ProductsPage productsPage = new LoginPage(getDriver())
                .open()
                .loginAsStandardUser();
        Assertions.assertThat(productsPage.isLogoutButtonDisplayed())
                .as("Logout btn should be displayed after successful login")
                .isTrue();
    }

    @Test(dataProvider = "unsuccessfulLoginDataProvider")
    public void checkUnsuccessfulLogin(String login, String pass, String expectedMessage) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginAs(login, pass);
        Assertions.assertThat(loginPage.getUnsuccessfulLoginMessage())
                .as("Message: " + expectedMessage + " should be displayed")
                .isEqualTo(expectedMessage);
    }

    @DataProvider(name = "unsuccessfulLoginDataProvider")
    public Object[][] unsuccessfulLoginDataProvider() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"test", "123", "Epic sadface: Username and password do not match any user in this service"},
                {"", "", "Epic sadface: Username is required"},
                {"locked_out_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
        };
    }
}
