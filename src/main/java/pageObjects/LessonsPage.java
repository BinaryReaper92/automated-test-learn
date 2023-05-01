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
}
