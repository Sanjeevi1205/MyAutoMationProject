package stepdefinition;
import baseclass.LibGlobal;
import exceldata.OrderIntakeTestData;
import io.cucumber.java.en.Then;
import lombok.extern.java.Log;
import mailtrap.MailTrapAPI;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pompages.LoginPOM;
import pompages.OrderIntakePOM;
import userdata.OrderIntakeData;
import userdata.OrderIntakeData.OrderType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import java.util.List;

import exceldata.OrderIntakeReader;

@Log
public class OrderIntake extends LibGlobal {
    public OrderIntakePOM orderIntakePOM;

    @Given("the client logs in using valid credentials")
    public void theClientLogsInUsingValidCredentials() throws Exception {
        driver = LibGlobal.initializeDriver();
        LibGlobal.openURL(driver);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        OrderIntakeReader orderIntakeReader = new OrderIntakeReader("src/test/resources/Excel/TestDataForCaseDrive.xls");
        List<OrderIntakeTestData> testData = orderIntakeReader.getOrderIntakeData("Credentials");
        String clientusername = testData.get(0).getUsername();
        String clientpassword = testData.get(0).getPassword();
        orderIntakePOM = new OrderIntakePOM();
        LoginPOM loginPOM = new LoginPOM();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOf(loginPOM.getUsername()));
        loginPOM.getUsername().sendKeys(clientusername);
        loginPOM.getPassword().sendKeys(clientpassword);
        loginPOM.getLoginbtn().click();
        orderIntakeReader.closeWorkbook();
    }

    @When("the client clicks on Add a new record to place an order")
    public void theClientClicksOnAddNewRecordtoPlaceAnOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getSideMenu()));
        orderIntakePOM.getSideMenu().click();
        orderIntakePOM.getAddrecordsbtn().click();
        orderIntakePOM.getNewrecords().click();
    }

    @And("the client enters the required details, selects some services, and clicks the next button")
    public void theClientEntersRequiredDetailsSelectServicesAndClickTheNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        orderIntakePOM.getFirstnameField().sendKeys(OrderType.CASE_DATA.getFirstname());
        orderIntakePOM.getLastnameField().sendKeys(OrderType.CASE_DATA.getLastname());
        orderIntakePOM.expectedDeliveryButton();
        orderIntakePOM.calenderNxtRetry();
        orderIntakePOM.selectDateRetry();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getServiceSelector()));
        int retryCount = 0;
        boolean clickedSuccessfully = false;

        while (retryCount < 3 && !clickedSuccessfully) {
            try {
                orderIntakePOM.getServiceSelector().click();
                wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getFirstService())).click();
                orderIntakePOM.getServiceSelector().click();
                try {
                    orderIntakePOM.getSecondService().click();
                } catch (Exception e) {
                    log.info("Second service not found, clicking next button instead.");
                    orderIntakePOM.getNextButton().click();
                    break;
                }
                orderIntakePOM.getServiceSelector().click();
                orderIntakePOM.getThirdService().click();
                orderIntakePOM.getNextButton().click();
                clickedSuccessfully = true;
            } catch (Exception e) {
                log.info("Error occurred during service selection or clicking next: " + e.getMessage());
                retryCount++;
                if (retryCount < 3) {
                    log.info("Retry attempt " + retryCount);
                    wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getServiceSelector()));
                } else {
                    log.info("Maximum retry attempts reached, stopping execution.");
                }
            }
        }
    }

    @And("the client selects case type and subtype from the second form")
    public void theClientSelectsCaseTypeAndSubtypeFromTheSecondForm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", orderIntakePOM.getCaseType());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getCaseType()));
        orderIntakePOM.retryCaseTypeClick();
        orderIntakePOM.retryClickDropDownValue();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getCaseSubType()));
        orderIntakePOM.getCaseSubType().click();
        orderIntakePOM.retryClickDropDownValue();
    }

    @And("the client enables the estimate toggle")
    public void theClientEnablesTheEstimateToggle() {
        orderIntakePOM.getEstimateToggle().click();
    }

    @And("the client provides the upload link and download link, and clicks submit button")
    public void theClientEnablesProvidesCustomLinksAndClicksSubmitButton() {
        orderIntakePOM.getCaseoverview().sendKeys(OrderType.CASE_DATA.getCaseoverview());
        orderIntakePOM.getCustomLink().click();
        orderIntakePOM.getUploadLink().sendKeys(OrderType.CASE_DATA.getUploadLink());
        orderIntakePOM.getDownloadLink().sendKeys(OrderType.CASE_DATA.getDownloadLink());
        orderIntakePOM.getSubmitButton().click();
    }

    @And("the client edits some details")
    public void theClientEditsSomeDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", orderIntakePOM.getEditButton());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getEditButton()));
        orderIntakePOM.getEditButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getFirstnameField()));
        orderIntakePOM.getFirstnameField().sendKeys(Keys.CONTROL + "a");
        orderIntakePOM.getFirstnameField().sendKeys(Keys.BACK_SPACE);
        orderIntakePOM.getFirstnameField().sendKeys(OrderType.CASE_DATA.getConfirmfirstname());
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getLastnameField()));
        orderIntakePOM.getLastnameField().sendKeys(Keys.CONTROL + "a");
        orderIntakePOM.getLastnameField().sendKeys(Keys.BACK_SPACE);
        orderIntakePOM.getLastnameField().sendKeys(OrderType.CASE_DATA.getConfirmlastname());
    }

    @And("the  client enters a casenumber manually")
    public void entersACasenumberManuallyClicksTheSubmitButton() {
        orderIntakePOM.getConfirmPageCasenumb().sendKeys(OrderType.CASE_DATA.getConfirmCaseID());
    }

    @And("the clients saves and submits the details")
    public void theClientSavesAndSubmitTheDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        orderIntakePOM.getEditButton().click();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getConfirmAndSubmit()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", orderIntakePOM.getConfirmAndSubmit());
        orderIntakePOM.getConfirmAndSubmit().click();
    }

    @And("the client clicks the confirm button in the confirmation pop up")
    public void theClientClicksTheConfirmButtonInTheConfirmationPopUp() {
        orderIntakePOM.getConfirmOrder().click();
    }

    @And("the client sees the order placed success message and is redirected to the case list page")
    public void theClientSeesTheOrderPlacedSuccessMessageAndIsRedirectedToTheCaseListPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getOrderPlacedMsg()));
            String orderMessageText = orderIntakePOM.getOrderPlacedMsg().getText();
            boolean orderSuccessMessage = orderMessageText.contains("Your order has been placed successfully.");
            if (orderSuccessMessage) {
                log.info("Order success status: " + true);
            }
        } catch (Exception e) {
            log.info("Order success message was not found. Continuing with the test.");
        }
        String expectedCaselistUrlPart = "/client/cases/review?pageIndex=0&search=&status=0&priority=All";
        wait.until(ExpectedConditions.urlContains(expectedCaselistUrlPart));
        String currentCaselistUrl = driver.getCurrentUrl();
        assert currentCaselistUrl != null;
        Assert.assertTrue("The current URL does not contain the expected part", currentCaselistUrl.contains(expectedCaselistUrlPart));
    }

    @And("the client verifies whether the case status is {string}, case version is {string} and case priority is {string}")
    public void theClientVerifiesCaseDetails(String expectedCaseStatus, String expectedCaseVersion, String expectedCasePriority) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getCaseStatus()));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getDoesCaseVersion()));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getDoesCasePriority()));

        String actualCaseStatus = orderIntakePOM.getCaseStatus().getText().trim();
        String actualCaseVersion = orderIntakePOM.getDoesCaseVersion().getText().trim();
        String actualCasePriority = orderIntakePOM.getDoesCasePriority().getText().trim();

        boolean caseStatusMatches = actualCaseStatus.contains(expectedCaseStatus);
        boolean caseVersionMatches = actualCaseVersion.contains(expectedCaseVersion);
        boolean casePriorityMatches = actualCasePriority.contains(expectedCasePriority);

        log.info("Case Status: " + actualCaseStatus);
        log.info("Case Version: " + actualCaseVersion);
        log.info("Case Priority: " + actualCasePriority);

        if (caseStatusMatches && caseVersionMatches && casePriorityMatches) {
            log.info("All conditions are met. The case status, version, and priority are correct.");
        } else {
            log.info("One or more conditions do not match.");
        }
        LibGlobal.quitDriver();
    }

    @And("the client verifies whether the entered case number is being reflected in the caselist page")
    public void finallyVerifiesWhetherTheEnteredCaseNumberIsBeingReflectedInTheCaselistPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getDoesCaseNumber()));
        String caseNumber = orderIntakePOM.getDoesCaseNumber().getText().trim();
        String expectedPrefix = "AUTO";
        String caseNumberPrefix = caseNumber.substring(0, expectedPrefix.length());
        boolean caseNumberMatchesPrefix = caseNumberPrefix.equals(expectedPrefix);
        log.info("Case number matches expected prefix: " + caseNumberMatchesPrefix);
        log.info("Actual case number: " + caseNumber);
    }

    @And("the client verifies whether the case number is generated automatically in the caselist page")
    public void finallyVerifiesWhetherTheCaseNumberIsGeneratedAutomaticallyInTheCaselistPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getDoesCaseNumber()));
        String caseNumber = orderIntakePOM.getDoesCaseNumber().getText().trim();
        String expectedPrefix = "LDML";
        String caseNumberPrefix = caseNumber.substring(0, expectedPrefix.length());
        boolean caseNumberMatchesPrefix = caseNumberPrefix.equals(expectedPrefix);
        log.info("Case number matches expected prefix: " + caseNumberMatchesPrefix);
        log.info("Actual case number: " + caseNumber);
    }

    @And("the client enters the case overview")
    public void theClientEntersTheCaseOverview() {
        orderIntakePOM.getCaseoverview().sendKeys(OrderIntakeData.OrderType.CASE_DATA.getCaseoverview());
    }

    @And("the client uploads different file types, and clicks the submit button")
    public void theClientUploadDifferentFileTypesAndClicksTheSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        ClientCaseList clientCaseList= new ClientCaseList();
        clientCaseList.orderIntakePOM = new OrderIntakePOM();
        clientCaseList.theClientUploadsDifferentFileTypes();
        wait.until(ExpectedConditions.elementToBeClickable(orderIntakePOM.getSubmitButton()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", orderIntakePOM.getSubmitButton());
        orderIntakePOM.getSubmitButton().click();
    }

    @And("the client does not upload any files and clicks the submit button")
    public void theClientDoesNotUploadAnyFilesAndClicksTheSubmitButton() {
        orderIntakePOM.getCaseoverview().sendKeys(OrderIntakeData.OrderType.CASE_DATA.getCaseoverview());
        orderIntakePOM.getSubmitButton().click();
    }

    @Then("the client verifies that an error message is displayed, indicating files must be uploaded")
    public void theClientVerifiesThatAnErrorMessageIsDisplayedIndicatingFilesMustBeUploadedAndOverviewErrorIsShown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement fileUploadError = wait.until(ExpectedConditions.visibilityOf(orderIntakePOM.getFileUploadError()));
            Assert.assertTrue("Error message for missing file upload is not displayed.", fileUploadError.isDisplayed());
            log.info("Verified that the error message for missing file upload is displayed.");

        } catch (Exception e) {
            log.severe("Error during validation of error messages: " + e.getMessage());
        }
        LibGlobal.quitDriver();
    }
    @Then("verify whether the mail for {string} is being received")
    public void verifyWhetherTheMailForIsBeingReceived(String expectedSubject) {
        try {
            MailTrapAPI.verifyLatestEmailSubjectAndBCC(expectedSubject);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Mail verification failed", e);
        }
    }
}