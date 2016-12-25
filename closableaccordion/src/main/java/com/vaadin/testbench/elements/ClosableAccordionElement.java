package com.vaadin.testbench.elements;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.vaadin.testbench.By;

@ServerClass("org.vaadin.addons.closableaccordion.ClosableAccordion")
public class ClosableAccordionElement extends AccordionElement {

    private static org.openqa.selenium.By byCaption = By
            .className("v-captiontext");
    private static org.openqa.selenium.By byClosable = By
            .className("v-accordion-caption-close");

    @Override
    public void closeTab(String tabCaption) {
        for (WebElement tabCell : findElements(byTabCell)) {
            WebElement tab = tabCell.findElement(byCaption);
            if (tabCaption.equals(tab.getText())) {
                try {
                    tabCell.findElement(byClosable).click();
                    // Going further causes a StaleElementReferenceException.
                    return;
                } catch (NoSuchElementException e) {
                    // Do nothing.
                }
            }
        }
    }

}
