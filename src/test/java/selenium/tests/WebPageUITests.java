package selenium.tests;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.testservice.TestServiceUrl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Epic("Web page UI Tests Epic")
@Feature("User can interact with elements on the Web page")
public class WebPageUITests {

    static TestServiceUrl testServiceUrl;

    WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        testServiceUrl = new TestServiceUrl();
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @Story("Fill in all required fields")
    @Description("Fill in all required fields, and submit the form.")
    void fillInRequiredFields() {
        driver.get(testServiceUrl.url1 + "automation-practice-form ");
        Actions actions = new Actions(driver);
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Gandalf");
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("The Grey");
        WebElement userEmailInput = driver.findElement(By.id("userEmail"));
        userEmailInput.sendKeys("palantir@example.com");
        WebElement selectGenderRadioButton = driver.findElement(By.id("gender-radio-3"));
        actions.click(selectGenderRadioButton).build().perform();
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.submit();

        WebElement submitFormTable = driver.findElement(By.id("example-modal-sizes-title-lg"));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfAllElements(submitFormTable));
        assertThat(submitFormTable.getText()).contains("Thanks for submitting the form");

        WebElement studentNameTable = driver.findElement(By.xpath("//td[text()='Student Name']//following-sibling::*"));
        assertThat(studentNameTable.getText()).contains("Gandalf The Grey");
        WebElement studentEmailTable = driver.findElement(By.xpath("//td[text()='Student Email']//following-sibling::*"));
        assertThat(studentEmailTable.getText()).contains("palantir@example.com");
        WebElement studentGenderTable = driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::*"));
        assertThat(studentGenderTable.getText()).contains("Other");
    }

    @Test
    @Story("Fill in required and non-required fields")
    @Description("Fill in required and non-required fields, and submit the form.")
    void fillInVariousFields() {
        driver.get(testServiceUrl.url1 + "automation-practice-form ");
        Actions actions = new Actions(driver);
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Gandalf");
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("The Grey");
        WebElement userNumberInput = driver.findElement(By.id("userNumber"));
        userNumberInput.sendKeys("0000000000");
        WebElement userEmailInput = driver.findElement(By.id("userEmail"));
        userEmailInput.sendKeys("palantir@example.com");
        WebElement selectGenderRadioButton = driver.findElement(By.id("gender-radio-1"));
        actions.click(selectGenderRadioButton).build().perform();
        WebElement selectHobbiesCheckbox = driver.findElement(By.id("hobbies-checkbox-3"));
        actions.moveToElement(selectHobbiesCheckbox).click().build().perform();
        WebElement subjectsDropdown = driver.findElement(By.id("subjectsInput"));
        actions.moveToElement(subjectsDropdown).click().sendKeys("Civics").build().perform();
        WebElement subjectsOptionDropdown = driver.findElement(By.id("react-select-2-option-0"));
        subjectsOptionDropdown.click();
        WebElement stateDropdown = driver.findElement(By.id("react-select-3-input"));
        actions.moveToElement(stateDropdown).click().sendKeys("NCR").build().perform();
        WebElement stateOptionDropdown = driver.findElement(By.id("react-select-3-option-0"));
        stateOptionDropdown.click();
        WebElement userAddressInput = driver.findElement(By.id("currentAddress"));
        actions.moveToElement(userAddressInput).click().build().perform();
        actions.sendKeys("Shire");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.submit();

        WebElement submitFormTable = driver.findElement(By.id("example-modal-sizes-title-lg"));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfAllElements(submitFormTable));
        assertThat(submitFormTable.getText()).contains("Thanks for submitting the form");

        WebElement studentNameTable = driver.findElement(By.xpath("//td[text()='Student Name']//following-sibling::*"));
        assertThat(studentNameTable.getText()).contains("Gandalf The Grey");
        WebElement studentEmailTable = driver.findElement(By.xpath("//td[text()='Student Email']//following-sibling::*"));
        assertThat(studentEmailTable.getText()).contains("palantir@example.com");
        WebElement studentGenderTable = driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::*"));
        assertThat(studentGenderTable.getText()).contains("Male");
        WebElement studentMobileTable = driver.findElement(By.xpath("//td[text()='Mobile']//following-sibling::*"));
        assertThat(studentMobileTable.getText()).contains("0000000000");
        WebElement studentSubjectsTable = driver.findElement(By.xpath("//td[text()='Subjects']//following-sibling::*"));
        assertThat(studentSubjectsTable.getText()).contains("Civics");
        WebElement stateCityTable = driver.findElement(By.xpath("//td[text()='State and City']//following-sibling::*"));
        assertThat(stateCityTable.getText()).contains("NCR");
        WebElement studentHobbiesTable = driver.findElement(By.xpath("//td[text()='Hobbies']//following-sibling::*"));
        assertThat(studentHobbiesTable.getText()).contains("Music");
        WebElement studentAddressTable = driver.findElement(By.xpath("//td[text()='Address']//following-sibling::*"));
        assertThat(studentAddressTable.getText()).contains("Shire");
    }

    @Test
    @Story("Select all from the folder")
    @Description("Select all from the folder.")
    void selectAll() {
        driver.get(testServiceUrl.url1 + "checkbox");
        Actions actions = new Actions(driver);
        WebElement selectAllCheckbox = driver.findElement(By.className("rct-checkbox"));
        actions.click(selectAllCheckbox).build().perform();

        WebElement selectionResult = driver.findElement(By.id("result"));
        assertThat(selectionResult.getText()).contains("""
                You have selected :
                home
                desktop
                notes
                commands
                documents
                workspace
                react
                angular
                veu
                office
                public
                private
                classified
                general
                downloads
                wordFile
                excelFile""");
    }

    @Test
    @Story("Select one option from the folder")
    @Description("Select one option from the folder.")
    void selectOneOption() {
        driver.get(testServiceUrl.url1 + "checkbox");
        Actions actions = new Actions(driver);
        WebElement homeToggle = driver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/span/button"));
        actions.click(homeToggle).build().perform();
        WebElement desktopToggle = driver.findElement(By.xpath("//label[@for='tree-node-documents']//preceding-sibling::button"));
        actions.click(desktopToggle).build().perform();
        WebElement workspaceToggle = driver.findElement(By.xpath("//label[@for='tree-node-workspace']//preceding-sibling::button"));
        actions.click(workspaceToggle).build().perform();
        WebElement selectAllCheckbox = driver.findElement(By.xpath("//*[@id=\"tree-node\"]/ol/li/ol/li[2]/ol/li[1]/ol/li[2]/span/label/span[3]//preceding-sibling::span[@class='rct-checkbox']"));
        actions.click(selectAllCheckbox).build().perform();

        WebElement selectionResult = driver.findElement(By.id("result"));
        assertThat(selectionResult.getText()).contains("You have selected :\n" +
                "angular");
    }

    @Test
    @Story("Change data field value in the table")
    @Description("Change data field value for a row in the table.")
    void changeValueInTheTable() {
        driver.get(testServiceUrl.url1 + "webtables");
        Actions actions = new Actions(driver);
        WebElement editButton = driver.findElement(By.xpath("//div[text()='Cierra']//following-sibling::div//span[@title='Edit']"));
        actions.click(editButton).build().perform();
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.clear();
        lastNameInput.sendKeys("Aldebaran");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.submit();

        WebElement lastNameTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[1]/div/div[2]"));
        assertThat(lastNameTable.getText()).contains("Aldebaran");
    }

    @Test
    @Story("Add row")
    @Description("Add row in the table.")
    void addRowInTheTable() {
        driver.get(testServiceUrl.url1 + "webtables");
        Actions actions = new Actions(driver);
        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        actions.click(addButton).build().perform();
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys("Conan");
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys("The Barbarian");
        WebElement userEmailInput = driver.findElement(By.id("userEmail"));
        userEmailInput.sendKeys("rrraarrrrrr@example.com");
        WebElement userAgeInput = driver.findElement(By.id("age"));
        userAgeInput.sendKeys("28");
        WebElement userSalaryInput = driver.findElement(By.id("salary"));
        userSalaryInput.sendKeys("9001");
        WebElement userDepartmentInput = driver.findElement(By.id("department"));
        userDepartmentInput.sendKeys("Problem Solving");
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.submit();

        WebElement firstNameTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[1]"));
        assertThat(firstNameTable.getText()).contains("Conan");
        WebElement lastNameTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[2]"));
        assertThat(lastNameTable.getText()).contains("The Barbarian");
        WebElement ageTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[3]"));
        assertThat(ageTable.getText()).contains("28");
        WebElement emailTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[4]"));
        assertThat(emailTable.getText()).contains("rrraarrrrrr@example.com");
        WebElement salaryTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[5]"));
        assertThat(salaryTable.getText()).contains("9001");
        WebElement departmentTable = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/div[4]/div/div[6]"));
        assertThat(departmentTable.getText()).contains("Problem Solving");
    }

    @Test
    @Story("Delete a row")
    @Description("Delete a row from the table.")
    void deleteRowFromTheTable() {
        driver.get(testServiceUrl.url1 + "webtables");
        Actions actions = new Actions(driver);
        WebElement editButton = driver.findElement(By.xpath("//div[text()='alden@example.com']//following-sibling::div//span[@title='Delete']"));
        actions.click(editButton).build().perform();

        WebElement changedTable = driver.findElement(By.className("rt-table"));
        assertThat(changedTable.getText()).doesNotContain("alden@example.com");
    }

    @Test
    @Story("Modal dialogs")
    @Description("Check text in modal dialogs.")
    void modalDialogs() {
        driver.get(testServiceUrl.url1 + "modal-dialogs");
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement smallModalButton = driver.findElement(By.id("showSmallModal"));
        actions.click(smallModalButton).build().perform();

        WebElement smallModalPopup = driver.findElement(By.className("modal-content"));
        wait.until(ExpectedConditions.visibilityOf(smallModalPopup));
        WebElement smallModalPopupTitle = driver.findElement(By.id("example-modal-sizes-title-sm"));
        assertThat(smallModalPopupTitle.getText()).contains("Small Modal");
        WebElement smallModalPopupBody = driver.findElement(By.className("modal-body"));
        assertThat(smallModalPopupBody.getText()).contains("This is a small modal. It has very less content");

        WebElement closeSmallModalButton = driver.findElement(By.id("closeSmallModal"));
        actions.click(closeSmallModalButton).build().perform();

        WebElement largeModalButton = driver.findElement(By.id("showLargeModal"));
        actions.click(largeModalButton).build().perform();

        WebElement largeModalPopup = driver.findElement(By.className("modal-content"));
        wait.until(ExpectedConditions.visibilityOf(largeModalPopup));
        WebElement largeModalPopupTitle = driver.findElement(By.id("example-modal-sizes-title-lg"));
        assertThat(largeModalPopupTitle.getText()).contains("Large Modal");
        WebElement largeModalPopupBody = driver.findElement(By.className("modal-body"));
        assertThat(largeModalPopupBody.getText()).contains("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
    }

    @Test
    @Story("Accordion test")
    @Description("Open the accordion and check the text.")
    void accordionTest() {
        driver.get(testServiceUrl.url1 + "accordian");
        Actions actions = new Actions(driver);
        WebElement openAccordionButton = driver.findElement(By.xpath("//*[@id=\"section2Heading\"]"));
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.elementToBeClickable(openAccordionButton));
        actions.click(openAccordionButton).build().perform();

        WebElement accordionBody = driver.findElement(By.xpath("//*[@id=\"section2Content\"]"));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait2.until(ExpectedConditions.visibilityOfAllElements(accordionBody));
        assertThat(accordionBody.getText()).contains("Hampden-Sydney College");
    }

    @Test
    @Story("Buttons visibility")
    @Description("Check that buttons are visible.")
    void buttonsVisibilityTest() {
        driver.get(testServiceUrl.url2 + "visibility");
        Actions actions = new Actions(driver);

        WebElement removedButton = driver.findElement(By.id("removedButton"));
        WebElement zeroWidthButton = driver.findElement(By.id("zeroWidthButton"));
        WebElement opacityZeroButton = driver.findElement(By.id("transparentButton"));
        WebElement displayNoneButton = driver.findElement(By.id("notdisplayedButton"));
        WebElement visibilityHiddenButton = driver.findElement(By.id("invisibleButton"));
        WebElement offscreenButton = driver.findElement(By.id("offscreenButton"));

        WebElement hideButton = driver.findElement(By.id("hideButton"));
        actions.click(hideButton).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(removedButton));

        int zeroWidthButtonWidth = zeroWidthButton.getSize().width;
        assertEquals(zeroWidthButtonWidth, 0);

        String opacityZeroButtonOpacity = opacityZeroButton.getCssValue("opacity");
        assertEquals(Integer.valueOf(opacityZeroButtonOpacity), 0);

        String displayNoneButtonDisplay = displayNoneButton.getCssValue("display");
        assertEquals(displayNoneButtonDisplay, "none");

        String visibilityHiddenButtonVisibility = visibilityHiddenButton.getCssValue("visibility");
        assertEquals(visibilityHiddenButtonVisibility, "hidden");

        WebElement removedButtonHidingLayer = driver.findElement(By.id("hidingLayer"));
        wait.until(ExpectedConditions.visibilityOf(removedButtonHidingLayer));

        wait.until(ExpectedConditions.attributeContains(offscreenButton, "class", "offscreen"));
    }
}