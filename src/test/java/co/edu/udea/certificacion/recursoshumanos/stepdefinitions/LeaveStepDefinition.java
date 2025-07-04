package co.edu.udea.certificacion.recursoshumanos.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

public class LeaveStepDefinition {

    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("que estoy en la página de login de OrangeHRM")
    public void queEstoyEnLaPaginaDeLoginDeOrangeHRM() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("ingreso el usuario {string} y la contraseña {string}")
    public void ingresoElUsuarioYLaContrasena(String username, String password) {
        driver.findElement(By.name("Admin")).sendKeys(username);
        driver.findElement(By.name("admin123")).sendKeys(password);
    }

    @When("hago clic en el botón {string}")
    public void hagoClicEnElBoton(String buttonText) {
        driver.findElement(By.xpath("//button[contains(text(), '" + buttonText + "')]")).click();
    }

    @Then("debo estar autenticado y redirigido a la página principal")
    public void deboEstarAutenticadoYRedirigidoALaPaginaPrincipal() {
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }

    @Given("que estoy en la página de {string}")
    public void queEstoyEnLaPaginaDe(String pageName) {
        if (pageName.equals("Leave List")) {
            driver.findElement(By.xpath("//span[text()='Leave List']")).click();
        } else if (pageName.equals("Apply Leave")) {
            driver.findElement(By.xpath("//span[text()='Apply']")).click();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @When("la página se carga completamente")
    public void laPaginaSeCargaCompletamente() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @Then("debo ver los filtros {string}, {string}, {string}, {string}, {string}, {string}, e {string}")
    public void deboVerLosFiltros(String filter1, String filter2, String filter3, String filter4, String filter5, String filter6, String filter7) {
        String[] filters = {filter1, filter2, filter3, filter4, filter5, filter6, filter7};
        for (String filter : filters) {
            Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(), '" + filter + "')]")).isDisplayed());
        }
    }

    @Then("debo ver las columnas de la tabla {string}, {string}, {string}, {string}, {string}, {string}, {string}, y {string}")
    public void deboVerLasColumnasDeLaTabla(String col1, String col2, String col3, String col4, String col5, String col6, String col7, String col8) {
        WebElement table = driver.findElement(By.className("oxd-table"));
        String[] columns = {col1, col2, col3, col4, col5, col6, col7, col8};
        for (String column : columns) {
            Assert.assertTrue(table.findElement(By.xpath(".//th[contains(text(), '" + column + "')]")).isDisplayed());
        }
    }

    @Then("si no hay registros, debo ver el mensaje {string}")
    public void siNoHayRegistrosDeboVerElMensaje(String message) {
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), '" + message + "')]")).isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
