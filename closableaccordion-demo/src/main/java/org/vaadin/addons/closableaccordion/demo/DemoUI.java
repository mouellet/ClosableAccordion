package org.vaadin.addons.closableaccordion.demo;

import javax.servlet.annotation.WebServlet;

import org.vaadin.addons.closableaccordion.ClosableAccordion;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
@Title("ClosableAccordion Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private static final String loremIpsum = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.";
    private int numTab = 1;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class, widgetset = "org.vaadin.addons.closableaccordion.demo.DemoWidgetSet")
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {

        // Initialize our new UI component
        final ClosableAccordion component = new ClosableAccordion() {
            {
                setSizeUndefined();
                setWidth("100%");

                for (numTab = 1; numTab < 4; numTab++) {
                    Tab tab = addTab(new VerticalLayout() {

                        {
                            setMargin(true);
                            setSpacing(true);

                            addComponent(new Label(loremIpsum));
                        }
                    });
                    tab.setClosable(true);
                    tab.setCaption("Tab #" + numTab);
                    tab.setStyleName("trash");
                }

                // Close handler
                setCloseHandler(new CloseHandler() {

                    @Override
                    public void onTabClose(TabSheet tabsheet,
                            Component tabContent) {
                        Tab tab = tabsheet.getTab(tabContent);
                        Notification.show("Closing " + tab.getCaption());
                        // We need to close it explicitly in the handler
                        tabsheet.removeTab(tab);
                    }
                });

                // For adding new tabs
                final Label addNewTabContent = new Label();
                Tab addNewTab = addTab(addNewTabContent, "+ Add a new tab");
                addNewTab.setStyleName("addnew");

                addSelectedTabChangeListener(new SelectedTabChangeListener() {

                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        if (event.getTabSheet().getSelectedTab()
                                .equals(addNewTabContent)) {
                            removeTab(getTab(addNewTabContent));

                            Tab newTab = addTab(new VerticalLayout() {

                                {
                                    setMargin(true);
                                    setSpacing(true);

                                    addComponent(new Label(loremIpsum));
                                }
                            });
                            newTab.setClosable(true);
                            newTab.setCaption("Tab #" + numTab++);
                            newTab.setStyleName("trash");

                            event.getTabSheet().setSelectedTab(newTab);

                            addTab(addNewTabContent, "+ Add a new tab")
                                    .setStyleName("addnew");
                        }
                    }
                });
            }
        };

        setContent(new VerticalLayout() {
            {
                setSizeUndefined();
                setWidth("100%");

                setMargin(false);

                setId("demo-base-layout");

                addComponent(new Panel() {
                    {
                        setSizeUndefined();
                        setWidth("100%");

                        setCaption("Accordion w/ closable tabs");

                        // Show it in the middle of the screen
                        setContent(new HorizontalLayout() {
                            {
                                setStyleName("demoContentLayout");

                                setSizeUndefined();
                                setWidth("100%");

                                setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

                                addComponent(component);
                            }
                        });
                    }
                });
            }
        });
    }

}
