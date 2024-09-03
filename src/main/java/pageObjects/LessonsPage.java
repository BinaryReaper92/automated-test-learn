package pageObjects;

import com.codeborne.selenide.SelenideElement;
import utilities.ElementFinder;

public class LessonsPage {

    public SelenideElement getLessonsMenuItem(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[1]/div[2]/div[14]/div[1]");
    }
    public SelenideElement getLessonsPageTitle(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/h1[1]");
    }
    public SelenideElement getAddLessonButton(){
        return ElementFinder.myFindElement("button.IconButton.dark > i.lemon.green.icon-add");
    }
    public SelenideElement getBaseDataTab(){
        return ElementFinder.myFindElement("//li[contains(@class, 'react-tabs__tab--selected')]//span[contains(text(), 'Base data')]");
    }
    public SelenideElement getLessonTitle(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getCompanyDropdown(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]");
    }
    public SelenideElement getCompanyTest(){
        return ElementFinder.myFindElement("//div[contains(text(),'! The new test')]");
    }
    public SelenideElement getSubsidiaryDropdown(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]");
    }
    public SelenideElement getSubsidiaryTest(){
        return ElementFinder.myFindElement("//div[contains(text(),'! new sub test')]");
    }
    public SelenideElement getDropOrBrowse(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[1]/div[1]");
    }
    public SelenideElement getTagsDropdown(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]");
    }
    public SelenideElement getTagsTest(){
        return ElementFinder.myFindElement("//div[contains(text(),'TEST')]");
    }

    public SelenideElement getSwitchButton(){
        return ElementFinder.myFindElement("//div[contains(@class, 'switch-button')]/div[contains(@class, 'berry')]");
    }
    public SelenideElement getSwitchButtonLabelText(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[6]/div[1]/div[2]/div[2]");
    }
    public SelenideElement getPriceField(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[7]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getAcceptanceRate(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[8]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getNeedToPassInDay(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[9]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getLessonType(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[10]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getDescription(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[1]/textarea[1]");
    }
    public SelenideElement getRequirements(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[12]/div[1]/div[1]/textarea[1]");
    }
    public SelenideElement getAddNewSpecificsButton(){
        return ElementFinder.myFindElement("//body/div[@id='root']/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[13]/div[1]/div[7]/div[1]/span[1]/span[1]/button[1]/i[1]");
    }
    public SelenideElement getFirstSpecificNameField(){
        return ElementFinder.myFindElement("/html/body/div/div/div[3]/div[2]/div/div/div[3]/form/div[1]/div[1]/div/div[13]/div[2]/div/div[3]/div/label");
    }
    public SelenideElement getFirstSpecificField(){
        return ElementFinder.myFindElement("/html/body/div/div/div[3]/div[2]/div/div/div[3]/form/div[1]/div[1]/div/div[13]/div[2]/div/div[3]/div/div/input");
    }
    public SelenideElement getFinishEditingButton(){
        return ElementFinder.myFindElement("//button[contains(text(),'Finish editing')]");
    }
    public SelenideElement getSuccessTitle(){
        return ElementFinder.myFindElement("//h5[contains(text(),'Success')]");
    }
    public SelenideElement getSuccessOkButton(){
        return ElementFinder.myFindElement("//button[contains(text(),'OK')]");
    }
}
