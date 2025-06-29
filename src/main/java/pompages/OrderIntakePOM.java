package pompages;
import baseclass.LibGlobal;
import lombok.Getter;
import lombok.extern.java.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

@Log
@Getter
public class OrderIntakePOM extends LibGlobal {
    public OrderIntakePOM() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1asfma']")
    private WebElement sideMenu;

    @FindBy(xpath = "//span[contains(text(),'Add Records')]")
    private WebElement addrecordsbtn;

    @FindBy(xpath = "//li[normalize-space()='New Records']")
    private WebElement newrecords;

    @FindBy(xpath = "//li[normalize-space()='Additional Records']")
    private WebElement additionalRecords;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstnameField;

    @FindBy(xpath = "//input[@name='middle_name']")
    private WebElement middlenameField;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastnameField;

    @FindBy(xpath = "//input[@placeholder='Case Number']")
    private WebElement casenumbField;

    @FindBy(xpath = "//span[contains(@class, 'MuiButtonBase-root') and contains(@class, 'MuiSwitch-switchBase') and contains(@class, 'MuiSwitch-colorPrimary') and contains(@class, 'PrivateSwitchBase-root')]")
    private WebElement expediteDisabled;

    @FindBy(xpath = "//span[@class='MuiButtonBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked PrivateSwitchBase-root MuiSwitch-switchBase MuiSwitch-colorPrimary Mui-checked Mui-checked css-fizi60']")
    private WebElement expediteEnabled;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeEnd MuiIconButton-sizeMedium css-142cb9c']")
    private WebElement expectedDelivery;

    public void expectedDeliveryButton() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, expectedDelivery, retries, timeout);
    }

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-edgeStart MuiIconButton-sizeMedium MuiPickersArrowSwitcher-button css-4be7ig']")
    private WebElement calendrNextBtn;

    public void calenderNxtRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, calendrNextBtn, retries, timeout);
    }

    @FindBy(xpath = "//button[normalize-space()='18']")
    private WebElement selectDate;

    public void selectDateRetry() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, selectDate, retries, timeout);
    }

    @FindBy(xpath = "//input[@id='free-solo-2-demo']")
    private WebElement serviceSelector;

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-0'][@tabindex='-1']")
    private WebElement firstService;

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-1'][@tabindex='-1']")
    private WebElement secondService;

    @FindBy(xpath = "//div[@id='free-solo-2-demo-option-2'][@tabindex='-1']")
    private WebElement thirdService;

    @FindBy(xpath = "//button[contains(text(),'Next')]")
    private WebElement nextButton;

    @FindBy(xpath = "//span[normalize-space()='Please enter your first name']")
    private WebElement firstnameErrorMess;

    @FindBy(xpath = "//span[normalize-space()='Please enter your last name']")
    private WebElement lastnameErrorMess;

    @FindBy(xpath = "//div[@class='MuiStack-root css-1tbd6bz']")
    private WebElement serviceSelectErrorMess;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container css-16xxdxa']//div[1]//div[1]//div[1]//div[1]//div[1]//button[1]//*[name()='svg']")
    private WebElement caseType;

    public void retryCaseTypeClick() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, caseType, retries, timeout);
    }

    // CaseType and CaseSubType Value

    @FindBy(xpath = "(//div[@class='MuiAutocomplete-option MuiBox-root css-0'][@tabindex='-1'])[1]")
    private WebElement dropDownValue;

    public void retryClickDropDownValue() {
        int retries = 3;
        Duration timeout = Duration.ofSeconds(20);
        LibGlobal.clickWithRetry(driver, dropDownValue, retries, timeout);
    }

    @FindBy(xpath = "//div[@class='MuiAutocomplete-root MuiAutocomplete-fullWidth MuiAutocomplete-hasPopupIcon css-1bjqpmb']//button[@title='Open']//*[name()='svg']")
    private WebElement caseSubType;

    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

    @FindBy(xpath = "//div[contains(text(),'100')] ")
    private WebElement uploadsuccess100;

    @FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-12 MuiGrid-grid-lg-12 css-18nbvmr']")
    private List<WebElement> fileUploadConfirms;

    @FindBy(xpath = "//button[contains(text(),'Submit')]")
    private WebElement submitButton;

    @FindBy(xpath = "//textarea[@name='case_overview']")
    private WebElement caseoverview;

    @FindBy(xpath = "//button[@type='button']//div[@class='d-flex align-items-center MuiBox-root css-0']//*[name()='svg']")
    private WebElement editButton;

    @FindBy(xpath = "//input[@name='first_name'][@id=':r4u:']")
    private WebElement confirmFirstName;

    @FindBy(xpath = "//input[@name='case_no']")
    private WebElement confirmPageCasenumb;

    @FindBy(xpath = "//*[name()='path' and contains(@d,'M433.941 1')]")
    private WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Confirm & Submit']")
    private WebElement confirmAndSubmit;

    @FindBy(xpath = "//button[normalize-space()='Confirm']")
    private WebElement confirmOrder;

    @FindBy(xpath = "//input[@class='PrivateSwitchBase-input MuiSwitch-input css-1m9pwf3']")
    private WebElement estimateToggle;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 text-center pointer-hand css-1d7d46l']")
    private WebElement customLink;

    @FindBy(xpath = "//input[@name='download_link']")
    private WebElement downloadLink;

    @FindBy(xpath = "//input[@name='upload_link']")
    private WebElement uploadLink;

    @FindBy(xpath = "//ol[@dir='ltr']//li")
    private WebElement orderPlacedMsg;

    @FindBy(xpath = "//tr[@data-index='0']//td[@data-index='6']")
    private WebElement caseStatus;

    @FindBy (xpath = "//input[@placeholder='Search Case Name']")
    private WebElement searchCase;

    @FindBy (xpath = "//div[@tabindex='-1'] [@data-option-index='0']")
    private WebElement selectCase;

    @FindBy (xpath="//div[@tabindex='-1'][@data-tag-index='0']")
    private WebElement doesSelectedService1;

    @FindBy(xpath = "//div[@tabindex='-1'][@data-tag-index='1']")
    private WebElement doesSelectedService2;

    @FindBy(xpath="//div[@tabindex='-1'][@data-tag-index='2']")
    private WebElement doesSelectedService3;

    @FindBy (xpath = "//div[contains(text(),'Additional Case added successfully.')]")
    private WebElement addSuccessMessage;

    @FindBy (xpath="//tr[@data-index='0'] //td[@data-index='2']")
    private WebElement doesCaseVersion;

    @FindBy (xpath="//tr[@data-index='0'] //td[@data-index='5']")
    private WebElement doesCasePriority;

    @FindBy (xpath="//tr[@data-index='0'] //td[@data-index='3']")
    private WebElement doesCaseNumber;

    @FindBy(xpath = "//span[contains(text(),'Cases')]")
    private WebElement caseList;

    @FindBy(xpath = "//li[normalize-space()='Review Cases']")
    private WebElement reviewCases;

    @FindBy (xpath = "//span[@class='MuiTypography-root MuiTypography-light css-h9oekb']")
    private WebElement caseOverError;

    @FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1 css-1fgiayz']")
    private WebElement fileUploadError;
}