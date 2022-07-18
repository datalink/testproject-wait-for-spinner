package au.com.datalink.testproject;

import io.testproject.java.annotations.v2.Action;
import io.testproject.java.annotations.v2.ActionParameter;
import io.testproject.java.sdk.v2.addons.WebAction;
import io.testproject.java.sdk.v2.addons.helpers.WebAddonHelper;
import io.testproject.java.sdk.v2.drivers.WebDriver;
import io.testproject.java.sdk.v2.enums.ExecutionResult;
import io.testproject.java.sdk.v2.exceptions.FailureException;
import io.testproject.java.sdk.v2.reporters.ActionReporter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

@Action(name = "Wait for Spinner")
public class WaitForSpinnerAction implements WebAction {

    @ActionParameter
    public String xpath = "//div[contains(@class,\"spinner\") and not(contains(@style,\"none\"))]";

    @ActionParameter
    public int sleep = 1000; // ms

    @ActionParameter
    public int maxTries = 120; // 120 x 1 second tries by default

    @ActionParameter
    public int triesNeededToPass = 2;

    public ExecutionResult execute(WebAddonHelper helper) throws FailureException {
        // Get Driver
        WebDriver driver = helper.getDriver();
        ActionReporter report = helper.getReporter();

        // Get the xpath element
        By by = By.xpath(xpath);

        int triesNotFound = 0; // number of times it has not found the element
        int triesOverall = 0; // number of tries overall

        do {
            triesOverall++;
            try {
                // Try to locate the element
                WebElement webElement = driver.findElement(by);
                // reset the not found counter if it found the element
                triesNotFound = 0;
            } catch (NoSuchElementException e) {
                // not found, increment.
                triesNotFound++;
            }

            // Now sleep
            try {
                Thread.sleep(this.sleep);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            // exit the loop after max tries, or after the element was confirmed to not exist
        } while (triesOverall < this.maxTries && triesNotFound < this.triesNeededToPass);

        if (triesNotFound == this.triesNeededToPass) {
            report.result(String.format("Element not present after %d tries", triesOverall));
            return ExecutionResult.PASSED;
        } else {
            report.result(String.format("Element still present after %d tries", triesOverall));
            return ExecutionResult.FAILED;
        }
    }
}