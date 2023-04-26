package utilities;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

public class CucumberEventListener implements ConcurrentEventListener {
    public static String EventMessages = null;

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        eventPublisher.registerHandlerFor(TestStepStarted.class, this::onTestStepStarted);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        TestCase testCase = event.getTestCase();
        String id = "" + testCase.getUri();

        EventMessages = id + "\n\n" ;
        System.out.println(EventMessages);
    }

    private void onTestStepStarted(TestStepStarted event) {
        TestStep testStep = event.getTestStep();
        if (testStep instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) testStep;
            String stepName = pickleStep.getStep().getText();
            String msg =  stepName + " started";
            Log4j.info(msg);
        }
    }

    private void onTestStepFinished(TestStepFinished event) {
        TestStep testStep = event.getTestStep();
        if (testStep instanceof PickleStepTestStep) {
            PickleStepTestStep pickleStep = (PickleStepTestStep) testStep;
            String stepName = pickleStep.getStep().getText();
            String msg = "["+event.getResult().getStatus()+"]: " + stepName + " finished!\n";
            if(!event.getResult().getStatus().isOk()){
                msg += event.getResult().getError().getMessage()+"\n\n";
                Log4j.error(msg);
            }else {
                Log4j.info(msg);
            }
            EventMessages += msg;
        }
    }
}
