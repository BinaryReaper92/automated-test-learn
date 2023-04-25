package utilities;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class OwnTrace {
    public static void trace(String sMessage)
    {
        Log4j.info(sMessage);
        System.out.println( "[MYTRACE]" + sMessage );
        executeJavaScript("console.log(`"+sMessage+"`)");
    }

    public static void trace(SelenideElement element, String sMessage )
    {
        String sPrintMessage = sMessage;
        if(element != null) {
            sPrintMessage =  sPrintMessage + " " + element.getTagName();
        }
        OwnTrace.trace(sPrintMessage);
    }

    public static void trace(SelenideElement element, String sMessage, Exception e )
    {
        OwnTrace.trace(element, sMessage + " " + e.getMessage());
    }
}
