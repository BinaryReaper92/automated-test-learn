package pageObjects;

import com.codeborne.selenide.SelenideElement;
import utilities.ElementFinder;

public class SpecificModal {
    public SelenideElement getNewSpecificTitle(){
        return ElementFinder.myFindElement("//h5[contains(text(),'Add New Specific')]");
    }
    public SelenideElement getKeyField(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getTypeDropdown(){
        return ElementFinder.myFindElement("//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]");
    }
    public SelenideElement getTypeShortText(){
        return ElementFinder.myFindElement("//div[contains(text(),'SHORTTEXT')]");
    }
    public SelenideElement getLevelDropdown(){
        return ElementFinder.myFindElement("#react-select-53-input");
    }
    public SelenideElement getAddItInGeneralBlockCheckbox(){
        return ElementFinder.myFindElement("//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[7]/div[1]/span[1]/label[1]/span[1]/span[1]/span[1]/i[1]");
    }
    public SelenideElement getShowUnderProductDropdown(){
        return ElementFinder.myFindElement("//body/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[6]/div[1]/span[1]/label[1]/span[1]/span[1]/span[1]/i[1]");
    }
    public SelenideElement getQuestionField(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[8]/div[1]/div[1]/input[1]");
    }
    public SelenideElement getFirstQuestion(){
        return ElementFinder.myFindElement("/html[1]/body[1]/div[3]/div[1]/div[1]/div[2]/div[1]/div[1]/div[8]/div[1]/div[3]/div[1]");
    }
    public SelenideElement getOkButton(){
        return ElementFinder.myFindElement("//button[contains(text(),'OK')]");
    }
}
