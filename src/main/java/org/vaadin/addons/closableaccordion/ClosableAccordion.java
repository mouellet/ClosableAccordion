package org.vaadin.addons.closableaccordion;

import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;

@SuppressWarnings("serial")
public class ClosableAccordion extends TabSheet {

    /**
     * Creates an empty accordion.
     */
    public ClosableAccordion() {
        super();
    }

    /**
     * Constructs a new accordion containing the given components.
     * 
     * @param components
     *            The components to add to the accordion. Each component will be added to a separate tab.
     */
    public ClosableAccordion(Component... components) {
        this();
        addComponents(components);
    }

}
