package org.vaadin.addons.closableaccordion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ClosableAccordionElement;
import com.vaadin.testbench.elements.NotificationElement;

public class ClosableAccordionITCase extends TestBenchTestCase {

    @Before
    public void setUp() {
        setDriver(TestBench.createDriver(new FirefoxDriver()));
        getDriver().get("http://localhost:8080/closableaccordion-demo");
        getDriver().manage().window().maximize();
    }

    @Test
    public void isClosable_closeTab_elementFound_tabClosed() {
        try {
            ClosableAccordionElement element = $(ClosableAccordionElement.class)
                    .first();

            List<String> captions = element.getTabCaptions();
            assertFalse(captions.isEmpty());
            assertTrue(captions.size() == 4);

            element.openTab(captions.get(1));

            element.closeTab(captions.get(0));

            if (isElementPresent(NotificationElement.class)) {
                $(NotificationElement.class).first().closeNotification();
            }

            assertTrue(element.getTabCaptions().size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @After
    public void tearDown() {
        getDriver().quit();
    }

}
